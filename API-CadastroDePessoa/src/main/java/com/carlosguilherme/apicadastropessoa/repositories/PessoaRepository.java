package com.carlosguilherme.apicadastropessoa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosguilherme.apicadastropessoa.entities.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	boolean existsByNome(String nome);	
	
	Optional<Pessoa> findByNome(String nome);
	

	
}
