package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Sede
 *
 */
@Entity

public class Sede implements Serializable {
	
	
	@OneToMany(mappedBy = "sede")
	private List<CuentaAhorros> cuentas;
	
	@ManyToOne
	@JoinColumn(name = "codigo_ciudad", nullable = false)
	private Ciudad ciudad;

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_sede")
	private Integer codigo;
	
	@Column(name= "direccion" , nullable = false, length = 150)
	private String direccion;
	
	@Column(name = "telefono", nullable = false)
	private Integer telefono;
	private static final long serialVersionUID = 1L;

	public Sede() {
		super();
	}   
	public Integer getCodigo() {
		return this.codigo;	
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}   
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}   
	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	
	
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
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
		Sede other = (Sede) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
   
}
