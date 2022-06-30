package com.cospas.springboot.backend.cospas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cospas.springboot.backend.cospas.models.entity.Usuario;
import com.cospas.springboot.backend.cospas.models.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuarios")
	public List<Usuario> listarUsuario() {
		return usuarioService.findAll();
	}

	//@ResponseEntity<?> nos permitira verificar si el usuario existe y nos mostrata el error 404
	
	/*@GetMapping("/usuarios/{id}")
	public Usuario showUsuario(@PathVariable Long id) {
		return usuarioService.findById(id);
	}
	*/
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> showUsuario(@PathVariable Long id){
		Usuario usuario = usuarioService.findById(id);
		
		Map<String, Object>response = new HashMap<>();
		
		if(usuario==null) {
			response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe en el sistema.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	/*
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario createUsuario(@RequestBody Usuario usuario) {
		return usuarioService.save(usuario);
	}
	*/
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
		Usuario UsuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			UsuarioNew = usuarioService.save(usuario);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la insercion en el sistema");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Usuario ha sido creado con exito");
		response.put("usuario", UsuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/*
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario updateUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario currentUser = usuarioService.findById(id);
		currentUser.setNombres(usuario.getNombres());
		currentUser.setApellidos(usuario.getApellidos());
		currentUser.setEmail(usuario.getEmail());
		currentUser.setNumeroDocumento(usuario.getNumeroDocumento());
		return usuarioService.save(currentUser);
	}
	*/
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
		Usuario currentUser = usuarioService.findById(id);
		
		Usuario usuarioUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		if(currentUser==null) {
			response.put("mensaje", "Error: no se puedo editar, usuario ID: ".concat(id.toString().concat(" no existe en el sistema.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
		try {
			currentUser.setNombres(usuario.getNombres());
			currentUser.setApellidos(usuario.getApellidos());
			currentUser.setEmail(usuario.getEmail());
			currentUser.setNumeroDocumento(usuario.getNumeroDocumento());
			
			usuarioUpdate =  usuarioService.save(currentUser);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en el sistema");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		response.put("mensaje", "El Usuario ha sido actualizado con exito");
		response.put("usuario", usuarioUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	/*
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUsuario(@PathVariable Long id) {
		usuarioService.delete(id);
	}
	*/
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usaurio en el sistema");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario eliminar con exito el sistema");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
