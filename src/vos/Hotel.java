package vos;

import java.util.ArrayList;
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
		
		/**
		 * Habitaciones del Hotel
		 */
		@JsonProperty(value="habitaciones")
		private ArrayList<HabitacionH> habitaciones;
		
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
		public Hotel(@JsonProperty(value="id") int id,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="categoria") Integer categoria,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="registroCamara")String registroCamara,@JsonProperty(value="registroST") String registroST )
		{
			super(id, nombre);
			this.categoria=categoria;
			this.ubicacion=ubicacion;
			this.registroCamara=registroCamara;
			this.registroST= registroST;
			habitaciones= new ArrayList<>();
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


		public ArrayList<HabitacionH> getHabitaciones() {
			return habitaciones;
		}


		public void setHabitaciones(ArrayList<HabitacionH> habitaciones) {
			this.habitaciones = habitaciones;
		}
	
		
}
