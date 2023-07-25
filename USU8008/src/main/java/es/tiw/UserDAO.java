package es.tiw;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserDAO extends CrudRepository<User, Long>{

	
	public List<User> findAll();

	
	public User findByusuario(String usuario);
	
	public User findByidusuario(int idusuario);

	
}
