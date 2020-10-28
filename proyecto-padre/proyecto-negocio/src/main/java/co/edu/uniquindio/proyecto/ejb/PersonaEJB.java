package co.edu.uniquindio.proyecto.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.banco.entidades.Persona;
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

}
