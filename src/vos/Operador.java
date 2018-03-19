package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa un operador 
 */
public class Operador {
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Id del operador
	 */
	@JsonProperty(value="id")
	private Long id;
	
	/**
	 * Nombre del operador
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo constructor de la clase operador
		 * <b>post: </b> Crea el operador con los valores que entran por parametro
		 * @param id - Id del operador.
		 * @param nombre - Nombre del operador.
		 */
		public Operador(@JsonProperty(value="id") Long id,@JsonProperty(value="nombre") String nombre) 
		{
			this.id= id;
			this.nombre=nombre;
		}


		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------
			

		/**
		 * obtener  el id  del operador 
		 * @return id 
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Cambia el id de un operador 
		 * @param id
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * obtener  el nombre  del operador 
		 * @return nombre 
		 */
		public String getNombre() {
			return nombre;
		}

		/**
		 * Cambia el nombre de un operador 
		 * @param nombre
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		

	
}
