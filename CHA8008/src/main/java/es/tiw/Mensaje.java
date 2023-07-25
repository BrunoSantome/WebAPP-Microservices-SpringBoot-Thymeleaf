package es.tiw;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje")
public class Mensaje implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idmensaje;
	private String texto;
	private int receptor;	
	private int emisor; 
	


	
	public Mensaje() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Mensaje(int idmensaje, String texto, int receptor, int emisor) {
		super();
		this.idmensaje=idmensaje;
		this.texto = texto;
		this.receptor = receptor;
		this.emisor = emisor;

	}
	
	public int getIdmensaje() {
		return idmensaje;
	}
	public void setIdmensaje(int idmensaje) {
		this.idmensaje = idmensaje;
	}

	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getReceptor() {
		return receptor;
	}
	public void setReceptor(int receptor) {
		this.receptor = receptor;
	}
	public int getEmisor() {
		return emisor;
	}
	public void setEmisor(int emisor) {
		this.emisor = emisor;
	}
	

}

