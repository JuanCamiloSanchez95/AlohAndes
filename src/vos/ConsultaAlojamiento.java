package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConsultaAlojamiento {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	@JsonProperty(value="inicio")
	private Date inicio;

	@JsonProperty(value="fin")
	private Date fin;

	@JsonProperty(value="servicios")
	private String servicios;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de ConsultaAlojamiento
	 * @param inicio - Fecha inicio del rango de consulta
	 * @param fin - Fecha fin del ranfo de consulta
	 * @param servicios - Servicios deseados, separados por coma
	 */
	public ConsultaAlojamiento(@JsonProperty(value="inicio") Date inicio, @JsonProperty(value="fin") Date fin,@JsonProperty(value="servicios") String servicios) {
		this.inicio=inicio;
		this.fin=fin;
		this.servicios=servicios;
	}
	public Date getInicio() {
		return inicio;
	}
	public Date getFin() {
		return fin;
	}
	public String getServicios() {
		return servicios;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public void setServicios(String servicios) {
		this.servicios = servicios;
	}
}
