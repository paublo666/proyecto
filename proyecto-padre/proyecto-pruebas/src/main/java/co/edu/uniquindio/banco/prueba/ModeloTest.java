package co.edu.uniquindio.banco.prueba;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.edu.uniquindio.banco.entidades.*;
import java.util.Map;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	@UsingDataSet({"persona.json", "vehiculo.json", "modelo.json", "ciudad.json","marca.json"})
	public void prueba () {

		Ciudad c = entitymanager.find(Ciudad.class, 1);
		Persona p = entitymanager.find(Persona.class, 1);
		Modelo mod = entitymanager.find(Modelo.class, 1);
		//Vehiculo v = new Vehiculo(1, 20000000, "vehiculo en buen estado", 2020, "01-10-2020", "verde", p, c, mod, TipoVehiculo.CARRO, TipoCombustible.GASOLINA,Transmision.MECANICA, "abc123");

		//v.setNuevo(true);
		//String s = v.getModelo().getId_marca().getNombre_marca();
		//System.out.println(s);	
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","marca.json"})
	public void listaTest ()
	{
		TypedQuery<Vehiculo> q = entitymanager.createNamedQuery("TODOS_VEHICULOS", Vehiculo.class);
		List <Vehiculo> l = q.getResultList();

		for (Vehiculo v : l) {
			System.out.println(v.getModelo().getNombre()+ " "+ v.getPrecio());
		}
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","marca.json"})
	public void autenticacionTest ()
	{
		TypedQuery<Persona> q = entitymanager.createNamedQuery("AUTENTICAR PERSONA", Persona.class);
		q.setParameter("email", "psa@mail");
		q.setParameter("clave", "123");

		List <Persona> l = q.getResultList();

		Assert.assertEquals(1, l.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","marca.json"})
	public void contarPersonasTest() {

		Query q = entitymanager.createNamedQuery("COUNT_PERSONAS");
		System.out.println(q.getSingleResult());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","marca.json"})
	public void vehiculoPlacaTest ()
	{
		TypedQuery<Object[]> q = entitymanager.createNamedQuery("VEHICULO_PLACA", Object[].class);
		q.setParameter("placa", "abc123");

		List <Object[]> l = q.getResultList();

		for (Object[] v : l) {
			System.out.println(v[0]+" "+v[1]+" "+v[2]+" ");		}

		//Assert.assertEquals(1, l.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","marca.json"})
	public void vehiculoPreguntasTest ()
	{
		TypedQuery<Object[]> q = entitymanager.createNamedQuery("VEHICULO_PREGUNTAS", Object[].class);

		List <Object[]> l = q.getResultList();

		for (Object[] v : l) {
			System.out.println(v[0]+" "+v[1]);		}

		//Assert.assertEquals(1, l.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","vehiculo_fotos.json","marca.json"})
	public void vehiculoFotosTest ()
	{
		TypedQuery<Object[]> q = entitymanager.createNamedQuery("VEHICULO_FOTOS", Object[].class);
		q.setParameter("marca", "chevrolet");
		//q.setParameter("min", 0);
		//q.setParameter("max",20.000);

		List <Object[]> l = q.getResultList();

		for (Object[] v : l) {
			System.out.println(v[0]+"  "+v[1]);		}

		//Assert.assertEquals(1, l.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","vehiculo_fotos.json","marca.json","vehiculo_caracteristica.json","caracteristica.json"})
	public void listaPersonasOrganizadaTest ()
	{

		TypedQuery<Persona> q = entitymanager.createNamedQuery("LISTA_PERSONAS_ORDENADA", Persona.class);

		List <Persona> l = q.getResultList();

		for (Persona v : l) {
			System.out.println(v);		}

		//Assert.assertEquals(1, l.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","vehiculo_fotos.json","marca.json","vehiculo_caracteristica.json","caracteristica.json"})
	public void caracteristicasVehiculoTest ()
	{
		ArrayList<Integer> lista= new ArrayList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);

		TypedQuery<Vehiculo> q = entitymanager.createNamedQuery("CARACTERISTICAS_VEHICULO", Vehiculo.class);
		q.setParameter("lista", lista);
		//q.setParameter("min", 0);
		//q.setParameter("max",20.000);

		List <Vehiculo> l = q.getResultList();

		for (Vehiculo v : l) {
			System.out.println(v);		}

		//Assert.assertEquals(1, l.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","vehiculo_fotos.json","marca.json","vehiculo_caracteristica.json","caracteristica.json"})
	public void cuentaVehiculosCaracteristicasTest ()
	{		

		ArrayList<Integer> lista= new ArrayList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);

		TypedQuery<Vehiculo> q = entitymanager.createNamedQuery("CUENTA_CARACTERISTICAS", Vehiculo.class);
		q.setParameter("lista", lista);

		List <Vehiculo> l = q.getResultList();

		System.out.println(l);

		//Assert.assertEquals(1, l.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","vehiculo_fotos.json","marca.json","vehiculo_caracteristica.json","caracteristica.json"})
	public void caracteristicasTest ()
	{

		TypedQuery<Caracteristica> q = entitymanager.createNamedQuery("VEHICULO_CARACTERISTICAS", Caracteristica.class);
		q.setParameter("id", 1);
		//q.setParameter("min", 0);
		//q.setParameter("max",20.000);

		List <Caracteristica> l = q.getResultList();

		for (Caracteristica v : l) {
			System.out.println(v);		}

		//Assert.assertEquals(1, l.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","vehiculo_fotos.json","marca.json","vehiculo_caracteristica.json","caracteristica.json"})
	public void vehiculosPorMarcaTest ()
	{
		TypedQuery<Vehiculo> q = entitymanager.createNamedQuery("VEHICULOS_POR_MARCA", Vehiculo.class);
		q.setParameter("marca","chevrolet");

		List <Vehiculo> l = q.getResultList();
		System.out.println(l);
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","vehiculo_fotos.json","marca.json","vehiculo_caracteristica.json","caracteristica.json"})
	public void vehiculosPorTipoTest ()
	{
		TypedQuery<Vehiculo> q = entitymanager.createNamedQuery("VEHICULOS_POR_TIPO", Vehiculo.class);
		q.setParameter("tipo",TipoVehiculo.CARRO);

		List <Vehiculo> l = q.getResultList();
		for(Vehiculo v: l) {
			System.out.println(v);
		}
	}


	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","pregunta.json","vehiculo_fotos.json","marca.json","vehiculo_caracteristica.json","caracteristica.json"})
	public void todasCaracteristicasVehiculoTest ()
	{
		ArrayList<Integer> lista= new ArrayList<Integer>();
		lista.add(1);
		lista.add(2);
		lista.add(3);

		TypedQuery<Vehiculo> q = entitymanager.createNamedQuery("TODAS_CARACTERISTICAS", Vehiculo.class);
		q.setParameter("lista", lista);
		q.setParameter("tamLista", lista.size());

		//q.setParameter("min", 0);
		//q.setParameter("max",20.000);

		List <Vehiculo> l = q.getResultList();

		for (Vehiculo v : l) {
			System.out.println(v);		}

		//Assert.assertEquals(1, l.size());

	}


	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","marca.json"})
	public void personaFavoritosTest ()
	{
		TypedQuery<Vehiculo> q = entitymanager.createNamedQuery("LISTA_PERSONA_FAVORITOS", Vehiculo.class);
		q.setParameter("email", "psassa@mail");

		List <Vehiculo> l = q.getResultList();

		for (Vehiculo v : l) {
			System.out.println(v);		}

		//Assert.assertEquals(1, l.size());

	}


	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({"persona.json", "vehiculo.json", "ciudad.json","modelo.json","marca.json"})
	public void listaVehiculoTransmisionTest ()
	{
		TypedQuery<Vehiculo> q = entitymanager.createNamedQuery("VEHICULOS_TRANSMISION", Vehiculo.class);
		q.setParameter("t", Transmision.AUTOMATICA);
		List <Vehiculo> l = q.getResultList();

		for (Vehiculo v : l) {
			System.out.println(v.getModelo().getNombre()+ " "+ v.getPrecio());
		}
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
	@UsingDataSet({"persona.json", "vehiculo.json", "modelo.json", "ciudad.json","marca.json"})
	public void RegistrarVehiculoTest ()
	{
		Ciudad c = entitymanager.find(Ciudad.class, 1);
		Persona p = entitymanager.find(Persona.class, 1);
		Modelo mod = entitymanager.find(Modelo.class, 1);

		//Vehiculo v = new Vehiculo(1, 2.0000000, "vehiculo en buen estado", 2020, "01-10-2020", "verde", p, c, mod, TipoVehiculo.CARRO, TipoCombustible.GASOLINA,Transmision.MECANICA, "abc123");

		//entitymanager.persist(v);

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


