package vos;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Cristian
 * Clase que representa el uso del sistema por parte de un Cliente
 */
public class UsoCliente {
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Id del cliente
	 */
	@JsonProperty(value="idCliente")
	private int idCliente;
	
	/**
	 * Tipo del cliente
	 */
	@JsonProperty(value="tipoCliente")
	private String tipoCliente;
	
	/**
	 * Dias contratados
	 */
	@JsonProperty(value="diasContratados")
	private int diasContratados;
	
	/**
	 * Tipo del Alojamiento
	 */
	@JsonProperty(value="tipoAlojamiento")
	private String tipoAlojamiento;
	
	/**
	 * Descripcion
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	/**
	 * Costo pagado
	 */
	@JsonProperty(value="costoPagado")
	private double costoPagado;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo constructor de la clase UsoCliente
	 * <b>post: </b> Crea un objeto UsoCliente con los valores que entran por parametro
	 * @param idCliente - id del cliente
	 * @param tipoCliente - tipo del cliente
	 * @param diasContratados - dis contratados por el cliente.
	 * @param tipoAlojamiento - tipo de alojamiento contratado.
	 * @param costoPagado - Costo pagado por alojamiento
	 * @param descripcion - Descripcion por alojamiento
	 */
	public UsoCliente(@JsonProperty(value="idCliente") Integer idCliente,@JsonProperty(value="tipoCliente") String tipoCliente,@JsonProperty(value="diasContratados") Integer diasContratados,@JsonProperty(value="tipoAlojamiento") String tipoAlojamiento,@JsonProperty(value="descripcion") String descripcion,@JsonProperty(value="costoPagado") double costoPagado) 
	{
		this.idCliente=idCliente;
		this.tipoCliente=tipoCliente;
		this.diasContratados= diasContratados;
		this.tipoAlojamiento=tipoAlojamiento;
		this.descripcion=descripcion;
		this.costoPagado=costoPagado;
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	//----------------------------------------------------------------------------------------------------------------------------------

	public int getDiasContratados() {
		return diasContratados;
	}

	public String getTipoAlojamiento() {
		return tipoAlojamiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getCostoPagado() {
		return costoPagado;
	}

	public void setDiasContratados(int diasContratados) {
		this.diasContratados = diasContratados;
	}

	public void setTipoAlojamiento(String tipoAlojamiento) {
		this.tipoAlojamiento = tipoAlojamiento;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCostoPagado(double costoPagado) {
		this.costoPagado = costoPagado;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
}
