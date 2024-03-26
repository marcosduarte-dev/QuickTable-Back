package com.facens.quicktable.model;

import com.facens.quicktable.dto.MesaDTO;
import com.facens.quicktable.enums.StatusMesa;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "mesa")
@EqualsAndHashCode
public class Mesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	
	@Enumerated(EnumType.STRING)
	private StatusMesa status;
	
	public static Mesa convert(MesaDTO mesaDTO) {
		Mesa mesa = new Mesa();
		
		mesa.setStatus(mesaDTO.getStatus());
		mesa.setTitulo(mesaDTO.getTitulo());
		
		return mesa;
	}
}