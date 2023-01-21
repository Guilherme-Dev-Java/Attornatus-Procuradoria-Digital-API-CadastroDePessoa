package com.carlosguilherme.apicadastropessoa.repositories;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.test.context.junit4.SpringRunner;

import com.carlosguilherme.apicadastropessoa.entities.Pessoa;




@PropertyMapping
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PessoaRepositoryTest {
	
	@Autowired
	PessoaRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	public static Pessoa criarPessoa() {
		return  Pessoa.builder()
				.nome("pessoa")
				.dataNascimento(LocalDate.parse("16-08-1987", DateTimeFormatter.ofPattern("dd-MM-aaaa")))
				.build();
		
	}
	
	
	@Test
	public void deveSalvarUmaPessoaNaBaseDeDados() {
		
		Pessoa pessoa = criarPessoa();
		
		Pessoa pessoaSalvo = repository.save(pessoa);
		
		Assertions.assertThat(pessoaSalvo.getId()).isNotNull();
	}

	
	@Test
	public void deveBuscarPessoaPeloNome() {
		Pessoa pessoa = criarPessoa();
		entityManager.persist(pessoa);
		
		Optional<Pessoa> result = ( repository).findByNome("pessoa");
		
		Assertions.assertThat(result.isPresent()).isTrue();
				
	}

	@Test
	public void deveRetornarABuscarVaziaDePessoaQuandooNomeNaoExistiNoBAncoDeDados() {
		
		Optional<Pessoa> result = ( repository).findByNome("pessoa");
		
		Assertions.assertThat(result.isPresent()).isFalse();
				
	}
	
	@Test
	public void verificarExistenciaDoNome() {
		
		Pessoa pessoa = criarPessoa();
		
		boolean result = repository.existsByNome("pessoa");
		
		Assertions.assertThat(result).isTrue();
		
}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverPessoaCadastradoComONome() {
		
		boolean result = repository.existsByNome("pessoa");
		
		Assertions.assertThat(result).isFalse();
		
	}
	
	
}
