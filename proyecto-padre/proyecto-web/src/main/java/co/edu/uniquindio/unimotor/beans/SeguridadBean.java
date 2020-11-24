package co.edu.uniquindio.unimotor.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import co.edu.uniquindio.banco.entidades.Persona;
import co.edu.uniquindio.proyecto.ejb.PersonaEJB;

@Named
@SessionScoped
public class SeguridadBean implements Serializable {

	@EJB
	private PersonaEJB unimotorEJB ;
	private static final long serialVersionUID = 1L;
	private Persona persona;
	
	private boolean autenticado;
	
	@NotBlank
	@Email
	private String emailLogin;
	
	@NotBlank
	private String passwordLogin;
	
	@PostConstruct
	public void inicializar () {
		autenticado=false;
		persona = new Persona();
	}

	public String autenticarUsuario () {
		try {
			Persona p = unimotorEJB.iniciarSesion(emailLogin, passwordLogin);
			if(p!=null) {
				autenticado=true;
				persona=p;
				return "/index?faces-redirect=true";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			FacesMessage msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("mensajes_sesion", msj);
		}
		return null;
	}
	
	public String cerrarSesion () {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}
	
	

}
