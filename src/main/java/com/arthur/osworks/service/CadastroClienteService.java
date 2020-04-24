package com.arthur.osworks.service;

import com.arthur.osworks.domain.model.Cliente;
import com.arthur.osworks.domain.repository.ClienteRepository;
import com.arthur.osworks.exception.NegocioException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    public void exlcuir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

}