package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import vos.Bebedor;
import vos.DineroOperador;
import vos.Hostal;
import vos.Hotel;
import vos.Operador;
import tm.AlohAndesTransactionManager;

public class DAOOperador {

	// ----------------------------------------------------------------------------------------------------------------------------------
	// ATRIBUTOS
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Arraylits de recursos que se usan para la ejecucion de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexion a la base de datos
	 */
	private Connection conn;

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE INICIALIZACION
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo constructor de la clase DAOReserva
	 */
	public DAOOperador() {
		recursos = new ArrayList<Object>();
	}
	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que agregar la informacion de un nuevo operador en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param operador- Operador que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addOperador(Operador operador) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.OPERADORES (ID, TIPO, NOMBRE) VALUES (%2$s, '%3$s', '%4$s')",
				AlohAndesTransactionManager.USUARIO, operador.getId(), operador.getTipo(), operador.getNombre());
		System.out.println(sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	

	/**
	 * Metodo que agregar la informacion de un nuevo hotel en la Base de Datos a
	 * partir del parametro ingresado
	 * <b>Precondicion: </b> la conexion a sido inicializadoa 
	 * @param hotel Hotel que desea agregar a la Base de Datos
	 * @throws SQLException - SQLException Genera excepcion si hay error en la conexion o en la  consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addHotel(Hotel hotel) throws SQLException, Exception {
		
		//Creacion del Operador
		String sql = String.format("INSERT INTO %1$s.OPERADORES (ID, TIPO, NOMBRE) VALUES (%2$s, '%3$s', '%4$s')",
				AlohAndesTransactionManager.USUARIO, hotel.getId(), hotel.getTipo(), hotel.getNombre());
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		// Creacion Hotel
		sql = String.format("INSERT INTO %1$s.HOTELES (ID, NOMBREHOTEL, CATEGORIA, REGISTROSI, REGISTROCAMARA) VALUES (%2$s, '%3$s', '%4$s')",
				AlohAndesTransactionManager.USUARIO, hotel.getId(), hotel.getNombreHotel(), hotel.getCategoria(), hotel.getRegistroST(), hotel.getRegistroCamara());
		System.out.println(sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	/**
	 * Metodo que agregar la informacion de un nuevo hotel en la Base de Datos a
	 * partir del parametro ingresado
	 * <b>Precondicion: </b> la conexion a sido inicializadoa 
	 * @param hotel Hotel que desea agregar a la Base de Datos
	 * @throws SQLException - SQLException Genera excepcion si hay error en la conexion o en la  consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public void addHostal(Hostal hostal) throws SQLException, Exception {
		
		//Creacion del Operador
		String sql = String.format("INSERT INTO %1$s.OPERADORES (ID, TIPO, NOMBRE) VALUES (%2$s, '%3$s', '%4$s')",
				AlohAndesTransactionManager.USUARIO, hostal.getId(), hostal.getTipo(), hostal.getNombre());
		System.out.println(sql);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		// Creacion Hostal
		sql = String.format("INSERT INTO %1$s.HOSTALES (ID, NOMBREHOSTAL, REGISTROSI, REGISTROCAMARA,HORARIOAPERTURA,HORARIOCIERRE) VALUES (%2$s, '%3$s', '%4$s','%5$s', '%6$s','%7$s')",
				AlohAndesTransactionManager.USUARIO, hostal.getId(), hostal.getNombreHostal(), hostal.getRegistroST(), hostal.getRegistroCamara(),hostal.getHorarioApertura(),hostal.getHorarioCierre());
		System.out.println(sql);

		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}

	/**
	 * Metodo que obtiene la informacion de todos los operadores en la Base de Datos
	 * <b>Precondicion: </b> la conexion a sido inicializadoa
	 * 
	 * @return lista con la informacion de todos los operadores que se encuentran en
	 *         la Base de Datos
	 * @throws SQLException
	 *             Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ArrayList<Operador> getOperadores() throws SQLException, Exception {
		ArrayList<Operador> operadores = new ArrayList<Operador>();
		String sql = String.format("SELECT * FROM %1$s.OPERADORES", AlohAndesTransactionManager.USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			operadores.add(convertResultSetToOperador(rs));
		}

		return operadores;
	}

	/**
	 * Metodo que obtiene la informacion del operador en la Base de Datos que tiene
	 * el identificador dado por parametro 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @param id el identificador del operador
	 * @return la informacion del operador que cumple con los criterios de la
	 *         sentecia SQL Null si no existe el operador con los criterios
	 *         establecidos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public Operador findOperadorById(Long id) throws SQLException, Exception {
		Operador operador = null;

		String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE ID = %2$d", AlohAndesTransactionManager.USUARIO,
				id);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			operador = convertResultSetToOperador(rs);
		}

		return operador;
	}

	/**
	 * Metodo que obtiene la informacion del diero obtenido por cada operador en la
	 * Base de Datos en el año dado por parametro <b>Precondicion: </b> la conexion
	 * a sido inicializado
	 * 
	 * @param year
	 *            el año de consulta
	 * @return Lista de cadenas con la informacion por operador del dinero ganado en
	 *         el año
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public ArrayList<DineroOperador> getDineroRecibidoOperadores(int year) throws SQLException, Exception {
		ArrayList<DineroOperador> dinero = new ArrayList<DineroOperador>();
		String sql = String.format("SELECT \"A2\".\"NOMBRE\" \"NOMBRE\",\"A1\".\"PRECIOESTADIA\"*\"A1\".\"DIAS\" \"SUMA\" "
				+ "FROM \"%1$s\".\"OPERADORES\" \"A2\", (SELECT \"A4\".\"OPERADOR\" \"OPERADOR\",\"A4\".\"PRECIOESTADIA\" \"PRECIOESTADIA\",SUM(\"A3\".\"CANTIDADDIAS\") \"DIAS\" "
				+ "FROM \"%1$s\".\"OFERTAS\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\" "
				+ "WHERE \"A3\".\"OFERTA\"=\"A4\".\"ID\" "
				+ "AND EXTRACT(YEAR FROM \"A3\".\"FECHALLEGADA\")=%2$s "
				+ "GROUP BY \"A4\".\"OPERADOR\",\"A4\".\"PRECIOESTADIA\") \"A1\" "
				+ "WHERE \"A1\".\"OPERADOR\"=\"A2\".\"ID\"",
				AlohAndesTransactionManager.USUARIO, year);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) {
			dinero.add(convertResultSetToDineroOperador(rs));
		}
		return dinero;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------
	// METODOS AUXILIARES
	// ----------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Metodo encargado de inicializar la conexion del DAO a la Base de Datos a
	 * partir del parametro <b>Postcondicion: </b> el atributo conn es inicializado
	 * 
	 * @param connection
	 *            la conexion generada en el TransactionManager para la comunicacion
	 *            con la Base de Datos
	 */
	public void setConn(Connection connection) {
		this.conn = connection;
	}

	/**
	 * Metodo que cierra todos los recursos que se encuentran en el arreglo de
	 * recursos <b>Postcondicion: </b> Todos los recurso del arreglo de recursos han
	 * sido cerrados.
	 */
	public void cerrarRecursos() {
		for (Object ob : recursos) {
			if (ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la
	 * tabla OPERADORES) en una instancia de la clase Operador.
	 * 
	 * @param resultSet
	 *            ResultSet con la informacion de un operador que se obtuvo de la
	 *            base de datos.
	 * @return Operador cuyos atributos corresponden a los valores asociados a un
	 *         registro particular de la tabla OPERADORES.
	 * @throws SQLException
	 *             Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Operador convertResultSetToOperador(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("ID");
		String nombre = resultSet.getString("NOMBRE");
		String tipo = resultSet.getString("TIPO");
		Operador op = new Operador(id, nombre, tipo);

		return op;
	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la
	 * tabla OPERADORES) en una instancia de la clase DineroOperador.
	 * 
	 * @param resultSet  ResultSet con la informacion de un operador que se obtuvo de labase de datos.
	 * @return DineroOperador cuyos atributos corresponden a los valores asociados a la consulta sobre la tabla OPERADORES.
	 * @throws SQLException   Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public DineroOperador convertResultSetToDineroOperador(ResultSet resultSet) throws SQLException {

		String nombre = resultSet.getString("NOMBRE");
		double dinero = resultSet.getDouble("SUMA");
		DineroOperador dOperador= new DineroOperador(nombre,dinero);
		return dOperador;
	}

}
