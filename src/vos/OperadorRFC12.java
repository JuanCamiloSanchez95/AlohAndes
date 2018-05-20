package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class OperadorRFC12 {

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
	 * Operador asociado
	 */
	@JsonProperty(value = "operador")
	private Operador operador;
	
	
/**
 * Metodo constructor para el operador del Requerimiento RFC12
 * @param semana - numero de semana
 * @param ingresos - ingresos en la semana
 * @param numReservas - numero de reservas en la semana
 */
	public OperadorRFC12(@JsonProperty(value = "ingresos") double ingresos,@JsonProperty(value = "numReservas") int numReservas) {
		this.ingresos = ingresos;
		this.numReservas = numReservas;
	}


	public double getIngresos() {
		return ingresos;
	}

	public int getNumReservas() {
		return numReservas;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}

	public void setNumReservas(int numReservas) {
		this.numReservas = numReservas;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}
}
