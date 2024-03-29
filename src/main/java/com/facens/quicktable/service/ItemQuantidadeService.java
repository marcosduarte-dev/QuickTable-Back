package com.facens.quicktable.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.facens.quicktable.dto.ItemQuantidadeDTO;
import com.facens.quicktable.dto.PedidoDTO;
import com.facens.quicktable.model.Item;
import com.facens.quicktable.model.ItemQuantidade;
import com.facens.quicktable.model.Pedido;
import com.facens.quicktable.repository.ItemQuantidadeRepository;
import com.facens.quicktable.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemQuantidadeService {
	
	private final ItemQuantidadeRepository repository;
	private final ItemRepository itemRepository;
	
	public ItemQuantidadeDTO save(ItemQuantidadeDTO itemDTO, PedidoDTO pedidoDTO) {
		
		Pedido pedido = new Pedido();
		pedido.setId(pedidoDTO.getId());
		
		Item item = itemRepository.findById(itemDTO.getItem().getId())
				.orElseThrow(() -> new RuntimeException("Item nao encontrado"));
		
		ItemQuantidade itemQuantidade = ItemQuantidade.convert(itemDTO);
		itemQuantidade.setPedido(pedido);
		itemQuantidade.setItem(item);
		repository.save(itemQuantidade);
		
		return ItemQuantidadeDTO.convert(itemQuantidade);
	}
	
	public List<ItemQuantidadeDTO> findByIdPedido(long id) {
		List<ItemQuantidade> lista = repository.findAllByIdPedido(id);
		return lista
				.stream()
				.map(ItemQuantidadeDTO::convert)
				.collect(Collectors.toList());
	}
}
