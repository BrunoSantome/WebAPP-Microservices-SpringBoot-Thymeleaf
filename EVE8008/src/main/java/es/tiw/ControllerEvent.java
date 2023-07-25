package es.tiw;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ControllerEvent {
	
	@Autowired
	private EventoDAO eventdao;
	
	
	//Registrar un evento nuevo
		@RequestMapping(value="/eventos/register", method=RequestMethod.POST)
		public ResponseEntity<Event> saveEvent(@RequestBody Event eventoToRegister){
			ResponseEntity<Event> response;
			Event newEvent = eventdao.save(eventoToRegister);
			if (newEvent == null) {
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				response = new ResponseEntity<>(newEvent, HttpStatus.CREATED);
			}
			return response;
		}
		

		//OBTENER TODOS LOS Eventos
			@RequestMapping(value="/eventos", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity <List<Event>> getAllProducto(){
				ResponseEntity <List<Event>> r =null;		
					List<Event> eventos= (List<Event>) eventdao.findAll();		
					 r = ResponseEntity.status(HttpStatus.OK).body(eventos);
					
				return r;
			}
		
		
	//BUSQUEDA AVANZADA
		@RequestMapping(value="/eventos/busqueda", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity <List<Event>> advancedSearch(@RequestParam(value = "eventName", required = false) String eventName, @RequestParam(value = "eventCity", required = false) String eventCity,
				@RequestParam(value = "eventCategory", required = false) String eventCategory, @RequestParam(value = "eventRoom", required = false) String eventRoom,
				@RequestParam(value = "eventDate", required = false) String eventDate) throws ParseException{
			ResponseEntity <List<Event>> r =null;	
				
			List<Event> eventos =  new ArrayList<Event>();
			
			//forzamos los null
			if( eventName.equals("")) {
				eventName = null;
			}
			if( eventCity.equals("")) {
				eventCity = null;

			}
			if( eventCategory.equals("")) {
				eventCategory = null;

			}
			if( eventRoom.equals("")) {
				eventRoom = null;

			}
			
			
			if ( !eventDate.equals("")) {
					 Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);  
					eventos= (List<Event>) eventdao.queryAdvancedDate(eventName, eventCity, eventRoom, eventCategory, date1);
			}else {
				
					eventos= (List<Event>) eventdao.queryAdvanced(eventName, eventCity, eventRoom, eventCategory);
			}
				
				 r = ResponseEntity.status(HttpStatus.OK).body(eventos);
				
			return r;
		}
		//OBTENER LAS CATEGORIAS
				@RequestMapping(value="/eventos/categorias", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity <List <String>> getAllCategories(){
					ResponseEntity <List <String>> r =null;		
					List <String>categorias= (List <String>) eventdao.queryCategory();		
						 r = ResponseEntity.status(HttpStatus.OK).body(categorias);
						 
					return r;
				}
				
				//obtener evento por id
				@RequestMapping(value = "/event/{idEvent}",method = RequestMethod.GET)
				public ResponseEntity<?> getEvent(	@RequestBody @PathVariable int idEvent) {
					
					ResponseEntity <?> response = null;

					Event event = eventdao.findByidevento(idEvent);
										
					if(event==null) {
						response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}else {
						response = ResponseEntity.status(HttpStatus.OK).body(event);
					}		
				
					return response;
				}
				// Remove an event
				// buscamos al evento, lo borramos y cargamos en una lista el resto de eventos para mostrarlos mas tarde
				@RequestMapping(value="/eventos/remove/{idevento}", method=RequestMethod.GET)
				public  @ResponseBody ResponseEntity <List<Event>> removeUser(@PathVariable Integer idevento){
					ResponseEntity <List<Event>> response;
					List<Event> eventList = null;		
					System.out.println(idevento);
					Event ev = eventdao.findByidevento(idevento);

					if (ev == null) {
						response = new ResponseEntity<>( eventList,HttpStatus.NOT_FOUND);
						System.out.println("Evento no encontrado");
						
					} else {
						eventdao.delete(ev);
						eventList = eventdao.findAll();
						response = new ResponseEntity<>( eventList,HttpStatus.OK);
						System.out.println("Evento "+ev.getEventName()+" fue borrado con exito");
					}
					return response;

				}	

				// Find a user by their username
				@RequestMapping(value="/eventos/edit/{idevento}")
				public  ResponseEntity <Event> editEvent(@PathVariable Integer idevento){
					System.out.println("Evento a buscar es: "+idevento);
					Event newEvent = eventdao.findByidevento(idevento);

					if (newEvent == null) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else {
						return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
					}
				}	
				
				// Update a event withouth id
				@RequestMapping(value="/evento/", method = RequestMethod.PUT)
				public @ResponseBody ResponseEntity<Event> updateUserWithouthId(@RequestBody Event pEvent) throws ParseException, Exception {
					
					ResponseEntity<Event> response;
					Event ev = eventdao.findByidevento(pEvent.getIdevento());
					if (ev == null) {
						response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else {
						
						ev.setEventDate(pEvent.getEventDate());
						ev.setEventName(pEvent.getEventName());
						ev.setEventCategory(pEvent.getEventCategory());
						ev.setEventRoom(pEvent.getEventRoom());
						ev.setEventCity(pEvent.getEventCity());
						response = new ResponseEntity<>(eventdao.save(ev), HttpStatus.OK);
					}
					return response;
				}
						
				// ADD  event 
				@RequestMapping(value="/evento/add", method = RequestMethod.PUT)
				public @ResponseBody ResponseEntity<Event> addEvent(@RequestBody Event pEvent) throws ParseException {
					ResponseEntity<Event> response;
					if (pEvent.getIdevento() == 0) {
						response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} else {
						
						response = new ResponseEntity<>(eventdao.save(pEvent), HttpStatus.OK);
					}
					return response;
				}

				
	
}
