package vos;

import java.sql.Date;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
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
		 * Capacidad de los alojamientos de la oferta
		 */
		@JsonProperty(value="capacidad")
		private int capacidad;
		
		/**
		 * Lista de reservas
		 */
		@JsonProperty(value="reservas")
		private ArrayList<Reserva> reservas;
		
		/**
		 * Alojamiento  de la oferta
		 */
		@JsonProperty(value="alojamiento")
		private Alojamiento alojamiento;
		
		
		/**
		 * Operador  de la oferta
		 */
		@JsonProperty(value="Operador")
		private Operador operador;
		
		/**
		 * Indice de ocupacion
		 */
		
	private double indiceOcupacion;
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Metodo constructor de la clase oferta
		 * <b>post: </b> Crea la oferta con los valores que entran por parametro
		 * @param id - Id de la oferta.
		 * @param precioEstadia -precio de la oferta
		 * @param nombre - nombre de la oferta
		 * @param descripcion - descripccion de la oferta
		 * @param fechaPublicacion -fecha de la publicacion de la oferta
		 */
		public Oferta(@JsonProperty(value="id") Integer id,@JsonProperty(value="precioEstadia") Double precioEstadia,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="descripcion") String descripccion,@JsonProperty(value="fechaPublicacion") Date fechaPublicacion,@JsonProperty(value="capacidad") Integer capacidad) 
		{
			this.id=id;
			this.precioEstadia=precioEstadia;
			this.nombre=nombre;
			this.descripcion=descripccion;
			this.fechaPublicacion=fechaPublicacion;
			this.capacidad=capacidad;
			reservas= new ArrayList<>();
			operador=null;
			alojamiento=null;
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

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		public Alojamiento getAlojamiento() {
			return alojamiento;
		}

		public void setAlojamiento(Alojamiento alojamiento) {
			this.alojamiento = alojamiento;
		}

		public Operador getOperador() {
			return operador;
		}

		public void setOperador(Operador operador) {
			this.operador = operador;
		}

		public double getIndiceOcupacion() {
			return indiceOcupacion;
		}


		public void setIndiceOcupacion(double indiceOcupacion) {
			this.indiceOcupacion = indiceOcupacion;
		}


		
}
