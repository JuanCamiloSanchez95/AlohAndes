package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa un hotel  
 */
public class Hostal extends Operador
{
	//----------------------------------------------------------------------------------------------------------------------------------
		// ATRIBUTOS
		//----------------------------------------------------------------------------------------------------------------------------------
			/**
			* horario de apertura del hostal
			*/
			@JsonProperty(value="horarioApertura")
			private String horarioApertura;
			
			/**
			* horario de cierre del hostal
			*/
			@JsonProperty(value="horarioCierre")
			private String horarioCierre;
			
			/**
			* ubicacion del hostal
			*/
			@JsonProperty(value="ubicacion")
			private String ubicacion;

			/**
			* registro de camara del hostal
			*/
			@JsonProperty(value="registroCamara")
			private String registroCamara;
			
			/**
			* registro de ST del hostal 
			*/
			@JsonProperty(value="registroST")
			private String registroST;
			
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODO CONSTRUCTOR
			//----------------------------------------------------------------------------------------------------------------------------------

			/**
			* Metodo constructor de la clase hostal 
			* <b>post: </b> Crea el hostal con los valores que entran por parametro
			* @param id - Id del hostal.
			* @param nombre - Nombre del hostal.
			* @param horarioApertura - horario Apertura del hostal
			* @param horarioCierre - horario Cierre del hostal
			* @param ubicacion - Ubicacion del hostal 
			* @param registroCamara - Registro de la camara del hostal
			* @param registroST -  Registro del ST  del hostal 
			*/
			public Hostal(@JsonProperty(value="id") Long id,@JsonProperty(value="nombre") String nombre,@JsonProperty(value="horarioApertura") String horarioApertura,@JsonProperty(value="horarioCierre") String horarioCierre,@JsonProperty(value="ubicacion") String ubicacion,@JsonProperty(value="registroCamara")String registroCamara,@JsonProperty(value="registroST") String registroST )
			{
				super(id, nombre);
				this.horarioApertura=horarioApertura;
				this.horarioCierre= horarioCierre;
				this.ubicacion=ubicacion;
				this.registroCamara=registroCamara;
				this.registroST= registroST;	
			}

			//----------------------------------------------------------------------------------------------------------------------------------
			// METODOS DE LA CLASE
			//----------------------------------------------------------------------------------------------------------------------------------
				
			
			public String getHorarioApertura() {
				return horarioApertura;
			}

			public void setHorarioApertura(String horarioApertura) {
				this.horarioApertura = horarioApertura;
			}

			public String getHorarioCierre() {
				return horarioCierre;
			}

			public void setHorarioCierre(String horarioCierre) {
				this.horarioCierre = horarioCierre;
			}

			public String getUbicacion() {
				return ubicacion;
			}

			public void setUbicacion(String ubicacion) {
				this.ubicacion = ubicacion;
			}

			public String getRegistroCamara() {
				return registroCamara;
			}

			public void setRegistroCamara(String registroCamara) {
				this.registroCamara = registroCamara;
			}

			public String getRegistroST() {
				return registroST;
			}

			public void setRegistroST(String registroST) {
				this.registroST = registroST;
			}

			

}
