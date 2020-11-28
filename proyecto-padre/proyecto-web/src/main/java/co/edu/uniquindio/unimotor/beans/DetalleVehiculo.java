package co.edu.uniquindio.unimotor.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import co.edu.uniquindio.banco.entidades.Caracteristica;
import co.edu.uniquindio.banco.entidades.Persona;
import co.edu.uniquindio.banco.entidades.Pregunta;
import co.edu.uniquindio.banco.entidades.Vehiculo;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@Named
@ViewScoped
public class DetalleVehiculo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersonaEJB unimotorEJB;
	
	private Vehiculo vehiculo ;
	
	private String pregunta;
	private boolean favorito;
	
	@Inject
	@ManagedProperty(value = "#{SeguridadBeam.persona}")
	private Persona persona;
	
	@Inject
	@ManagedProperty(value = "#{param.vehiculo}")
	private String proyectoParam;
	
	@PostConstruct
	public void inicializar () {
		if(proyectoParam!=null && !proyectoParam.isEmpty()) {
			try {
				int codigoV = Integer.parseInt(proyectoParam);
				vehiculo=unimotorEJB.encontrarVehiculo(codigoV);
				preguntas=unimotorEJB.obtenerPreguntasVehiculo(codigoV);
				caractetisticas=unimotorEJB.obtenerCaracteristicasVehiculo(codigoV);
			} catch (Exception e) {
				// TODO: handle exception
			} 
		}
	}
	
	public void hacerPregunta() {
		try {
			Pregunta p = unimotorEJB.hacerPregunta(persona, vehiculo, pregunta);
			if(p!=null) {
				preguntas.add(p);
			}

		} catch (Exception e) {
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("mensaje_sesion", msj);		}
	}
	
	
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	private List<Pregunta> preguntas;
	private List<Caracteristica> caractetisticas;
	
	
	public String getProyectoParam() {
		return proyectoParam;
	}
	public void setProyectoParam(String proyectoParam) {
		this.proyectoParam = proyectoParam;
	}
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public List<Caracteristica> getCaractetisticas() {
		return caractetisticas;
	}
	public void setCaractetisticas(List<Caracteristica> caractetisticas) {
		this.caractetisticas = caractetisticas;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public boolean isFavorito() {
		return favorito;
	}
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}

}


