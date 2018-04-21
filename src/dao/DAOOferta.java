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
import vos.Reserva;;

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
	 * Metodo constructor de la clase DAOReserva <br/>
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

		String sql = String.format("SELECT * FROM %1$s.OFERTA", AlohAndesTransactionManager.USUARIO);

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
	 * @param id el identificador de la reserva
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
	 * Metodo que elimina la oferta, en la Base de Datos, que tiene el identificador dado por parametro<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>  
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
	 * Metodo que obtiene la informacion de todos los bebedores en la Base de Datos <br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * @return	lista con la informacion de todos los bebedores que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Oferta> getOfertasMasPopulares() throws SQLException, Exception {
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		String sql = String.format("SELECT OFERTAS.ID FROM OFERTAS,RESERVASOFERTA WHERE RESERVASOFERTA.OFERTA=OFERTAS.ID AND ROWNUM <=20 GROUP BY OFERTAS.IDORDER BY COUNT(OFERTAS.ID) DESC");
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			ofertas.add(findOfertaById(rs.getInt("OFERTAS.ID")));
		}
		return ofertas;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AUXILIARES
	//----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a partir del parametro <br/>
	 * <b>Postcondicion: </b> el atributo conn es inicializado <br/>
	 * @param connection la conexion generada en el TransactionManager para la comunicacion con la Base de Datos
	 */
	public void setConn(Connection connection){
		this.conn = connection;
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de recursos<br/>
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
			Integer longitudEstadia= resultSet.getInt("LONGITUDESTADIA");
			Double precioEstadia = resultSet.getDouble("PRECIOESTADIA");
			String nombre = resultSet.getString("NOMBRE");
			String ubicacion = resultSet.getString("UBICACION");
			String descripcion = resultSet.getString("DESCRIPCION");
			String fp = resultSet.getString("FECHAPUBLICACION");
			Double costo = resultSet.getDouble("COSTO");
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD");
			Date fechaPublicacion = (Date) df.parse(fp);
			Integer operador= resultSet.getInt("OPERADOR");
			Integer habitacion= resultSet.getInt("HABITACION");
			Integer apartamento= resultSet.getInt("APARTAMENTO");
			Integer vivienda= resultSet.getInt("VIVIENDA");
			Oferta oferta = new Oferta(id, longitudEstadia, precioEstadia, nombre, ubicacion, descripcion, fechaPublicacion);

			return oferta;
		} 
		catch(Exception e)
		{
			throw new SQLException(e.getMessage());
		}
	}


}
