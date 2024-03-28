package com.facens.quicktable.dto;

import com.facens.quicktable.model.Categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {
	private Long id;
	@NotBlank(message = "Categoria é obrigatório!")
	private String categoria;

	public static CategoriaDTO convert(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();

		categoriaDTO.setId(categoria.getId());
		categoriaDTO.setCategoria(categoria.getCategoria());

		return categoriaDTO;
	}
}
