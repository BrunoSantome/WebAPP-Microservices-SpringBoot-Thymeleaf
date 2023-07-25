package es.tiw;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.tiw.*;


@Entity
@Table(name = "eventos")
public class Event implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="idevento")
	private int idevento;
	private String eventName;
	private String eventCategory;
	
	@Temporal(TemporalType.DATE)
	private Date eventDate;
	private String eventCity;
	private String eventRoom;
	
	@Lob
	private byte[] eventPicture;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="event",
			fetch = FetchType.EAGER)
	private List<Ticket> tickets;

	public Event() {
	}
	

	public Event(int idevento, String eventCategory, String eventCity, Date eventDate, String eventName,
			byte[] eventPicture, String eventRoom) {
		super();
		this.idevento = idevento;
		this.eventCategory = eventCategory;
		this.eventCity = eventCity;
		this.eventDate = eventDate;
		this.eventName = eventName;
		this.eventPicture = eventPicture;
		this.eventRoom = eventRoom;
	}

	public int getIdevento() {
		return this.idevento;
	}

	public void setIdevento(int idevento) {
		this.idevento = idevento;
	}

	public String getEventCategory() {
		return this.eventCategory;
	}

	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}

	public String getEventCity() {
		return this.eventCity;
	}

	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}

	public Date getEventDate() {
		return this.eventDate;
	}
	
	public void setEventDate(String eventDate) throws ParseException {
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);  
		this.eventDate = date1;
	}

	public void setEventDate(Date eventDate) throws ParseException {
		
		this.eventDate = eventDate;
	}

	
	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public byte[] getEventPicture() {
		return this.eventPicture;
	}

	public void setEventPicture(byte[] eventPicture) {
		this.eventPicture = eventPicture;
	}

	public String getEventRoom() {
		return this.eventRoom;
	}

	public void setEventRoom(String eventRoom) {
		this.eventRoom = eventRoom;
	}

	
}
