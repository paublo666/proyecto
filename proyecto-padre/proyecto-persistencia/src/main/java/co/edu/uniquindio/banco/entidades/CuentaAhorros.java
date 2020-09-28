package co.edu.uniquindio.banco.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CuentaAhorros
 *
 */
@Entity

public class CuentaAhorros implements Serializable {
	
	
	@ManyToOne
	@JoinColumn(name = "codigo_sede", nullable = false)
	private Sede sede; 
	
	@ManyToOne
	@JoinColumn(name = "cedula_cliente", nullable = false)
	private Cliente cliente;

	   
	@Id
	@Column(name = "numero_cuenta")
	private Integer numero;
	
	@Column(name = "saldo", nullable = false)
	private Integer saldo;
	
	@Column(name = "fecha_apertura", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaApertura;
	private static final long serialVersionUID = 1L;

	public CuentaAhorros() {
		super();
	}   
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}   
	public Integer getSaldo() {
		return this.saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}   
	public Date getFechaApertura() {
		return this.fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
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
		CuentaAhorros other = (CuentaAhorros) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	
   
}
