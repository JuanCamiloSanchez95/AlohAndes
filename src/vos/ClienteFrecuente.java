package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa un cliente frecuente para un alojamiento.
 * @author Cristian 
 */
public class ClienteFrecuente {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
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
	 * Numero de usos
	 */
	@JsonProperty(value="numUsos")
	private int numUsos;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Clase constructor para la consulta de cliente frecuente
	 * @param nombre - nombre del cliente
	 * @param vinculo - vinculo del cliente
	 * @param numUsos - numero de usos en el alojamiento
	 */
	public ClienteFrecuente(@JsonProperty(value="nombre") String nombre,@JsonProperty(value="vinculo") String vinculo,@JsonProperty(value="numUsos") int numUsos) {
		this.nombre = nombre;
		this.vinculo = vinculo;
		this.numUsos = numUsos;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public String getVinculo() {
		return vinculo;
	}

	public int getNumUsos() {
		return numUsos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public void setNumUsos(int numUsos) {
		this.numUsos = numUsos;
	}
	
	
}
