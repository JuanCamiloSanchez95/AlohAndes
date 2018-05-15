package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase de Apoyo para RFC1
 * @author Cristian
 */
 
public class DineroOperador {

	/**
	 * Nombre de Operador
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Nombre de Operador
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	/**
	 * Dinero ganado por Operador
	 */
	@JsonProperty(value="dinero")
	private double dinero;
	
	/**
	 * Metodo constructor de la clase de apoyo
	 * @param nombre - nombre del operador
	 * @param dinero - dinero ganado por el operador
	 */
	public DineroOperador(@JsonProperty(value="nombre")String nombre,@JsonProperty(value="tipo")String tipo,@JsonProperty(value="dinero") double dinero) {
		this.nombre= nombre;
		this.tipo=tipo;
		this.dinero = dinero;
	}

	public String getNombre() {
		return nombre;
	}

	public double getDinero() {
		return dinero;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
}
