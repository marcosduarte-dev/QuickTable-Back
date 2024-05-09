package com.facens.quicktable.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.facens.quicktable.dto.ReservaDTO;
import com.facens.quicktable.enums.StatusMesa;
import com.facens.quicktable.enums.StatusPedido;
import com.facens.quicktable.model.Mesa;
import com.facens.quicktable.model.Pedido;
import com.facens.quicktable.model.Reserva;
import com.facens.quicktable.repository.MesaRepository;
import com.facens.quicktable.repository.PedidoRepository;
import com.facens.quicktable.repository.ReservaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {

	private final ReservaRepository repository;
	private final PedidoRepository pedidoRepository;
	private final MesaRepository mesaRepository;
	private final MesaService mesaService;

	public List<ReservaDTO> getAll() {
		List<Reserva> reservas = repository.findAll();
		return reservas
				.stream()
				.map(ReservaDTO::convert)
				.collect(Collectors.toList());
	}

	public ReservaDTO findById(long id) {
		Reserva reserva = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Reserva nao encontrado"));
		return ReservaDTO.convert(reserva);
	}

	public ReservaDTO save(ReservaDTO reservaDTO) {
		Mesa mesa = mesaRepository.findById(reservaDTO.getMesa().getId())
				.orElseThrow(() -> new RuntimeException("Reserva nao encontrado"));
		Reserva reserva = Reserva.convert(reservaDTO);
		reserva.setMesa(mesa);
		repository.save(reserva);
		return ReservaDTO.convert(reserva);
	}

	public ReservaDTO delete(long id) {
		Reserva reserva = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());

		repository.delete(reserva);
		return ReservaDTO.convert(reserva);
	}

	public ReservaDTO edit(Long id, ReservaDTO reservaDTO) {
		Reserva reserva = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		
		if (reservaDTO.getQtde_participantes() != null &&
				!reserva.getQtde_participantes().equals(reservaDTO.getQtde_participantes())) {
			reserva.setQtde_participantes(reservaDTO.getQtde_participantes());
		}
		
		if (reservaDTO.getTotal_gasto() != null &&
				!reserva.getTotal_gasto().equals(reservaDTO.getTotal_gasto())) {
			reserva.setTotal_gasto(reservaDTO.getTotal_gasto());
		}
		
		if (reservaDTO.getData_fechamento() != null &&
				!reserva.getData_fechamento().equals(reservaDTO.getData_fechamento())) {
			reserva.setData_fechamento(reservaDTO.getData_fechamento());
		}
		
		reserva = repository.save(reserva);
		return ReservaDTO.convert(reserva);
	}
	
	public Reserva adicionarParticipante(Long idReserva) {
		Reserva reserva = repository.findById(idReserva)
				.orElseThrow(() -> new RuntimeException("Reserva nao encontrado"));
		reserva.setQtde_participantes(reserva.getQtde_participantes() + 1);
		repository.save(reserva);
		return reserva;
	}
	
	public ReservaDTO entrarNaReserva(Long idMesa) {
		Mesa mesa = mesaRepository.findById(idMesa)
				.orElseThrow(() -> new RuntimeException("Mesa nao encontrado"));
		
		if (mesa.getStatus().equals(StatusMesa.USO)) {
			Reserva reserva = repository.findAllByIdMesa(idMesa).get(0);
			return ReservaDTO.convert(adicionarParticipante(reserva.getId()));
		} else if (mesa.getStatus().equals(StatusMesa.ABERTO)) {
			Reserva reserva = new Reserva();
			reserva.setData_abertura(LocalDateTime.now());
			mesa = mesaService.usarMesa(idMesa);
			reserva.setMesa(mesa);
			reserva.setQtde_participantes((long) 1);
			reserva.setTotal_gasto((float) 0);
			
			return save(ReservaDTO.convert(reserva));
		} else {
			throw new RuntimeException("Houve um erro interno");
		}
	}
	
	public ReservaDTO pegarReservaAtivaPelaMesa(Long idMesa) {
		List<Reserva> listaReserva = repository.findAllByIdMesa(idMesa);
		Reserva reserva = listaReserva.get(0);
		
		return ReservaDTO.convert(reserva);
	}
	
	public ReservaDTO fecharReserva(Long idReserva) {
		Reserva reserva = repository.findById(idReserva)
				.orElseThrow(() -> new RuntimeException());
		
		List<Pedido> listaPedidos = pedidoRepository.getAllByIdReserva(idReserva);
		
		for (Pedido pedido : listaPedidos) {
			if(pedido.getStatus() == StatusPedido.ANDAMENTO) {
				pedido.setStatus(StatusPedido.FINALIZADO);
				pedidoRepository.save(pedido);
			}
		}
		
		Mesa mesa = mesaRepository.findById(reserva.getMesa().getId())
				.orElseThrow(() -> new RuntimeException());
		
		mesa.setStatus(StatusMesa.ABERTO);
		mesaRepository.save(mesa);
		reserva.setMesa(mesa);
		reserva.setData_fechamento(LocalDateTime.now());
		reserva.setId(idReserva);
		reserva = repository.save(reserva);
		return ReservaDTO.convert(reserva);
	}
}
