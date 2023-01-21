package com.carlosguilherme.apicadastropessoa.services.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosguilherme.apicadastropessoa.entities.Pessoa;
import com.carlosguilherme.apicadastropessoa.execption.RegraException;
import com.carlosguilherme.apicadastropessoa.repositories.PessoaRepository;
import com.carlosguilherme.apicadastropessoa.services.PessoaService;



@Service
public class PessoaServiceImp implements PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
		
	public PessoaServiceImp(PessoaRepository pessoaRepository) {
		super();
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	@Transactional
	public Pessoa cadastrarPessoa(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@Override
	@Transactional
	public void excluirPessoa(Pessoa pessoa) {
		Objects.requireNonNull(pessoa.getId());
		pessoaRepository.delete(pessoa);
				
	}
	
	
	@Override
	@Transactional
	public Pessoa alterarPessoa(Pessoa pessoa) {
		Objects.requireNonNull(pessoa.getId());
		return pessoaRepository.save(pessoa);
	}
			
	@Override
	@Transactional
	public Collection<Pessoa> listarPessoa() {
		
		List<Pessoa> pessoas = new ArrayList<>();
		
		return pessoas;
	}

	
	@Override
	@Transactional
	public void validarNome(String nome) {
		boolean isCadastrado = pessoaRepository.existsByNome(nome);
		if(isCadastrado) {
			throw new RegraException("Pessoa já cadastrada");
		}
	}

	@Override
	public Optional<Pessoa> ObterPorId(Long id) {
		return pessoaRepository.findById(id);
	}


}
