package br.com.empresa.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.empresa.models.Cargo;
import br.com.empresa.models.Departamento;
import br.com.empresa.models.Funcionario;
import br.com.empresa.models.dtos.DepartamentoHistoricoDto;
import br.com.empresa.models.dtos.FuncionarioDto;
import br.com.empresa.models.dtos.FuncionarioHistoricoDto;
import br.com.empresa.repositories.DepartamentoRepository;
import br.com.empresa.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	private final FuncionarioRepository funcionarioRepository;
	private final DepartamentoRepository departamentoRepository;
	private final CargoService cargoService;

	public FuncionarioService(FuncionarioRepository funcionarioRepository, CargoService cargoService,
			DepartamentoRepository departamentoRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoService = cargoService;
		this.departamentoRepository = departamentoRepository;
	}

	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	public Funcionario save(FuncionarioDto funcionarioDto) {
		Funcionario funcionario = fromDto(funcionarioDto);
		return funcionarioRepository.save(funcionario);
	}

	public Funcionario fromDto(FuncionarioDto funcionarioDto) {
		Departamento departamento = departamentoRepository.findById(funcionarioDto.getDepartamento())
				.orElseThrow(() -> new RuntimeException("Departamento inexistente"));
		Cargo cargo = cargoService.findById(funcionarioDto.getCargo());
		Funcionario funcionario = new Funcionario(funcionarioDto.getNome(), funcionarioDto.getDataNascimento(),
				funcionarioDto.getDocumento(), cargo);
		funcionario.adicionarDepartamento(departamento);
		return funcionario;
	}

	public List<Funcionario> findAllByDepartamento(Long id) {
		return funcionarioRepository.findAllByDepartamentosDepartamentoId(id);
	}

	public Funcionario update(Long id, FuncionarioDto funcionarioDto) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Funcionário inexistente"));
		Cargo cargo = funcionario.getCargo();
		Departamento departamento = funcionario.getDepartamentoAtivo().getDepartamento();

		if (departamento.getId() != funcionarioDto.getDepartamento()) {
			funcionario.getDepartamentoAtivo().setDataFim();
			departamento = departamentoRepository.findById(funcionarioDto.getDepartamento()).get();
			funcionario.adicionarDepartamento(departamento);

		}
		if (funcionarioDto.getCargo() != cargo.getId()) {
			cargo = cargoService.findById(funcionarioDto.getCargo());
		}
		funcionario.setCargo(cargo);

		return funcionarioRepository.save(funcionario);
	}

	public Funcionario getFuncionarioById(Long id) {
		return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionarário inexistente"));
	}

	public FuncionarioHistoricoDto getHistoricoDepartamentos(Long id) {
		Funcionario funcionario = getFuncionarioById(id);
		List<DepartamentoHistoricoDto> departamentoHistorico = new ArrayList<>();

		funcionario.getDepartamentos().stream().forEach((d) -> {
			departamentoHistorico.add(
					new DepartamentoHistoricoDto(d.getDepartamento().getNome(), d.getDataInicio(), d.getDataFim()));
		});

		FuncionarioHistoricoDto funcHistorico = new FuncionarioHistoricoDto(funcionario.getId(), funcionario.getNome(),
				departamentoHistorico);

		return funcHistorico;
	}
}
