package com.facens.quicktable.model;

import com.facens.quicktable.dto.CategoriaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name="categoria")
@EqualsAndHashCode
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String categoria;

	public static Categoria convert(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();

		categoria.setCategoria(categoriaDTO.getCategoria());

		return categoria;
	}
}
