package es.tiw;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserTlDAO extends CrudRepository<UserTl, Long>{

	
	public List<UserTl> findAll();
	
	
	public UserTl findByusuario(String usuario);
	
	public UserTl findByidusuario(int idusuario);

	
}
