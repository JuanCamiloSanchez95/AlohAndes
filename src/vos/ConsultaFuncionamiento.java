package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConsultaFuncionamiento {
	
	/**
	 * Numero de Semana
	 */
	@JsonProperty(value = "semana")
	private int semana;
	
	/**
	 * Oferta con mayor ocupacion
	 */
	@JsonProperty(value = "ofertaMayorOcupacion")
	private OfertaRFC12 ofertaMayorOcupacion;
	
	/**
	 * Oferta con menor ocupacion
	 */
	@JsonProperty(value = "ofertaMenorOcupacion")
	private OfertaRFC12 ofertaMenorOcupacion;
	
	/**
	 * Alojamiento con mayor ocupacion
	 */
	@JsonProperty(value = "operadorMayorSolicitud")
	private OperadorRFC12 operadorMayorSolicitud;
	
	/**
	 * Alojamiento con menor ocupacion
	 */
	@JsonProperty(value = "operadorMenorSolicitud")
	private OperadorRFC12 operadorMenorSolicitud;

	/**
	 * Metodo constructor de una consulta de funcionamiento en una semana dada
	 * @param semana - semana de consulta
	 */
	public ConsultaFuncionamiento(@JsonProperty(value = "semana") int semana) {
		this.semana=semana;
	}

	public int getSemana() {
		return semana;
	}

	public OfertaRFC12 getOfertaMayorOcupacion() {
		return ofertaMayorOcupacion;
	}

	public OfertaRFC12 getOfertaMenorOcupacion() {
		return ofertaMenorOcupacion;
	}

	public OperadorRFC12 getOperadorMayorSolicitud() {
		return operadorMayorSolicitud;
	}

	public OperadorRFC12 getOperadorMenorSolicitud() {
		return operadorMenorSolicitud;
	}

	public void setSemana(int semana) {
		this.semana = semana;
	}

	public void setOfertaMayorOcupacion(OfertaRFC12 ofertaMayorOcupacion) {
		this.ofertaMayorOcupacion = ofertaMayorOcupacion;
	}

	public void setOfertaMenorOcupacion(OfertaRFC12 ofertaMenorOcupacion) {
		this.ofertaMenorOcupacion = ofertaMenorOcupacion;
	}

	public void setOperadorMayorSolicitud(OperadorRFC12 operadorMayorSolicitud) {
		this.operadorMayorSolicitud = operadorMayorSolicitud;
	}

	public void setOperadorMenorSolicitud(OperadorRFC12 operadorMenorSolicitud) {
		this.operadorMenorSolicitud = operadorMenorSolicitud;
	}




}
