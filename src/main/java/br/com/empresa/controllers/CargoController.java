package br.com.empresa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.empresa.models.Cargo;
import br.com.empresa.services.CargoService;

@RestController
@RequestMapping("cargo")
public class CargoController {

	private final CargoService cargoService;

	@Autowired
	public CargoController(CargoService cargoService) {
		this.cargoService = cargoService;
	}

	@PostMapping
	public ResponseEntity<Cargo> save(@RequestBody Cargo cargo, UriComponentsBuilder uriBuilder) {
		Cargo save = cargoService.save(cargo);
		URI location = uriBuilder.pathSegment("cargo/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(location).body(save);
	}
	
	@GetMapping
	public ResponseEntity<List<Cargo>> findAll(){
		return ResponseEntity.ok(cargoService.findAll());
	}
}
