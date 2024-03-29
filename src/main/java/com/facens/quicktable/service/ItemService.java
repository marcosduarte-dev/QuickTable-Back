package com.facens.quicktable.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.facens.quicktable.dto.ItemDTO;
import com.facens.quicktable.model.Categoria;
import com.facens.quicktable.model.Item;
import com.facens.quicktable.repository.CategoriaRepository;
import com.facens.quicktable.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository repository;
	private final CategoriaRepository categoriaRepository;

	public List<ItemDTO> getAll() {
		List<Item> items = repository.findAll();
		return items
				.stream()
				.map(ItemDTO::convert)
				.collect(Collectors.toList());
	}

	public ItemDTO findById(long id) {
		Item item = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Item nao encontrado"));
		return ItemDTO.convert(item);
	}

	public ItemDTO save(ItemDTO itemDTO) {
		
		Categoria categoria = categoriaRepository.findById(itemDTO.getCategoria().getId())
				.orElseThrow(() -> new RuntimeException("Categoria nao encontrado"));
		
		Item item = Item.convert(itemDTO);
		item.setCategoria(categoria);
		repository.save(item);
		
		return ItemDTO.convert(item);
	}

	public ItemDTO delete(long id) {
		Item item = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());

		repository.delete(item);
		return ItemDTO.convert(item);
	}

	public ItemDTO edit(Long id, ItemDTO itemDTO) {
		Item item = repository.findById(id)
				.orElseThrow(() -> new RuntimeException());
		
		if (itemDTO.getCategoria() != null) {
			item.setCategoria(Categoria.convert(itemDTO.getCategoria()));
		}
		
		if (itemDTO.getNome() != null &&
				!item.getNome().equals(itemDTO.getNome())) {
			item.setNome(itemDTO.getNome());
		}
		
		if (itemDTO.getPreco() != null &&
				!item.getPreco().equals(itemDTO.getPreco())) {
			item.setPreco(itemDTO.getPreco());
		}
		
		item = repository.save(item);
		return ItemDTO.convert(item);
	}
}
