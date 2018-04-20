package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;
/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una vivienda 
 */
public class Vivienda {
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
			/**
			 * Id de la vivenda 
			 */
			@JsonProperty(value="id")
			private int id;
			/**
			 *  precio de la vivienda 
			 */
			@JsonProperty(value="precio")
			private double precio;
			
			/**
			 * Persona  encargada de la vivienda
			 */
			private Persona persona;
			
			/**
			 * Lista de ofertas de la vivienda 
			 */
			private ArrayList<Oferta> ofertas;
			
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODO CONSTRUCTOR
			//----------------------------------------------------------------------------------------------------------------------------------
			/**
			 * Metodo constructor de la clase Vivienda
			 * <b>post: </b> Crea la Vivienda con los valores que entran por parametro
			 * @param id - Id de la Vivienda.
			 * @param precio -precio de la Vivienda
			 */
			public Vivienda(@JsonProperty(value="id") Integer id,@JsonProperty(value="precio") Double precio)
			{
				this.id=id;
				this.precio=precio;
				persona=null;
				ofertas=new ArrayList<>();
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

			public double getPrecio() {
				return precio;
			}

			public void setPrecio(double precio) {
				this.precio = precio;
			}

			public Persona getPersona() {
				return persona;
			}

			public void setPersona(Persona persona) {
				this.persona = persona;
			}

			public ArrayList<Oferta> getOfertas() {
				return ofertas;
			}

			public void setOfertas(ArrayList<Oferta> ofertas) {
				this.ofertas = ofertas;
			}

}
