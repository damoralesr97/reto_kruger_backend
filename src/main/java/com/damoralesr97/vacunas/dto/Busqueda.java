package com.damoralesr97.vacunas.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.damoralesr97.vacunas.entity.EnumVacunas;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Busqueda {

	private Boolean estadoVacuna;
	@Enumerated(EnumType.STRING)
	private EnumVacunas tipoVacuna;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicio;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fechaFin;

	public Boolean getEstadoVacuna() {
		return estadoVacuna;
	}

	public void setEstadoVacuna(Boolean estadoVacuna) {
		this.estadoVacuna = estadoVacuna;
	}

	public EnumVacunas getTipoVacuna() {
		return tipoVacuna;
	}

	public void setTipoVacuna(EnumVacunas tipoVacuna) {
		this.tipoVacuna = tipoVacuna;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}
