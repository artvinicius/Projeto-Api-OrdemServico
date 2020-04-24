package com.arthur.osworks.service;

import com.arthur.osworks.domain.model.Cliente;
import com.arthur.osworks.domain.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void exlcuir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

}