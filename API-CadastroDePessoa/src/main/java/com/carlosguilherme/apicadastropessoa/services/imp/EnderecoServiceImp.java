package com.carlosguilherme.apicadastropessoa.services.imp;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosguilherme.apicadastropessoa.entities.Endereco;
import com.carlosguilherme.apicadastropessoa.enums.StatusEndereco;
import com.carlosguilherme.apicadastropessoa.execption.RegraException;
import com.carlosguilherme.apicadastropessoa.repositories.EnderecoRepository;
import com.carlosguilherme.apicadastropessoa.services.EnderecoService;


@Service
public class EnderecoServiceImp implements EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public EnderecoServiceImp(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}

	@Override
	public Endereco cadastrarEndereco(Endereco endereco) {
		validarEndereco(endereco);
		endereco.setStatus(StatusEndereco.PRINCIPAL);
		return enderecoRepository.save(endereco);
	}

	@Override
	public void excluirEndereco(Endereco endereco) {
		Objects.requireNonNull(endereco.getId());
		enderecoRepository.delete(endereco);
	}

	@Override
	public Endereco alterarEndereco(Endereco endereco) {
		Objects.requireNonNull(endereco.getId());
		validarEndereco(endereco);
		return enderecoRepository.save(endereco);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Endereco> consultarEndereco(Endereco filtrarEndereco) {
		Example<Endereco> example = Example.of(filtrarEndereco, ExampleMatcher.matching()
				.withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING)); 
		return enderecoRepository.findAll(example);
	}

	@Override
	@Transactional
	public void validarEndereco(Endereco endereco) {
		
		if(endereco.getLogradouro()== null || endereco.getLogradouro().trim().equals("")) {
			throw new RegraException("Informe um Logradouro válido!");
		}
		
		if(endereco.getCidade()== null || endereco.getCidade().trim().equals("")) {
			throw new RegraException("Informe uma Cidade válido!");
		}
		
		if(endereco.getBairro()== null || endereco.getBairro().trim().equals("")) {
			throw new RegraException("Informe um Bairro válido!");
		}
		
		if(endereco.getCep()== null || endereco.getCep().trim().equals("")) {
			throw new RegraException("Informe um CEP válido!");
		}
		
		if(endereco.getNumero()== null || endereco.getNumero().trim().equals("")) {
			throw new RegraException("Informe um Número válido!");
		}
		
	}

	@Override
	public Optional<Endereco> ObterPorId(Long id) {
		return enderecoRepository.findById(id);
	}


	

}
