package com.facens.quicktable.dto;

import java.util.List;

import com.facens.quicktable.enums.StatusPedido;
import com.facens.quicktable.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
	
	private Long id;
	private List<ItemQuantidadeDTO> itemQuantidade;
	private ReservaDTO reserva;
	private float totalPedido;
	private String nome_cliente;
	private StatusPedido status;
	
	public static PedidoDTO convert(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();

		pedidoDTO.setId(pedido.getId());
		if (pedido.getReserva() != null) {
			pedidoDTO.setReserva(ReservaDTO.convert(pedido.getReserva()));
		}
		pedidoDTO.setNome_cliente(pedido.getNome_cliente());
		pedidoDTO.setStatus(pedido.getStatus());
		
		return pedidoDTO;
	}
	
}
