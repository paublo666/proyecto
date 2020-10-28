package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pregunta
 *
 */
@Entity

public class Pregunta implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_pregunta")
	private Integer id_pregunta;
	
	@Column(name="texto_pregunta", length = 200, nullable = false)
	private String texto_pregunta;
	
	@Column(name = "fecha_pregunta")
	private String fecha_pregunta;
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo", nullable = false)
	private Vehiculo vehiculo;
	
	@ManyToOne
	@JoinColumn(name = "id_persona", nullable = false)
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name="pregunta")
	private Pregunta pregunta;
	
	
	private static final long serialVersionUID = 1L;

	public Pregunta() {
		super();
	}   
	public Integer getId_pregunta() {
		return this.id_pregunta;
	}

	public void setId_pregunta(Integer id_pregunta) {
		this.id_pregunta = id_pregunta;
	}   
	public String getTexto_pregunta() {
		return this.texto_pregunta;
	}

	public void setTexto_pregunta(String texto_pregunta) {
		this.texto_pregunta = texto_pregunta;
	}   
	public String getFecha_pregunta() {
		return this.fecha_pregunta;
	}

	public void setFecha_pregunta(String fecha_pregunta) {
		this.fecha_pregunta = fecha_pregunta;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_pregunta == null) ? 0 : id_pregunta.hashCode());
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
		Pregunta other = (Pregunta) obj;
		if (id_pregunta == null) {
			if (other.id_pregunta != null)
				return false;
		} else if (!id_pregunta.equals(other.id_pregunta))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pregunta [id_pregunta=" + id_pregunta + ", texto_pregunta=" + texto_pregunta + ", fecha_pregunta="
				+ fecha_pregunta + ", vehiculo=" + vehiculo + ", persona=" + persona + "]";
	}

	

	
}
