package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Modelo
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "LISTA_MODELOS", query = "select m from Modelo m" )
	})


public class Modelo implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modelo")
	private Integer id_modelo;
	
	@Column(name = "nombre_modelo", nullable = false)
	private String nombre;
	
	@OneToMany(mappedBy = "modelo")
	private List<Vehiculo> vehiculos;
	
	@JoinColumn(name = "id_marca", nullable = false)
	private Marca id_marca;
	
	private static final long serialVersionUID = 1L;

	public Modelo() {
		super();
	}   
	public Integer getId_modelo() {
		return this.id_modelo;
	}

	public void setId_modelo(Integer id_modelo) {
		this.id_modelo = id_modelo;
	}   
	
	public Marca getId_marca() {
		return id_marca;
	}
	public void setId_marca(Marca id_marca) {
		this.id_marca = id_marca;
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
		result = prime * result + ((id_modelo == null) ? 0 : id_modelo.hashCode());
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
		Modelo other = (Modelo) obj;
		if (id_modelo == null) {
			if (other.id_modelo != null)
				return false;
		} else if (!id_modelo.equals(other.id_modelo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Modelo [id_modelo=" + id_modelo + ", nombre=" + nombre + ", id_marca=" + id_marca + "]";
	}
	
   
}
