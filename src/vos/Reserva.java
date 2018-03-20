package vos;

import java.sql.Date;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa una reserva  
 */
public class Reserva
{
	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
			/**
			 * Id de la reserva
			 */
			@JsonProperty(value="id")
			private int id;
			
			/**
			 * dia de Llegada de la reserva
			 */
			@JsonProperty(value="diaLlegada")
			private Date diaLlegada;
			
			/**
			 * dia de Salida de la reserva
			 */
			@JsonProperty(value="diaSalida")
			private Date diaSalida;
			
			/**
			 * Costo de la reserva
			 */
			@JsonProperty(value="costo")
			private double costo;
			
			/**
			 * Estado de la reserva
			 */
			@JsonProperty(value="cancelada")
			private boolean cancelada;
			
			/**
			 * Oferta de la reserva
			 */
			@JsonProperty(value="oferta")
			private Oferta oferta;
			
			/**
			 * Cliente de la reserva
			 */
			@JsonProperty(value="cliente")
			private Cliente cliente;
			
			//----------------------------------------------------------------------------------------------------------------------------------
			// METODO CONSTRUCTOR
			//----------------------------------------------------------------------------------------------------------------------------------

			/**
			 * Metodo constructor de la clase reserva
			 * <b>post: </b> Crea la reserva con los valores que entran por parametro
			 * @param id - Id de la reserva.
			 * @param diaLlegada - diaLlegada de la reserva.
			 * @param diaSalida -diaSalida de la reserva
			 * @param costo - costo de la  reserva
			 * @param cancelada - estado de la  reserva
			 */
			public Reserva(@JsonProperty(value="id") Integer id,@JsonProperty(value="diaLlegada") Date diaLlegada,@JsonProperty(value="diaSalida") Date diaSalida,@JsonProperty(value="costo") double costo,@JsonProperty(value="cancelada") boolean cancelada) 
			{
				this.id=id;
				this.diaLlegada=diaLlegada;
				this.diaSalida=diaSalida;
				this.costo= costo;
				this.cancelada=cancelada;
				oferta=null;
				cliente=null;
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

			public Date getDiaLlegada() {
				return diaLlegada;
			}

			public void setDiaLlegada(Date diaLlegada) {
				this.diaLlegada = diaLlegada;
			}

			public Date getDiaSalida() {
				return diaSalida;
			}

			public void setDiaSalida(Date diaSalida) {
				this.diaSalida = diaSalida;
			}

			public double getCosto() {
				return costo;
			}

			public void setCosto(double costo) {
				this.costo = costo;
			}

			public boolean isCancelada() {
				return cancelada;
			}

			public void setCancelada(boolean cancelada) {
				this.cancelada = cancelada;
			}

			public Oferta getOferta() {
				return oferta;
			}

			public void setOferta(Oferta oferta) {
				this.oferta = oferta;
			}

			public Cliente getCliente() {
				return cliente;
			}

			public void setCliente(Cliente cliente) {
				this.cliente = cliente;
			}

			
			
}
