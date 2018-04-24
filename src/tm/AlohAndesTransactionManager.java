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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import dao.DAOAlojamiento;
import dao.DAOCliente;
import dao.DAOOferta;
import dao.DAOOperador;
import dao.DAOReserva;
import vos.Alojamiento;
import vos.Oferta;
import vos.Operador;
import vos.Reserva;
import vos.UsoCliente;

/**
 * @author Cristian M. Amaya	- 	cm.amaya10@uniandes.edu.co
 * @author Juan Camilo Sanchez	-	jc.sanchez12@uniandes.edu.co
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
	public final static String USUARIO = "ISIS2304A021810";
	
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
	 * <b>Metodo Contructor de la Clase AlohAndesTransactionManager</b>
	 * <b>Postcondicion: </b>	Se crea un objeto  AlohAndesTransactionManager,
	 * 						 	Se inicializa el path absoluto del archivo de conexion,
	 * 							Se inicializna los atributos para la conexion con la Base de Datos
	 * @param contextPathP Path absoluto que se encuentra en el servidor del contexto del deploy actual
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
	 * Metodo encargado de inicializar los atributos utilizados para la conexion con la Base de Datos.
	 * <b>post: </b> Se inicializan los atributos para la conexion
	 * @throws IOException Se genera una excepcion al no encontrar el archivo o al tener dificultades durante su lectura
	 * @throws ClassNotFoundException Se genera una excepcion si no se encuentra la clase
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
	 * Metodo encargado de generar una conexion con la Base de Datos.
	 * <b>Precondicion: </b>Los atributos para la conexion con la Base de Datos han sido inicializados
	 * @return Objeto Connection, el cual hace referencia a la conexion a la base de datos
	 * @throws SQLException Cualquier error que se pueda llegar a generar durante la conexion a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("[ALOHANDES APP] Attempting Connection to: " + url + " - By User: " + user);
		return DriverManager.getConnection(url, user, password);
	}


	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS TRANSACCIONALES
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que modela la transaccion que agrega una reserva a la base de datos.
	 * <b> post: </b> se ha agregado la reserva que entra como parametro
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
	 * Metodo que modela la transaccion que elimina de la base de datos a la reserva que entra por parametro.
	 * Solamente se elimina si existe la reserva en la Base de Datos
	 * <b> post: </b> se ha eliminado la reserva que entra por parametro
	 * @param reserva - Reserva a eliminar. reserva != null
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
	 * Metodo que modela la transaccion que retorna todas las reservas de la base de datos. 
	 * @return List<Reserva> - Lista de reservas que contiene el resultado de la consulta.
	 * @throws Exception - Cualquier error que se genere durante la transaccion
	 */
	public List<Reserva> getAllReservas() throws Exception {
		DAOReserva daoReserva = new DAOReserva();
		List<Reserva> reservas;
		try {
			this.conn = darConexion();
			daoReserva.setConn(conn);
			reservas = daoReserva.getReservas();
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoReserva.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return reservas;
	}
	
	/**
	 * Metodo que modela la transaccion que elimina de la base de datos a la oferta que entra por parametro.
	 * Solamente se actualiza si existe la oferta en la Base de Datos
	 * <b> post: </b> se ha eliminado la oferta que entra por parametro
	 * @param oferta - Oferta a eliminar. oferta != null
	 * @throws Exception - Cualquier error que se genere eliminando a la oferta.
	 */
	public void deleteOferta(Oferta oferta) throws Exception 
	{
		DAOReserva daoReserva = new DAOReserva( );
		DAOOferta daoOferta = new DAOOferta( );
		try
		{
			this.conn = darConexion();
			daoOferta.setConn( conn );
			daoReserva.setConn( conn );
			if(daoOferta.findOfertaById(oferta.getId())==null)
			{
				throw new Exception("No existe una reserva con el id indicado para eliminar");
			}
			if(!daoReserva.getReservasOfertaById(oferta.getId()).isEmpty())
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
	 * Metodo que modela la transaccion que retorna todas los operadores de la base de datos. 
	 * @return List<Operador> - Lista de operadores que contiene el resultado de la consulta.
	 * @throws Exception - Cualquier error que se genere durante la transaccion
	 */
	public List<Operador> getAllOperadores() throws Exception {
		DAOOperador daoOperador = new DAOOperador();
		List<Operador> operadores;
		try {
			this.conn = darConexion();
			daoOperador.setConn(conn);
			operadores = daoOperador.getOperadores();
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoOperador.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return operadores;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna todas los ofertas de la base de datos. 
	 * @return List<Operador> - Lista de ofertas que contiene el resultado de la consulta.
	 * @throws Exception - Cualquier error que se genere durante la transaccion
	 */
	public List<Oferta> getAllOfertas() throws Exception {
		DAOOferta daoOferta = new DAOOferta();
		List<Oferta> ofertas;
		try {
			this.conn = darConexion();
			daoOferta.setConn(conn);
			ofertas = daoOferta.getOfertas();
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoOferta.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return ofertas;
	}
	
	/**
	 * Metodo que modela la transaccion que encuentra las ofertas mas populares.
	 * <b> post: </b> se ha encontrado las ofertas mas populares
	 * @return Lista de Ofertas mas populares en la base de datos
	 * @throws Exception - Cualquier error que se genere buscando las ofertas.
	 */
	public ArrayList<Oferta> getOfertasMasPopulares() throws Exception 
	{
		DAOOferta daoOferta= new DAOOferta( );
		try
		{
			this.conn = darConexion();
			daoOferta.setConn( conn );
			return daoOferta.getOfertasMasPopulares();

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
	 * Metodo que modela la transaccion que encuentra los indices de ocupacion de las ofertas.
	 * <b> post: </b> se ha encontrado las ofertas con su respectivo indices de ocupacion.
	 * @return Lista de Ofertas con indice de ocupacion en la base de datos
	 * @throws Exception - Cualquier error que se genere buscando las ofertas.
	 */
	public ArrayList<Oferta> getOfertasConIndice() throws Exception 
	{
		DAOOferta daoOferta= new DAOOferta( );
		try
		{
			this.conn = darConexion();
			daoOferta.setConn( conn );
			return daoOferta.getIndicesOcupacion();

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
	 * Metodo que modela la transaccion que encuentra el dinero obtenido por los operadores en el año actual.
	 * <b> post: </b> se ha encontrado el dinero ganado por cada operador
	 * @return Lista de cadenas con la informacion del dinero ganado por cada operador
	 * @throws Exception - Cualquier error que se genere buscando las reservas y ofertas de los operadores.
	 */
	public ArrayList<Operador> getDineroRecibidoOperadores() throws Exception 
	{
		DAOOperador daoOperador= new DAOOperador( );
		try
		{
			this.conn = darConexion();
			daoOperador.setConn( conn );
			int year = Calendar.getInstance().get(Calendar.YEAR);
			return daoOperador.getDineroRecibidoOperadores(year);

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
	
	/**
	 * Metodo que modela la transaccion que retorna los usos de un cliente en la base de datos.
	 * @param id - id del Cliente 
	 * @return List<UsoCliente> - Lista de usos de un cliente que contiene el resultado de la consulta.
	 * @throws Exception - Cualquier error que se genere durante la transaccion
	 */
	public List<UsoCliente> getUsosByCliente(Long id) throws Exception {
		DAOCliente daoCliente = new DAOCliente();
		List<UsoCliente> usos;
		try {
			this.conn = darConexion();
			daoCliente.setConn(conn);
			if(daoCliente.findClienteByDocument(id.intValue())==null)
			{
				throw new Exception("El cliente no existe");
			}
			usos = daoCliente.usosDelCliente(id.intValue());
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usos;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna los usos de un cliente en la base de datos.
	 * @param id - id del Cliente 
	 * @return List<UsoCliente> - Lista de usos de un cliente que contiene el resultado de la consulta.
	 * @throws Exception - Cualquier error que se genere durante la transaccion
	 */
	public List<UsoCliente> getUsosByVinculo() throws Exception {
		DAOCliente daoCliente = new DAOCliente();
		List<UsoCliente> usos;
		try {
			this.conn = darConexion();
			daoCliente.setConn(conn);
			usos = daoCliente.usosPorVinculo();
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usos;
	}
	
	/**
	 * Metodo que modela la transaccion que retorna los alojamientos que cumplen los parametros en la base de datos.
	 * @param inicio - Fecha inicial del rango de consulta
	 * @param fin - Fecha final del rango de consulta
	 * @param servicios - servicios que debe incluir el alojamiento
	 * @return List<Alojamiento> - Lista de alojamientos que contiene el resultado de la consulta.
	 * @throws Exception - Cualquier error que se genere durante la transaccion
	 */
	public List<Alojamiento> getAlojamientosByFechasAndServicios(Date inicio,Date fin, String servicios) throws Exception {
		DAOAlojamiento daoAlojamiento = new DAOAlojamiento();
		List<Alojamiento> alojamientos;
		try {
			this.conn = darConexion();
			daoAlojamiento.setConn(conn);
			alojamientos = daoAlojamiento.getAlojamientosByFechaAndServicios(inicio, fin, servicios);
		} catch (SQLException sqlException) {
			System.err.println("[EXCEPTION] SQLException:" + sqlException.getMessage());
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			System.err.println("[EXCEPTION] General Exception:" + exception.getMessage());
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				daoAlojamiento.cerrarRecursos();
				if (this.conn != null) {
					this.conn.close();
				}
			} catch (SQLException exception) {
				System.err.println("[EXCEPTION] SQLException While Closing Resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return alojamientos;
	}

}
