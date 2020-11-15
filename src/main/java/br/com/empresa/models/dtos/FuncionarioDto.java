package br.com.empresa.models.dtos;

import java.time.LocalDate;

public class FuncionarioDto {

	private String nome;

	private LocalDate dataNascimento;

	private String documento;

	private Long cargoId;

	private Long departamentoId;

	public FuncionarioDto(String nome, LocalDate dataNascimento, String documento, Long cargoId,
			Long departamentoId) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.documento = documento;
		this.cargoId = cargoId;
		this.departamentoId = departamentoId;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getDocumento() {
		return documento;
	}

	public Long getCargo() {
		return cargoId;
	}

	public Long getDepartamento() {
		return departamentoId;
	}
}
