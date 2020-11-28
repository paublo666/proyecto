package co.edu.uniquindio.proyecto.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.edu.uniquindio.banco.entidades.Marca;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@Path("/marcas")
public class MarcaREST {
	
	@EJB
	private PersonaEJB unimotorEJB;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Marca> listaMarcas(){
		return unimotorEJB.listaMarcas();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Marca obtenerMarca(@PathParam("id") Integer id) {
		try {
			return unimotorEJB.obtenerMarca(id);
		} catch (Exception e) {
			return null;

		}
	}
	
	public void registrarMarca(Marca marca){
		try {
			unimotorEJB.guardarMarca(marca);
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}
	
	
	public void actualizarMarca(Marca marca){
		try {
			unimotorEJB.actualizarMarca(marca);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void eliminarMarca(Integer id){
		try {
			unimotorEJB.eliminarMarca(id);
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}

}
