package vos;

import java.sql.Date;

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
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
		
		
		
}
