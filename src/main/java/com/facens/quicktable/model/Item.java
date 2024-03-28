package com.facens.quicktable.model;

import com.facens.quicktable.dto.ItemDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "item")
@EqualsAndHashCode
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	@NotNull
	private String nome;
	@NotNull
	private Float preco;

	public static Item convert(ItemDTO itemDTO) {
		Item item = new Item();

		if (item.getCategoria() != null) {
			item.setCategoria(Categoria.convert(itemDTO.getCategoria()));
		}
		item.setNome(itemDTO.getNome());
		item.setPreco(itemDTO.getPreco());

		return item;
	}
}
