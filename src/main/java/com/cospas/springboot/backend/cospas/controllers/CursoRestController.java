package com.cospas.springboot.backend.cospas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cospas.springboot.backend.cospas.models.entity.Curso;
import com.cospas.springboot.backend.cospas.models.services.ICursoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CursoRestController {

	@Autowired
	private ICursoService cursoService;
	
	@GetMapping("/cursos")
	public List<Curso> listarCurso(){
		return cursoService.findAll();
	}
}
