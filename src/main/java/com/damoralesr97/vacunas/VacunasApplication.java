package com.damoralesr97.vacunas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.damoralesr97.vacunas.entity.Usuario;
import com.damoralesr97.vacunas.repository.RolRepository;
import com.damoralesr97.vacunas.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@SecurityScheme(name = "basicAuth", // can be set to anything
		type = SecuritySchemeType.HTTP, scheme = "basic", in = SecuritySchemeIn.HEADER)
public class VacunasApplication implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(VacunasApplication.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(VacunasApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Usuario ususario = new Usuario();
		if (usuarioRepository.findByUsrUsername("admin") != null) {
			log.info("User admin already exists");
		} else {
			ususario.setUsrUsername("admin");
			ususario.setUsrPassword(passwordEncoder.encode("admin"));
			ususario.setRol(rolRepository.findByRolNombre("admin").get(0));
			usuarioRepository.save(ususario);
		}

	}

}
