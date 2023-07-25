package es.tiw;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private UserDAO daous;


	// Save a user
	@RequestMapping(value="/usuarios", method=RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User userToRegister){
		ResponseEntity<User> response;
		User newUser = daous.save(userToRegister);
		if (newUser == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(newUser, HttpStatus.CREATED);
		}
		return response;
	}
	
	
	
	/// Realizar el login
	
	@RequestMapping(value = "/usuarios/login",method = RequestMethod.POST			)
	public ResponseEntity<?> login(	@RequestBody User aux) {
		
		
		String userName = aux.getUsuario();
		String password = aux.getPasswd();
		
		ResponseEntity <?> response = null;
		MensajeError error = new MensajeError("4", "El email o contraseña son incorrectos.");
		
		
		User u = daous.findByusuario(userName);
		if (u != null && u.getPasswd().equals(password)) {
			response = (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(u);
		} else {
			response = (ResponseEntity<?>) ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
		}
		return response;
	}
	
	@RequestMapping(value = "/users/{idUser}",method = RequestMethod.GET)
	public ResponseEntity<?> getUser(	@RequestBody @PathVariable int idUser) {
		
		ResponseEntity <?> response = null;

		User user = daous.findByidusuario(idUser);
		
		if(user==null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			response = ResponseEntity.status(HttpStatus.OK).body(user);
		}		
	
		return response;
	}
	
	// Find a user by their ID
		@RequestMapping(value="/usuarios/{idUser}", method=RequestMethod.GET)
		public ResponseEntity<User> getUser(@RequestParam Integer idUser ){

			User newUser = daous.findByidusuario(idUser);
			if (newUser == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newUser, HttpStatus.CREATED);
			}
		}
		// Get all users
		@RequestMapping(value="/usuarios", method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<List<User>> getUsers(){
			List<User> userList;
			userList = daous.findAll();
			System.out.println("Numero de usuarios totales: "+userList.size());

			return new ResponseEntity<>(userList, HttpStatus.OK);
		}



		// Remove an user
		// buscamos al usuario, lo borramos y cargamos en una lista el resto de usuarios para mostrarlos mas tarde
		@RequestMapping(value="/usuarios/remove/{idusuario}", method=RequestMethod.GET)
		public  @ResponseBody ResponseEntity <List<User>> removeUser(@PathVariable Integer idusuario){
			ResponseEntity <List<User>> response;
			List<User> userList = null;		
			User us = daous.findByidusuario(idusuario);

			if (us == null) {
				response = new ResponseEntity<>( userList,HttpStatus.NOT_FOUND);
			} else {
				daous.delete(us);
				userList = daous.findAll();
				response = new ResponseEntity<>( userList,HttpStatus.OK);
				System.out.println("Usuario "+us.getUsuario()+" fue borrado con exito");
			}
			return response;

		}	


		// Find a user by their username
		@RequestMapping(value="/usuarios/edit/{idusuario}")
		public  ResponseEntity <User> editUser(@PathVariable Integer idusuario){
			User newUser = daous.findByidusuario(idusuario);

			if (newUser == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newUser, HttpStatus.CREATED);
			}
		}	


		// Update a user withouth id
		@RequestMapping(value="/usuario", method = RequestMethod.PUT)
		public @ResponseBody ResponseEntity<User> updateUserWithouthId(@RequestBody User pUser) {
			ResponseEntity<User> response;
			User us = daous.findByidusuario(pUser.getIdusuario());
			if (us == null) {
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				us.setUsuario(pUser.getUsuario());
				us.setcompleteName(pUser.getcompleteName());
				us.setDirection(pUser.getDirection());
				us.setTelf(pUser.getTelf());
				us.setPermisos(pUser.getPermisos());
				us.setPasswd(pUser.getPasswd());
				response = new ResponseEntity<>(daous.save(us), HttpStatus.OK);
			}
			return response;
		}

		// ADD  User 
		@RequestMapping(value="/usuario/add", method = RequestMethod.PUT)
		public @ResponseBody ResponseEntity<User> addEvent(@RequestBody User user) throws ParseException {
			ResponseEntity<User> response;
			if (user.getIdusuario() == 0) {
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				response = new ResponseEntity<>(daous.save(user), HttpStatus.OK);
			}
			return response;
		}
	
}
