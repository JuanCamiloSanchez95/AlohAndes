package vos;



import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una persona natural  
 */
public class PersonaNatural extends Operador
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
			
	/**
	 * documento de la persona 
	 */
	@JsonProperty(value="documento")
	private Long documento;
	
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
		* @param documento - Documento de la Persona Natural 
		* @param vinculo - vinculo de la Persona Natural 
		*/
		public PersonaNatural(@JsonProperty(value="id") int id,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="documento") Long documento,@JsonProperty(value="vinculo") String vinculo )
		{
			super(id, nombre);
			this.documento=documento;
			this.vinculo=vinculo;	
		}

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------

		public Long getDocumento() {
			return documento;
		}

		public void setDocumento(Long documento) {
			this.documento = documento;
		}

		public String getVinculo() {
			return vinculo;
		}

		public void setVinculo(String vinculo) {
			this.vinculo = vinculo;
		}
		
		
}
