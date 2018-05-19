package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class AnalisisOperacion {

	@JsonProperty(value = "categoria")
	private String categoria;
	
	@JsonProperty(value = "unidadTiempo")
	private String unidadTiempo;
	
	/**
	 * Periodo con mayor demanda
	 */
	@JsonProperty(value = "mayorDemanda")
	private EstadisticaOperacion mayorDemanda;
	
	/**
	 * Periodo con mayor ingresos
	 */
	@JsonProperty(value = "mayorIngreso")
	private EstadisticaOperacion mayorIngreso;
	
	/**
	 * Periodo con menor ocupacion
	 */
	@JsonProperty(value = "menorOcupacion")
	private EstadisticaOperacion menorOcupacion;

	public AnalisisOperacion(String categoria, String unidadTiempo) {
		this.categoria = categoria;
		this.unidadTiempo = unidadTiempo;
		this.mayorDemanda = null;
		this.mayorIngreso = null;
		this.menorOcupacion = null;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getUnidadTiempo() {
		return unidadTiempo;
	}

	public EstadisticaOperacion getMayorDemanda() {
		return mayorDemanda;
	}

	public EstadisticaOperacion getMayorIngreso() {
		return mayorIngreso;
	}

	public EstadisticaOperacion getMenorOcupacion() {
		return menorOcupacion;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setUnidadTiempo(String unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}

	public void setMayorDemanda(EstadisticaOperacion mayorDemanda) {
		this.mayorDemanda = mayorDemanda;
	}

	public void setMayorIngreso(EstadisticaOperacion mayorIngreso) {
		this.mayorIngreso = mayorIngreso;
	}

	public void setMenorOcupacion(EstadisticaOperacion menorOcupacion) {
		this.menorOcupacion = menorOcupacion;
	}
}
