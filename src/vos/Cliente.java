package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 * 
 * @author Juan
 * Clase que representa un hotel  
 */
public class Cliente 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Id del cliente
		 */
		@JsonProperty(value="documento")
		private int documento;
		
		/**
		 * Nombre del cliente
		 */
		@JsonProperty(value="nombre")
		private String nombre;

		/**
		 * vinculo del cliente
		 */
		@JsonProperty(value="vinculo")
		private String vinculo;
		
		/**
		 * lista de reservas del cliente
		 */
		@JsonProperty(value="reservas")
		private ArrayList<Reserva> reservas;
		
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------

			/**
			 * Metodo constructor de la clase Cliente
			 * <b>post: </b> Crea el Cliente con los valores que entran por parametro
			 * @param documento - Id del Cliente.
			 * @param nombre - Nombre del Cliente.
			 * @param vinculo - Vinculo del cliente
			 */
			public Cliente(@JsonProperty(value="documento") Integer documento,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="vinculo") String vinculo) 
			{
				this.documento= documento;
				this.nombre=nombre;
				this.vinculo=vinculo;
				reservas= new ArrayList<>();
			}
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE LA CLASE
			//----------------------------------------------------------------------------------------------------------------------------------
				

			public int getDocumento() {
				return documento;
			}


			public void setDocumento(int documento) {
				this.documento = documento;
			}


			public String getNombre() {
				return nombre;
			}


			public void setNombre(String nombre) {
				this.nombre = nombre;
			}


			public String getVinculo() {
				return vinculo;
			}


			public void setVinculo(String vinculo) {
				this.vinculo = vinculo;
			}


			public ArrayList<Reserva> getReservas() {
				return reservas;
			}


			public void setReservas(ArrayList<Reserva> reservas) {
				this.reservas = reservas;
			}
			
			
	 
}
