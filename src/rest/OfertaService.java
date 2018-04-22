package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesTransactionManager;
import vos.Oferta;

@Path("ofertas")
public class OfertaService {


	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Atributo que usa la anotacion @Context para tener el ServletContext de la conexion actual.
	 */
	@Context
	private ServletContext context;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS REST
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que recibe una oferta en formato JSON y la elimina de la Base de Datos <br/>
	 * <b>Precondicion: </b> El archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>Postcondicion: </b> Se elimina de la Base de datos la oferta con la informacion correspondiente.<br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/ofertas <br/>
	 * @param bebedor JSON con la informacion de la oferta que se desea eliminar
	 * @return	<b>Response Status 200</b> - JSON que contiene la oferta que se desea eliminar <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOferta(Oferta oferta) {
		try{
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
			tm.deleteOferta(oferta);
			return Response.status( 200 ).entity( oferta ).build( );	
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	/**
	 * Metodo GET que trae las 20 ofertas mas populares en la Base de datos. <br/>
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/oferta/populares <br/>
	 * @return	<b>Response Status 200</b> - JSON que contiene las 20 ofertas mas populares que estan en la Base de Datos <br/>
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Path("/populares")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOfertasPopulares() {
		
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			List<Oferta> ofertas;
			ofertas = tm.getOfertasMasPopulares();
			return Response.status(200).entity(ofertas).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
}
