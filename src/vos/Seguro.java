package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Seguro 
{
	//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------

			
			/**
			 * Id del seguro
			 */
			@JsonProperty(value="id")
			private int id;

			
			/**
			 *  nombre del seguro  
			 */
			@JsonProperty(value="nombre")
			private String nombre;
			

			/**
			 * costo del seguro
			 */
			@JsonProperty(value="costo")
			private double costo;


			//----------------------------------------------------------------------------------------------------------------------------------
			// METODO CONSTRUCTOR
			//----------------------------------------------------------------------------------------------------------------------------------
			public Seguro(@JsonProperty(value="id") Integer id,@JsonProperty(value="nombre") String nombre, @JsonProperty(value="costo") double costo)
			{
				this.nombre=nombre;
				this.costo=costo;
				this.id=id;
				
			}
			
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE LA CLASE
			//----------------------------------------------------------------------------------------------------------------------------------


			public int getId() {
				return id;
			}


			public void setId(int id) {
				this.id = id;
			}


			public String getNombre() {
				return nombre;
			}


			public void setNombre(String nombre) {
				this.nombre = nombre;
			}


			public double getCosto() {
				return costo;
			}


			public void setCosto(double costo) {
				this.costo = costo;
			}

}
