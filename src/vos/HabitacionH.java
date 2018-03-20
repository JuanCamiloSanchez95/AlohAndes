package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una habitacionH  
 */
public class HabitacionH extends Habitacion
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
	 * descripccion de la habitacion
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	/**
	 * Hotel  al que pertenece 
	 */
	@JsonProperty(value="hotel")
	private Hotel hotel;
	
	/**
	 * Hosta  al que pertenece 
	 */
	@JsonProperty(value="hostal")
	private Hotel hostal;
	
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
	public HabitacionH(@JsonProperty(value="id") Integer id,@JsonProperty(value="descripccion") String descripccion,@JsonProperty(value="precio") Double precio,@JsonProperty(value="periodoFacturacion") int periodoFacturacion,@JsonProperty(value="capacidad") int capacidad,@JsonProperty(value="tipo") String tipo)
	{
		super(id, descripccion, precio, periodoFacturacion);
		this.capacidad=capacidad;
		this.tipo=tipo;
		hotel=null;
		hostal=null;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Hotel getHostal() {
		return hostal;
	}

	public void setHostal(Hotel hostal) {
		this.hostal = hostal;
	}
	
}
