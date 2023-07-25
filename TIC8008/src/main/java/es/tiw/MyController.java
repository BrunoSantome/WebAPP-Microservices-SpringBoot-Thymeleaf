package es.tiw;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.tiw.*;

@Controller
@CrossOrigin
public class MyController {

	@Autowired
	private TicketDAO daotic;

	@RequestMapping(value = "/tickets/{IdEvent}", method = RequestMethod.GET)
	public ResponseEntity<?> getTicket(@RequestBody @PathVariable int IdEvent) {

		ResponseEntity<?> response = null;

		List<Ticket> tickets = (List<Ticket>) daotic.query1(IdEvent);
		List<Ticket> result = new ArrayList();

		if (tickets.isEmpty() == true) {
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(tickets);
		}
		return response;
	}

	// añadido responsebody para la paarte admin, si falla en user quitarlo
	@RequestMapping(value = "/tickets/add", method = RequestMethod.POST)
	public ResponseEntity<Ticket> saveTicket(@RequestBody @Validated Ticket ticketToRegister) {
		ResponseEntity<Ticket> response;

		Ticket newTicket = daotic.save(ticketToRegister);
		if (newTicket == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(newTicket, HttpStatus.CREATED);
		}
		return response;
	}

	@RequestMapping(value = "/admin/tickets/add", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Ticket> saveTicketAdmin(@RequestBody @Validated Ticket ticketToRegister) {
		ResponseEntity<Ticket> response;

		Ticket newTicket = daotic.save(ticketToRegister);
		if (newTicket == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(newTicket, HttpStatus.CREATED);
		}
		return response;
	}

	// Remove ticket
	// buscamos al ticket, lo borramos y cargamos en una lista el resto de tickets
	// para mostrarlos mas tarde
	@RequestMapping(value = "/tickets/remove/{idtickets}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Ticket>> removeTicket(@PathVariable Integer idtickets) {
		ResponseEntity<List<Ticket>> response;
		List<Ticket> ticketList = null;
		System.out.println(idtickets);
		Ticket tic = daotic.findByidtickets(idtickets);

		if (tic == null) {
			response = new ResponseEntity<>(ticketList, HttpStatus.NOT_FOUND);

		} else {
			int idevent = tic.getEvent().getIdevento();
			daotic.delete(tic);
			ticketList = daotic.query1(idevent);
			response = new ResponseEntity<>(ticketList, HttpStatus.OK);
		}
		return response;

	}

	// devuelve los datos de un ticket por su id
	@RequestMapping(value = "/ticket/{idtickets}", method = RequestMethod.GET)
	public ResponseEntity<?> getTicketById(@RequestBody @PathVariable int idtickets) {

		ResponseEntity<?> response = null;

		Ticket ticket = daotic.findByidtickets(idtickets);

		if (ticket == null) {
			response = (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND);
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(ticket);
		}
		return response;
	}

	// UPDATE TICKETS
	@RequestMapping(value = "/tickets/edit/", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) throws ParseException {
		ResponseEntity<Ticket> response;
		Ticket tic = daotic.findByidtickets(ticket.getIdtickets());
		System.out.println("imprimiendo evento:::: " + ticket.getIdtickets());
		if (tic == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			tic.setEvent(ticket.getEvent());
			tic.setNumbefOfTickets(ticket.getNumbefOfTickets());
			tic.setTicketsType(ticket.getTicketsType());
			tic.setTotalCost(ticket.getTotalCost());
			tic.setUsuario(ticket.getUsuario());
			response = new ResponseEntity<>(daotic.save(tic), HttpStatus.OK);
		}
		return response;
	}

}
