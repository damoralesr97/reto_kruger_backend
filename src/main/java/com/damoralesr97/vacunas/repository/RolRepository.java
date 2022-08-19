package com.damoralesr97.vacunas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damoralesr97.vacunas.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {

	public List<Rol> findByRolNombre(String rolNombre);

}
