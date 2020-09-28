package co.edu.uniquindio.banco.entidades;

import java.awt.List;

import java.util.*;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.faces.event.WebsocketEvent.Closed;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity

public class Cliente implements Serializable {
	
	
	@OneToMany(mappedBy = "cliente")
	private java.util.List<CuentaAhorros> cuentasAhorros;

	
	@OneToMany(mappedBy = "cliente")
	private java.util.List<Prestamo> prestamos;

	   
	@Id
	@Column(name= "cedula", length = 10)
	private String cedula;
	
	@Column(name = "nombre", length = 200, nullable = false)
	private String nombre;
	
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private Genero genero;
	@ElementCollection
	private java.util.Map<String,Integer> telefonos;

	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
	}   

	public Cliente(String cedula, String nombre, String email) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.email = email;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public java.util.Map<String,Integer> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(java.util.Map<String,Integer> telefonos) {
		this.telefonos = telefonos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}
   
	public static void main (String []args) {
		
		Cliente c1= new Cliente("123", "pepito", "pepe@mail");
		Cliente c2 = new Cliente ("123", "pepito", "pepe@mail");
		
		System.out.println(c1.equals(c2));
		
		
	}
	
}
