package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa un servicio 
 */
public class Servicio 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Id del servicio
	 */
	@JsonProperty(value="id")
	private int id;
	
	/**
	 * Nombre del servicio
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * descripccion del servicio
	 */
	@JsonProperty(value="descripccion")
	private String descripccion;
	
	/**
	 * costo del servicio
	 */
	@JsonProperty(value="costo")
	private double costo;
	
	/**
	 * lista de operadores
	 */
	@JsonProperty(value="operadores")
	private ArrayList<Operador> operadores;
	
	/**
	 * lista de habitaciones
	 */
	@JsonProperty(value="alojamientos")
	private ArrayList<Alojamiento> alojamientos;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

			/**
			 * Metodo constructor de la clase servicio
			 * <b>post: </b> Crea el servicio con los valores que entran por parametro
			 * @param id - Id del servicio.
			 * @param nombre - Nombre del servicio.
			 * @param descripccion - descripccion del servicio.
			 * @param costo - costo del servicio.
			 */
			public Servicio(@JsonProperty(value="id") Integer id,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="descripccion") String descripccion,@JsonProperty(value="costo") double costo) 
			{
				this.id= id;
				this.nombre=nombre;
				this.descripccion=descripccion;
				this.costo=costo;
				operadores=new ArrayList<>();
				alojamientos= new ArrayList<>();
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

			public String getDescripccion() {
				return descripccion;
			}

			public void setDescripccion(String descripccion) {
				this.descripccion = descripccion;
			}

			public double getCosto() {
				return costo;
			}

			public void setCosto(double costo) {
				this.costo = costo;
			}

			public ArrayList<Operador> getOperadores() {
				return operadores;
			}

			public void setOperadores(ArrayList<Operador> operadores) {
				this.operadores = operadores;
			}

			public ArrayList<Alojamiento> getAlojamientos() {
				return alojamientos;
			}

			public void setAlojamientos(ArrayList<Alojamiento> alojamientos) {
				this.alojamientos = alojamientos;
			}


			
}
