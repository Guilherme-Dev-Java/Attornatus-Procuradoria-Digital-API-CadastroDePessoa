package com.carlosguilherme.apicadastropessoa.resources;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosguilherme.apicadastropessoa.api.dto.PessoaDTO;
import com.carlosguilherme.apicadastropessoa.entities.Pessoa;
import com.carlosguilherme.apicadastropessoa.execption.RegraException;
import com.carlosguilherme.apicadastropessoa.services.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaResource {
	
	private PessoaService service;
	
	public PessoaResource(PessoaService service) {
		this.service = service;
	}

	@PostMapping("/cadastro")
	public ResponseEntity<Serializable> cadastrar(@RequestBody PessoaDTO dto ) {
		
		Pessoa pessoa = Pessoa.builder()
				.nome(dto.getNome())
				.dataNascimento(dto.getDataNascimento())
				.build();
		
		try {
			Pessoa pessoaCadastrada = service.cadastrarPessoa(pessoa);
			
			return new ResponseEntity<Serializable>(pessoaCadastrada, HttpStatus.CREATED);
			
		} catch (RegraException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PostMapping("/alterar")
	public ResponseEntity<Serializable> alterar(@RequestBody PessoaDTO dto ) {
		
		
		Pessoa cadastro = Pessoa.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.dataNascimento(dto.getDataNascimento())
				.build();
		
		try {
			Pessoa pessoaCadastrada = service.cadastrarPessoa(cadastro);
			
			return new ResponseEntity<Serializable>(pessoaCadastrada, HttpStatus.CREATED);
			
		} catch (RegraException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	
	
}
