package vos;

import java.sql.Date;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una oferta 
 */
public class Oferta 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Id de la oferta
		 */
		@JsonProperty(value="id")
		private int id;
		
		/**
		 * longitud de estadia de la oferta
		 */
		@JsonProperty(value="longitudEstadia")
		private int longitudEstadia;
		
		/**
		 * precio de estadia de la oferta
		 */
		@JsonProperty(value="precioEstadia")
		private double precioEstadia;
		
		/**
		 * Nombre de la oferta
		 */
		@JsonProperty(value="nombre")
		private String nombre;
		
		/**
		 * ubicacion de la oferta
		 */
		@JsonProperty(value="ubicacion")
		private String ubicacion;
		
		/**
		 * descripcion de la oferta
		 */
		@JsonProperty(value="descripcion")
		private String descripcion;
		
		/**
		 * Fecha de publicacion de la oferta
		 */
		@JsonProperty(value="fechaPublicacion")
		private Date fechaPublicacion;
		
		/**
		 * Lista de reservas
		 */
		@JsonProperty(value="reservas")
		private ArrayList<Reserva> reservas;
		
		/**
		 * Habitacion de la oferta
		 */
		@JsonProperty(value="habitacion")
		private Habitacion habitacion;
		
		/**
		 * Vivienda de la oferta
		 */
		@JsonProperty(value="vivienda")
		private Vivienda vivienda;
		
		/**
		 * Apartamento de la oferta
		 */
		@JsonProperty(value="apartamento")
		private Apartamento apartamento;
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Metodo constructor de la clase oferta
		 * <b>post: </b> Crea la oferta con los valores que entran por parametro
		 * @param id - Id de la oferta.
		 * @param longitudEstadia - descipccion de la oferta.
		 * @param precioEstadia -precio de la oferta
		 * @param nombre - nombre de la oferta
		 * @param ubicacion - ubicacion de la ooferta 
		 * @param descripccion - descripccion de la oferta
		 * @param fechaPublicacion -fecha de la publicacion de la oferta
		 */
		public Oferta(@JsonProperty(value="id") Integer id,@JsonProperty(value="longitudEstadia") Integer longitudEstadia,@JsonProperty(value="precioEstadia") Double precioEstadia,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="descripccion") String descripccion,@JsonProperty(value="fechaPublicacion") Date fechaPublicacion) 
		{
			this.id=id;
			this.longitudEstadia=longitudEstadia;
			this.precioEstadia=precioEstadia;
			this.nombre=nombre;
			this.ubicacion=ubicacion;
			this.descripcion=descripccion;
			this.fechaPublicacion=fechaPublicacion;
			reservas= new ArrayList<>();
			habitacion=null;
			apartamento=null;
			vivienda=null;
		}

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getLongitudEstadia() {
			return longitudEstadia;
		}

		public void setLongitudEstadia(int longitudEstadia) {
			this.longitudEstadia = longitudEstadia;
		}

		public double getPrecioEstadia() {
			return precioEstadia;
		}

		public void setPrecioEstadia(double precioEstadia) {
			this.precioEstadia = precioEstadia;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getUbicacion() {
			return ubicacion;
		}

		public void setUbicacion(String ubicacion) {
			this.ubicacion = ubicacion;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Date getFechaPublicacion() {
			return fechaPublicacion;
		}

		public void setFechaPublicacion(Date fechaPublicacion) {
			this.fechaPublicacion = fechaPublicacion;
		}

		public ArrayList<Reserva> getReservas() {
			return reservas;
		}

		public void setReservas(ArrayList<Reserva> reservas) {
			this.reservas = reservas;
		}

		public Habitacion getHabitacion() {
			return habitacion;
		}

		public void setHabitacion(Habitacion habitacion) {
			this.habitacion = habitacion;
		}

		public Vivienda getVivienda() {
			return vivienda;
		}

		public void setVivienda(Vivienda vivienda) {
			this.vivienda = vivienda;
		}

		public Apartamento getApartamento() {
			return apartamento;
		}

		public void setApartamento(Apartamento apartamento) {
			this.apartamento = apartamento;
		}
		
		
		
		
}
