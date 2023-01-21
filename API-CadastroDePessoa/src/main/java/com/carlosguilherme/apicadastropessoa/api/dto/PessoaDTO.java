package com.carlosguilherme.apicadastropessoa.api.dto;

import java.time.LocalDate;



import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import jakarta.persistence.Convert;
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
public class PessoaDTO {
	
	private Long id;
	
	private String nome;
	
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataNascimento;

}
