package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una viviendaUniversitaria  
 */
public class ViviendaUniversitaria extends Operador 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
			
	/**
	* ubicacion de la vivienda Universitaria
	*/
	@JsonProperty(value="ubicacion")
	private String ubicacion;

	/**
	* capacidad de la vivienda universitaria 
	*/
	@JsonProperty(value="capacidad")
	private Integer capacidad;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	* Metodo constructor de la clase Vivienda Universitaria 
	* <b>post: </b> Crea la Vivienda Universitaria con los valores que entran por parametro
	* @param id - Id de la Vivienda Universitaria.
	* @param nombre - Nombre de la Vivienda Universitaria.
	* @param capacidad - Capacidad de la Vivienda Universitaria
	* @param ubicacion - Ubicacion de la Vivienda Universitaria
	*/
	
	public ViviendaUniversitaria(@JsonProperty(value="id") int id,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="capacidad") Integer capacidad,@JsonProperty(value="ubicacion") String ubicacion )
	{
		super(id, nombre);
		this.capacidad=capacidad;
		this.ubicacion=ubicacion;	
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------
		
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	
	
	
	
}
