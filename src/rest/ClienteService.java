package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesTransactionManager;
import vos.Oferta;
import vos.UsoCliente;

/**
 *  @author Cristian Amaya - cm.amaya10@uniandes.edu.co
 * Clase que expone servicios REST con ruta base: http://localhost:8080/AlohAndes/rest/clientes/...
 */
@Path("clientes")
public class ClienteService {
	
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
			 * Metodo GET que trae las 20 ofertas mas populares en la Base de datos. <br/>
			 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
			 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/oferta/populares <br/>
			 * @return	<b>Response Status 200</b> - JSON que contiene las 20 ofertas mas populares que estan en la Base de Datos <br/>
			 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
			 */			
			@GET
			@Path("/usos/{id: \\d+}")
			@Produces({ MediaType.APPLICATION_JSON })
			public Response getUsos(@PathParam("id") Long id) {
				
				try {
					AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
					List<UsoCliente> usos;
					usos = tm.getUsosByCliente(id);
					return Response.status(200).entity(usos).build();
				} 
				catch (Exception e) {
					return Response.status(500).entity(doErrorMessage(e)).build();
				}
			}

}
