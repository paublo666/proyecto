package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Caracteristica
 *
 */
@Entity

public class Caracteristica implements Serializable {
	
	@ManyToMany
	private List<Vehiculo> vehiculo;

	@GeneratedValue 
	@Column (name = "id_caracteristica", nullable = false)
	@Id
	private Integer id_caracteristica;
	
	@Column(name = "detalle_caracteristica")
	private String detalle_caracteristica;
	private static final long serialVersionUID = 1L;
	
	

	public Caracteristica() {
		super();
	}   
	public Integer getId_caracteristica() {
		return this.id_caracteristica;
	}

	public void setId_caracteristica(Integer id_caracteristica) {
		this.id_caracteristica = id_caracteristica;
	}   
	public String getDetalle_caracteristica() {
		return this.detalle_caracteristica;
	}

	public void setDetalle_caracteristica(String detalle_caracteristica) {
		this.detalle_caracteristica = detalle_caracteristica;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detalle_caracteristica == null) ? 0 : detalle_caracteristica.hashCode());
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
		Caracteristica other = (Caracteristica) obj;
		if (detalle_caracteristica == null) {
			if (other.detalle_caracteristica != null)
				return false;
		} else if (!detalle_caracteristica.equals(other.detalle_caracteristica))
			return false;
		return true;
	}
   
	
}
