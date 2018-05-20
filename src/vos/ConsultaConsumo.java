package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConsultaConsumo {
	/**
	 * Id del Operador
	 */
	@JsonProperty(value = "idOferta")
	private Long idOferta;

	/**
	 * Fecha inicio de consulta
	 */
	@JsonProperty(value = "fechaInicio")
	private Date fechaInicio;
	
	/**
	 * Fecha inicio de consulta
	 */
	@JsonProperty(value = "fechaFinal")
	private Date fechaFinal;
	
	/**
	 * Criterio de Ordenamiento
	 */
	@JsonProperty(value = "criterioOrdenamiento")
	private String criterioOrdenamiento;
	
	/**
	 * Criterio de Agrupamiento
	 */
	@JsonProperty(value = "criterioAgrupamiento")
	private String criterioAgrupamiento;

	public ConsultaConsumo(@JsonProperty(value = "idOferta")Long idOferta,@JsonProperty(value = "fechaInicio") Date fechaInicio,
			@JsonProperty(value = "fechaFinal") Date fechaFinal,@JsonProperty(value = "criterioOrdenamiento") String criterioOrdenamiento,
			@JsonProperty(value = "criterioAgrupamiento") String criterioAgrupamiento) {
		this.idOferta = idOferta;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.criterioOrdenamiento = criterioOrdenamiento;
		this.criterioAgrupamiento = criterioAgrupamiento;
	}
	
	
	public ConsultaConsumo(@JsonProperty(value = "idOferta")Long idOferta,@JsonProperty(value = "fechaInicio") Date fechaInicio,
			@JsonProperty(value = "fechaFinal") Date fechaFinal) {
		this.idOferta = idOferta;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}


	public Long getIdOferta() {
		return idOferta;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public Date getFechaFinal() {
		return fechaFinal;
	}


	public String getCriterioOrdenamiento() {
		return criterioOrdenamiento;
	}


	public String getCriterioAgrupamiento() {
		return criterioAgrupamiento;
	}


	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}


	public void setCriterioOrdenamiento(String criterioOrdenamiento) {
		this.criterioOrdenamiento = criterioOrdenamiento;
	}


	public void setCriterioAgrupamiento(String criterioAgrupamiento) {
		this.criterioAgrupamiento = criterioAgrupamiento;
	}
	
	
}
