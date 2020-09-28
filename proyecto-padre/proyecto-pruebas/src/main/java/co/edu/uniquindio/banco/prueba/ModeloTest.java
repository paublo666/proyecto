package co.edu.uniquindio.banco.prueba;

import javax.persistence.EntityManager;
import java.util.HashMap;
import co.edu.uniquindio.banco.entidades.*;
import java.util.Map;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


import co.edu.uniquindio.banco.entidades.Cliente;
import co.edu.uniquindio.banco.entidades.Genero;

@RunWith(Arquillian.class)

public class ModeloTest {

	@PersistenceContext
	private EntityManager entitymanager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Cliente.class.getPackage())
				.addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test () {
		
	}
	
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void persistenciaClienteTest() {
		
		Cliente c = new Cliente("1231111111", "pepe", "ppee@mail");
		c.setGenero(Genero.MASCULINO);
				
		Map<String, Integer> tels= new HashMap<String, Integer>();
		tels.put("casa", 01234567);
		tels.put("celular", 01236667);
		
		
		entitymanager.persist(c);
		

	}
	
	
}


