package com.carlosguilherme.apicadastropessoa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;


import com.carlosguilherme.apicadastropessoa.entities.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	boolean existsByLogradouro(String nome);
	
}
