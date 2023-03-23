package org.teniskia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas")
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descripcion;
	private Double precio;
	private Double talla;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getTalla() {
		return talla;
	}
	public void setTalla(Double talla) {
		this.talla = talla;
	}
	@Override
	public String toString() {
		return "Venta [id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + ", talla=" + talla + "]";
	}
	
	
	
	
	
	

}
