package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class OperadorRFC12 {
	/**
	 * Numero de Semana
	 */
	@JsonProperty(value = "semana")
	private int semana;
	
	
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
	public OperadorRFC12(int semana, double ingresos, int numReservas) {
		this.semana = semana;
		this.ingresos = ingresos;
		this.numReservas = numReservas;
	}

	public int getSemana() {
		return semana;
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

	public void setSemana(int semana) {
		this.semana = semana;
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
