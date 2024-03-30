package com.facens.quicktable.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facens.quicktable.dto.ItemDTO;
import com.facens.quicktable.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cardapio")
@RequiredArgsConstructor
public class CardapioController {
	
	private final ItemService itemService;
	
	@Tag(name = "Cardapio", description = "EndPoints relacionado com o cardapio")
	@Operation(summary = "Listar Todos Itens do Cardapio",
    description = "Listar todos itens do cardapio. a resposta e uma lista com todos os itens criados.")
	@GetMapping
	public ResponseEntity<List<ItemDTO>> getAll() {
		List<ItemDTO> lista = itemService.getAll();
		
		return ResponseEntity.ok(lista);
	}
}
