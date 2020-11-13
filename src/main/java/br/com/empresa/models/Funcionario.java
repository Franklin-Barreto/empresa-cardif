package br.com.empresa.models;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "funcionario_id")
	private Integer id;
	@Column(name = "funcionario_name", length = 50, nullable = false)
	private String nome;
	private int idade;
	private LocalDate dataNascimento;
	@Column(name = "funcionario_document", length = 50, nullable = false)
	private String documento;

	@ManyToOne
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	public Funcionario(String nome, LocalDate dataNascimento, String documento, Cargo cargo) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.documento = documento;
		this.cargo = cargo;
	}

	public Funcionario() {
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getDocumento() {
		return documento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
		this.cargo.addFuncionario(this);
	}

}
