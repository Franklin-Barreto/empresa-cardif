package br.com.empresa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.empresa.models.Funcionario;
import br.com.empresa.models.dtos.FuncionarioDto;
import br.com.empresa.models.dtos.FuncionarioHistoricoDto;
import br.com.empresa.services.FuncionarioService;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

	private final FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@GetMapping
	public ResponseEntity<List<Funcionario>> listarTodosOsFuncionariosAtivos() {
		return ResponseEntity.ok(funcionarioService.findAll());
	}

	@PostMapping
	public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody FuncionarioDto funcionarioDto,
			UriComponentsBuilder uriBuilder) {
		Funcionario funcionarioSalvo = funcionarioService.save(funcionarioDto);
		URI location = getLocation(uriBuilder, "funcionario/{id}", funcionarioSalvo.getId());
		return ResponseEntity.created(location).body(funcionarioSalvo);
	}

	@GetMapping("/departamento/{id}")
	public ResponseEntity<List<Funcionario>> listarFuncionariosPorDepartamento(@PathVariable("id") Long id) {
		return ResponseEntity.ok(funcionarioService.findAllByDepartamento(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioDto funcionarioDto,
			UriComponentsBuilder uriBuilder) {
		return ResponseEntity.ok(funcionarioService.update(id, funcionarioDto));
	}

	@GetMapping("/departamento/historico/{id}")
	public ResponseEntity<FuncionarioHistoricoDto> listarHistoricoDepartamentosFuncionario(@PathVariable Long id) {
		FuncionarioHistoricoDto historico = funcionarioService.getHistoricoDepartamentos(id);
		return ResponseEntity.ok(historico);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerFuncionario(@PathVariable Long id) {
		funcionarioService.demitir(id);
		return ResponseEntity.ok().build();
	}

	private URI getLocation(UriComponentsBuilder uriBuilder, String path, Long id) {
		URI location = uriBuilder.pathSegment(path).buildAndExpand(id).toUri();
		return location;
	}

}
