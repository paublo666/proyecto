package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vehiculo
 *
 */
@Entity

public class Vehiculo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idvehiculo", nullable = false)
	private Integer idvehiculo;
	
	@Column(name = "precio", nullable = false)
	private Double precio;
	
	@Column(name = "descripcion", length = 100, nullable = false)
	private String descripcion;
	
	@Column(name = "anio", nullable = false)
	private Integer anio;
	
	@Column(name="fecha_publicacion", nullable = false)
	private String fecha;
	
	@Column(name="nombre_vehiculo", nullable = false)
	private String nombre_vehiculo;
	
	@Column(name="color_vehiculo", nullable = false)
	private String color;
	
	@ElementCollection
	private java.util.Map<String,Integer> fotos;
	
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name = "id_persona")
	private Persona idpersona;
	
	
	@ManyToOne
	@JoinColumn(name = "id_ciudad", nullable = false)
	private Ciudad ciudad;
	
	@ManyToOne
	@JoinColumn(name = "id_modelo")
	private Modelo modelo;
	
	@ManyToOne
	@JoinColumn(name= "id_marca")
	private Marca marca;
	
	@OneToMany(mappedBy = "vehiculo")
	private List<Favorito> favoritos;
	
	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "tipo_vehiculo", nullable = false)
	private TipoVehiculo tipovehiculo;
	
	@Enumerated(EnumType.STRING)
	@JoinColumn(name =" tipo_combustible", nullable = false )
	private TipoCombustible tipocombustible;
	
	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "transmision", nullable = false)
	private Transmision transmision ;
	
	@OneToMany(mappedBy = "vehiculo")
	private List<Pregunta> preguntas;
	
	

	public Vehiculo(Integer idvehiculo, Double precio, String descripcion, Integer anio, String fecha,
			String nombre_vehiculo, String color, Persona idpersona, Ciudad ciudad,
			Modelo modelo, Marca marca, TipoVehiculo tipovehiculo, TipoCombustible tipocombustible,
			Transmision transmision) {
		super();
		this.idvehiculo = idvehiculo;
		this.precio = precio;
		this.descripcion = descripcion;
		this.anio = anio;
		this.fecha = fecha;
		this.nombre_vehiculo = nombre_vehiculo;
		this.color = color;
		this.idpersona = idpersona;
		this.ciudad = ciudad;
		this.modelo = modelo;
		this.marca = marca;
		this.tipovehiculo = tipovehiculo;
		this.tipocombustible = tipocombustible;
		this.transmision = transmision;
	}
	public Vehiculo() {
		super();
	}   
	public Integer getIdvehiculo() {
		return this.idvehiculo;
	}

	public void setIdvehiculo(Integer idvehiculo) {
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
	@Override
	public String toString() {
		return "Vehiculo [idvehiculo=" + idvehiculo + ", precio=" + precio + ", descripcion=" + descripcion + ", anio="
				+ anio + ", fecha=" + fecha + ", nombre_vehiculo=" + nombre_vehiculo + ", color=" + color
				+ ", idpersona=" + idpersona + ", ciudad=" + ciudad + ", modelo=" + modelo + ", marca=" + marca
				+ ", tipovehiculo=" + tipovehiculo + ", tipocombustible=" + tipocombustible + ", transmision="
				+ transmision + "]";
	}
	
	

   
	
}
