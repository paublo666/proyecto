package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Vehiculo
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "TODOS_VEHICULOS", query = "select v from Vehiculo v "),
	@NamedQuery(name = "VEHICULOS_TRANSMISION", query = "select v from Vehiculo v where v.transmision = :t"),
	@NamedQuery(name = "VEHICULO_PLACA", query = "select v.modelo.nombre, v.precio, v.anio from Vehiculo v where v.placa = :placa"),
	@NamedQuery(name = "VEHICULO_PREGUNTAS", query = "select v, p from Vehiculo v left join v.preguntas p"),
	//retorna las caracteristicas de un vehiculo
	@NamedQuery(name = "VEHICULO_CARACTERISTICAS", query = "select c from Vehiculo v join v.caracteristicas c where v.idvehiculo = :id"),
	//retorna los vehiculos que tienen fotos y los queno tambien
	@NamedQuery(name = "VEHICULO_FOTOS", query = "select v, f from Vehiculo v join v.fotos f where v.modelo.id_marca.nombre_marca = :marca"),
	//retorna los vehiculos que tienen almenos una de las caracteristicas pasadas por parametro
	@NamedQuery(name = "CARACTERISTICAS_VEHICULO", query = "select v from Vehiculo v join v.caracteristicas c where c.id_caracteristica IN :lista group by v"),
	//retorna el vehiculo que tenga todas las caracteristicas que se pasan por parametro 
	@NamedQuery(name = "TODAS_CARACTERISTICAS", query = "select v from Vehiculo v join v.caracteristicas c where c.id_caracteristica IN :lista group by v having count(v) = :tamLista"),
	//retorna el numero de vehiculos que tienen almenos una caractreristica q se pasa por parametro 
	@NamedQuery(name = "CUENTA_CARACTERISTICAS", query = "select count (v) from Vehiculo v join v.caracteristicas c where c.id_caracteristica IN :lista group by v"),
	@NamedQuery(name = "VEHICULOS_POR_MARCA", query = "select v from Vehiculo v where v.modelo.id_marca.nombre_marca = :marca group by v"),
	@NamedQuery(name = "VEHICULOS_POR_TIPO", query = "select v from Vehiculo v where v.tipovehiculo = :tipo group by v"),
	@NamedQuery(name = "BUSCAR_POR_PLACA", query = "select v from Vehiculo v where v.placa = :placa"),
	@NamedQuery(name = "BUSCAR", query = "select v from Vehiculo v where v.nombre_publicacion like :busqueda"),


})
public class Vehiculo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idvehiculo", nullable = false)
	private Integer idvehiculo;
	
	@NotBlank(message = "nombre de la publicacion no puede ser vacio")
	@Column(name = "nombre_publicacion", length = 100, nullable = false)
	private String nombre_publicacion;
	
	@NotNull(message = "ingrese el precio")
	@Column(name = "precio", nullable = false)
	private Long precio;
	
	@NotNull(message = "seleccione una opcion")
	@Column(name = "nuevo", nullable = false)
	private boolean nuevo;
	
	@NotBlank(message = "la descripcion no puede ser vacio")
	@Column(name = "descripcion", length = 100, nullable = false)
	private String descripcion;
	
	@NotNull(message = "ingrese el anio del vehiculo")
	@Column(name = "anio", nullable = false)
	private Integer anio;
	
	@NotBlank(message = "la placa no puede ser vacio")
	@Column(name = "placa", nullable = false, unique = true)
	private String placa;
	
	@Column(name="fecha_publicacion")
	private Date fecha;
	
	@NotBlank(message = "el color no puede ser vacio")
	@Column(name="color_vehiculo", nullable = false)
	private String color;
	
	@ElementCollection
	private java.util.ArrayList<String> fotos;
	
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
	
	@ManyToMany
	private List<Caracteristica> caracteristicas;
	
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
	

	public Vehiculo(Integer idvehiculo, Long precio, String descripcion, Integer anio, Date fecha,
			 String color, Persona idpersona, Ciudad ciudad,
			Modelo modelo, TipoVehiculo tipovehiculo, TipoCombustible tipocombustible,
			Transmision transmision, String placa) {
		super();
		this.idvehiculo = idvehiculo;
		this.precio = precio;
		this.descripcion = descripcion;
		this.anio = anio;
		this.fecha = fecha;
		this.color = color;
		this.idpersona = idpersona;
		this.ciudad = ciudad;
		this.modelo = modelo;
		this.tipovehiculo = tipovehiculo;
		this.tipocombustible = tipocombustible;
		this.transmision = transmision;
		this.placa= placa;
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
	
	public boolean isNuevo() {
		return nuevo;
	}
	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}
	public String getNombre_publicacion() {
		return nombre_publicacion;
	}
	public void setNombre_publicacion(String nombre_publicacion) {
		this.nombre_publicacion = nombre_publicacion;
	}
	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public Long getPrecio() {
		return this.precio;
	}

	public void setPrecio(Long precio) {
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
	
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public java.util.ArrayList<String> getFotos() {
		return fotos;
	}
	public void setFotos(java.util.ArrayList<String> fotos) {
		this.fotos = fotos;
	}
	public Persona getIdpersona() {
		return idpersona;
	}
	public void setIdpersona(Persona idpersona) {
		this.idpersona = idpersona;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public List<Caracteristica> getCaracteristica() {
		return caracteristicas;
	}
	public void setCaracteristica(List<Caracteristica> caracteristica) {
		this.caracteristicas = caracteristica;
	}
	public List<Favorito> getFavoritos() {
		return favoritos;
	}
	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}
	public TipoVehiculo getTipovehiculo() {
		return tipovehiculo;
	}
	public void setTipovehiculo(TipoVehiculo tipovehiculo) {
		this.tipovehiculo = tipovehiculo;
	}
	public TipoCombustible getTipocombustible() {
		return tipocombustible;
	}
	public void setTipocombustible(TipoCombustible tipocombustible) {
		this.tipocombustible = tipocombustible;
	}
	public Transmision getTransmision() {
		return transmision;
	}
	public void setTransmision(Transmision transmision) {
		this.transmision = transmision;
	}
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
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
				+ anio + ", fecha=" + fecha + ", color=" + color
				+ ", idpersona=" + idpersona + ", ciudad=" + ciudad + ", modelo=" + modelo 
				+ ", tipovehiculo=" + tipovehiculo + ", tipocombustible=" + tipocombustible + ", transmision="
				+ transmision + ", placa = " + placa + " ]";
	}
	
	

   
	
}
