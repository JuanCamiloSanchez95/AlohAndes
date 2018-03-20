package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.ParranderosTransactionManager;
import vos.Bebedor;

/**
 * 
 * @author Juan
 * @author Laura 
 * Clase que representa el servicio de aparatamento 
 */
@Path("apartamentos")
public class ApartamentoService 
{
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
		 * Metodo GET que trae a todos los apartamentos en la Base de datos. <br/>
		 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/apartamentos <br/>
		 * @return	<b>Response Status 200</b> - JSON que contiene a todos los apartamentos que estan en la Base de Datos <br/>
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */			
		@GET
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getApartamentos() {
			
			try {
				ParranderosTransactionManager tm = new ParranderosTransactionManager(getPath());
				List<Bebedor> bebedores;
				//Por simplicidad, solamente se obtienen los primeros 50 resultados de la consulta
				bebedores = tm.getAllBebedores();
				return Response.status(200).entity(bebedores).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		
}
