package co.edu.uniquindio.unimotor.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import co.edu.uniquindio.banco.entidades.Vehiculo;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@Named
@SessionScoped
public class InicioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersonaEJB unimotorEJB;
	
	private List<Vehiculo> vehiculos;
	
	@PostConstruct
	public void inicializar() {
		vehiculos = unimotorEJB.listaVehiculos();
	}
	
	public String irAlDetalle(String id) {
		return "detalleVehiculo?faces-redirect=true&amp;vehiculo="+id;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	
	
	
	
	
}
