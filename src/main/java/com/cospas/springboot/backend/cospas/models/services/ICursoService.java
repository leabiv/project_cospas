package com.cospas.springboot.backend.cospas.models.services;

import java.util.List;

import com.cospas.springboot.backend.cospas.models.entity.Curso;

public interface ICursoService {

	public List<Curso> findAll();

	public Curso findById(Long id);

	public Curso save(Curso curso);

	public void delete(Long id);
}
