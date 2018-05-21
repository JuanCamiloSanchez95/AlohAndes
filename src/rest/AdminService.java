package rest;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.AlohAndesTransactionManager;
import vos.Alojamiento;
import vos.ClienteBueno;
import vos.ClienteFrecuente;
import vos.ConsultaAlojamiento;
import vos.ConsultaConsumo;
import vos.ConsultaFuncionamiento;
import vos.Consumo;

@Path("admin")
public class AdminService {

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
		 * Metodo GET que trae las consultas de funcionamiento por semana en la Base de Datos
		 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/admin/funcionamiento
		 * @return	<b>Response Status 200</b> - JSON Listas de consultas de funcionamiento por semana
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */
		@GET
		@Path( "/funcionamiento" )
		@Produces( { MediaType.APPLICATION_JSON } )
		public Response getConsultaFuncionamiento( )
		{
			try{
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
				List<ConsultaFuncionamiento> consultas;
				consultas = tm.consultaFuncionamiento();
				return Response.status( 200 ).entity( consultas ).build( );			
			}
			catch( Exception e )
			{
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
		
		/**
		 * Metodo GET que trae los clientes buenos en la Base de Datos
		 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario <br/>
		 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/admin/funcionamiento
		 * @return	<b>Response Status 200</b> - JSON Listas de consultas de funcionamiento por semana
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */
		@GET
		@Path( "/ClientesBuenos" )
		@Produces( { MediaType.APPLICATION_JSON } )
		public Response getClientesBuenos( )
		{
			try{
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager( getPath( ) );
				List<ClienteBueno> clientes;
				clientes = tm.consultaClienteBuenos();
				return Response.status( 200 ).entity( clientes ).build( );			
			}
			catch( Exception e )
			{
				return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
			}
		}
		
		
		/**
		 * Metodo POST que trae los consumos de clientes que esten en un rango de fechas y con una oferta dada en la Base de datos. 
		 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario.
		 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/admin/consumos
		 * @return	<b>Response Status 200</b> - JSON que contiene los consumos resultantes de la busqueda en la Base de Datos.
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */			
		@POST
		@Path( "/consumos" )
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getConsumosByFechasAndOferta(ConsultaConsumo request) {

			try {
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
				List<Consumo> consumos;
				consumos=tm.consultaConsumos(request);
				return Response.status(200).entity(consumos).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
		
		/**
		 * Metodo POST que trae los consumos de clientes que no esten en un rango de fechas o con una oferta dada en la Base de datos. 
		 * <b>Precondicion: </b> el archivo <em>'conectionData'</em> ha sido inicializado con las credenciales del usuario.
		 * <b>URL: </b> http://localhost:8080/AlohAndes/rest/admin/RFC11
		 * @return	<b>Response Status 200</b> - JSON que contiene los consumos resultantes de la busqueda en la Base de Datos.
		 * 			<b>Response Status 500</b> - Excepcion durante el transcurso de la transaccion
		 */			
		@POST
		@Path( "/RFC11" )
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces({ MediaType.APPLICATION_JSON })
		public Response getConsumosNotByFechasAndOferta(ConsultaConsumo request) {

			try {
				AlohAndesTransactionManager tm = new AlohAndesTransactionManager(getPath());
				List<Consumo> consumos;
				consumos=tm.consultaNoConsumos(request);
				return Response.status(200).entity(consumos).build();
			} 
			catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
		}
}
