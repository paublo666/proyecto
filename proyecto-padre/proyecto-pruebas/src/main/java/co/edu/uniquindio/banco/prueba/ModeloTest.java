package co.edu.uniquindio.banco.prueba;

import javax.persistence.EntityManager;
import java.util.HashMap;
import co.edu.uniquindio.banco.entidades.*;
import java.util.Map;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(Arquillian.class)

public class ModeloTest {

	@PersistenceContext
	private EntityManager entitymanager;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class,

				"prueba.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml",

						"META-INF/persistence.xml")

				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void test () {
		
		
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void buscarPersonaTest ()
	{
		Persona p = entitymanager.find(Persona.class, 1);
		Assert.assertNotNull(p);
		
	}
	
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json"})
	public void buscarVehiculoTest ()
	{
		Vehiculo v = entitymanager.find(Vehiculo.class, 1);
		System.out.println(v);
		//Assert.assertNotNull(v);
		
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "marca.json", "modelo.json", "ciudad.json"})
	public void RegistrarVehiculoTest ()
	{
		Ciudad c = entitymanager.find(Ciudad.class, 1);
		Persona p = entitymanager.find(Persona.class, 1);
		Marca m = entitymanager.find(Marca.class, 1);
		Modelo mod = entitymanager.find(Modelo.class, 1);
		
		Vehiculo v = new Vehiculo(1, 2.0000000, "vehiculo en buen estado", 2020, "01-10-2020", "chevrolet spark", "verde", p, c, mod, m,TipoVehiculo.CARRO, TipoCombustible.GASOLINA,Transmision.MECANICA);

		entitymanager.persist(v);
		
		Vehiculo vbuscado = entitymanager.find(Vehiculo.class, 1);
		Assert.assertNotNull(vbuscado);
		
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void eliminarPersonaTest ()
	{
		Persona p = entitymanager.find(Persona.class, 1);
		entitymanager.remove(p);
		
		Persona pbuscado = entitymanager.find(Persona.class, 1);
		Assert.assertNull(pbuscado);
		
	}
	
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json"})
	public void actualizarPersonaTest ()
	{
		Persona p = entitymanager.find(Persona.class, 1);
		p.setNombre("juan");
		entitymanager.merge(p);
		
		Persona pbuscado = entitymanager.find(Persona.class, 1);
		Assert.assertEquals("juan", pbuscado.getNombre());
		
	}
	
	
	
	@Ignore
	@Test
	@Transactional(value = TransactionMode.COMMIT)
	public void persistenciaPersonaTest() {
		
		//Cliente c = new Cliente("1231111111", "pepe", "ppee@mail");
		//c.setGenero(Genero.MASCULINO);
				
		Map<String, Integer> tels= new HashMap<String, Integer>();
		tels.put("casa", 01234567);
		tels.put("celular", 01236667);
		
		
		//entitymanager.persist(c);
		

	}
	
	
}


