package br.com.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
