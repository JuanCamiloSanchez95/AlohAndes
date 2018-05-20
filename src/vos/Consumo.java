package vos;

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
	
	
}
