package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class IndiceOcupacion {
	
	/**
	 * Id de la oferta
	 */
	@JsonProperty(value="id")
	private Long id;
	

	/**
	 * Nombre de la oferta
	 */
	@JsonProperty(value = "nombre")
	private String nombre;

	/**
	 * Capacidad de los alojamientos de la oferta
	 */
	@JsonProperty(value = "capacidad")
	private int capacidad;

	/**
	 * Indice de ocupacion
	 */
	@JsonProperty(value = "indiceOcupacion")
	private double indiceOcupacion;
	
	/**
	 * Numero de Reservas
	 */
	@JsonProperty(value = "numReservas")
	private int numReservas;
	
	/**
	 * Metodo Constructor de la clase auxiliar Indice de Ocupacion
	 * @param id - identificador de oferta
	 * @param nombre - nombre de la oferta
	 * @param capacidad - capacidad de la oferta
	 * @param numReservas - numero de reservas de la oferta
	 */
	public IndiceOcupacion(@JsonProperty(value = "id") Long id, @JsonProperty(value = "nombre") String nombre, @JsonProperty(value = "capacidad") int capacidad ,@JsonProperty(value = "numReservas") int numReservas) {
		this.id=id;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.numReservas = numReservas;
		this.indiceOcupacion = ((double)numReservas)/capacidad;
	}


	public String getNombre() {
		return nombre;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}


	public double getIndiceOcupacion() {
		return indiceOcupacion;
	}


	public void setIndiceOcupacion(double indiceOcupacion) {
		this.indiceOcupacion = indiceOcupacion;
	}


	public int getNumReservas() {
		return numReservas;
	}


	public void setNumReservas(int numReservas) {
		this.numReservas = numReservas;
	}
}
