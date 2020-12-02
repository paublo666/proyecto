package co.edu.uniquindio.banco.prueba;

import javax.ejb.EJB;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.edu.uniquindio.banco.entidades.Persona;
import co.edu.uniquindio.banco.entidades.Vehiculo;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@RunWith(Arquillian.class)

public class NegocioTest {

	@EJB
	private PersonaEJB personaejb;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class).addClass(PersonaEJB.class).addPackage(Vehiculo.class.getPackage())
				.addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","marca.json"})
	public void iniciarSesionTest() throws Exception{

		System.out.println(personaejb.iniciarSesion("psa@mail", "123"));

	}
	


}




