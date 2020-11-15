package br.com.empresa.models.dtos;

import java.util.Collections;
import java.util.List;

public class FuncionarioHistoricoDto {

	private Long id;
	private String nome;
	private List<DepartamentoHistoricoDto> departamentoHistorico;

	public FuncionarioHistoricoDto(Long id, String nome, List<DepartamentoHistoricoDto> departamentoHistorico) {
		this.id = id;
		this.nome = nome;
		this.departamentoHistorico = departamentoHistorico;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<DepartamentoHistoricoDto> getDepartamentoHistorico() {
		return Collections.unmodifiableList(departamentoHistorico);
	}

}
