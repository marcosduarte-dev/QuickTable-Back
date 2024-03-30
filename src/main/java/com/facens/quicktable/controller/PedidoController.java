package com.facens.quicktable.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.facens.quicktable.dto.ItemQuantidadeDTO;
import com.facens.quicktable.dto.PedidoDTO;
import com.facens.quicktable.service.ItemQuantidadeService;
import com.facens.quicktable.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService service;
	private final ItemQuantidadeService itemQuantidadeService;
	
	@Tag(name = "CRUD Pedido", description = "Metodos de CRUD para objeto Pedido")
	@Operation(summary = "Listar Todos Pedidos",
    description = "Listar todos pedidos. a resposta e uma lista com todos os pedidos criados.")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<PedidoDTO>> getAll() {
		List<PedidoDTO> lista = service.getAll();
		
		for(PedidoDTO pedido : lista) {
			List<ItemQuantidadeDTO> listaItemQuantidade = itemQuantidadeService.findByIdPedido(pedido.getId());
			
			float totalPedido = (float) 0.0;
			
			for (ItemQuantidadeDTO itemQuantidade : listaItemQuantidade) {
				totalPedido += itemQuantidade.getItem().getPreco() * itemQuantidade.getQuantidade();
			}
			
			pedido.setTotalPedido(totalPedido);
			
			pedido.setItemQuantidade(listaItemQuantidade);
		}
		
		return ResponseEntity.ok(lista);
	}
	
	@Tag(name = "CRUD Pedido", description = "Metodos de CRUD para objeto Pedido")
	@Operation(summary = "Listar Todos Pedidos pelo nome",
    description = "Listar todos pedidos pelo nome. a resposta e uma lista com todos os pedidos criados.")
	@GetMapping("/nomeCliente/{nomeCliente}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<PedidoDTO>> getByNomeCliente(String nomeCliente) {
		List<PedidoDTO> lista = service.getByNomeCliente(nomeCliente);
		
		for(PedidoDTO pedido : lista) {
			List<ItemQuantidadeDTO> listaItemQuantidade = itemQuantidadeService.findByIdPedido(pedido.getId());
			
			float totalPedido = (float) 0.0;
			
			for (ItemQuantidadeDTO itemQuantidade : listaItemQuantidade) {
				totalPedido += itemQuantidade.getItem().getPreco() * itemQuantidade.getQuantidade();
			}
			
			pedido.setTotalPedido(totalPedido);
			
			pedido.setItemQuantidade(listaItemQuantidade);
		}
		
		return ResponseEntity.ok(lista);
	}
/*
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PedidoDTO> getById(@PathVariable Long id) {
		PedidoDTO objeto = service.findById(id);
		
		return ResponseEntity.ok(objeto);
	}
*/
	@Tag(name = "CRUD Pedido", description = "Metodos de CRUD para objeto Pedido")
	@Operation(summary = "Adicionar Pedido",
    description = "Adicionar um novo pedido. a resposta e o pedido adicionado no banco de dados.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PedidoDTO> add(@RequestBody @Valid PedidoDTO pedidoDTO, UriComponentsBuilder uriBuilder) {
		PedidoDTO savedPedido = service.save(pedidoDTO);
		
		for (ItemQuantidadeDTO itemquantidade : pedidoDTO.getItemQuantidade()) {
			itemQuantidadeService.save(itemquantidade, savedPedido);
		}
		
		pedidoDTO.setId(savedPedido.getId());
		var uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(savedPedido.getId()).toUri();
		return ResponseEntity.created(uri).body(pedidoDTO);
	}

	/*@PatchMapping("/{id}")
	public ResponseEntity<PedidoDTO> edit(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
		PedidoDTO objeto = service.edit(id, pedidoDTO);
		
		return ResponseEntity.ok(objeto);
	}*/
	
	@Tag(name = "CRUD Pedido", description = "Metodos de CRUD para objeto Pedido")
	@Operation(summary = "Deletar Pedido pelo ID",
    description = "Deletar um Pedido pelo ID enviado.")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable Long id)/* throws UserNotFoundException */ {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
