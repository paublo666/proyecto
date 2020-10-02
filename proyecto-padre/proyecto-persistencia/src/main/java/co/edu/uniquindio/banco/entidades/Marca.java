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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_marca")
	private Integer id_marca;
	
	@Column(name = "nombre_marca", nullable = false)
	private String nombre_marca;
	private static final long serialVersionUID = 1L;

	
	@OneToMany(mappedBy = "marca")
	private List<Vehiculo> vehiculos;
	
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
   
}
