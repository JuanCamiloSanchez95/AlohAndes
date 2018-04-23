package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tm.AlohAndesTransactionManager;
import vos.Cliente;

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
	 * Metodo que obtiene los dias contratados por un cliente que tiene el identificador dado por parametro
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @param documento - documento de identificacion del cliente
	 * @return dias contratados por el cliente en la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public int diasContratados(int documento) throws SQLException, Exception {
		int dias = 0;
		String sql = String.format("SELECT SUM(\"A2\".\"CANTIDADDIAS\") \"DIAS\" "
				+ "FROM \"%1$s\".\"RESERVAS\" \"A2\",\"%1$s\".\"RESERVASCLIENTE\" \"A1\" "
				+ "WHERE \"A1\".\"RESERVAID\"=\"A2\".\"ID\" AND \"A1\".\"CLIENTEID\"=%2$d", AlohAndesTransactionManager.USUARIO, documento); 
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			dias = rs.getInt("DIAS");
		}
		return dias;
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
}
