package com.formaciondbi.springboot.app.oauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formaciondbi.springboot.app.commons.usuarios.models.entity.Usuario;

@FeignClient(name="servicio-usuario")
public interface UsuarioFeignClient {
	
	@GetMapping("/usuarios/search/buscar-username")
	public Usuario findByUsername(@RequestParam String username);
	
}
