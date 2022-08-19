package com.damoralesr97.vacunas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.damoralesr97.vacunas.dto.Busqueda;
import com.damoralesr97.vacunas.entity.Dosis;
import com.damoralesr97.vacunas.entity.Empleado;
import com.damoralesr97.vacunas.entity.Usuario;
import com.damoralesr97.vacunas.repository.EmpleadoRepository;
import com.damoralesr97.vacunas.repository.RolRepository;

@Service
public class EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	public Empleado save(Empleado empleado) {
		Usuario usuario = new Usuario();
		String username = empleado.getEmpNombres().split(" ")[0].toLowerCase().charAt(0)
				+ empleado.getEmpApellidos().split(" ")[0].toLowerCase();
		usuario.setUsrUsername(username);
		usuario.setUsrPassword(passwordEncoder.encode(empleado.getEmpCedula()));
		usuario.setRol(rolRepository.findByRolNombre("user").get(0));
		empleado.setUsuario(usuario);
		return empleadoRepository.save(empleado);
	}

	public Empleado adminActualizarEmpleado(Integer id, Empleado empleadoNuevo) throws Exception {
		Optional<Empleado> empleado = empleadoRepository.findById(id);

		if (empleado.get() == null)
			throw new Exception("El empleado que intenta actualizar no existe");

		if (empleadoNuevo.getEmpEstadoVacuna() != null)
			if (empleadoNuevo.getEmpEstadoVacuna() == true
					&& (empleadoNuevo.getDosis() == null || empleadoNuevo.getDosis().size() == 0))
				throw new Exception("Debe indicar informacion sobre las vacunas");

		empleado.get().setEmpCedula(
				empleadoNuevo.getEmpCedula() == null ? empleado.get().getEmpCedula() : empleadoNuevo.getEmpCedula());
		empleado.get().setEmpNombres(
				empleadoNuevo.getEmpNombres() == null ? empleado.get().getEmpNombres() : empleadoNuevo.getEmpNombres());
		empleado.get().setEmpApellidos(empleadoNuevo.getEmpApellidos() == null ? empleado.get().getEmpApellidos()
				: empleadoNuevo.getEmpApellidos());
		empleado.get().setEmpEmail(
				empleadoNuevo.getEmpEmail() == null ? empleado.get().getEmpEmail() : empleadoNuevo.getEmpEmail());
		empleado.get().setEmpFechaNac(empleadoNuevo.getEmpFechaNac() == null ? empleado.get().getEmpFechaNac()
				: empleadoNuevo.getEmpFechaNac());
		empleado.get().setEmpDireccion(empleadoNuevo.getEmpDireccion() == null ? empleado.get().getEmpDireccion()
				: empleadoNuevo.getEmpDireccion());
		empleado.get().setEmpTelefono(empleadoNuevo.getEmpTelefono() == null ? empleado.get().getEmpTelefono()
				: empleadoNuevo.getEmpTelefono());
		empleado.get()
				.setEmpEstadoVacuna(empleadoNuevo.getEmpEstadoVacuna() == null ? empleado.get().getEmpEstadoVacuna()
						: empleadoNuevo.getEmpEstadoVacuna());

		if (empleadoNuevo.getDosis() != null)
			for (Dosis d : empleadoNuevo.getDosis()) {
				d.setEmpleado(empleado.get());
				empleado.get().getDosis().add(d);
			}

		empleadoRepository.save(empleado.get());
		return empleado.get();
	}

	public Empleado eliminarEmpleado(Integer id) {
		Optional<Empleado> empleado = empleadoRepository.findById(id);
		empleadoRepository.delete(empleado.get());
		return empleado.get();
	}

	public Empleado me(Integer id) {
		return empleadoRepository.findById(id).get();
	}

	public List<Empleado> busquedaFiltros(Busqueda busqueda) throws Exception {
		if (busqueda.getEstadoVacuna() != null) {
			return empleadoRepository.findByEmpEstadoVacuna(busqueda.getEstadoVacuna());
		} else if (busqueda.getTipoVacuna() != null) {
			return empleadoRepository.findByDosisDosVacuna(busqueda.getTipoVacuna());
		} else if (busqueda.getFechaInicio() != null && busqueda.getFechaFin() != null) {
			return empleadoRepository.findByDosisDosFechaBetween(busqueda.getFechaInicio(), busqueda.getFechaFin());
		}
		throw new Exception("Ingrese un criterio de busqueda");
	}

}
