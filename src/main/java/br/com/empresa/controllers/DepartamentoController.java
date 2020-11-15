package br.com.empresa.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.models.Departamento;
import br.com.empresa.models.dtos.ChefeDepartamentoDto;
import br.com.empresa.services.DepartamentoService;

@RestController
@RequestMapping("departamento")
public class DepartamentoController {

	private final DepartamentoService departamentoService;

	public DepartamentoController(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}
	
	@PutMapping("/{departamentoId}/chefe")
	public ResponseEntity<Departamento> salvarChefeDepartamento(@PathVariable Long departamentoId, @RequestBody ChefeDepartamentoDto chefe){
		Departamento departamento = departamentoService.definirChefe(departamentoId, chefe.getIdChefe());
		return ResponseEntity.ok(departamento);
	}
}
