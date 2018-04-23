package vos;

import java.sql.Date;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * Clase que representa una reserva  
 */
public class Reserva
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Id de la reserva
	 */
	@JsonProperty(value="id")
	private int id;

	/**
	 * dia de Llegada de la reserva
	 */
	@JsonProperty(value="diaLlegada")
	private Date diaLlegada;


	/**
	 * cantidadDias de la reserva
	 */
	@JsonProperty(value="cantidadDias")
	private int cantidadDias;

	/**
	 * Estado de la reserva
	 */
	@JsonProperty(value="recarga")
	private double recarga;

	/**
	 * Oferta de la reserva
	 */
	@JsonProperty(value="oferta")
	private Oferta oferta;

	/**
	 * Cliente de la reserva
	 */
	@JsonProperty(value="cliente")
	private Cliente cliente;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 *  Metodo constructor de la clase reserva
	 * <b>post: </b> Crea la reserva con los valores que entran por parametro
	 * @param id identificador de la reseva
	 * @param diaLlegada Fecha de llegada 
	 * @param cantidadDias Cantidad de dias reservados
	 * @param recarga Recargo en caso de cancelacion
	 */
	public Reserva(@JsonProperty(value="id") Integer id,@JsonProperty(value="diaLlegada") Date diaLlegada,@JsonProperty(value="cantidadDias") int cantidadDias,@JsonProperty(value="recarga") double recarga) 
	{
		this.id=id;
		this.diaLlegada=diaLlegada;
		this.cantidadDias= cantidadDias;
		this.recarga=recarga;
		oferta=null;
		cliente=null;
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

	public Date getDiaLlegada() {
		return diaLlegada;
	}

	public void setDiaLlegada(Date diaLlegada) {
		this.diaLlegada = diaLlegada;
	}



	public int getCantidadDias() {
		return cantidadDias;
	}

	public void setCantidadDias(int cantidadDias) {
		this.cantidadDias = cantidadDias;
	}

	public double getRecarga() {
		return recarga;
	}

	public void setRecarga(double recarga) {
		this.recarga = recarga;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



}
