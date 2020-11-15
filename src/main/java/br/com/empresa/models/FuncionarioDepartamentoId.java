package br.com.empresa.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FuncionarioDepartamentoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "funcionario_id")
	private Long funcionarioId;

	@Column(name = "departamento_id")
	private Long departamentoId;

	public FuncionarioDepartamentoId(Long funcionarioId, Long departamentoId) {
		this.funcionarioId = funcionarioId;
		this.departamentoId = departamentoId;
	}

	
	@SuppressWarnings("unused")
	private FuncionarioDepartamentoId() {
	}

	public Long getFuncionarioId() {
		return funcionarioId;
	}

	public Long getDepartamentoId() {
		return departamentoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departamentoId == null) ? 0 : departamentoId.hashCode());
		result = prime * result + ((funcionarioId == null) ? 0 : funcionarioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioDepartamentoId other = (FuncionarioDepartamentoId) obj;
		if (departamentoId == null) {
			if (other.departamentoId != null)
				return false;
		} else if (!departamentoId.equals(other.departamentoId))
			return false;
		if (funcionarioId == null) {
			if (other.funcionarioId != null)
				return false;
		} else if (!funcionarioId.equals(other.funcionarioId))
			return false;
		return true;
	}

}
