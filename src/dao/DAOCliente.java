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
	 * Metodo que obtiene la informacion del cliente en la Base de Datos que tiene el identificador dado por parametro
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @param documento - documento de identificacion del cliente
	 * @return la informacion del cliente que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe el cliente con los criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Cliente findClienteByDocument(int documento) throws SQLException, Exception 
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
	 * @return usos de la plataforma en el cliente en la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */	
	public ArrayList<UsoCliente> usosDelCliente(int id) throws SQLException, Exception {
		ArrayList<UsoCliente> usos = new ArrayList<UsoCliente>();
		String sql=String.format("SELECT \"A5\".\"DOCUMENTO\" \"DOCUMENTO\",\"A5\".\"VINCULO\" \"VINCULO\",\"A1\".\"TIPO\" \"TIPO\","
				+ "\"A1\".\"DESCRIPCION\" \"DESCRIPCION\",\"A3\".\"CANTIDADDIAS\" \"CANTIDADDIAS\",\"A2\".\"PRECIOESTADIA\" \"PRECIOESTADIA\""
				+ " FROM \"%1$s\".\"CLIENTES\" \"A5\",\"%1$s\".\"RESERVASCLIENTE\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\""
				+ ",\"%1$s\".\"OFERTAS\" \"A2\",\"%1$s\".\"ALOJAMIENTOS\" \"A1\" "
				+ "WHERE \"A5\".\"DOCUMENTO\"= %2$d AND \"A4\".\"CLIENTEID\"=\"A5\".\"DOCUMENTO\" "
				+ "AND \"A4\".\"RESERVAID\"=\"A3\".\"ID\" AND \"A3\".\"OFERTA\"=\"A2\".\"ID\" "
				+ "AND \"A2\".\"ALOJAMIENTOID\"=\"A1\".\"ID\"", AlohAndesTransactionManager.USUARIO,id);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next()) {
			usos.add(convertResultSetToUsoCliente(rs));
		}

		return usos;
	}


	public ArrayList<String> vinculosDeClientes() throws SQLException, Exception {
		ArrayList<String> vinculos = new ArrayList<String>();
		String sql=String.format("SELECT DISTINCT(VINCULO) AS VINCULO FROM %1$s.CLIENTES", AlohAndesTransactionManager.USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while(rs.next()) {
			vinculos.add(rs.getString("VINCULO"));
		}

		return vinculos;
	}

	public ArrayList<UsoCliente> usosPorVinculo() throws SQLException, Exception {
		ArrayList<String> vinculos = vinculosDeClientes();
		ArrayList<UsoCliente> usos = new ArrayList<UsoCliente>();

		for(int i=0;i<vinculos.size();i++) {
			String sql=String.format("SELECT \"A5\".\"DOCUMENTO\" \"DOCUMENTO\",\"A5\".\"VINCULO\" \"VINCULO\",\"A1\".\"TIPO\" \"TIPO\",\"A1\".\"DESCRIPCION\" \"DESCRIPCION\",\"A3\".\"CANTIDADDIAS\" \"CANTIDADDIAS\",\"A2\".\"PRECIOESTADIA\" \"PRECIOESTADIA\" "
					+ "FROM \"%1$s\".\"CLIENTES\" \"A5\",\"%1$s\".\"RESERVASCLIENTE\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\",\"%1$s\".\"OFERTAS\" \"A2\",\"%1$s\".\"ALOJAMIENTOS\" \"A1\""
					+ " WHERE \"A5\".\"VINCULO\"='%2$s' AND \"A4\".\"CLIENTEID\"=\"A5\".\"DOCUMENTO\" AND \"A4\".\"RESERVAID\"=\"A3\".\"ID\" AND \"A3\".\"OFERTA\"=\"A2\".\"ID\" AND \"A2\".\"ALOJAMIENTOID\"=\"A1\".\"ID\"", AlohAndesTransactionManager.USUARIO,vinculos.get(i));

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while(rs.next()) {
				usos.add(convertResultSetToUsoCliente(rs));
			}
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
		Integer documento= resultSet.getInt("DOCUMENTO");
		String nombre= resultSet.getString("NOMBRE");
		String vinculo = resultSet.getString("VINCULO");
		Cliente cliente = new Cliente(documento, nombre, vinculo);

		return cliente;
	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL en una instancia de la clase UsoCliente.
	 * @param resultSet ResultSet con la informacion de los usos de un cliente que se obtuvo de la base de datos.
	 * @return Uso del Cliente cuyos atributos corresponden a los valores asociados a los datos de la Base de datos.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public UsoCliente convertResultSetToUsoCliente(ResultSet resultSet) throws SQLException {
		Integer documento= resultSet.getInt("DOCUMENTO");
		String tipoCliente= resultSet.getString("VINCULO");
		String tipo= resultSet.getString("TIPO");
		String des = resultSet.getString("DESCRIPCION");
		int dias = resultSet.getInt("CANTIDADDIAS");
		Double precio=resultSet.getDouble("PRECIOESTADIA");
		UsoCliente uso=new UsoCliente(documento,tipoCliente,dias,tipo,des,dias*precio);

		return uso;
	}
}
