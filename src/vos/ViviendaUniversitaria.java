package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * Clase que representa una viviendaUniversitaria  
 */
public class ViviendaUniversitaria extends Operador 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	* Metodo constructor de la clase Vivienda Universitaria 
	* <b>post: </b> Crea la Vivienda Universitaria con los valores que entran por parametro
	* @param id - Id de la Vivienda Universitaria.
	* @param nombre - Nombre de la Vivienda Universitaria.
	*/
	
	public ViviendaUniversitaria(@JsonProperty(value="id") int id,@JsonProperty(value="nombre") String nombre)
	{
		super(id, nombre,"Vivienda Universitaria");
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------
		
}
