package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ClienteBueno {

	/**
	 * Numero de reservas
	 */
	@JsonProperty(value="numReservas")
	private int numReservas;
	
	/**
	 * Promedio de reservas mensuales
	 */
	@JsonProperty(value="reservasMensuales")
	private double reservasMensuales;
	
	/**
	 * Numero de reservas en suites
	 */
	@JsonProperty(value="reservasSuites")
	private int reservasSuites;
	
	/**
	 * Numero de reservas costosas
	 */
	@JsonProperty(value="reservasCostosas")
	private int reservasCostosas;
	
	/**
	 * Cliente bueno
	 */
	@JsonProperty(value="cliente")
	private Cliente cliente;
	
	/**
	 * COnstructor vacio de la clase ClienteBueno
	 */
	public ClienteBueno() {
		
	}

	public int getNumReservas() {
		return numReservas;
	}

	public double getReservasMensuales() {
		return reservasMensuales;
	}

	public int getReservasSuites() {
		return reservasSuites;
	}

	public int getReservasCostosas() {
		return reservasCostosas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setNumReservas(int numReservas) {
		this.numReservas = numReservas;
	}

	public void setReservasMensuales(double reservasMensuales) {
		this.reservasMensuales = reservasMensuales;
	}

	public void setReservasSuites(int reservasSuites) {
		this.reservasSuites = reservasSuites;
	}

	public void setReservasCostosas(int reservasCostosas) {
		this.reservasCostosas = reservasCostosas;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
