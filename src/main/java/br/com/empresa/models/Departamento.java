package br.com.empresa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "departamento_id")
	private Long id;

	@Column(name = "departamento_name", length = 50, nullable = false)
	private String nome;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "funcionario_fk")
	private Funcionario chefe;


	public Long getId() {
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

}
