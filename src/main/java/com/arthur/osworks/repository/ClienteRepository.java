package com.arthur.osworks.repository;

import java.util.List;

import com.arthur.osworks.domain.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//Métodos de pesquisa no jpa por nome
List<Cliente> findByNome(String nome);
//Métodos de pesquisa no jpa por uma inicial, Ex: art e ele buscar o usuário Arthur
List<Cliente> findByNomeContaining(String nome);
//Método para buscar Email cadastrado no banco
Cliente findByEmail(String email);
}