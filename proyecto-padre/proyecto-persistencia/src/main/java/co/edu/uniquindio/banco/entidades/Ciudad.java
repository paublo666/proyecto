package co.edu.uniquindio.banco.entidades;

import java.awt.List;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ciudad
 *
 */
@Entity

public class Ciudad implements Serializable {
	

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	@Id
	private Integer codigo;
	
	@Column(name = "nombre", nullable = false, length = 150)   
	private String nombre;
	
	@OneToMany(mappedBy = "ciudad")
	private java.util.List<Persona> personas;
	
	
	@OneToMany(mappedBy = "ciudad")
	private java.util.List<Vehiculo> vehiculos;
	
	private static final long serialVersionUID = 1L;

	public Ciudad() {
		super();
	}   
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Ciudad other = (Ciudad) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ciudad [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
	
}
