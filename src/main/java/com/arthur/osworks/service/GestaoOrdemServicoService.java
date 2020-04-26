package com.arthur.osworks.service;

import java.time.OffsetDateTime;

import com.arthur.osworks.api.model.Comentario;
import com.arthur.osworks.domain.model.Cliente;
import com.arthur.osworks.domain.model.OrdemServico;
import com.arthur.osworks.domain.model.StatusOrdemServico;
import com.arthur.osworks.exception.EntidadeNaoEncontradaException;
import com.arthur.osworks.exception.NegocioException;
import com.arthur.osworks.repository.ClienteRepository;
import com.arthur.osworks.repository.ComentarioRepository;
import com.arthur.osworks.repository.OrdemServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestaoOrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

    // Método para finalizar uma ordem de serviço
    public void finalizar(Long ordemServicoId) {
        OrdemServico ordemServico = buscar(ordemServicoId);

        ordemServico.finalizar();

        ordemServicoRepository.save(ordemServico);
    }

    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscar(ordemServicoId);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);

    }

    private OrdemServico buscar(Long ordemServicoId) {
        return ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
    }

}