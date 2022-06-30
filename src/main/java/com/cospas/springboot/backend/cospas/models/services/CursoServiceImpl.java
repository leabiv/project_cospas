package com.cospas.springboot.backend.cospas.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cospas.springboot.backend.cospas.models.entity.Curso;
import com.cospas.springboot.backend.cospas.models.repository.ICursoRepository;

@Service
public class CursoServiceImpl implements ICursoService{

	@Autowired
	private ICursoRepository cursoRepository;
	
	@Override
	public List<Curso> findAll() {
		return (List<Curso>) cursoRepository.findAll();
	}

	@Override
	public Curso findById(Long id) {
		return cursoRepository.findById(id).orElse(null);
	}

	@Override
	public Curso save(Curso curso) {
		return cursoRepository.save(curso);
	}

	@Override
	public void delete(Long id) {
		cursoRepository.deleteById(id);
	}

}
