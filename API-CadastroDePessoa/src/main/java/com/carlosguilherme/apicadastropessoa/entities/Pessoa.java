package com.carlosguilherme.apicadastropessoa.entities;

import java.io.Serializable;
import java.time.LocalDate;



import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;



@Entity
@Table(name = "tb_pessoa")
@Builder
@Data
public class Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataNascimento;
	

	
		
}
