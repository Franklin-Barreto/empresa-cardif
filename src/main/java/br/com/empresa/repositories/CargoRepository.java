package br.com.empresa.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.empresa.models.Cargo;

public interface CargoRepository extends CrudRepository<Cargo, Long> {

}
