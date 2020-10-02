package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Favorito
 *
 */
@Entity

public class Favorito implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_favorito", nullable = false)
	private Integer id_favorito;
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo")
	private Vehiculo vehiculo;
	
	@ManyToOne
	@JoinColumn(name = "id_persona")
	private Persona persona;
	
	
	private static final long serialVersionUID = 1L;

	public Favorito() {
		super();
	}   
	public Integer getId_favorito() {
		return this.id_favorito;
	}

	public void setId_favorito(Integer id_favorito) {
		this.id_favorito = id_favorito;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_favorito == null) ? 0 : id_favorito.hashCode());
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
		Favorito other = (Favorito) obj;
		if (id_favorito == null) {
			if (other.id_favorito != null)
				return false;
		} else if (!id_favorito.equals(other.id_favorito))
			return false;
		return true;
	}
	
	
   
}
