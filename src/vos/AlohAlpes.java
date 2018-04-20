package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * Clase que representa el negocio de AlhoAlpes 
 */
public class AlohAlpes 
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Id del negocio
		 */
		@JsonProperty(value="id")
		private int id;
		
		/**
		 * Nombre del negocio
		 */
		@JsonProperty(value="nombre")
		private String nombre;
		
		/**
		 * lista de operadores del negocio
		 */
		@JsonProperty(value="operadores")
		private ArrayList<Operador> operadores;
		
		/**
		 * lista de clientes del negocio
		 */
		@JsonProperty(value="clientes")
		private ArrayList<Cliente> clientes;
		
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODO CONSTRUCTOR
		//----------------------------------------------------------------------------------------------------------------------------------

			/**
			 * Metodo constructor de la clase negocio
			 * <b>post: </b> Crea el negocio con los valores que entran por parametro
			 * @param id - Id del negocio.
			 */
			public AlohAlpes(@JsonProperty(value="id") Integer id) 
			{
				this.id= id;
				operadores=new ArrayList<>();
				clientes= new ArrayList<>();
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


			public ArrayList<Operador> getOperadores() {
				return operadores;
			}

			public void setOperadores(ArrayList<Operador> operadores) {
				this.operadores = operadores;
			}

			public ArrayList<Cliente> getClientes() {
				return clientes;
			}

			public void setClientes(ArrayList<Cliente> clientes) {
				this.clientes = clientes;
			}
			
			
}

