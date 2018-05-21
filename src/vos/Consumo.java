package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class Consumo {

	/**
	 * Id del cliente
	 */
	@JsonProperty(value="documento")
	private Long documento;
	
	/**
	 * Nombre del cliente
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * vinculo del cliente
	 */
	@JsonProperty(value="vinculo")
	private String vinculo;
	
	/**
	 * Tipo del alojamiento
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	/**
	 * Fecha de la reserva
	 */
	@JsonProperty(value="fecha")
	private Date fecha;
	
	/**
	 * Dias de la reserva
	 */
	@JsonProperty(value="dias")
	private int dias;

	public Consumo(@JsonProperty(value="documento") Long documento,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="vinculo") String vinculo,@JsonProperty(value="tipo") String tipo,@JsonProperty(value="fecha") Date fecha,@JsonProperty(value="dias") int dias) {
		this.documento = documento;
		this.nombre = nombre;
		this.vinculo = vinculo;
		this.tipo = tipo;
		this.fecha = fecha;
		this.dias = dias;
	}
	
}
