package br.com.empresa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.empresa.models.Funcionario;
import br.com.empresa.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	private final FuncionarioRepository funcionarioRepository;

	public FuncionarioService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	public Funcionario save(Funcionario funcionario) {

		return funcionarioRepository.save(funcionario);
	}
}
