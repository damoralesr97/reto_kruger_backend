package com.damoralesr97.vacunas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damoralesr97.vacunas.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Usuario findByUsrUsername(String usrUsername);

}
