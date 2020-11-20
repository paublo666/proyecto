package co.edu.uniquindio.proyecto.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniquindio.banco.entidades.Ciudad;
import co.edu.uniquindio.banco.entidades.Marca;
import co.edu.uniquindio.banco.entidades.Modelo;
import co.edu.uniquindio.banco.entidades.Persona;

/**
 * Session Bean implementation class SetupEJB
 */
@Singleton
@LocalBean
@Startup
public class SetupEJB {
	
	@PersistenceContext
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public SetupEJB() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void config () {
    	
    	TypedQuery<Long> q = entityManager.createNamedQuery("COUNT_PERSONAS", Long.class);
    	if(q.getSingleResult()==0) {
    	
    	Ciudad c1 = new Ciudad();
    	c1.setNombre("Armenia");
    	entityManager.persist(c1);
    	
    	Map<String,Integer> tels= new HashMap<>();
    	tels.put("casa",7353844);
    	tels.put("trabajo",300559);
    	
    	Persona persona = new Persona("pablo", "pablo@gmail.com","123", "calle 13", c1, tels);
    	entityManager.persist(persona);
    	
    	Marca mar= new Marca();
    	mar.setNombre_marca("ferrari");
    	entityManager.persist(mar);
    	
    	Modelo m = new Modelo();
    	m.setNombre("ferrari 2020");
    	m.setId_marca(mar);
    	entityManager.persist(m);
    	

    	}
    	
    }

}
