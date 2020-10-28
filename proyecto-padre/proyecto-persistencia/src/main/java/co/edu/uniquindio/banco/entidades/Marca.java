package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Marca
 *
 */
@Entity

public class Marca implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_marca")
	private Integer id_marca;
	
	@Column(name = "nombre_marca")
	private String nombre_marca;
	
	@OneToMany(mappedBy = "id_marca")
	private List<Modelo> lista_modelos;
	
	private static final long serialVersionUID = 1L;

	public Marca() {
		super();
	}   
	public Integer getId_marca() {
		return this.id_marca;
	}

	public void setId_marca(Integer id_marca) {
		this.id_marca = id_marca;
	}   
	public String getNombre_marca() {
		return this.nombre_marca;
	}

	public void setNombre_marca(String nombre_marca) {
		this.nombre_marca = nombre_marca;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_marca == null) ? 0 : id_marca.hashCode());
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
		Marca other = (Marca) obj;
		if (id_marca == null) {
			if (other.id_marca != null)
				return false;
		} else if (!id_marca.equals(other.id_marca))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Marca [id_marca=" + id_marca + ", nombre_marca=" + nombre_marca + "]";
	}
   
}
