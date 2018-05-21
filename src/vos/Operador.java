package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
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
	private Long id;
	
	/**
	 * Nombre del operador
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * Tipo del operador
	 */
	@JsonProperty(value="tipo")
	private String tipo;
	
	/**
	 * Lista de servicios del operador
	 */
	@JsonProperty(value="servicios")
	private ArrayList<Servicio> servicios;
	
	/**
	 * Ofertas del operador
	 */
	@JsonProperty(value="ofertas")
	private ArrayList<Oferta> ofertas;
	
	/**
	 * Alojamientos que ofrece  el operador
	 */
	@JsonProperty(value="alojamientjos")
	private ArrayList<Alojamiento> alojamientos;
	
	/**
	 * Dinero ganado en el a�o actual
	 */
	private double dineroGanado;
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo constructor de la clase operador
		 * <b>post: </b> Crea el operador con los valores que entran por parametro
		 * @param id - Id del operador.
		 * @param nombre - Nombre del operador.
		 * @param tipo - Tipo del operador.
		 */
		public Operador(@JsonProperty(value="id") Long id,@JsonProperty(value="nombre") String nombre, @JsonProperty(value="tipo") String tipo) 
		{
			this.id= id;
			this.nombre=nombre;
			this.tipo=tipo;
			servicios=new ArrayList<>();
			ofertas= new ArrayList<>();
			alojamientos= new ArrayList<>();
		}
 

		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE LA CLASE
		//----------------------------------------------------------------------------------------------------------------------------------
			

		/**
		 * obtener  el id  del operador 
		 * @return id 
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Cambia el id de un operador 
		 * @param id Identificador del operador
		 */
		public void setId(Long id) {
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
		 * @param nombre - nombre nuevo del operador
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getTipo() {
			return tipo;
		}


		public void setTipo(String tipo) {
			this.tipo = tipo;
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


		public ArrayList<Alojamiento> getAlojamientos() {
			return alojamientos;
		}


		public void setAlojamientos(ArrayList<Alojamiento> alojamientos) {
			this.alojamientos = alojamientos;
		}


		public double getDineroGanado() {
			return dineroGanado;
		}


		public void setDineroGanado(double dineroGanado) {
			this.dineroGanado = dineroGanado;
		}
		

	
}
