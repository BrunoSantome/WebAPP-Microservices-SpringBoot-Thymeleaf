package es.tiw;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class ControllerChat {
	@Autowired
	private MensajeDAO mensajeDAO;

	

	@RequestMapping(value="/mensajes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> openChat (
			@RequestParam(value = "emisor", required = true) int emisor,
			@RequestParam(value = "receptor", required = true) int receptor) {
	
		ResponseEntity <?> r =null;
		List <Mensaje> mensajes = mensajeDAO.findByEmisorAndReceptor(emisor,receptor);	
		//Nos interesan tambien las respuestas
		//List<Mensaje> mensajes2 = mensajeDAO.findByEmisorAndReceptor(receptor, emisor);
		
		if(mensajes.isEmpty()==true) {
			r = ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensajes);

		}else {
			
			 r = ResponseEntity.status(HttpStatus.OK).body(mensajes);
		}
		return r;
	}

	

				

		@RequestMapping(value = "/mensajes", method = RequestMethod.POST, 
				consumes=MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)				
				
		public ResponseEntity<?> savemsg(@RequestBody Mensaje m) {	
				ResponseEntity <?> r = null;

				if(m.getTexto().equals("")) {
					r =  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
					
				}else {
					Mensaje mensaje=mensajeDAO.save(m);
					r =  ResponseEntity.status(HttpStatus.CREATED).body(mensaje);

				}
			return r;
		}
		
	
		@RequestMapping(value = "/mensajes/{idmensaje}", method = RequestMethod.DELETE)
		
		public ResponseEntity<?> deletemsg(@PathVariable int idmensaje) {
			ResponseEntity <?> r = null;
			Mensaje m = mensajeDAO.findByIdmensaje(idmensaje);
			if (m != null) {
				mensajeDAO.deleteById(idmensaje);
				r = (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(m);
			}else {
				r = (ResponseEntity<?>) ResponseEntity.status(HttpStatus.NOT_FOUND);
			}
			return r;
		}
		
		@RequestMapping(value ="/mensajes/emisor/{emisor}",
				method = RequestMethod.GET, 		
				produces = MediaType.APPLICATION_JSON_VALUE)
		
		public ResponseEntity <?> getAllMensajesEmisor(@PathVariable int emisor) {
			
			ResponseEntity <?> r =null;		
			List <Mensaje> mensajes = mensajeDAO.findByEmisor(emisor);
			if(mensajes.isEmpty()==true) {
				 r = ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensajes);
			}else {
				 r = ResponseEntity.status(HttpStatus.OK).body(mensajes);

			}
			return r;
		}
		@RequestMapping(value ="/mensajes/receptor/{receptor}",
				method = RequestMethod.GET, 		
				produces = MediaType.APPLICATION_JSON_VALUE)
		
		public ResponseEntity <?> getAllMensajesReceptor(@PathVariable int receptor) {
			
			ResponseEntity <?> r =null;		
			List <Mensaje> mensajes = mensajeDAO.findByReceptor(receptor);
			if(mensajes.isEmpty()==true) {
				 r = ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensajes);
			}else {
				 r = ResponseEntity.status(HttpStatus.OK).body(mensajes);

			}
			return r;
		}
			
		
 
	
}
