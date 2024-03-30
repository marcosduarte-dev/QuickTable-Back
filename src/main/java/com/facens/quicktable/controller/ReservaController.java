package com.facens.quicktable.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.facens.quicktable.dto.ReservaDTO;
import com.facens.quicktable.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

	private final ReservaService service;

	@Tag(name = "CRUD Reserva", description = "Metodos de CRUD para objeto Reserva")
	@Operation(summary = "Listar Todas Reservas",
    description = "Listar todas reservas. a resposta e uma lista com todas as reservas criadas.")
	@GetMapping
	public ResponseEntity<List<ReservaDTO>> getAll() {
		List<ReservaDTO> lista = service.getAll();
		
		return ResponseEntity.ok(lista);
	}

	/*@GetMapping("/{id}")
	public ResponseEntity<ReservaDTO> getById(@PathVariable Long id) {
		ReservaDTO objeto = service.findById(id);
		
		return ResponseEntity.ok(objeto);
	}*/

	@Tag(name = "CRUD Reserva", description = "Metodos de CRUD para objeto Reserva")
	@Operation(summary = "Adicionar Reserva",
    description = "Adicionar uma nova reserva. a resposta e a reserva adicionada no banco de dados.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ReservaDTO> add(@RequestBody @Valid ReservaDTO reservaDTO, UriComponentsBuilder uriBuilder) {
		ReservaDTO savedReserva = service.save(reservaDTO);
		
		var uri = uriBuilder.path("/reservas/{id}").buildAndExpand(savedReserva.getId()).toUri();

		return ResponseEntity.created(uri).body(savedReserva);
	}

	/*@PatchMapping("/{id}")
	public ResponseEntity<ReservaDTO> edit(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
		ReservaDTO objeto = service.edit(id, reservaDTO);
		
		return ResponseEntity.ok(objeto);
	}*/

	//@DeleteMapping("/{id}")
	//public ResponseEntity<Void> delete(@PathVariable Long id)/* throws UserNotFoundException */ {
	//	service.delete(id);
		
	//	return ResponseEntity.noContent().build();
	//}
	
	@Tag(name = "CRUD Reserva", description = "Metodos de CRUD para objeto Reserva")
	@Operation(summary = "entrarNaReserva",
    description = "Participar de uma reserva. a resposta e a reserva adicionada/atualizada no banco de dados.")
	@PostMapping("/entrarNaReserva/{idMesa}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ReservaDTO> entrarNaReserva(@PathVariable Long idMesa) {
		ReservaDTO savedReserva = service.entrarNaReserva(idMesa);

		return ResponseEntity.ok(savedReserva);
	}
}
