package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tm.AlohAndesTransactionManager;
import vos.Cliente;
import vos.Oferta;
import vos.UsoCliente;
import vos.UsoTipo;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicacion
 */
public class DAOCliente {

	//----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase DAOCliente
	 */
	public DAOCliente() {
		recursos = new ArrayList<Object>();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Metodo que agregar la informacion de un nuevo cliente en la Base de Datos a partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
	 * @param cliente Cliente que desea agregar a la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addCliente(Cliente cliente) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.CLIENTES (DOCUMENTO, NOMBRE, VINCULO) VALUES (%2$s, '%3$s', '%4$s')", 
				AlohAndesTransactionManager.USUARIO, 
									cliente.getDocumento(), 
									cliente.getNombre(),
									cliente.getVinculo());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	/**
	 * Metodo que obtiene la informacion del cliente en la Base de Datos que tiene el identificador dado por parametro
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @param documento - documento de identificacion del cliente
	 * @return la informacion del cliente que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el cliente con los criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Cliente findClienteByDocument(Long documento) throws SQLException, Exception 
	{
		Cliente cliente = null;

		String sql = String.format("SELECT * FROM %1$s.CLIENTES WHERE DOCUMENTO = %2$d", AlohAndesTransactionManager.USUARIO, documento); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			cliente = convertResultSetToCliente(rs);
		}

		return cliente;
	}

	/**
	 * Metodo que obtiene los usos de la plataforma por un cliente que tiene el identificador dado por parametro
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @param id - documento de identificacion del cliente
	 * @return usos de la plataforma por el cliente en la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */	
	public ArrayList<UsoCliente> usosDelCliente(Long id) throws SQLException, Exception {
		ArrayList<UsoCliente> usos = new ArrayList<UsoCliente>();
		String sql=String.format("SELECT \"A5\".\"NOMBRE\" \"NOMBRE\",STATS_MODE(\"A1\".\"TIPO\") \"TIPOFRECUENTE\",SUM(\"A3\".\"CANTIDADDIAS\") \"DIAS\",SUM(\"A2\".\"PRECIOESTADIA\") \"PRECIOS\" "
				+ "FROM \"%1$s\".\"CLIENTES\" \"A5\",\"%1$s\".\"RESERVASCLIENTE\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\",\"%1$s\".\"OFERTAS\" \"A2\",\"%1$s\".\"ALOJAMIENTOS\" \"A1\""
				+ " WHERE \"A5\".\"DOCUMENTO\"= %2$d"
				+ "AND \"A4\".\"CLIENTEID\"=\"A5\".\"DOCUMENTO\" "
				+ "AND \"A4\".\"RESERVAID\"=\"A3\".\"ID\" "
				+ "AND \"A3\".\"OFERTA\"=\"A2\".\"ID\" "
				+ "AND \"A2\".\"ALOJAMIENTOID\"=\"A1\".\"ID\""
				+ " GROUP BY \"A5\".\"NOMBRE\"", AlohAndesTransactionManager.USUARIO,id);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next()) {
			usos.add(convertResultSetToUsoCliente(rs));
		}

		return usos;
	}

	/**
	 * Metodo que obtiene los usos de la plataforma por todos los tipos de clientes 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @return usos de la plataforma por los diferentes tipos de cliente en la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */	
	public ArrayList<UsoTipo> usosPorVinculo() throws SQLException, Exception {
		ArrayList<UsoTipo> usos = new ArrayList<UsoTipo>();
		String sql = String.format(
				"SELECT \"A5\".\"VINCULO\" \"VINCULO\",STATS_MODE(\"A1\".\"TIPO\") \"TIPOFRECUENTE\",SUM(\"A3\".\"CANTIDADDIAS\") \"DIAS\",SUM(\"A2\".\"PRECIOESTADIA\") \"PRECIOS\""
				+ " FROM \"%1$s\".\"CLIENTES\" \"A5\",\"%1$s\".\"RESERVASCLIENTE\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\",\"%1$s\".\"OFERTAS\" \"A2\",\"%1$s\".\"ALOJAMIENTOS\" \"A1\" "
				+ "WHERE \"A4\".\"CLIENTEID\"=\"A5\".\"DOCUMENTO\" "
				+ "AND \"A4\".\"RESERVAID\"=\"A3\".\"ID\" "
				+ "AND \"A3\".\"OFERTA\"=\"A2\".\"ID\" "
				+ "AND \"A2\".\"ALOJAMIENTOID\"=\"A1\".\"ID\" "
				+ "GROUP BY \"A5\".\"VINCULO\"",
				AlohAndesTransactionManager.USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			usos.add(convertResultSetToUsoTipo(rs));
		}
		return usos;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AUXILIARES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro
	 * <b>Postcondicion: </b> el atributo conn es inicializado
	 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection){
		this.conn = connection;
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos
	 * <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han sido cerrados.
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla CLIENTE) en una instancia de la clase Cliente.
	 * @param resultSet ResultSet con la informacion de un cliente que se obtuvo de la base de datos.
	 * @return Cliente cuyos atributos corresponden a los valores asociados a un registro particular de la tabla CLIENTES.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Cliente convertResultSetToCliente(ResultSet resultSet) throws SQLException {
		Long documento= resultSet.getLong("DOCUMENTO");
		String nombre= resultSet.getString("NOMBRE");
		String vinculo = resultSet.getString("VINCULO");
		Cliente cliente = new Cliente(documento, nombre, vinculo);

		return cliente;
	}
	
	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL en una instancia de la clase UsoCliente.
	 * @param resultSet ResultSet con la informacion de un cliente que se obtuvo de la base de datos.
	 * @return Uso del Cliente cuyos atributos corresponden a los valores asociados a los datos de la Base de datos.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public UsoCliente convertResultSetToUsoCliente(ResultSet resultSet) throws SQLException {
		String nombreCliente= resultSet.getString("NOMBRE");
		String tipo= resultSet.getString("TIPOFRECUENTE");
		int dias = resultSet.getInt("DIAS");
		Double precio=resultSet.getDouble("PRECIOS");
		UsoCliente uso=new UsoCliente(nombreCliente,dias,tipo,dias*precio);

		return uso;
	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL en una instancia de la clase UsoTipo.
	 * @param resultSet ResultSet con la informacion de los usos de un tipo de cliente que se obtuvo de la base de datos.
	 * @return Uso del Cliente cuyos atributos corresponden a los valores asociados a los datos de la Base de datos.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public UsoTipo convertResultSetToUsoTipo(ResultSet resultSet) throws SQLException {
		String tipoCliente= resultSet.getString("VINCULO");
		String tipo= resultSet.getString("TIPOFRECUENTE");
		int dias = resultSet.getInt("DIAS");
		Double precio=resultSet.getDouble("PRECIOS");
		UsoTipo uso=new UsoTipo(tipoCliente,dias,tipo,dias*precio);

		return uso;
	}
}
