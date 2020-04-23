package com.arthur.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.osworks.domain.model.Cliente;
import com.arthur.osworks.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
		// return clienteRepository.findByNome("Arthur"); Método que buscar por nome junto com o do repository
		// return clienteRepository.findByNomeContaining("Ar"); Método que busca contendo o Ar do nome
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get()); // Tratando erro caso o id do cliente não exista colocando código
														// de status no cabeçalho da resposta 404
		}
		return ResponseEntity.notFound().build(); // Retorna apenas 404 sem corpo nenhum

	}

	// Cadastro de um cliente
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // Retornando o cabeçalho de created 201
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);

		return ResponseEntity.ok(cliente);

	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		clienteRepository.deleteById(clienteId);

		return ResponseEntity.noContent().build();
	}

}
