package com.arthur.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
	var cliente1 = new Cliente();
	var cliente2 = new Cliente();
	
	cliente1.setId(1L);
	cliente1.setNome("Arthur V");
	cliente1.setTelefone("81 9 9411-9930");
	cliente1.setEmail("arthur@gmil.com");

	cliente2.setId(2L);
	cliente2.setNome("Álvaro E");
	cliente2.setTelefone("81 9 9412-9940");
	cliente2.setEmail("alvaro@gmil.com");
	
	return Arrays.asList(cliente1, cliente2);
	
	
	}
	
}
