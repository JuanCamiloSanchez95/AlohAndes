package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesTransactionManager;

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

}
