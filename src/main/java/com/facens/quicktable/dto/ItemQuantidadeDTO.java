package com.facens.quicktable.dto;

import com.facens.quicktable.model.ItemQuantidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemQuantidadeDTO {
	private Long id;
	private ItemDTO item;
	private int quantidade;
	
	public static ItemQuantidadeDTO convert(ItemQuantidade itemQuantidade) { 
		ItemQuantidadeDTO itemQuantidadeDTO = new ItemQuantidadeDTO(); 
		itemQuantidadeDTO.setQuantidade(
				itemQuantidade.getQuantidade()); 
		itemQuantidadeDTO.setItem(ItemDTO.convert(itemQuantidade.getItem()));
		itemQuantidadeDTO.setId(itemQuantidade.getId());
		return itemQuantidadeDTO;
	}
}
