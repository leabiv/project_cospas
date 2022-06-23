package com.cospas.springboot.backend.cospas.models.services;

import java.util.List;

import com.cospas.springboot.backend.cospas.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario>findAll();
	public Usuario findById(Long id);
	public Usuario save(Usuario usuario);
	public void delete(Long id);
}
