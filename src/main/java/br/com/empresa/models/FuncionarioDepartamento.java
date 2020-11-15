package br.com.empresa.models;

import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class FuncionarioDepartamento {

	@EmbeddedId
	private FuncionarioDepartamentoId funcionarioDepartamentoId;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("funcionarioId")
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departamento_id")
	@MapsId("departamentoId")
	private Departamento departamento;

	private LocalDate dataInicio;
	private LocalDate dataFim;

	@SuppressWarnings("unused")
	private FuncionarioDepartamento() {
	}

	public FuncionarioDepartamento(Funcionario funcionario, Departamento departamento) {
		this.funcionario = funcionario;
		this.departamento = departamento;
		this.dataInicio = LocalDate.now();
		this.funcionarioDepartamentoId = new FuncionarioDepartamentoId(funcionario.getId(), departamento.getId());
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim() {
		this.dataFim = LocalDate.now();
	}

}
