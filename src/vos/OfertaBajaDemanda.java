package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class OfertaBajaDemanda {

	/**
	 * Id de la oferta
	 */
	@JsonProperty(value="id")
	private Long id;
	
	/**
	 * Nombre de la oferta
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	 /**
	 * Numeros de reservas de la oferta
	 */
	@JsonProperty(value="numeroReservas")
	private int numeroReservas;
	
	 /**
	 * Maxima distancia entre reservas de la oferta
	 */
	@JsonProperty(value="distanciaReservas")
	private int distanciaReservas;
	
	
	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param numeroReservas
	 */
	public OfertaBajaDemanda(@JsonProperty(value="id") Long id, @JsonProperty(value="nombre") String nombre, @JsonProperty(value="numeroReservas") int numeroReservas,@JsonProperty(value="distanciaReservas") int distanciaReservas) {
		this.id = id;
		this.nombre = nombre;
		this.numeroReservas = numeroReservas;
		this.distanciaReservas = distanciaReservas;
	}


	public Long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public int getNumeroReservas() {
		return numeroReservas;
	}


	public int getDistanciaReservas() {
		return distanciaReservas;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setNumeroReservas(int numeroReservas) {
		this.numeroReservas = numeroReservas;
	}


	public void setDistanciaReservas(int distanciaReservas) {
		this.distanciaReservas = distanciaReservas;
	}
	
}
