package es.tiw;

import java.io.Serializable;


import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.tiw.*;


@Entity
@Table(name = "tickets")
public class Ticket implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idtickets")
	private int idtickets;

	private int numbefOfTickets;

	private String ticketsType;

	private int totalCost;

	//bi-directional many-to-one association to Evento
	@ManyToOne 
	@JoinColumn(name="idevento")
	private Event event;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne 
	@JoinColumn(name="idusuario")
	private User usuario;
	
	public Ticket() {
	}
	
	public Ticket(int idtickets, int numbefOfTickets, String ticketsType, int totalCost, Event evento,
			User usuario) {
		super();
		this.idtickets = idtickets;
		this.numbefOfTickets = numbefOfTickets;
		this.ticketsType = ticketsType;
		this.totalCost = totalCost;
		this.event = evento;
		this.usuario = usuario;
	}


	public int getIdtickets() {
		return this.idtickets;
	}

	public void setIdtickets(int idtickets) {
		this.idtickets = idtickets;
	}

	public int getNumbefOfTickets() {
		return this.numbefOfTickets;
	}

	public void setNumbefOfTickets(int numbefOfTickets) {
		this.numbefOfTickets = numbefOfTickets;
	}

	public String getTicketsType() {
		return this.ticketsType;
	}

	public void setTicketsType(String ticketsType) {
		this.ticketsType = ticketsType;
	}

	public int getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event evento) {
		this.event = evento;
	}

	public User getUsuario() {
		return this.usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

}