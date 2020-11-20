package co.edu.uniquindio.unimotor.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import co.edu.uniquindio.banco.entidades.Modelo;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@Named
@ApplicationScoped
@FacesConfig (version = Version.JSF_2_3)
public class ModeloConverter implements Converter<Modelo>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	PersonaEJB unimotorEJB;

	@Override
	public Modelo getAsObject(FacesContext context, UIComponent component, String value) {
		Modelo modelo = null;
		if(value!=null) {
			modelo=unimotorEJB.encontrarModelo(Integer.parseInt(value));
		}
		return modelo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Modelo value) {
		if(value!=null) {
			return String.format("%d", value.getId_modelo());
		}
		return "";
	}

}
