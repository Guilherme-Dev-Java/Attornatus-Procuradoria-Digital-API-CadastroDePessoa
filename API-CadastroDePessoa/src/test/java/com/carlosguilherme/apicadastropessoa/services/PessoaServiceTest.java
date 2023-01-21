package com.carlosguilherme.apicadastropessoa.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.carlosguilherme.apicadastropessoa.entities.Pessoa;
import com.carlosguilherme.apicadastropessoa.execption.RegraException;
import com.carlosguilherme.apicadastropessoa.repositories.PessoaRepository;
import com.carlosguilherme.apicadastropessoa.services.imp.PessoaServiceImp;


@RunWith(SpringRunner.class)
public class PessoaServiceTest {
	
	@SpyBean
	PessoaServiceImp service;
	
	@SpyBean
	PessoaRepository repository;

	@Before
	public void setUp() {
		
		service = new PessoaServiceImp(repository);
		
	}
	
	@Test(expected = Test.None.class)
	public void deveSalvarUmaPessoaComSucesso() {
		Mockito.doNothing().when(service).validarNome(Mockito.anyString());
		Pessoa pessoa = Pessoa.builder().id(1L).nome("nome").dataNascimento(LocalDate.parse("16-08-1987", DateTimeFormatter.ofPattern("dd-MM-aaaa"))).build();
		Mockito.when(repository.save(Mockito.any(Pessoa.class))).thenReturn(pessoa);
		
		Pessoa pessoaSalva = service.cadastrarPessoa(pessoa);
		
		Assertions.assertThat(pessoaSalva).isNotNull();
		Assertions.assertThat(pessoaSalva.getId()).isEqualTo(1L);
		Assertions.assertThat(pessoaSalva.getNome()).isEqualTo("nome");
		Assertions.assertThat(pessoaSalva.getDataNascimento()).isEqualTo(LocalDate.parse("16-08-1987", DateTimeFormatter.ofPattern("dd-MM-aaaa")));
		
	}
	
	@Test(expected = Test.None.class)
	public void deveValidarNome() {

		Mockito.when(repository.existsByNome(Mockito.anyString())).thenReturn(false);
		
		service.validarNome("pessoa");

	}
	
	@Test
	public void deveLancarErroQuandoNaoEncontraPessoaPeloNomeInformado() {
		
		Mockito.when(repository.findByNome(Mockito.anyString())).thenReturn(Optional.empty());
		
		Throwable exception = Assertions.catchThrowable(() -> service.validarNome("nome"));
		
		Assertions.assertThat(exception).isInstanceOf(RegraException.class).hasMessage("Nenhuma pessoa encontrada pelo nome informado!");
	}
	
	
}
