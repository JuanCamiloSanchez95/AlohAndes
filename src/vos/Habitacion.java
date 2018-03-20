package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una habitacion  
 */
public class Habitacion
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
		/**
		 * Id de la habitacion
		 */
		@JsonProperty(value="id")
		private int id;
		
		/**
		 * descripccion de la habitacion
		 */
		@JsonProperty(value="descripccion")
		private String descripccion;
		

		/**
		 * precio de la habitacion
		 */
		@JsonProperty(value="precio")
		private Double precio;
		
		
		/**
		 * periodo de Facturacion de la habitacion
		 */
		@JsonProperty(value="periodoFacturacion")
		private int periodoFacturacion;
		
		/**
		 * lista de  servicios de una  habitacion
		 */
		@JsonProperty(value="servicios")
		private ArrayList<Servicio> servicios;
		
		/**
		 * lista de  ofertas de una  habitacion
		 */
		@JsonProperty(value="ofertas")
		private ArrayList<Servicio> ofertas;
		
		
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
			 */
			public Habitacion(@JsonProperty(value="id") Integer id,@JsonProperty(value="descripccion") String descripccion,@JsonProperty(value="precio") Double precio,@JsonProperty(value="periodoFacturacion") int periodoFacturacion) 
			{
				this.id=id;
				this.descripccion=descripccion;
				this.precio=precio;
				this.periodoFacturacion=periodoFacturacion;
				servicios= new ArrayList<>();
				ofertas= new ArrayList<>();
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

			public String getDescripccion() {
				return descripccion;
			}

			public void setDescripccion(String descripccion) {
				this.descripccion = descripccion;
			}

			public Double getPrecio() {
				return precio;
			}

			public void setPrecio(Double precio) {
				this.precio = precio;
			}

			public int getPeriodoFacturacion() {
				return periodoFacturacion;
			}

			public void setPeriodoFacturacion(int periodoFacturacion) {
				this.periodoFacturacion = periodoFacturacion;
			}

			public ArrayList<Servicio> getServicios() {
				return servicios;
			}

			public void setServicios(ArrayList<Servicio> servicios) {
				this.servicios = servicios;
			}

			public ArrayList<Servicio> getOfertas() {
				return ofertas;
			}

			public void setOfertas(ArrayList<Servicio> ofertas) {
				this.ofertas = ofertas;
			}
			
			
}
