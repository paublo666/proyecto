package co.edu.uniquindio.proyecto.ejb;

import javax.ejb.Remote;

import co.edu.uniquindio.banco.entidades.Persona;
import co.edu.uniquindio.banco.entidades.Vehiculo;
import co.edu.uniquindio.proyecto.excepcion.VehiculoInexistenteException;

@Remote
public interface PersonaEJBRemote {
	
	 void registrarPersona (Persona persona) throws Exception;
	 
	 Persona iniciarSesion (String email, String clave) throws Exception;
	 
	 void eliminarVehiculo (Vehiculo vehiculo) throws VehiculoInexistenteException;
	 
	 void modificarVehiculo (Vehiculo vehiculo) throws VehiculoInexistenteException;

}
