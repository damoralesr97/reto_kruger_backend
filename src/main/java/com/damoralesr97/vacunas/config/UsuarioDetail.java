package com.damoralesr97.vacunas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.damoralesr97.vacunas.entity.Usuario;
import com.damoralesr97.vacunas.repository.UsuarioRepository;

@Service
public class UsuarioDetail implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsrUsername(username);
		String rol = usuario.getRol().getRolNombre().toUpperCase();
		return org.springframework.security.core.userdetails.User.withUsername(username)
				.password(usuario.getUsrPassword()).roles(rol).build();
	}

}
