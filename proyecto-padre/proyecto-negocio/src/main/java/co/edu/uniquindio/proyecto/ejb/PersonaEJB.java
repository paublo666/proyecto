package co.edu.uniquindio.proyecto.ejb;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import co.edu.uniquindio.banco.entidades.Caracteristica;
import co.edu.uniquindio.banco.entidades.Ciudad;
import co.edu.uniquindio.banco.entidades.Marca;
import co.edu.uniquindio.banco.entidades.Modelo;
import co.edu.uniquindio.banco.entidades.Persona;
import co.edu.uniquindio.banco.entidades.Pregunta;
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
	
	public void enviarCorreo(String email, String password) throws Exception {


		Properties propiedad = new Properties();
		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
		propiedad.setProperty("mail.smtp.starttls.enable", "true");
		propiedad.setProperty("mail.smtp.port", "587");
		propiedad.setProperty("mail.smtp.auth", "true");
		propiedad.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session sesion = Session.getDefaultInstance(propiedad);

		String correoEnvia ="pcduquer@uqvirtual.edu.co";
		String contraseña ="960116047201";
		try {



			String destinatario =email;
			String asunto = "Recuperar Contraseña";
			String mensaje = "la contraseña es: "+ password ;


			MimeMessage mail = new MimeMessage(sesion);


			mail.setFrom(new InternetAddress (correoEnvia));

			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			mail.setSubject(asunto);
			mail.setText(mensaje);


			Transport transporte = sesion.getTransport("smtp");


			transporte.connect(correoEnvia, contraseña);
			transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
			transporte.close();


			JOptionPane.showMessageDialog(null, "correo Enviado");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Persona> recuperarPersona(String emailLogin)
	{
		TypedQuery<Persona> q= entityManager.createNamedQuery("BUSCAR_POR_EMAIL", Persona.class);
		q.setParameter("email", emailLogin);
		return q.getResultList();
	}
	
	public List<Caracteristica> listaCaracteristicas(){
		TypedQuery<Caracteristica> q= entityManager.createNamedQuery("LISTA_CARACTERISTICAS",Caracteristica.class);
		return q.getResultList();
		
	}
	
	public Ciudad encontrarCiudad(Integer id) {
		return entityManager.find(Ciudad.class, id);
	}
	
	public Vehiculo encontrarVehiculo(Integer id) {
		return entityManager.find(Vehiculo.class, id);
	}
	
	public Pregunta hacerPregunta(Persona persona, Vehiculo vehiculo, String textoPregunta) {
		Pregunta pregunta = null;
		if(persona!=null && vehiculo!=null) {
			pregunta = new Pregunta(textoPregunta, vehiculo, persona);
			entityManager.persist(pregunta);
		}
		return pregunta;
	}
	
	public List<Caracteristica> obtenerCaracteristicasVehiculo(Integer codigoV){
		TypedQuery<Caracteristica> q = entityManager.createNamedQuery("LISTA_CARACTERISTICAS_VEHICULOS", Caracteristica.class);
		q.setParameter("id",codigoV);
		return q.getResultList();
	}
	
	public List<Pregunta> obtenerPreguntasVehiculo(Integer codigoV){
		TypedQuery<Pregunta> q = entityManager.createNamedQuery("LISTA_PREGUNTAS",Pregunta.class);
		q.setParameter("id",codigoV);
		return q.getResultList();
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
	
	public List<Marca> listaMarcas()
	{
		TypedQuery<Marca> q = entityManager.createNamedQuery("LISTA_MARCAS", Marca.class);
		return q.getResultList();
	}
	
	public Marca obtenerMarca(Integer id) throws Exception {
		Marca m = entityManager.find(Marca.class, id);
		if(m!=null) {
			return m;
		}else {
			throw new Exception("la marca no existe");
		}
		
	}
	
	public boolean buscarNombreMarca(String nombreMarca) {
		TypedQuery<Marca> q = entityManager.createNamedQuery("MARCA_POR_NOMBRE",Marca.class);
		q.setParameter("nombre",nombreMarca);
		List<Marca> l= q.getResultList();
		if(l.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void guardarMarca (Marca marca) throws Exception {
		if(buscarNombreMarca(marca.getNombre_marca())) {
			throw new Exception("el nombre de la marca ya esta registrado");
		}
		entityManager.persist(marca);
	}
	
	public void eliminarMarca(Integer id) throws Exception {
		Marca marca = entityManager.find(Marca.class,id);
		if(marca!=null) {
			entityManager.remove(marca);
		}else {
			throw new Exception("la marca no existe");
		}
	}
	
	public void actualizarMarca (Marca marca) throws Exception {
		if (marca!=null) {
			entityManager.merge(marca);
		}else {
			throw new Exception("la marca es null");
		}
	}
	
	
}
