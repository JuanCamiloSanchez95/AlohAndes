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
import vos.DineroOperador;
import vos.Operador;

/**
 *  @author Cristian Amaya - cm.amaya10@uniandes.edu.co
 * Clase que expone servicios REST con ruta base: http://localhost:8080/AlohAndes/rest/operadores/...
 */
@Path("operadores")
public class OperadorService {
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
	 * Metodo GET que trae a todos los operadores en la Base de datos.
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario.
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/operadores
	 * @return	<b>Response Status 200</b> - JSON que contiene a todas los operadores que estan en la Base de Datos
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getOperadores() {
		
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			List<Operador> operadores;
			operadores = tm.getAllOperadores();
			return Response.status(200).entity(operadores).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
	/**
	 * Metodo GET que trae la informacion del dinero ganado por los proveedores en el año actual.
	 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario.
	 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/operadores/dinero
	 * @return	<b>Response Status 200</b> - JSON que contiene el dinero de todas los operadores que estan en la Base de Datos
	 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
	 */			
	@GET
	@Path("/dinero")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getDineroGanadoOperadores() {
		
		try {
			AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
			List<DineroOperador> dineroOperadores;
			dineroOperadores = tm.getDineroRecibidoOperadores();
			return Response.status(200).entity(dineroOperadores).build();
		} 
		catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	}
	
}
