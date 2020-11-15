package br.com.empresa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.empresa.services.DepartamentoService;

@RestController
@RequestMapping("departamento")
public class DepartamentoController {

	DepartamentoService departamentoService;
}
