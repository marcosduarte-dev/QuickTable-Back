package com.facens.quicktable.dto;

import com.facens.quicktable.enums.StatusMesa;
import com.facens.quicktable.model.Mesa;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaDTO {
	private Long id;
	@NotNull
	private String titulo;
	@NotNull
	private StatusMesa status;
	
	public static MesaDTO convert(Mesa mesa) { 
		MesaDTO mesaDTO = new MesaDTO(); 
		
		mesaDTO.setId(mesa.getId());
		mesaDTO.setTitulo(mesa.getTitulo());
		mesaDTO.setStatus(mesa.getStatus());
		
		return mesaDTO;
	}
}
