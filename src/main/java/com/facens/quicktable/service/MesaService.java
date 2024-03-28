package com.facens.quicktable.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.facens.quicktable.dto.MesaDTO;
import com.facens.quicktable.enums.StatusMesa;
import com.facens.quicktable.model.Mesa;
import com.facens.quicktable.repository.MesaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MesaService {
	
	private final MesaRepository repository;
	
	public List<MesaDTO> getAll() {
		List<Mesa> mesas = repository.findAll();
		return mesas
				.stream()
				.map(MesaDTO::convert)
				.collect(Collectors.toList());
	}
	
	public MesaDTO findById(long userID) {
		Mesa mesa = repository.findById(userID)
				.orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
		return MesaDTO.convert(mesa);
	}
	
	public MesaDTO save(MesaDTO mesaDTO) {
		Mesa mesa = repository.save(Mesa.convert(mesaDTO));
		return MesaDTO.convert(mesa);
	}
	
	public MesaDTO delete(long id) { 
		Mesa mesa = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		
		repository.delete(mesa); 
		return MesaDTO.convert(mesa);
	}
	
	public MesaDTO edit(Long id, MesaDTO mesaDTO) { 
		Mesa mesa = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		if (mesaDTO.getStatus() != null &&
		!mesa.getStatus().equals(mesaDTO.getStatus())) { 
			mesa.setStatus(mesaDTO.getStatus());
		}
		if (mesaDTO.getTitulo() != null &&
		!mesa.getTitulo().equals(mesaDTO.getTitulo())) { 
			mesa.setTitulo(mesaDTO.getTitulo());
		}

		mesa = repository.save(mesa); 
		return MesaDTO.convert(mesa);
	}
	
	public Mesa usarMesa(Long id) {
		Mesa mesa = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		mesa.setStatus(StatusMesa.USO);
		
		return repository.save(mesa);
	}
	
	public Mesa fecharMesa(Long id) {
		Mesa mesa = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		mesa.setStatus(StatusMesa.FECHADO);
		
		return repository.save(mesa);
	}
	
	public Mesa abrirMesa(Long id) {
		Mesa mesa = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		mesa.setStatus(StatusMesa.ABERTO);
		
		return repository.save(mesa);
	}
}
