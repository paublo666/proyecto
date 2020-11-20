package co.edu.uniquindio.proyecto.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.edu.uniquindio.banco.entidades.Ciudad;
import co.edu.uniquindio.banco.entidades.Modelo;
import co.edu.uniquindio.banco.entidades.Persona;
import co.edu.uniquindio.banco.entidades.TipoCombustible;
import co.edu.uniquindio.banco.entidades.TipoVehiculo;
import co.edu.uniquindio.banco.entidades.Transmision;
import co.edu.uniquindio.banco.entidades.Vehiculo;
import co.edu.uniquindio.proyecto.excepcion.VehiculoInexistenteException;

@Remote
public interface PersonaEJBRemote {
	
	 void registrarPersona (Persona persona) throws Exception;
	 
	 Persona iniciarSesion (String email, String clave) throws Exception;
	 
	 void eliminarVehiculo (Vehiculo vehiculo) throws VehiculoInexistenteException;
	 
	 void modificarVehiculo (Vehiculo vehiculo) throws VehiculoInexistenteException;
	 
	 Ciudad encontrarCiudad(Integer id);
	 
	 void registrarVehiculo(Vehiculo vehiculo) throws Exception;
	 
	 List<TipoVehiculo> obtenerTiposVehiculos();
	 
	 List<TipoCombustible> obtenerTiposCombustible();
	 
	 List<Vehiculo> buscarVehiculo (String busqueda);

	 List<Transmision> obtenerTiposTransmision();
	 
	 Persona encontrarPersona(Integer id);
	 
	 Modelo encontrarModelo (Integer id);


}
