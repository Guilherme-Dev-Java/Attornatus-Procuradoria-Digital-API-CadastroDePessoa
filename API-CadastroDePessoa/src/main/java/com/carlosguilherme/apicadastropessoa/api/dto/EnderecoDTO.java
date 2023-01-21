package com.carlosguilherme.apicadastropessoa.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
	
	private Long id;
	
	private String logradouro;
	
	private String numero;
	
	private String cidade;
	
	private String bairro;
	
	private String cep;
	
	private String status;
	
	private Long pessoa;

}
