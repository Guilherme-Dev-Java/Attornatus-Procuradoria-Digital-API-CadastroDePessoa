package com.carlosguilherme.apicadastropessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EntityScan(basePackages = {"com.carlosguilherme.apicadastropessoa.entities"})
@ComponentScan
@EnableJpaRepositories(basePackages = {"com.carlosguilherme.apicadastropessoa.repositories"})
@EnableTransactionManagement
@SpringBootApplication
public class ApiCadastroDePessoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCadastroDePessoaApplication.class, args);
	}

}
