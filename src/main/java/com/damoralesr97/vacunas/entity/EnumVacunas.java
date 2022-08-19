package com.damoralesr97.vacunas.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumVacunas {

	SPUTNIK("Sputnik"), ASTRAZENECA("AstraZeneca"), PFIZER("Pfizer"), JHONSONYJHONSON("Johnson&Johnson");

	private String nombreVacuna;

	EnumVacunas(String name) {
		this.nombreVacuna = name;
	}

	public String getNombreVacuna() {
		return nombreVacuna;
	}

	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}

}
