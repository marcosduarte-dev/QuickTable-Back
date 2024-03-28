package com.facens.quicktable.dto;

import java.time.LocalDateTime;

import com.facens.quicktable.model.Reserva;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {
	private Long id;
	private MesaDTO mesa;
	private Long qtde_participantes;
	private Float total_gasto;
	private LocalDateTime data_abertura;
	private LocalDateTime data_fechamento;
	public static ReservaDTO convert(Reserva reserva) {
		ReservaDTO reservaDTO = new ReservaDTO();

		reservaDTO.setId(reserva.getId());
		if (reserva.getMesa() != null) {
			reservaDTO.setMesa(MesaDTO.convert(reserva.getMesa()));
		}
		reservaDTO.setQtde_participantes(reserva.getQtde_participantes());
		reservaDTO.setTotal_gasto(reserva.getTotal_gasto());
		reservaDTO.setData_abertura(reserva.getData_abertura());
		reservaDTO.setData_fechamento(reserva.getData_fechamento());

		return reservaDTO;
	}
}
