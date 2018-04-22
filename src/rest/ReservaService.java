package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesTransactionManager;
import vos.Reserva;

/**
 *  @author Cristian Amaya - cm.amaya10@uniandes.edu.co
 * Clase que expone servicios REST con ruta base: http://localhost:8080/AlohAndes/rest/reservas/...
 */
@Path("reservas")
public class ReservaService {

	// ----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la
	 * conexion actual.
	 */
	@Context
	private ServletContext context;

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	// ----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy
	 * actual dentro del servidor.
	 * 
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}

	private String doErrorMessage(Exception e) {
		return "{ \"ERROR\": \"" + e.getMessage() + "\"}";
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS REST
	// ----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo GET que trae a todos las reservas en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/reservas <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene a todas las reservas que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getReservas() {
		
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			List<Reserva> reservas;
			reservas = tm.getAllReservas();
			return Response.status(200).entity(reservas).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	/**
	 * Metodo que recibe una reserva en formato JSON y la agrega a la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se agrega a la Base de datos la informacion correspondiente a la reserva. <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/reservas <br/>
	 * @param bebedor JSON con la informacion de la reserva que se desea agregar
	 * @return	<b>Response Status 200</b> - JSON que contiene la reserva que ha sido agregado <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReserva(Reserva reserva) {
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			tm.addReserva(reserva);;
			return Response.status( 200 ).entity( reserva ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	
	/**
	 * Metodo que recibe una reserva en formato JSON y la elimina de la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se elimina de la Base de datos a la reserva con la informacion correspondiente.<br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/reservas <br/>
	 * @param bebedor JSON con la informacion de la reserva que se desea eliminar
	 * @return	<b>Response Status 200</b> - JSON que contiene la reserva que se desea eliminar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteReserva(Reserva reserva) {
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			tm.deleteReserva(reserva);
			return Response.status( 200 ).entity( reserva ).build( );	
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
}
