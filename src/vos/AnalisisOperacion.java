package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa una solicitud de analisis de operacion.
 * @author Cristian 
 */
public class AnalisisOperacion {
	
	// ----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------------------------------------------------------------------------------

	@JsonProperty(value = "categoria")
	private String categoria;
	
	@JsonProperty(value = "unidadTiempo")
	private String unidadTiempo;
	
	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	// ----------------------------------------------------------------------------------------------------------------------------------


	/**
	 * Constructor de solicitud de analisis de operacion.
	 * @param categoria - categoria de Alojamiento
	 * @param unidadTiempo - Unitdad de Tiempo (Mes,Semana)
	 */
	public AnalisisOperacion(@JsonProperty(value = "categoria") String categoria,@JsonProperty(value = "unidadTiempo") String unidadTiempo) {
		this.categoria = categoria;
		this.unidadTiempo = unidadTiempo;
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE LA CLASE
	// ----------------------------------------------------------------------------------------------------------------------------------

	public String getCategoria() {
		return categoria;
	}

	public String getUnidadTiempo() {
		return unidadTiempo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setUnidadTiempo(String unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}
	
	
}
