package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Cristian Clase que representa el uso del sistema por parte de un tipo
 *         de cliente
 */
public class UsoTipo {
	// ----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Tipo del cliente
	 */
	@JsonProperty(value = "tipoCliente")
	private String tipoCliente;

	/**
	 * Dias contratados
	 */
	@JsonProperty(value = "diasContratados")
	private int diasContratados;

	/**
	 * Tipo del Alojamiento
	 */
	@JsonProperty(value = "alojamientoFrecuente")
	private String alojamientoFrecuente;

	/**
	 * Costo pagado
	 */
	@JsonProperty(value = "dineroPagado")
	private double dineroPagado;

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase UsoTipo
	 *  <b>post: </b> Crea un objeto UsoTipo con los valores que entran por parametro
	 * 
	 * @param tipoCliente - tipo del cliente
	 * @param diasContratados- suma de dias contratados por el tipo de cliente.
	 * @param alojamientoFrecuente - tipo de alojamiento mas frecuentamente contratado.
	 * @param dineroPagado - Dinero total pagado por todos los alojamientos
	 */
	public UsoTipo(@JsonProperty(value = "tipoCliente") String tipoCliente,
			@JsonProperty(value = "diasContratados") Integer diasContratados,
			@JsonProperty(value = "alojamientoFrecuente") String alojamientoFrecuente,
			@JsonProperty(value = "dineroPagado") double dineroPagado) {
		this.tipoCliente = tipoCliente;
		this.diasContratados = diasContratados;
		this.alojamientoFrecuente = alojamientoFrecuente;
		this.dineroPagado = dineroPagado;

	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	// ----------------------------------------------------------------------------------------------------------------------------------

	public int getDiasContratados() {
		return diasContratados;
	}

	public String getAlojamientoFrecuente() {
		return alojamientoFrecuente;
	}

	public double getDineroPagado() {
		return dineroPagado;
	}

	public void setDiasContratados(int diasContratados) {
		this.diasContratados = diasContratados;
	}

	public void setAlojamientoFrecuente(String alojamientoFrecuente) {
		this.alojamientoFrecuente = alojamientoFrecuente;
	}

	public void setDineroPagado(double dineroPagado) {
		this.dineroPagado = dineroPagado;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
}
