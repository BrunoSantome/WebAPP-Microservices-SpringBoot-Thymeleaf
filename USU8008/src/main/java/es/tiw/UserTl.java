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
public class UserTl implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name="idusuario")
	private int idusuario;
	private String usuario;
	
	private String completeName;
	private String passwd;
	private String direction;
	private String telf;
	private String permisos;
	@OneToMany(mappedBy="usuario",
			fetch = FetchType.EAGER)
	private List<Ticket> tickets;

	
	public UserTl(int idusuario, String user, String completeName, String password, String direction, String telf, String permisos) {
		super();
		this.idusuario = idusuario;
		this.usuario = user;
		this.completeName = completeName;
		this.passwd = password;
		this.direction = direction;
		this.telf = telf;
		this.permisos=permisos;
	}
	
	
	
	public UserTl() {
	
	}

	public String getPermisos() {
		return permisos;
	}


	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}


	public int getidusuario() {
		return idusuario;
	}

	public void setidusuario(int iduser) {
		this.idusuario = iduser;
	}

	public String getusuario() {
		return usuario;
	}

	public void setusuario(String user) {
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

	public String gettelf() {
		return telf;
	}

	public void settelf(String phone) {
		this.telf = phone;
	}
}

