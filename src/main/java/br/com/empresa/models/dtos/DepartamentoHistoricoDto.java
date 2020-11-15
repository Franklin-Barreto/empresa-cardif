package br.com.empresa.models.dtos;

import java.time.LocalDate;

public class DepartamentoHistoricoDto {

	private String departamentoNome;
	private LocalDate dataInicio;
	private LocalDate dataFim;

	public DepartamentoHistoricoDto(String departamentoNome, LocalDate dataInicio, LocalDate dataFim) {
		this.departamentoNome = departamentoNome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;

	}

	public String getDepartamentoNome() {
		return departamentoNome;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

}
