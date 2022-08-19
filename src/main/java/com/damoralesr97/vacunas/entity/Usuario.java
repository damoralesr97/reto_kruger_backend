package com.damoralesr97.vacunas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_USUARIOS")
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Integer usrId;

    @Column(name = "usr_username")
    private String usrUsername;

    @Column(name = "usr_password")
    private String usrPassword;

    @OneToOne
    @JoinColumn(name = "usr_rol_id", referencedColumnName = "rol_id")
    private Rol rol;

	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public String getUsrUsername() {
		return usrUsername;
	}

	public void setUsrUsername(String usrUsername) {
		this.usrUsername = usrUsername;
	}

	public String getUsrPassword() {
		return usrPassword;
	}

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
    
    
}
