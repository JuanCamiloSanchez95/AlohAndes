package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import tm.AlohAndesTransactionManager;
import vos.Oferta;

public class DAOOferta {

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
	 * Metodo constructor de la clase DAOReserva
	 */
	public DAOOferta() {
		recursos = new ArrayList<Object>();
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Metodo que obtiene la informacion de todos las ofertas en la Base de Datos
	 * <b>Precondicion: </b> la conexion a sido inicializada
	 * @return	lista con la informacion de todos las ofertas que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Oferta> getOfertas() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();

		String sql = String.format("SELECT * FROM %1$s.OFERTAS", AlohAndesTransactionManager.USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(convertResultSetToOferta(rs));
		}
		return ofertas;
	}
	
	/**
	 * Metodo que obtiene la informacion de todos las ofertas de un operador en la Base de Datos
	 * <b>Precondicion: </b> la conexion a sido inicializada
	 * @param idOperador id del Operador cuyas ofertas se quieren buscar
	 * @return	lista con la informacion de todos las ofertas que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Oferta> getOfertasByOperador(int idOperador) throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();

		String sql = String.format("SELECT * FROM %1$s.OFERTAS WHERE OPERADOR = %2$d", AlohAndesTransactionManager.USUARIO, idOperador);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(convertResultSetToOferta(rs));
		}
		return ofertas;
	}
	

	
	
	/**
	 * Metodo que obtiene la informacion de la oferta en la Base de Datos que tiene el identificador dado por parametro
	 * <b>Precondicion: </b> la conexion a sido inicializada
	 * @param id el identificador de la oferta
	 * @return la informacion de la oferta que cumple con los criterios de la sentecia SQL
	 * 			Null si no existe la oferta con los criterios establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Oferta findOfertaById(int id) throws SQLException, Exception 
	{
		Oferta oferta = null;

		String sql = String.format("SELECT * FROM %1$s.OFERTAS WHERE ID = %2$d", AlohAndesTransactionManager.USUARIO, id); 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			oferta = convertResultSetToOferta(rs);
		}

		return oferta;
	}

	
	/**
	 * Metodo que elimina la oferta, en la Base de Datos, que tiene el identificador dado por parametr
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @param oferta Oferta que se desea eliminar de la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void deleteOferta(Oferta oferta) throws SQLException, Exception {

		String sql = String.format("DELETE FROM %1$s.OFERTAS WHERE ID = %2$d", AlohAndesTransactionManager.USUARIO, oferta.getId());

		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	
	/**
	 * Metodo que obtiene el numero de reservas de una oferta en la Base de Datos 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @param id - Identificador de la oferta
	 * @return	numero de reservas actuales de la oferta
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public int getNumReservas(int id) throws SQLException, Exception {
		
		int numReservas=0;
		
		String sql = String.format("SELECT COUNT(ID) AS NUMRESERVAS FROM %1$s.RESERVAS WHERE OFERTA = %2$d", AlohAndesTransactionManager.USUARIO, id);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if(rs.next()) {
			numReservas = rs.getInt("NUMRESERVAS");
		}
		
		return numReservas;
	}
	
	/**
	 * Metodo que obtiene la informacion de las 20 ofertas mas populares en la Base de Datos 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @return	lista con la informacion de las 20 ofertas mas populares que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Oferta> getIndicesOcupacion() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = getOfertas();
		
		for(int i=0;i<ofertas.size();i++) {
			Oferta actual=ofertas.get(i);
			int numReservas= getNumReservas(actual.getId());
			actual.setIndiceOcupacion(((double) numReservas)/actual.getCapacidad());
		}
		
		return ofertas;
	}
	
	/**
	 * Metodo que obtiene la informacion de las 20 ofertas mas populares en la Base de Datos 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @return	lista con la informacion de las 20 ofertas mas populares que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Oferta> getOfertasMasPopulares() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		String sql = String.format("SELECT OFERTAS.ID FROM OFERTAS,RESERVAS WHERE RESERVAS.OFERTA=OFERTAS.ID AND ROWNUM <=20 GROUP BY OFERTAS.ID ORDER BY COUNT(OFERTAS.ID) DESC");
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(findOfertaById(rs.getInt("ID")));
		}
		return ofertas;
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
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla OFERTAS) en una instancia de la clase Oferta.
	 * @param resultSet ResultSet con la informacion de un bebedor que se obtuvo de la base de datos.
	 * @return Oferta cuyos atributos corresponden a los valores asociados a un registro particular de la tabla OFERTAS.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Oferta convertResultSetToOferta(ResultSet resultSet) throws SQLException {

		try
		{
			Integer id= resultSet.getInt("ID");
			Double precioEstadia = resultSet.getDouble("PRECIOESTADIA");
			String nombre = resultSet.getString("NOMBRE");
			String descripcion = resultSet.getString("DESCRIPCION");
			Date fp = resultSet.getDate("FECHAPUBLICACION");
			//SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
			//Date fechaPublicacion = (Date) df.parse(fp);
			Integer capacidad= resultSet.getInt("CAPACIDAD");
			Integer operador= resultSet.getInt("OPERADOR");
			Integer alojamientoId= resultSet.getInt("ALOJAMIENTOID");
			Oferta oferta = new Oferta(id, precioEstadia, nombre, descripcion, fp,capacidad);

			return oferta;
		} 
		catch(Exception e)
		{
			throw new SQLException(e.getMessage());
		}
	}


}
