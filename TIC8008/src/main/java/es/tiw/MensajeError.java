package es.tiw;

import java.io.Serializable;

public class MensajeError implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String codigo;
	String mensaje;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public MensajeError(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	public MensajeError() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
