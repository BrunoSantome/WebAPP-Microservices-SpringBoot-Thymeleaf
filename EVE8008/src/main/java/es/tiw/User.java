package es.tiw;

import java.io.Serializable;


import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.tiw.*;


@Entity
@Table(name = "usuarios")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="idusuario")
	private int idusuario;
	private String usuario;
	
	@Column(name="completeName")
	private String completeName;
	private String passwd;
	private String direction;
	private String telf;
	private String permisos;
	@OneToMany(mappedBy="usuario",
			fetch = FetchType.EAGER)//perfe
	private List<Ticket> tickets;

	
	public User(int iduser, String user, String completeName, String password, String direction, String phone, String permisos) {
		super();
		this.idusuario = iduser;
		this.usuario = user;
		this.completeName = completeName;
		this.passwd = password;
		this.direction = direction;
		this.telf = phone;
		this.permisos=permisos;
	}
	
	
	
	public User() {
	
	}

	public String getPermisos() {
		return permisos;
	}


	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}


	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int iduser) {
		this.idusuario = iduser;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String user) {
		this.usuario = user;
	}

	public String getcompleteName() {
		return completeName;
	}

	public void setcompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String password) {
		this.passwd = password;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String phone) {
		this.telf = phone;
	}
}

