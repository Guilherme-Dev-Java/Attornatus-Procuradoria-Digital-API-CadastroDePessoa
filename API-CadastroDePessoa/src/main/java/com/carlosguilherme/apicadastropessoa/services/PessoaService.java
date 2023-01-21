package com.carlosguilherme.apicadastropessoa.services;

import java.util.Collection;
import java.util.Optional;

import com.carlosguilherme.apicadastropessoa.entities.Pessoa;

public interface PessoaService {

	public Pessoa cadastrarPessoa(Pessoa pessoa);

	public void excluirPessoa(Pessoa pessoa);

	public Pessoa alterarPessoa(Pessoa pessoa);

	public Collection<Pessoa> listarPessoa();
	
	void validarNome (String nome);
	
	Optional<Pessoa> ObterPorId(Long id);

	

	
}
