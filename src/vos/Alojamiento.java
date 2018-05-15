package vos;

import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Alojamiento 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del alojamiento
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * Nombre del alojamiento
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * Tipo del alojamiento
	 */
	@JsonProperty(value="tipo")
	private String tipo;

	/**
	 * ubicacion del alojamiento
	 */
	@JsonProperty(value="ubicacion")
	private String ubicacion;

	/**
	 * descripcion del alojamiento
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;

	/**
	 * costo del alojamiento
	 */
	@JsonProperty(value="costo")
	private double costo;

	/**
	 * minimoPeriodo del alojamiento
	 */
	@JsonProperty(value="minimoPeriodo")
	private int minimoPeriodo;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	public Alojamiento(@JsonProperty(value="id") Integer id,@JsonProperty(value="nombre") String nombre, @JsonProperty(value="tipo") String tipo,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="descripcion") String descripcion,@JsonProperty(value="costo") double costo, @JsonProperty(value="minimoPeriodo")int minimoPeriodo) 
	{
		this.id= id;
		this.nombre=nombre;
		this.tipo=tipo;
		this.ubicacion=ubicacion;
		this.descripcion=descripcion;
		this.costo=costo;
		this.minimoPeriodo=minimoPeriodo;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS
	//----------------------------------------------------------------------------------------------------------------------------------


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getMinimoPeriodo() {
		return minimoPeriodo;
	}

	public void setMinimoPeriodo(int minimoPeriodo) {
		this.minimoPeriodo = minimoPeriodo;
	}
}
