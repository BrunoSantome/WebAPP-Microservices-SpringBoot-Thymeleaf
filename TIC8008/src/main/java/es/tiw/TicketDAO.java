package es.tiw;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface TicketDAO extends CrudRepository<Ticket, Long>{

	
	public List<Ticket> findAll();
	
	
	public Ticket findByidtickets(int idtickets);
	
	public List<Ticket> findByEvent(int idevento);
	

	@Query(value = "SELECT t FROM Ticket t WHERE  t.event.idevento = :idEvent")
	public List<Ticket> query1(@Param("idEvent")int idEvent);

	//("SELECT t FROM Ticket t WHERE t.evento.idevento = " + idEvent);
	//("SELECT t FROM Ticket t WHERE t.evento.idevento = " + idEvent)
}
