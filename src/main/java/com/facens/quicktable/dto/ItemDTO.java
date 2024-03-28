package com.facens.quicktable.dto;

import com.facens.quicktable.model.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	private long id;
	private CategoriaDTO categoria;
	private String nome;
	private Float preco;
	public static ItemDTO convert(Item item) {
		ItemDTO itemDTO = new ItemDTO();

		itemDTO.setId(item.getId());
		if (item.getCategoria() != null) {
			itemDTO.setCategoria(CategoriaDTO.convert(item.getCategoria()));
		}
		itemDTO.setNome(item.getNome());
		itemDTO.setPreco(item.getPreco());

		return itemDTO;
	}
	
	public ItemDTO(Long id) {
        this.id = id;
    }
}
