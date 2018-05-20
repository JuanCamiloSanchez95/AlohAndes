package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class EstadisticaOperacion {

	/**
	 * Dia/Mes/Año o Semana
	 */
	@JsonProperty(value = "periodo")
	private String periodo;
	
	
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


	public EstadisticaOperacion(@JsonProperty(value = "periodo") String periodo, @JsonProperty(value = "ingresos") double ingresos,@JsonProperty(value = "numReservas") int numReservas) {
		this.periodo = periodo;
		this.ingresos = ingresos;
		this.numReservas = numReservas;
	}


	public String getPeriodo() {
		return periodo;
	}


	public double getIngresos() {
		return ingresos;
	}


	public int getNumReservas() {
		return numReservas;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}


	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}


	public void setNumReservas(int numReservas) {
		this.numReservas = numReservas;
	}
	
}
