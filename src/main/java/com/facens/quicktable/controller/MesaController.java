package com.facens.quicktable.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.facens.quicktable.dto.MesaDTO;
import com.facens.quicktable.service.MesaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaController {

	private final MesaService service;
	
	@Tag(name = "CRUD Mesa", description = "Metodos de CRUD para objeto Mesa")
	@Operation(summary = "Listar Todas Mesas",
    description = "Listar todas mesas. a resposta e uma lista com todas as mesas criadas.")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<MesaDTO>> getAll() {
		List<MesaDTO> lista = service.getAll();
		
		return ResponseEntity.ok(lista);
	}

	@Tag(name = "CRUD Mesa", description = "Metodos de CRUD para objeto Mesa")
	@Operation(summary = "Listar Mesa pelo ID",
    description = "Lista uma mesa pelo seu ID. a resposta e a mesa com o ID enviado.")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<MesaDTO> getById(@PathVariable Long id) {
		MesaDTO objeto = service.findById(id);
		
		return ResponseEntity.ok(objeto);
	}

	@Tag(name = "CRUD Mesa", description = "Metodos de CRUD para objeto Mesa")
	@Operation(summary = "Adicionar Mesa",
    description = "Adicionar uma nova mesa. a resposta e a mesa adicionada no banco de dados.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<MesaDTO> add(@RequestBody @Valid MesaDTO mesaDTO, UriComponentsBuilder uriBuilder) {
		MesaDTO savedMesa = service.save(mesaDTO);
		
		var uri = uriBuilder.path("/mesas/{id}").buildAndExpand(savedMesa.getId()).toUri();

		return ResponseEntity.created(uri).body(savedMesa);
	}

	@Tag(name = "CRUD Mesa", description = "Metodos de CRUD para objeto Mesa")
	@Operation(summary = "Editar Mesa pelo ID",
    description = "Editar uma mesa. a resposta e a mesa editada.")
	@PatchMapping("/{id}")
	public ResponseEntity<MesaDTO> edit(@PathVariable Long id, @RequestBody MesaDTO mesaDTO) {
		MesaDTO objeto = service.edit(id, mesaDTO);
		
		return ResponseEntity.ok(objeto);
	}

	@Tag(name = "CRUD Mesa", description = "Metodos de CRUD para objeto Mesa")
	@Operation(summary = "Deletar Mesa pelo ID",
    description = "Deletar uma mesa pelo ID enviado.")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable Long id)/* throws UserNotFoundException */ {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
