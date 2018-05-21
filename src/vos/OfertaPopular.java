package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class OfertaPopular {
	
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
	 * Metodo constructor de la clase auxiliar Oferta Popular
	 * @param id - id de la oferta
	 * @param nombre - nombre de la oferta
	 * @param numeroReservas - numero de reservas de la oferta
	 */
	public OfertaPopular(@JsonProperty(value="id") Long id, @JsonProperty(value="nombre") String nombre, @JsonProperty(value="numeroReservas") int numeroReservas) {
		this.id = id;
		this.nombre = nombre;
		this.numeroReservas = numeroReservas;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroReservas(int numeroReservas) {
		this.numeroReservas = numeroReservas;
	}


}
