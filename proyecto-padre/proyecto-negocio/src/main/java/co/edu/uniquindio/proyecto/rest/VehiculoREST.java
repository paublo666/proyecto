package co.edu.uniquindio.proyecto.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.edu.uniquindio.banco.entidades.Vehiculo;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@Path("/vehiculos")
public class VehiculoREST {
	
	@EJB
	private PersonaEJB unimotorEJB;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response listaVehiculos(){
		return Response.status(200).entity(unimotorEJB.listaVehiculos()).build() ;
	}

	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response obtenerVehiculo(@PathParam("id") Integer id) {
		try {
			Vehiculo vehiculo = unimotorEJB.obtenerVehiculo(id);
			return Response.status(200).entity(vehiculo).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mensaje\" : \""+e.getMessage()+"\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response registrarVehiculo(Vehiculo vehiculo){
		try {
			unimotorEJB.guardarVehiculo(vehiculo);
			return Response.status(200).entity("{\"mensaje\" : \"la marca de registro correctamente\"}").type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mensaje\" : \""+e.getMessage()+"\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response actualizarVehiculo(Vehiculo vehiculo){
		try {
			unimotorEJB.actualizarVehiculo(vehiculo);
			return Response.status(200).entity("{\"mensaje\" : \"la marca de actualizo correctamente\"}").type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mensaje\" : \""+e.getMessage()+"\"}").type(MediaType.APPLICATION_JSON).build();
		}		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response eliminarVehiculo( @PathParam("id") Integer id){
		try {
			unimotorEJB.eliminarVehiculo(id);
			return Response.status(200).entity("{\"mensaje\" : \"la marca de elimino correctamente\"}").type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			return Response.status(500).entity("{\"mensaje\" : \""+e.getMessage()+"\"}").type(MediaType.APPLICATION_JSON).build();
		}
	}

}
