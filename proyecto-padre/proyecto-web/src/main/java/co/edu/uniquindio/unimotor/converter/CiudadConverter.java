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

import co.edu.uniquindio.banco.entidades.Ciudad;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@ApplicationScoped
@Named
@FacesConfig(version = Version.JSF_2_3)
public class CiudadConverter implements Converter<Ciudad>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EJB
	PersonaEJB unimotorEJB;

	@Override
	public Ciudad getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		Ciudad ciudad=null;
		if(value !=null) {
			ciudad = unimotorEJB.encontrarCiudad(Integer.parseInt(value));
		}
		return ciudad;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Ciudad value) {
		// TODO Auto-generated method stub
		if(value!=null) {
			return String.format("%d", value.getCodigo());
		}
		return "";
	}

}
