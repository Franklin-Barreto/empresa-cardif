package br.com.empresa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.empresa.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	@Query("SELECT f FROM Funcionario f JOIN FETCH f.cargo LEFT JOIN FETCH f.departamentos")
	List<Funcionario> findAll();
}
