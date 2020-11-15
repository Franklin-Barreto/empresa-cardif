package br.com.empresa.services;

import org.springframework.stereotype.Service;

import br.com.empresa.models.Departamento;
import br.com.empresa.models.Funcionario;
import br.com.empresa.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {

	private DepartamentoRepository departamentoRepository;
	private FuncionarioService funcionarioService;

	public DepartamentoService(DepartamentoRepository departamentoRepository, FuncionarioService funcionarioService) {
		this.departamentoRepository = departamentoRepository;
		this.funcionarioService = funcionarioService;
	}

	public Departamento definirChefe(Long departamentoId, Long funcionarioId) {
		Departamento departamento = departamentoRepository.findById(departamentoId)
				.orElseThrow(() -> new RuntimeException("Departamento inexistente"));
		Funcionario funcionario = funcionarioService.getFuncionarioById(funcionarioId);
		if (!funcionario.getDepartamentoAtivo().getDepartamento().equals(departamento)) {
			throw new RuntimeException("Funcionário não faz parte desse departamento");
		}
		departamento.setChefe(funcionario);
		return departamento;
	}
}
