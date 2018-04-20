package vos;

import java.util.ArrayList;


import org.codehaus.jackson.annotate.JsonProperty;
/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa un aparatamento 
 */
public class Apartamento  
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Id del apartamento 
		 */
		@JsonProperty(value="id")
		private int id;
		
		/**
		 * Verificador de si esta amoblado el apartamento 
		 */
		@JsonProperty(value="amoblado")
		private boolean amoblado;
		
		/**
		 *  precio del apartamento 
		 */
		@JsonProperty(value="precio")
		private double precio;
		
		/**
		 *  precio de la adminsitracion del apartamento 
		 */
		@JsonProperty(value="administracion")
		private double administracion;
		
		/**
		 * Persona  encargada del apartamento
		 */
		private Persona persona;
		
		/**
		 * Lista de ofertas del apartamento 
		 */
		private ArrayList<Oferta> ofertas;
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Metodo constructor de la clase apartamento
		 * <b>post: </b> Crea el apartamento con los valores que entran por parametro
		 * @param id - Id del apartamento.
		 * @param amoblado - descipccion de si el  apartamento esta amobaldo .
		 * @param precio -precio del apartamento
		 * @param administracion - precio de la adminsitracion del apartamento 
		 */
		public Apartamento(@JsonProperty(value="id") Integer id,@JsonProperty(value="amoblado") boolean amoblado,@JsonProperty(value="precio") Double precio,@JsonProperty(value="adminsitracion") double adminsitracion)
		{
			this.id=id;
			this.amoblado=amoblado;
			this.precio=precio;
			this.administracion=adminsitracion;
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

		public boolean isAmoblado() {
			return amoblado;
		}

		public void setAmoblado(boolean amoblado) {
			this.amoblado = amoblado;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public double getAdministracion() {
			return administracion;
		}

		public void setAdministracion(double administracion) {
			this.administracion = administracion;
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
