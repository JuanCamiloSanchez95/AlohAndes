package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una habitacionVivienda  
 */
public class HabitacionVivienda extends Habitacion
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		
	/**
	 * nombre de la habitacion
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * indicador si  la habitacion es compartida
	 */
	@JsonProperty(value="compartida")
	private boolean compartida;
	
	/**
	 * Persona a cargo de la  habitacionvivienda
	 */
	@JsonProperty(value="persona")
	private PersonaNatural persona;
	
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODO CONSTRUCTOR
	//----------------------------------------------------------------------------------------------------------------------------------

			/**
			 * Metodo constructor de la clase habitacion
			 * <b>post: </b> Crea la habitacion con los valores que entran por parametro
			 * @param id - Id de la habitacion.
			 * @param descripccion - descipccion de la habitacion.
			 * @param precio -precio de la habitacion
			 * @param periodoFacturacion - periodo en el que factura  la  habitacion
			 * @param compartida - verificador  de la habitacio si es compartida
			 * @param tipo - tipo de la habitacion
			 */
			public HabitacionVivienda(@JsonProperty(value="id") Integer id,@JsonProperty(value="precio") Double precio,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="descripccion") String descripccion,@JsonProperty(value="periodoFacturacion") int periodoFacturacion,@JsonProperty(value="compartida") boolean  compartida)
			{
				super(id, descripccion, precio, periodoFacturacion);
				this.nombre=nombre;
				this.compartida=compartida;
				persona=null;
			}
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE LA CLASE
			//----------------------------------------------------------------------------------------------------------------------------------
			
			public String getNombre() {
				return nombre;
			}

			public void setNombre(String nombre) {
				this.nombre = nombre;
			}

			public boolean isCompartida() {
				return compartida;
			}

			public void setCompartida(boolean compartida) {
				this.compartida = compartida;
			}

			public PersonaNatural getPersona() {
				return persona;
			}

			public void setPersona(PersonaNatural persona) {
				this.persona = persona;
			}

	

}
