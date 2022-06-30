package com.cospas.springboot.backend.cospas.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.cospas.springboot.backend.cospas.models.entity.Curso;

public interface ICursoRepository extends CrudRepository<Curso, Long>{

}
