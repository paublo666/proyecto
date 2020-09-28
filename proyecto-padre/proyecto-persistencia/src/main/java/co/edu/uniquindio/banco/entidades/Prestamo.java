package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Prestamo
 *
 */
@Entity

public class Prestamo implements Serializable {
	
	@ManyToOne
	@JoinColumn(name = "cedula_cliente", nullable = false)
	private Cliente cliente;

	@Id
	@Column(name= "numero")
	private Integer numero;
	
	@Column(name="valor_total", nullable = false)
	private Integer valor_total;
	
	@Column(name = "tasa_interes", precision = 2, scale = 2, nullable = false)
	private Float tasa_interes;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_inicio", nullable = false)
	private Date fechaInicio;
	
	@Column(name = "plazo_meses", nullable = false)
	private Integer plazoMeses;
	private static final long serialVersionUID = 1L;

	public Prestamo() {
		super();
	}   
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}   
	public Integer getValor_total() {
		return this.valor_total;
	}

	public void setValor_total(Integer valor_total) {
		this.valor_total = valor_total;
	}   
	public Float getTasa_interes() {
		return this.tasa_interes;
	}

	public void setTasa_interes(Float tasa_interes) {
		this.tasa_interes = tasa_interes;
	}   
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}   
	public Integer getPlazoMeses() {
		return this.plazoMeses;
	}

	public void setPlazoMeses(Integer plazoMeses) {
		this.plazoMeses = plazoMeses;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Prestamo other = (Prestamo) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	
   
}
