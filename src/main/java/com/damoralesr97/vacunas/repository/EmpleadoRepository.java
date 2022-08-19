package com.damoralesr97.vacunas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.damoralesr97.vacunas.entity.Empleado;
import com.damoralesr97.vacunas.entity.EnumVacunas;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	public List<Empleado> findByEmpEstadoVacuna(Boolean empEstadoVacuna);

	public List<Empleado> findByDosisDosVacuna(EnumVacunas dosVacuna);

	public List<Empleado> findByDosisDosFechaBetween(Date fechaInicio, Date fechaFin);

}
