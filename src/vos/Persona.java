package vos;




import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * Clase que representa una persona natural  
 */
public class Persona extends Operador
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * vinculo de la persona 
	 */
	@JsonProperty(value="vinculo")
	private String vinculo;
	

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

		/**
		* Metodo constructor de la clase Persona Natural 
		* <b>post: </b> Crea la Persona Natural  con los valores que entran por parametro
		* @param id - Id de la Persona Natural .
		* @param nombre - Nombre de la Persona Natural  .
		* @param vinculo - vinculo de la Persona Natural 
		*/
		public Persona(@JsonProperty(value="id") Long id,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="vinculo") String vinculo )
		{
			super(id,nombre,"Persona");
			this.vinculo=vinculo;	
		}

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------


		public String getVinculo() {
			return vinculo;
		}

		public void setVinculo(String vinculo) {
			this.vinculo = vinculo;
		}
		
}
