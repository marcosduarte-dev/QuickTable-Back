package com.facens.quicktable.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.facens.quicktable.dto.CategoriaDTO;
import com.facens.quicktable.model.Categoria;
import com.facens.quicktable.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

	private final CategoriaRepository repository;

	public List<CategoriaDTO> getAll() {
		List<Categoria> categorias = repository.findAll();
		return categorias
				.stream()
				.map(CategoriaDTO::convert)
				.collect(Collectors.toList());
	}

	public CategoriaDTO findById(long id) {
		Categoria categoria = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria nao encontrado"));
		return CategoriaDTO.convert(categoria);
	}

	public CategoriaDTO save(CategoriaDTO categoriaDTO) {
		Categoria categoria = repository.save(Categoria.convert(categoriaDTO));
		return CategoriaDTO.convert(categoria);
	}

	public CategoriaDTO delete(long id) {
		Categoria categoria = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());

		repository.delete(categoria);
		return CategoriaDTO.convert(categoria);
	}

	public CategoriaDTO edit(Long id, CategoriaDTO categoriaDTO) {
		Categoria categoria = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		if (categoriaDTO.getCategoria() != null &&
				!categoria.getCategoria().equals(categoriaDTO.getCategoria())) {
			categoria.setCategoria(categoriaDTO.getCategoria());
		}
		categoria = repository.save(categoria);
		return CategoriaDTO.convert(categoria);
	}
}
