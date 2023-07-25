package es.tiw;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface EventoDAO extends CrudRepository<Event, Integer > {

	public List<Event> findAll();
	
	public Event findByidevento(int idevento);

	public List<Event> findByeventName(String name);
	public List<Event> findByeventCity(String city);
	public List<Event> findByeventCategory(String category);
	public List<Event> findByeventDate(Date date);
	public List<Event> findByeventRoom(String room);

	@Query (value="SELECT distinct e.eventCategory FROM Event e")
	public List <String> queryCategory();

	@Query(value = "SELECT e FROM Event e WHERE (:custName is null OR e.eventName LIKE :custName) AND (:custCity is null OR e.eventCity LIKE :custCity) AND (:custRoom is null OR e.eventRoom LIKE :custRoom) AND (:custCategory is null OR e.eventCategory LIKE :custCategory) ")
	public List<Event> queryAdvanced(@Param("custName")String custName,@Param("custCity") String custCity, @Param("custRoom") String custRoom, @Param("custCategory") String custCategory);

	@Query(value = "SELECT e FROM Event e WHERE (:custName is null OR e.eventName LIKE :custName) AND (:custCity is null OR e.eventCity LIKE :custCity) AND (:custRoom is null OR e.eventRoom LIKE :custRoom) AND (:custCategory is null OR e.eventCategory LIKE :custCategory) AND (:custDate is null OR e.eventDate LIKE :custDate)")
	public List<Event> queryAdvancedDate(@Param("custName")String custName,@Param("custCity") String custCity, @Param("custRoom") String custRoom, @Param("custCategory") String custCategory,  @Param("custDate") Date custDate);


	
	
}
