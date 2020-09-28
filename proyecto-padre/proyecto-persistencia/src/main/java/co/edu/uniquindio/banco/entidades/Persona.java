package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity

public class Persona implements Serializable {

	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String id;
	
	@Column(name = "nombre", length = 200, nullable = false)
	private String nombre;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "clave", length = 100, nullable = false)
	private String clave;
	
	@Column(name = "direccion", length = 200, nullable = false)
	private String direccion;
	private static final long serialVersionUID = 1L;
	
	@JoinColumn(name = "codigo_ciudad", nullable = false)
	@ManyToOne
	private Ciudad ciudad ;

	public Persona() {
		super();
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}   
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		return true;
	}
	
	
   
}
