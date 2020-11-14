package br.com.empresa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.empresa.models.Funcionario;
import br.com.empresa.services.FuncionarioService;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

	private final FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	@GetMapping
	public ResponseEntity<List<Funcionario>> findAll() {
		return ResponseEntity.ok(funcionarioService.findAll());
	}

	@PostMapping
	public ResponseEntity<Funcionario> save(Funcionario funcionario, UriComponentsBuilder uriBuilder) {
		Funcionario funcionarioSalvo = funcionarioService.save(funcionario);
		URI location = uriBuilder.pathSegment("funcionario/{id}").buildAndExpand(funcionarioSalvo.getId()).toUri();
		return ResponseEntity.created(location).body(funcionarioSalvo);
	}

}
