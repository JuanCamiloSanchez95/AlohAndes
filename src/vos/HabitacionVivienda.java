package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * Clase que representa una habitacionVivienda  
 */
public class HabitacionVivienda extends Alojamiento
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		
	/**
	 * urlEsquema de la habitacion
	 */
	@JsonProperty(value="urlEsquema")
	private String urlEsquema;

	/**
	 * indicador si  la habitacion es compartida
	 */
	@JsonProperty(value="compartida")
	private boolean compartida;
	
	 
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 
	 * @param id
	 * @param nombre
	 * @param tipo
	 * @param ubicacion
	 * @param descripcion
	 * @param costo
	 * @param minimoPeriodo
	 * @param compartida
	 * @param urlEsquema
	 */
	public HabitacionVivienda(@JsonProperty(value="id") Integer id,@JsonProperty(value="nombre") String nombre, @JsonProperty(value="tipo") String tipo,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="descripcion") String descripcion,@JsonProperty(value="costo") double costo, @JsonProperty(value="minimoPeriodo")int minimoPeriodo,@JsonProperty(value="compartida")boolean compartida,@JsonProperty(value="urlEsquema")String urlEsquema) 
	{
		super(id, nombre, tipo, ubicacion, descripcion, costo, minimoPeriodo);
		this.compartida=compartida;
		this.urlEsquema=urlEsquema;
	}
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE LA CLASE
			//----------------------------------------------------------------------------------------------------------------------------------



	public String getUrlEsquema() {
		return urlEsquema;
	}



	public void setUrlEsquema(String urlEsquema) {
		this.urlEsquema = urlEsquema;
	}



	public boolean isCompartida() {
		return compartida;
	}



	public void setCompartida(boolean compartida) {
		this.compartida = compartida;
	}
			


}
