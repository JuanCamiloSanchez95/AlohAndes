package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa un hotel  
 */
public class Hotel extends Operador
{
	

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		/**
		* categoria del hotel
		*/
		@JsonProperty(value="categoria")
		private Integer categoria;
		
		/**
		* ubicacion del hotel
		*/
		@JsonProperty(value="ubicacion")
		private String ubicacion;

		/**
		* registro de camara del hotel
		*/
		@JsonProperty(value="registroCamara")
		private String registroCamara;
		
		/**
		* registro de ST del hotel
		*/
		@JsonProperty(value="registroST")
		private String registroST;
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		* Metodo constructor de la clase hotel 
		* <b>post: </b> Crea el hotel con los valores que entran por parametro
		* @param id - Id del hotel.
		* @param nombre - Nombre del hotel.
		* @param categoria - Categoria del hotel
		* @param ubicacion - Ubicacion del hotel 
		* @param registroCamara - Registro de la camara del hotel
		* @param registroST -  Registro del ST  del hotel 
		*/
		public Hotel(@JsonProperty(value="id") Long id,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="categoria") Integer categoria,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="registroCamara")String registroCamara,@JsonProperty(value="registroST") String registroST )
		{
			super(id, nombre);
			this.categoria=categoria;
			this.ubicacion=ubicacion;
			this.registroCamara=registroCamara;
			this.registroST= registroST;	
		}

		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------
			
		public Integer getCategoria() {
			return categoria;
		}

		public void setCategoria(Integer categoria) {
			this.categoria = categoria;
		}

		public String getUbicacion() {
			return ubicacion;
		}

		public void setUbicacion(String ubicacion) {
			this.ubicacion = ubicacion;
		}

		public String getRegistroCamara() {
			return registroCamara;
		}

		public void setRegistroCamara(String registroCamara) {
			this.registroCamara = registroCamara;
		}

		public String getRegistroST() {
			return registroST;
		}

		public void setRegistroST(String registroST) {
			this.registroST = registroST;
		}
	
}
