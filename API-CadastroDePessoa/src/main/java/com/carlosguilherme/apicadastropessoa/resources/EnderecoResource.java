package com.carlosguilherme.apicadastropessoa.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosguilherme.apicadastropessoa.api.dto.EnderecoDTO;
import com.carlosguilherme.apicadastropessoa.entities.Endereco;
import com.carlosguilherme.apicadastropessoa.entities.Pessoa;
import com.carlosguilherme.apicadastropessoa.enums.StatusEndereco;
import com.carlosguilherme.apicadastropessoa.execption.RegraException;
import com.carlosguilherme.apicadastropessoa.services.EnderecoService;
import com.carlosguilherme.apicadastropessoa.services.PessoaService;
;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoResource {
	
	private EnderecoService service;
	
	private PessoaService pessoaService;
	
	public EnderecoResource (EnderecoService service) {
		this.service = service;
	}
	
	@PostMapping("/cadastro")
	public ResponseEntity cadastrar(@RequestBody EnderecoDTO dto ) {
		
		try {
			Endereco entity = converter(dto);
			entity = service.cadastrarEndereco(entity);
			return new ResponseEntity(entity, HttpStatus.CREATED);
			
			
		} 
		catch (RegraException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@PutMapping("{id}")
	public ResponseEntity alterar(@PathVariable("id") Long id, @RequestBody EnderecoDTO dto) {
		return service.ObterPorId(id).map(entidade -> {
			try {
				Endereco endereco = converter(dto);
				endereco.setId(entidade.getId());
				service.alterarEndereco(endereco);
				return ResponseEntity.ok(endereco);
			} catch (RegraException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Endereço não encontrado!", HttpStatus.BAD_REQUEST));

	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.ObterPorId(id).map(entidade -> {
			service.excluirEndereco(entidade);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Endereço não encontrado!", HttpStatus.BAD_REQUEST));
		
	}


	
	private Endereco converter(EnderecoDTO dto) {
		
		Endereco endereco = new Endereco();
		endereco.setId(dto.getId());
		endereco.setLogradouro(dto.getLogradouro());
		endereco.setNumero(dto.getNumero());
		endereco.setCidade(dto.getCidade());
		endereco.setCep(dto.getCep());
		endereco.setBairro(dto.getBairro());
		
		Pessoa pessoa = pessoaService.ObterPorId(dto.getPessoa())
				.orElseThrow(()->new RegraException("Pessoa não encontrado para o ID informado!"));
		
		endereco.setPessoa(pessoa);
		endereco.setStatus(StatusEndereco.valueOf(dto.getStatus()));
		
		return endereco;
		
		
	}
}
