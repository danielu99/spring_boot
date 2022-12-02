package com.formaciondbi.springboot.app.oauth.security.event;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.formaciondbi.springboot.app.commons.usuarios.models.entity.Usuario;
import com.formaciondbi.springboot.app.oauth.services.IUsuarioService;

import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		if (authentication.getName().equalsIgnoreCase("frontendapp")) {
			// if(authentication.getDetails() instanceof WebAuthenticationDetails) {
			return;
		}
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login:" + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		if(Objects.nonNull(usuario.getIntentos())&&usuario.getIntentos()>0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		String mensaje = "Error en el login "+exception.getMessage();
		System.out.println(mensaje);
		log.error(mensaje);
		
		try {
			StringBuilder errors = new StringBuilder();
			errors.append(mensaje);
			
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if(Objects.isNull(usuario.getIntentos())) {
				usuario.setIntentos(0);
			}
			
			log.info(String.format("Intentos actual es de %d", usuario.getIntentos()));
			usuario.setIntentos(usuario.getIntentos()+1);
			log.info(String.format("Intentos después es de %d", usuario.getIntentos()));
			
			errors.append(" - Intentos del login: "+usuario.getIntentos());
			
			if(usuario.getIntentos()>=3) {
				String errorMaxIntentos = String.format("El usuario %s ha sido deshabilitado por máximos intentos", usuario.getUsername());
				log.error(errorMaxIntentos);
				errors.append(" - "+errorMaxIntentos);
				usuario.setEnabled(Boolean.FALSE);
			}
			
			usuarioService.update(usuario, usuario.getId());
			
			tracer.currentSpan().tag("error.mensaje", errors.toString());
		}catch(FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}
	}

}
