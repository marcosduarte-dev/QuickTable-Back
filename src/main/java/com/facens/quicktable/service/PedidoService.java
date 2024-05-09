package com.facens.quicktable.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.facens.quicktable.dto.ItemQuantidadeDTO;
import com.facens.quicktable.dto.PedidoDTO;
import com.facens.quicktable.enums.StatusPedido;
import com.facens.quicktable.model.Item;
import com.facens.quicktable.model.Pedido;
import com.facens.quicktable.model.Reserva;
import com.facens.quicktable.repository.ItemRepository;
import com.facens.quicktable.repository.PedidoRepository;
import com.facens.quicktable.repository.ReservaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository repository;
	private final ReservaRepository reservaRepository;
	private final ItemRepository itemRepository;

	public List<PedidoDTO> getAll() {
		List<Pedido> pedidos = repository.findAll();
		return pedidos
				.stream()
				.map(PedidoDTO::convert)
				.collect(Collectors.toList());
	}
	
	public List<PedidoDTO> getByNomeCliente(String nomeCliente) {
		List<Pedido> pedidos = repository.getAllByNomeCliente(nomeCliente);
		return pedidos
				.stream()
				.map(PedidoDTO::convert)
				.collect(Collectors.toList());
	}
	
	public List<PedidoDTO> getByIdReserva(Long idReserva) {
		List<Pedido> pedidos = repository.getAllByIdReserva(idReserva);
		return pedidos
				.stream()
				.map(PedidoDTO::convert)
				.collect(Collectors.toList());
	}
	
	public List<PedidoDTO> getPedidosAndamento(String status) {
		
		StatusPedido statusPe = StatusPedido.ANDAMENTO;
		
		List<Pedido> pedidos = repository.getAllPedidosStatus(statusPe);
		return pedidos
				.stream()
				.map(PedidoDTO::convert)
				.collect(Collectors.toList());
	}

	public PedidoDTO findById(long id) {
		Pedido pedido = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));
		return PedidoDTO.convert(pedido);
	}

	public PedidoDTO save(PedidoDTO pedidoDTO) {
		
		Reserva reserva = reservaRepository.findById(pedidoDTO.getReserva().getId())
				.orElseThrow(() -> new RuntimeException("Reserva nao encontrado"));
		
		float totalPedido = (float) 0.0;
		
		for (ItemQuantidadeDTO itemquantidade : pedidoDTO.getItemQuantidade()) {
			Item item = itemRepository.findById(itemquantidade.getItem().getId())
					.orElseThrow(() -> new RuntimeException("Item nao encontrado"));
			totalPedido = itemquantidade.getQuantidade() * item.getPreco();
		}
		
		pedidoDTO.setTotalPedido(totalPedido);
		
		reserva.setTotal_gasto(reserva.getTotal_gasto() + pedidoDTO.getTotalPedido());
		
		pedidoDTO.setStatus(StatusPedido.ANDAMENTO);
		Pedido pedido = Pedido.convert(pedidoDTO);
		pedido.setReserva(reserva);
		pedido.setStatus(StatusPedido.ANDAMENTO);
		
		System.out.println(pedido.getStatus());
		repository.save(pedido);
		
		return PedidoDTO.convert(pedido);
	}

	public PedidoDTO delete(long id) {
		Pedido pedido = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());

		repository.delete(pedido);
		return PedidoDTO.convert(pedido);
	}

	public PedidoDTO edit(Long id, PedidoDTO pedidoDTO) {
		Pedido pedido = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		
		pedido = repository.save(pedido);
		return PedidoDTO.convert(pedido);
	}
	
	public PedidoDTO fecharPedido(Long id) {
		Pedido pedido = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		
		pedido.setStatus(StatusPedido.FINALIZADO);
		
		pedido = repository.save(pedido);
		return PedidoDTO.convert(pedido);
	}
}
