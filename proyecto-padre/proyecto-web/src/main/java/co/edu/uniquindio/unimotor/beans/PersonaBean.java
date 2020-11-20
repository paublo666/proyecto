package co.edu.uniquindio.unimotor.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.uniquindio.banco.entidades.Ciudad;
import co.edu.uniquindio.banco.entidades.Modelo;
import co.edu.uniquindio.banco.entidades.Persona;
import co.edu.uniquindio.banco.entidades.TipoCombustible;
import co.edu.uniquindio.banco.entidades.TipoVehiculo;
import co.edu.uniquindio.banco.entidades.Transmision;
import co.edu.uniquindio.banco.entidades.Vehiculo;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@FacesConfig(version = Version.JSF_2_3)
@Named
@ViewScoped
public class PersonaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private PersonaEJB unimotorEJB;
	private Persona persona, persona1;
	private Ciudad ciudad;
	private Vehiculo vehiculo;
	private List<TipoVehiculo> tiposVehiculos;
	private List<TipoCombustible> tiposCombustible;
	private List<Transmision> transmision;
	private Modelo modelo;
	private String busqueda;
	//lista de vehiculos buscados por parametro
	private List<Vehiculo> lista;
	private List<Ciudad> ciudades;
	private List<Vehiculo> vehiculos;
	//lista de las personas en la bd
	private List<Persona> personas;
	//lista de modelos
	private List<Modelo> modelos;

	@Inject
	@javax.faces.annotation.ManagedProperty(value = "#{param.busqueda}")
	private String busquedaParam;


	@PostConstruct
	public void inicializar () {
		modelos=unimotorEJB.listaModelos();
		personas=unimotorEJB.listaPersonas();
		vehiculos=unimotorEJB.listaVehiculos();
		ciudades=unimotorEJB.obtenerListaCiudades();
		ciudad=unimotorEJB.encontrarCiudad(1);
		persona = new Persona();
		vehiculo= new Vehiculo();
		tiposVehiculos = unimotorEJB.obtenerTiposVehiculos();
		tiposCombustible= unimotorEJB.obtenerTiposCombustible();
		transmision=unimotorEJB.obtenerTiposTransmision();
		persona1=unimotorEJB.encontrarPersona(1);
		modelo=unimotorEJB.encontrarModelo(1);
		if(busquedaParam!=null) {
			lista =unimotorEJB.buscarVehiculo(busquedaParam);
		}
	}

	public String buscar() {
		System.out.println(lista);
		return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
	}


	public void buscarVehiculo() {

	}

	public void registrarPersona () {
		try {
			
			unimotorEJB.registrarPersona(persona);

			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
			FacesContext.getCurrentInstance().addMessage("mensajes_registro_persona", msj);

		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("mensajes_registro_persona", msj);
		}

	}

	public void registrarVehiculo() {
		try {
			vehiculo.setIdpersona(persona1);
			vehiculo.setModelo(modelo);
			unimotorEJB.registrarVehiculo(vehiculo);
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
			FacesContext.getCurrentInstance().addMessage("mensajes_registro_vehiculo", msj);
		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("mensajes_registro_vehiculo", msj);			
		}
	}

	public String getBusquedaParam() {
		return busquedaParam;
	}

	public void setBusquedaParam(String busquedaParam) {
		this.busquedaParam = busquedaParam;
	}

	public List<Vehiculo> getLista() {
		return lista;
	}

	public void setLista(List<Vehiculo> lista) {
		this.lista = lista;
	}
	

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Persona getPersona1() {
		return persona1;
	}

	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}

	public List<TipoCombustible> getTiposCombustible() {
		return tiposCombustible;
	}

	public void setTiposCombustible(List<TipoCombustible> tiposCombustible) {
		this.tiposCombustible = tiposCombustible;
	}

	public List<Transmision> getTransmision() {
		return transmision;
	}

	public void setTransmision(List<Transmision> transmision) {
		this.transmision = transmision;
	}

	public PersonaEJB getUnimotorEJB() {
		return unimotorEJB;
	}

	public void setUnimotorEJB(PersonaEJB unimotorEJB) {
		this.unimotorEJB = unimotorEJB;
	}

	public List<TipoVehiculo> getTiposVehiculos() {
		return tiposVehiculos;
	}

	public void setTiposVehiculos(List<TipoVehiculo> tiposVehiculos) {
		this.tiposVehiculos = tiposVehiculos;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}


}
