package vos;



import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * Clase que representa una habitacion  
 */
public class HabitacionH extends Alojamiento
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * capacidad de la habitacion
		 */
		@JsonProperty(value="capacidad")
		private int capacidad;
		
		/**
		 * tipo de la habitacion
		 */
		@JsonProperty(value="tipoHabitacion")
		private String tipoHabitacion;
		

		/**
		 * numeroHabitacion de la habitacion
		 */
		@JsonProperty(value="numeroHabitacion")
		private int numeroHabitacion;
		
		
		/**
		 * ubicacion de la habitacion
		 */
		@JsonProperty(value="ubicacionH")
		private String ubicacionH;
		
		/**
		 * tamano de la habitacion
		 */
		@JsonProperty(value="tamano")
		private String tamano;
		

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * 
		 * @param id
		 * @param nombre
		 * @param tipo
		 * @param ubicacion
		 * @param descripcion
		 * @param costo
		 * @param minimoPeriodo
		 * @param capacidad
		 * @param tipoHabitacion
		 * @param numeroHabitacion
		 * @param ubicacionH
		 * @param tamano
		 */
		public HabitacionH(@JsonProperty(value="id") Integer id,@JsonProperty(value="nombre") String nombre, @JsonProperty(value="tipo") String tipo,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="descripcion") String descripcion,@JsonProperty(value="costo") double costo, @JsonProperty(value="minimoPeriodo")int minimoPeriodo,@JsonProperty(value="capacidad")int capacidad,@JsonProperty(value="tipoHabitacion")String tipoHabitacion,@JsonProperty(value="numeroHabitacion")int numeroHabitacion,@JsonProperty(value="ubicacionH")String ubicacionH,@JsonProperty(value="tamano")String tamano) 
		{
			super(id, nombre, tipo, ubicacion, descripcion, costo, minimoPeriodo);
			this.capacidad=capacidad;
			this.tipoHabitacion=tipoHabitacion;
			this.numeroHabitacion=numeroHabitacion;
			this.ubicacionH=ubicacionH;
			this.tamano=tamano;
		}
			
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------
			

			

			public int getCapacidad() {
				return capacidad;
			}


			public void setCapacidad(int capacidad) {
				this.capacidad = capacidad;
			}


			public String getTipoHabitacion() {
				return tipoHabitacion;
			}


			public void setTipoHabitacion(String tipoHabitacion) {
				this.tipoHabitacion = tipoHabitacion;
			}


			public int getNumeroHabitacion() {
				return numeroHabitacion;
			}


			public void setNumeroHabitacion(int numeroHabitacion) {
				this.numeroHabitacion = numeroHabitacion;
			}


			public String getUbicacionH() {
				return ubicacionH;
			}


			public void setUbicacionH(String ubicacionH) {
				this.ubicacionH = ubicacionH;
			}


			public String getTamano() {
				return tamano;
			}


			public void setTamano(String tamano) {
				this.tamano = tamano;
			}
			
}

