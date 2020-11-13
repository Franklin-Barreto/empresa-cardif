package br.com.empresa.models;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cargo_id")
	private Integer id;

	@Column(name = "cargo_name", length = 50, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "cargo")
	private Set<Funcionario> funcionarios;

	public Cargo() {
	}

	public Cargo(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Set<Funcionario> getFuncionarios() {
		return Collections.unmodifiableSet(funcionarios);
	}

	public void addFuncionario(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
		funcionario.setCargo(this);
	}
}
