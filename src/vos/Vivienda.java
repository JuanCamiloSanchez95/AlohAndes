package vos;



import org.codehaus.jackson.annotate.JsonProperty;
/**
 * 
 * @author Juan
 * Clase que representa una vivienda 
 */
public class Vivienda extends Alojamiento
{
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
			/**
			 * numero de habitaciones de la vivenda 
			 */
			@JsonProperty(value="numeroHabitaciones")
			private int numeroHabitaciones;
			/**
			 *  menaje de la vivienda 
			 */
			@JsonProperty(value="menaje")
			private String menaje;
			
			/**
			 *  Seguro de la vivienda 
			 */
			@JsonProperty(value="seguro")
			private Seguro seguro;
			
			
			
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODO CONSTRUCTOR
			//----------------------------------------------------------------------------------------------------------------------------------
	
			
				public Vivienda(@JsonProperty(value="id") Integer id,@JsonProperty(value="nombre") String nombre, @JsonProperty(value="tipo") String tipo,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="descripcion") String descripcion,@JsonProperty(value="costo") double costo, @JsonProperty(value="minimoPeriodo")int minimoPeriodo,@JsonProperty(value="numeroHabitaciones") Integer numeroHabitaciones,@JsonProperty(value="menaje") String menaje) 
			{
				super(id, nombre, tipo, ubicacion, descripcion, costo, minimoPeriodo);
				this.numeroHabitaciones=numeroHabitaciones;
				this.menaje=menaje;
			}
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE LA CLASE
			//----------------------------------------------------------------------------------------------------------------------------------



				public int getNumeroHabitaciones() {
					return numeroHabitaciones;
				}



				public Seguro getSeguro() {
					return seguro;
				}



				public void setSeguro(Seguro seguro) {
					this.seguro = seguro;
				}



				public void setNumeroHabitaciones(int numeroHabitaciones) {
					this.numeroHabitaciones = numeroHabitaciones;
				}



				public String getMenaje() {
					return menaje;
				}



				public void setMenaje(String menaje) {
					this.menaje = menaje;
				}
				

	
}
