package br.com.empresa.models;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "departamento_id")
	private Integer id;

	@Column(name = "departamento_name", length = 50, nullable = false)
	private String nome;

	@OneToOne
	@JoinColumn(name = "funcionario_fk")
	private Funcionario chefe;

	@ManyToMany(mappedBy = "departamentos")
	private Set<Funcionario> funcionarios;

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Funcionario getChefe() {
		return chefe;
	}

	public void setChefe(Funcionario chefe) {
		this.chefe = chefe;
	}

	public Set<Funcionario> getFuncionarios() {
		return Collections.unmodifiableSet(funcionarios);
	}

	public void addFuncionario(Funcionario funcionario) {
		this.funcionarios.add(funcionario);
		funcionario.adicionarDepartamento(this);
	}
}
