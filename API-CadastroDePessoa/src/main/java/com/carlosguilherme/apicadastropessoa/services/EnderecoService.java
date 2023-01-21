package com.carlosguilherme.apicadastropessoa.services;


import java.util.List;
import java.util.Optional;

import com.carlosguilherme.apicadastropessoa.entities.Endereco;


public interface EnderecoService {
	
	public Endereco cadastrarEndereco(Endereco endereco);

	public void excluirEndereco(Endereco endereco);

	public Endereco alterarEndereco(Endereco endereco);

	List<Endereco> consultarEndereco(Endereco filtrarEndereco); 
	
	void validarEndereco(Endereco endereco);
	
	Optional<Endereco> ObterPorId(Long id);

	

}
