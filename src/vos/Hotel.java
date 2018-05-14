package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Cristian
 * Clase que representa un hotel  
 */
public class Hotel extends Operador
{
	

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		
	/**
	 * Nombre del Hotel
	 */
	private String nombreHotel;
	
	/**
		* Categoria del hotel
		*/
		@JsonProperty(value="categoria")
		private Integer categoria;
		

		/**
		* Registro de camara del hotel
		*/
		@JsonProperty(value="registroCamara")
		private String registroCamara;
		
		/**
		* Registro de ST del hotel
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
		* @param nombre - Nombre del operador
		* @param nombreHotel - Nombre del hotel.
		* @param categoria - Categoria del hotel
		* @param tipo - Hotel 
		* @param registroCamara - Registro de la camara del hotel
		* @param registroST -  Registro del ST  del hotel 
		*/
		public Hotel(@JsonProperty(value="id") Long id,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="nombre") String nombreHotel, @JsonProperty(value="categoria") Integer categoria,@JsonProperty(value="registroCamara")String registroCamara,@JsonProperty(value="registroST") String registroST )
		{
			super(id, nombre,"Hotel");
			
			this.nombreHotel = nombreHotel;
			this.categoria=categoria;
			this.registroCamara=registroCamara;
			this.registroST= registroST;
		}

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------
			
		public String getNombreHotel() {
			return nombreHotel;
		}

		public void setNombreHotel(String nombreHotel) {
			this.nombreHotel = nombreHotel;
		}

		public Integer getCategoria() {
			return categoria;
		}

		public void setCategoria(Integer categoria) {
			this.categoria = categoria;
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
