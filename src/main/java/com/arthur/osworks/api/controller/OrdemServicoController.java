package com.arthur.osworks.api.controller;



import com.arthur.osworks.domain.model.OrdemServico;
import com.arthur.osworks.service.GestaoOrdemServicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServico;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
   public OrdemServico criar(@RequestBody OrdemServico ordemServico){
       return gestaoOrdemServico.criar(ordemServico);
   }

}