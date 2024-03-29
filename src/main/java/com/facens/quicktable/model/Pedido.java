package com.facens.quicktable.model;

import com.facens.quicktable.dto.PedidoDTO;
import com.facens.quicktable.enums.StatusPedido;

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
@Entity(name = "pedido")
@EqualsAndHashCode
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "id_reserva")
	private Reserva reserva;
	
	private String nome_cliente;
	private StatusPedido status;

	public static Pedido convert(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();

		if (pedido.getReserva() != null) {
			pedido.setReserva(Reserva.convert(pedidoDTO.getReserva()));
		}
		
		pedido.setNome_cliente(pedidoDTO.getNome_cliente());
		pedido.setStatus(pedidoDTO.getStatus());
		
		return pedido;
	}
}
