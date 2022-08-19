package com.damoralesr97.vacunas.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "T_DOSIS")
public class Dosis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dos_id")
	private Integer dosId;

	@Column(name = "dos_numero")
	private int dosNumero;

	@Column(name = "dos_vacuna")
	@Enumerated(EnumType.STRING)
	private EnumVacunas dosVacuna;

	@Column(name = "dos_fecha")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dosFecha;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Empleado.class)
	@JoinColumn(name = "dos_emp_id", referencedColumnName = "emp_id")
	private Empleado empleado;

	public Integer getDosId() {
		return dosId;
	}

	public void setDosId(Integer dosId) {
		this.dosId = dosId;
	}

	public int getDosNumero() {
		return dosNumero;
	}

	public void setDosNumero(int dosNumero) {
		this.dosNumero = dosNumero;
	}

	public EnumVacunas getDosVacuna() {
		return dosVacuna;
	}

	public void setDosVacuna(EnumVacunas dosVacuna) {
		this.dosVacuna = dosVacuna;
	}

	public Date getDosFecha() {
		return dosFecha;
	}

	public void setDosFecha(Date dosFecha) {
		this.dosFecha = dosFecha;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}
