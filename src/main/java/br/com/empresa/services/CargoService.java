package br.com.empresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.empresa.models.Cargo;
import br.com.empresa.repositories.CargoRepository;

@Service
public class CargoService {

	private final CargoRepository cargoRepository;

	@Autowired
	public CargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public Cargo save(Cargo cargo) {

		return cargoRepository.save(cargo);
	}

	public List<Cargo> findAll() {
		return (List<Cargo>) cargoRepository.findAll();
	}
	
	public Cargo findById(Long id) {
		return cargoRepository.findById(id).orElseThrow(()->new RuntimeException("Cargo inexistente"));
	}
}
