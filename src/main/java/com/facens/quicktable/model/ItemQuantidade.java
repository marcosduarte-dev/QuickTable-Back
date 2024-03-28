package com.facens.quicktable.model;

import com.facens.quicktable.dto.ItemQuantidadeDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemQuantidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	@ManyToOne
	@JoinColumn(name = "id_item")
	private Item item;
	private int quantidade;
	
	public static ItemQuantidade convert(ItemQuantidadeDTO itemQuantidadeDTO) { 
		ItemQuantidade item = new ItemQuantidade(); 
		item.setItem(
				Item.convert(itemQuantidadeDTO.getItem())); 
		item.setQuantidade(itemQuantidadeDTO.getQuantidade()); 
		return item;
	}
}
