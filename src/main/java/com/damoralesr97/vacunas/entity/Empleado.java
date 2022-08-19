package com.damoralesr97.vacunas.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "T_EMPLEADOS")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Integer empId;

	@NotNull(message = "El numero de cedula es requerido")
	@Column(name = "emp_cedula", length = 10, unique = true)
	@Size(min = 10, max = 10, message = "La cedula debe tener 10 digitos")
	@Pattern(regexp = "^[0-9]{10}$", message = "La cedula debe tener 10 digitos")
	private String empCedula;

	@NotNull(message = "El nombre es requerido")
	@Column(name = "emp_nombres")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre debe tener solo letras")
	private String empNombres;

	@NotNull(message = "El apellido es requerido")
	@Column(name = "emp_apellidos")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "El apellido debe tener solo letras")
	private String empApellidos;

	@NotNull(message = "El email es requerido")
	@Column(name = "emp_email")
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "El email ingresado no es valido")
	private String empEmail;

	@Column(name = "emp_fecha_nac")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date empFechaNac;

	@Column(name = "emp_telefono")
	@Pattern(regexp = "^[0-9]{10}$", message = "El telefono debe tener 10 digitos")
	private String empTelefono;

	@Column(name = "emp_direccion")
	private String empDireccion;

	@Column(name = "emp_estado_vacuna")
	private Boolean empEstadoVacuna;

	@JsonManagedReference
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Dosis> dosis;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "emp_usr_id", referencedColumnName = "usr_id")
	private Usuario usuario;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpCedula() {
		return empCedula;
	}

	public void setEmpCedula(String empCedula) {
		this.empCedula = empCedula;
	}

	public String getEmpNombres() {
		return empNombres;
	}

	public void setEmpNombres(String empNombres) {
		this.empNombres = empNombres;
	}

	public String getEmpApellidos() {
		return empApellidos;
	}

	public void setEmpApellidos(String empApellidos) {
		this.empApellidos = empApellidos;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public Date getEmpFechaNac() {
		return empFechaNac;
	}

	public void setEmpFechaNac(Date empFechaNac) {
		this.empFechaNac = empFechaNac;
	}

	public String getEmpTelefono() {
		return empTelefono;
	}

	public void setEmpTelefono(String empTelefono) {
		this.empTelefono = empTelefono;
	}

	public String getEmpDireccion() {
		return empDireccion;
	}

	public void setEmpDireccion(String empDireccion) {
		this.empDireccion = empDireccion;
	}

	public Boolean getEmpEstadoVacuna() {
		return empEstadoVacuna;
	}

	public void setEmpEstadoVacuna(Boolean empEstadoVacuna) {
		this.empEstadoVacuna = empEstadoVacuna;
	}

	public List<Dosis> getDosis() {
		return dosis;
	}

	public void setDosis(List<Dosis> dosis) {
		this.dosis = dosis;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Empleado [empId=" + empId + ", empCedula=" + empCedula + ", empNombres=" + empNombres
				+ ", empApellidos=" + empApellidos + ", empEmail=" + empEmail + ", empFechaNac=" + empFechaNac
				+ ", empTelefono=" + empTelefono + ", empDireccion=" + empDireccion + ", empEstadoVacuna="
				+ empEstadoVacuna + ", dosis=" + dosis + ", usuario=" + usuario + "]";
	}

}
