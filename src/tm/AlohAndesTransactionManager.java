/**-------------------------------------------------------------------
 * ISIS2304 - Sistemas Transaccionales
 * Departamento de Ingenieria de Sistemas
 * Universidad de los Andes
 * Bogota, Colombia
 * 
 * Actividad: Tutorial Parranderos: Arquitectura
 * Autores:
 * 			Santiago Cortes Fernandez	-	s.cortes@uniandes.edu.co
 * 			Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package tm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import dao.DAOCliente;
import dao.DAOOferta;
import dao.DAOOperador;
import dao.DAOReserva;
import vos.Oferta;
import vos.Reserva;

/**
 * @author Cristian M. Amaya	- 	cm.amaya10@uniandes.edu.co
 * @author Juan David Vega Guzman		-	jd.vega11@uniandes.edu.co
 * 
 * Clase que representa al Manejador de Transacciones de la Aplicacion (Fachada en patron singleton de la aplicacion)
 * Responsabilidades de la clase: 
 * 		Intermediario entre los servicios REST de la aplicacion y la comunicacion con la Base de Datos
 * 		Modelar y manejar autonomamente las transacciones y las reglas de negocio.
 */
public class AlohAndesTransactionManager {

	//----------------------------------------------------------------------------------------------------------------------------------
	// CONSTANTES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constante para indicar el usuario Oracle del estudiante
	 */
	public final static String USUARIO = "ISIS2304A301810";
	
	/**
	 * Constante que contiene el path relativo del archivo que tiene los datos de la conexion
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estatico que contiene el path absoluto del archivo que tiene los datos de la conexion
	 */
	private static String CONNECTION_DATA_PATH;


	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * Atributo que representa la conexion a la base de datos
	 */
	private Connection conn;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE CONEXION E INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * <b>Metodo Contructor de la Clase AlohAndesTransactionManager</b> <br/>
	 * <b>Postcondicion: </b>	Se crea un objeto  AlohAndesTransactionManager,
	 * 						 	Se inicializa el path absoluto del archivo de conexion,
	 * 							Se inicializna los atributos para la conexion con la Base de Datos
	 * @param contextPathP Path absoluto que se encuentra en el servidor del contexto del deploy actual
	 * @throws IOException Se genera una excepcion al tener dificultades con la inicializacion de la conexion<br/>
	 * @throws ClassNotFoundException 
	 */
	public AlohAndesTransactionManager(String contextPathP) {

		try {
			CONNECTION_DATA_PATH = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
			initializeConnectionData();
		} 
		catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo encargado de inicializar los atributos utilizados para la conexion con la Base de Datos.<br/>
	 * <b>post: </b> Se inicializan los atributos para la conexion<br/>
	 * @throws IOException Se genera una excepcion al no encontrar el archivo o al tener dificultades durante su lectura<br/>
	 * @throws ClassNotFoundException 
	 */
	private void initializeConnectionData() throws IOException, ClassNotFoundException {

		FileInputStream fileInputStream = new FileInputStream(new File(AlohAndesTransactionManager.CONNECTION_DATA_PATH));
		Properties properties = new Properties();

		properties.load(fileInputStream);
		fileInputStream.close();

		this.url = properties.getProperty("url");
		this.user = properties.getProperty("usuario");
		this.password = properties.getProperty("clave");
		this.driver = properties.getProperty("driver");

		//Class.forName(driver);
	}

	/**
	 * Metodo encargado de generar una conexion con la Base de Datos.<br/>
	 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos han sido inicializados<br/>
	 * @return Objeto Connection, el cual hace referencia a la conexion a la base de datos
	 * @throws SQLException Cualquier error que se pueda llegar a generar durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("[PARRANDEROS APP] Attempting Connection to: " + url + " - By User: " + user);
		return DriverManager.getConnection(url, user, password);
	}


	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS TRANSACCIONALES
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que modela la transaccion que agrega una reserva a la base de datos. <br/>
	 * <b> post: </b> se ha agregado la reserva que entra como parametro <br/>
	 * @param reserva - la reserva a agregar. reserva != null
	 * @throws Exception - Cualquier error que se genere agregando la reserva
	 */
	public void addReserva(Reserva reserva) throws Exception 
	{

		DAOReserva daoReserva= new DAOReserva( );
		DAOCliente daoCliente = new DAOCliente();
		DAOOferta daoOferta = new DAOOferta();
		try
		{

			if(daoReserva.findReservaById(reserva.getId())!=null)
			{
				throw new Exception("Ya existe una reserva con el id indicado");
			}
			if(daoCliente.findClienteByDocument(reserva.getCliente().getDocumento())==null)
			{
				throw new Exception("El cliente de la reserva no existe");
			}
			if(daoOferta.findOfertaById(reserva.getOferta().getId())==null)
			{
				throw new Exception("La oferta de la reserva no existe");
			}
			daoReserva.addReserva(reserva);
			

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoReserva.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que elimina de la base de datos a la reserva que entra por parametro. <br/>
	 * Solamente se elimina si existe la reserva en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado la reserva que entra por parametro <br/>
	 * @param Reserva - reserva a eliminar. reserva != null
	 * @throws Exception - Cualquier error que se genere eliminando la reseva.
	 */
	public void deleteReserva(Reserva reserva) throws Exception 
	{
		DAOReserva daoReserva = new DAOReserva( );
		try
		{
			this.conn = darConexion();
			daoReserva.setConn( conn );
			if(daoReserva.findReservaById(reserva.getId())==null)
			{
				throw new Exception("No existe una reserva con el id indicado");
			}
			daoReserva.deleteReserva(reserva);


		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoReserva.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	
	/**
	 * Metodo que modela la transaccion que elimina de la base de datos a la oferta que entra por parametro. <br/>
	 * Solamente se actualiza si existe la oferta en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado la oferta que entra por parametro <br/>
	 * @param Oferta - oferta a eliminar. oferta != null
	 * @throws Exception - Cualquier error que se genere eliminando a la oferta.
	 */
	public void deleteOferta(Oferta oferta) throws Exception 
	{
		DAOOferta daoOferta = new DAOOferta( );
		try
		{
			this.conn = darConexion();
			daoOferta.setConn( conn );

			if(daoOferta.findOfertaById(oferta.getId())==null)
			{
				throw new Exception("No existe una reserva con el id indicado para eliminar");
			}
			if(!daoOferta.getReservasOfertaById(oferta.getId()).isEmpty())
			{
				throw new Exception("La oferta tiene reservas y no puede ser eliminada");
			}
			daoOferta.deleteOferta(oferta);


		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOferta.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	/**
	 * Metodo que modela la transaccion que encuentra la oferta mas popular. <br/>
	 * Solamente se actualiza si existe el bebedor en la Base de Datos <br/>
	 * <b> post: </b> se ha eliminado el bebedor que entra por parametro <br/>
	 * @param Bebedor - bebedor a eliminar. bebedor != null
	 * @throws Exception - Cualquier error que se genere eliminando al bebedor.
	 */
	public ArrayList<Oferta> getOfertasMasPopu() throws Exception 
	{
		DAOOferta daoOferta= new DAOOferta( );
		try
		{
			this.conn = darConexion();
			daoOferta.setConn( conn );
			return daoOferta.getOfertasMasPopu();

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOferta.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}
	
	public ArrayList<String> getDineroRecibidoOperadores() throws Exception 
	{
		DAOOperador daoOperador= new DAOOperador( );
		try
		{
			this.conn = darConexion();
			daoOperador.setConn( conn );
			return daoOperador.getDineroRecibidoOperadores();

		}
		catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} 
		catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} 
		finally {
			try {
				daoOperador.cerrarRecursos();
				if(this.conn!=null){
					this.conn.close();					
				}
			}
			catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}	
	}

}
