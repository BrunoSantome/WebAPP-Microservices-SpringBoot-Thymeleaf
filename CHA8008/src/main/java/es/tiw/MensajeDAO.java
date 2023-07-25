
package es.tiw;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface MensajeDAO extends CrudRepository<Mensaje, Integer> {
	
	
	
	public List<Mensaje> findAll();
	
	public List<Mensaje> findByReceptor(int receptor); 
	public List<Mensaje> findByEmisor(int emisor);
	
	public Mensaje findByIdmensaje(int idmensaje); 

	public List<Mensaje> findByEmisorAndReceptor(int emisor, int receptor); 
	

	
	

	
	
}



