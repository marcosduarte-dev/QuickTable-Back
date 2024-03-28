package com.facens.quicktable.model;

import java.time.LocalDateTime;

import com.facens.quicktable.dto.ReservaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "id_mesa")
	private Mesa mesa;
	private Long qtde_participantes;
	private Float total_gasto;
	private LocalDateTime data_abertura;
	private LocalDateTime data_fechamento;

	public static Reserva convert(ReservaDTO reservaDTO) {
		Reserva reserva = new Reserva();

		if(reservaDTO.getMesa() != null) {
			reserva.setMesa(Mesa.convert(reservaDTO.getMesa()));
		}
		reserva.setQtde_participantes(reservaDTO.getQtde_participantes());
		reserva.setTotal_gasto(reservaDTO.getTotal_gasto());
		reserva.setData_abertura(reservaDTO.getData_abertura());
		reserva.setData_fechamento(reservaDTO.getData_fechamento());

		return reserva;
	}
}
