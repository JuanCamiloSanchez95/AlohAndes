package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una habitacionUniversitaria  
 */
public class HabitacionUniversitaria extends Habitacion
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
			
	/**
	 * ubicacion de la habitacion
	 */
	@JsonProperty(value="ubicacion")
	private String ubicacion;
	
	/**
	 * descripccion de la habitacion
	 */
	@JsonProperty(value="descripccion")
	private String descripccion;
	
	/**
	 * indicador si  la habitacion es compartida
	 */
	@JsonProperty(value="compartida")
	private boolean compartida;
	
	/**
	 * ViviendaUniversitaria a la que pertenece la  habitacion
	 */
	@JsonProperty(value="viviendaU")
	private ViviendaUniversitaria viviendaU;
	//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo constructor de la clase habitacionH
		 * <b>post: </b> Crea la habitacionH con los valores que entran por parametro
		 * @param id - Id de la habitacionH.
		 * @param descripccion - descipccion de la habitacionH.
		 * @param precio -precio de la habitacionH
		 * @param periodoFacturacion - periodo en el que factura  la  habitacionH
		 * @param capacidad - capacidad de la habitacioH
		 * @param tipo - tipo de la habitacionH
		 */
		public HabitacionUniversitaria(@JsonProperty(value="id") Integer id,@JsonProperty(value="precio") Double precio,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="descripccion") String descripccion,@JsonProperty(value="periodoFacturacion") int periodoFacturacion,@JsonProperty(value="compartida") boolean  compartida)
		{
			super(id, descripccion, precio, periodoFacturacion);
			this.ubicacion=ubicacion;
			this.descripccion=descripccion;
			this.compartida=compartida;
			viviendaU=null;
		}

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------
		
		public String getUbicacion() {
			return ubicacion;
		}

		public void setUbicacion(String ubicacion) {
			this.ubicacion = ubicacion;
		}

		public String getDescripccion() {
			return descripccion;
		}

		public void setDescripccion(String descripccion) {
			this.descripccion = descripccion;
		}

		public boolean isCompartida() {
			return compartida;
		}

		public void setCompartida(boolean compartida) {
			this.compartida = compartida;
		}

		public ViviendaUniversitaria getViviendaU() {
			return viviendaU;
		}

		public void setViviendaU(ViviendaUniversitaria viviendaU) {
			this.viviendaU = viviendaU;
		}
		
		
	
}
