package co.edu.uniquindio.proyecto.ejb;

import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.banco.entidades.Caracteristica;
import co.edu.uniquindio.banco.entidades.Ciudad;
import co.edu.uniquindio.banco.entidades.Modelo;
import co.edu.uniquindio.banco.entidades.Persona;
import co.edu.uniquindio.banco.entidades.TipoCombustible;
import co.edu.uniquindio.banco.entidades.TipoVehiculo;
import co.edu.uniquindio.banco.entidades.Transmision;
import co.edu.uniquindio.banco.entidades.Vehiculo;
import co.edu.uniquindio.proyecto.excepcion.VehiculoInexistenteException;

/**
 * Session Bean implementation class PersonaEJB
 */
@Stateless
@LocalBean
public class PersonaEJB implements PersonaEJBRemote {
	
	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public PersonaEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void registrarPersona(Persona persona) throws Exception{
		
		if(buscarPorEmail(persona.getEmail())) {
			throw new Exception("la direccion de correo ya se encuentra registrada");
		}
		
		entityManager.persist(persona);
		
	}
	
	public List<Caracteristica> listaCaracteristicas(){
		TypedQuery<Caracteristica> q= entityManager.createNamedQuery("LISTA_CARACTERISTICAS",Caracteristica.class);
		return q.getResultList();
		
	}
	
	public Ciudad encontrarCiudad(Integer id) {
		return entityManager.find(Ciudad.class, id);
	}
	
	public List<Ciudad> obtenerListaCiudades ()
	{
		TypedQuery<Ciudad> q = entityManager.createNamedQuery("LISTA_CIUDADES", Ciudad.class);
		List<Ciudad> ciudades =q.getResultList();
		return ciudades;
	}
	public boolean buscarPorEmail (String email) {
		
		TypedQuery<Persona> q = entityManager.createNamedQuery("BUSCAR_POR_EMAIL", Persona.class);
		q.setParameter("email",email);
		
		List<Persona> l= q.getResultList();
		
		if(l.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public Persona iniciarSesion(String email, String clave) throws Exception {
		
		TypedQuery<Persona> q = entityManager.createNamedQuery("AUTENTICAR PERSONA", Persona.class);
		q.setParameter("email",email);
		q.setParameter("clave",clave);
		
		List<Persona> l = q.getResultList();
		
		if(l.isEmpty()) {
			throw new Exception("los datos de autenticacion son incorrectos");
		}
		Persona p =l.get(0);
		return p;
	}

	@Override
	public void eliminarVehiculo(Vehiculo vehiculo) throws VehiculoInexistenteException {
		
		Vehiculo v = entityManager.find(Vehiculo.class,vehiculo.getIdvehiculo());
		
		if(v==null) {
			throw new VehiculoInexistenteException("vehiculo no registrado en la base de datos");
		}
		entityManager.remove(v);
	}

	@Override
	public void modificarVehiculo(Vehiculo vehiculo) throws VehiculoInexistenteException {
		// TODO Auto-generated method stub
		Vehiculo v = entityManager.find(Vehiculo.class,vehiculo.getIdvehiculo());
		
		if(v==null) {
			throw new VehiculoInexistenteException("vehiculo no registrado en la base de datos");
		}
		entityManager.merge(v);
		
	}
	
	public boolean buscarPlaca (String placa) {
		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("BUSCAR_POR_PLACA", Vehiculo.class);
		q.setParameter("placa",placa);
		
		List<Vehiculo> l = q.getResultList();
		
		if(l.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void registrarVehiculo(Vehiculo vehiculo) throws Exception {
		
		if(buscarPlaca(vehiculo.getPlaca())) {
			throw new Exception("el vehiculo ya se encuentra registrado");
			
		}
		entityManager.persist(vehiculo);

	}

	@Override
	public List<TipoVehiculo> obtenerTiposVehiculos() {
		// TODO Auto-generated method stub
		return Arrays.asList(TipoVehiculo.values());
	}

	@Override
	public List<TipoCombustible> obtenerTiposCombustible() {
		// TODO Auto-generated method stub
		return Arrays.asList(TipoCombustible.values());
	}

	@Override
	public List<Transmision> obtenerTiposTransmision() {
		// TODO Auto-generated method stub
		return Arrays.asList(Transmision.values());
	}

	@Override
	public Persona encontrarPersona(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Persona.class, id);
	}

	@Override
	public Modelo encontrarModelo(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Modelo.class, id);
	}
	
	public Caracteristica encontrarCaracteristica(Integer id) {
		return entityManager.find(Caracteristica.class,id);
	}

	@Override
	public List<Vehiculo> buscarVehiculo(String busqueda) {
		TypedQuery<Vehiculo> q = entityManager.createNamedQuery("BUSCAR", Vehiculo.class);
		q.setParameter("busqueda", "%"+busqueda+"%");
		return q.getResultList();
	}
	
	
	public List<Vehiculo> listaVehiculos(){
		
		TypedQuery<Vehiculo> q =entityManager.createNamedQuery("TODOS_VEHICULOS", Vehiculo.class);
		return q.getResultList();
		
	}
	
	public List<Persona> listaPersonas(){
		TypedQuery<Persona> q= entityManager.createNamedQuery("LISTA_PERSONAS",Persona.class);
		return q.getResultList();
	}
	
	public List<Modelo> listaModelos(){
		TypedQuery<Modelo> q= entityManager.createNamedQuery("LISTA_MODELOS",Modelo.class);
		return q.getResultList();
	}

}
