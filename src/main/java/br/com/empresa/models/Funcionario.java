package br.com.empresa.models;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "funcionario_id")
	private Integer id;
	@Column(name = "funcionario_name", length = 50, nullable = false)
	private String nome;

	@Transient
	private int idade;

	@Column(name = "funcionario_birthday")
	private LocalDate dataNascimento;

	@Column(name = "funcionario_document", length = 50, nullable = false)
	private String documento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo_id")
	private Cargo cargo;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch = FetchType.EAGER)
	@JoinTable(name = "funcionario_departamento", 
	joinColumns = @JoinColumn(name = "funcionario_id"), 
	inverseJoinColumns = @JoinColumn(name = "departamento_id"))
	private Set<Departamento> departamentos;

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
		return LocalDate.now().compareTo(dataNascimento);
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


	public Set<Departamento> getDepartamentos() {
		return Collections.unmodifiableSet(departamentos);
	}

	public void adicionarDepartamento(Departamento departamento) {
		departamentos.add(departamento);
		departamento.addFuncionario(this);
	}
}
