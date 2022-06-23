package com.cospas.springboot.backend.cospas.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.cospas.springboot.backend.cospas.models.entity.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long>{

}
