package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vehiculo
 *
 */
@Entity

public class Vehiculo implements Serializable {

	@Id
	@Column(name = "idvehiculo", length = 100, nullable = false)
	private String idvehiculo;
	
	@Column(name = "precio", nullable = false)
	private Double precio;
	
	@Column(name = "descripcion", length = 100, nullable = false)
	private String descripcion;
	
	@Column(name = "anio", nullable = false)
	private Integer anio;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ciudad", nullable = false)
	private Ciudad ciudad;
	
	@Enumerated(EnumType.STRING)
	@JoinColumn(name =" tipocombustible", nullable = false )
	private TipoCombustible tipocombustible;

	public Vehiculo() {
		super();
	}   
	public String getIdvehiculo() {
		return this.idvehiculo;
	}

	public void setIdvehiculo(String idvehiculo) {
		this.idvehiculo = idvehiculo;
	}   
	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}   
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}   
	public Integer getAnio() {
		return this.anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idvehiculo == null) ? 0 : idvehiculo.hashCode());
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
		Vehiculo other = (Vehiculo) obj;
		if (idvehiculo == null) {
			if (other.idvehiculo != null)
				return false;
		} else if (!idvehiculo.equals(other.idvehiculo))
			return false;
		return true;
	}
   
	
}
