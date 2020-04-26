package com.arthur.osworks.service;

import java.time.OffsetDateTime;

import com.arthur.osworks.domain.model.Cliente;
import com.arthur.osworks.domain.model.OrdemServico;
import com.arthur.osworks.domain.model.StatusOrdemServico;
import com.arthur.osworks.exception.NegocioException;
import com.arthur.osworks.repository.ClienteRepository;
import com.arthur.osworks.repository.OrdemServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
            .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

}