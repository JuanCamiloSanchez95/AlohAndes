package vos;



import org.codehaus.jackson.annotate.JsonProperty;
/**
 * 
 * @author Juan
 * Clase que representa un aparatamento 
 */
public class Apartamento extends Alojamiento
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

		
		/**
		 * Verificador de si esta amoblado el apartamento 
		 */
		@JsonProperty(value="amoblado")
		private boolean amoblado;

		
		/**
		 *  precio de la adminsitracion del apartamento 
		 */
		@JsonProperty(value="administracion")
		private double administracion;
		

		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo constructor de la clase Apartamento
	 * <b>post: </b> Crea un objeto Apartamento con los valores que entran por parametro
	 * @param id - Identificador del operador
	 * @param nombre - Nombre del operador
	 * @param tipo - tipo del operador
	 * @param ubicacion - ubicacion del hotel
	 * @param descripcion - descripcion 
	 * @param costo
	 * @param minimoPeriodo
	 * @param amoblado
	 * @param administracion
	 */
		public Apartamento(@JsonProperty(value="id") Integer id,@JsonProperty(value="nombre") String nombre, @JsonProperty(value="tipo") String tipo,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="descripcion") String descripcion,@JsonProperty(value="costo") double costo, @JsonProperty(value="minimoPeriodo")int minimoPeriodo,@JsonProperty(value="amoblado")boolean amoblado,@JsonProperty(value="administracion")double administracion) 
		{
			super(id, nombre, tipo, ubicacion, descripcion, costo, minimoPeriodo);
			this.amoblado=amoblado;
			this.administracion=administracion;
		}
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------


		public boolean isAmoblado() {
			return amoblado;
		}

		public void setAmoblado(boolean amoblado) {
			this.amoblado = amoblado;
		}

		public double getAdministracion() {
			return administracion;
		}

		public void setAdministracion(double administracion) {
			this.administracion = administracion;
		}


		
}
