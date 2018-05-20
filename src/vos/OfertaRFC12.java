package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class OfertaRFC12 {
	
	/**
	 * Ingreso en el periodo
	 */
	@JsonProperty(value = "ingresos")
	private double ingresos;
	
	
	/**
	 * Numero de Reservas en el periodo
	 */
	@JsonProperty(value = "numReservas")
	private int numReservas;
	
	/**
	 * Oferta asociada
	 */
	@JsonProperty(value = "oferta")
	private Oferta oferta;
	
	
/**
 * Metodo constructor para la oferta del Requerimiento RFC12
 * @param ingresos - ingresos en la semana
 * @param numReservas - numero de reservas en la semana
 */
	public OfertaRFC12(@JsonProperty(value = "ingresos") double ingresos,@JsonProperty(value = "numReservas") int numReservas) {
		this.ingresos = ingresos;
		this.numReservas = numReservas;
	}

	public double getIngresos() {
		return ingresos;
	}

	public int getNumReservas() {
		return numReservas;
	}


	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}

	public void setNumReservas(int numReservas) {
		this.numReservas = numReservas;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}


}
