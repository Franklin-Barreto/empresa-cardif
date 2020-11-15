package br.com.empresa.models;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "funcionario_id")
	private Long id;
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

	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<FuncionarioDepartamento> departamentos;

	public Funcionario(String nome, LocalDate dataNascimento, String documento, Cargo cargo) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.documento = documento;
		this.cargo = cargo;
		this.departamentos = new HashSet<>();
	}

	public Funcionario() {
	}

	public Long getId() {
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

	public Set<FuncionarioDepartamento> getDepartamentos() {
		return Collections.unmodifiableSet(departamentos);
	}

	public void adicionarDepartamento(Departamento departamento) {
		FuncionarioDepartamento funcionarioDepartamento = new FuncionarioDepartamento(this, departamento);
		departamentos.add(funcionarioDepartamento);
	}

	@JsonIgnore
	public FuncionarioDepartamento getDepartamentoAtivo() {
		return departamentos.stream().filter(fd -> fd.getDataFim() == null).findFirst()
				.orElseThrow(() -> new RuntimeException("Não existe existe"));
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
