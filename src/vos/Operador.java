package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa un operador 
 */
public class Operador {
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Id del operador
	 */
	@JsonProperty(value="id")
	private int id;
	
	/**
	 * Nombre del operador
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * lista de servicios del operador
	 */
	@JsonProperty(value="servicios")
	private ArrayList<Servicio> servicios;
	
	/**
	 * ofertas del operador
	 */
	@JsonProperty(value="ofertas")
	private ArrayList<Oferta> ofertas;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo constructor de la clase operador
		 * <b>post: </b> Crea el operador con los valores que entran por parametro
		 * @param id - Id del operador.
		 * @param nombre - Nombre del operador.
		 */
		public Operador(@JsonProperty(value="id") Integer id,@JsonProperty(value="nombre") String nombre) 
		{
			this.id= id;
			this.nombre=nombre;
			servicios=new ArrayList<>();
			ofertas= new ArrayList<>();
		}
 

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------
			

		/**
		 * obtener  el id  del operador 
		 * @return id 
		 */
		public int getId() {
			return id;
		}

		/**
		 * Cambia el id de un operador 
		 * @param id
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * obtener  el nombre  del operador 
		 * @return nombre 
		 */
		public String getNombre() {
			return nombre;
		}

		/**
		 * Cambia el nombre de un operador 
		 * @param nombre
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public ArrayList<Servicio> getServicios() {
			return servicios;
		}


		public void setServicios(ArrayList<Servicio> servicios) {
			this.servicios = servicios;
		}


		public ArrayList<Oferta> getOfertas() {
			return ofertas;
		}


		public void setOfertas(ArrayList<Oferta> ofertas) {
			this.ofertas = ofertas;
		}
		

	
}
