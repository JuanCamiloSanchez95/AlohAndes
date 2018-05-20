package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tm.AlohAndesTransactionManager;
import vos.Alojamiento;
import vos.AnalisisOperacion;
import vos.Apartamento;
import vos.Cliente;
import vos.ClienteFrecuente;
import vos.EstadisticaOperacion;
import vos.HabitacionHostal;
import vos.HabitacionHotel;
import vos.HabitacionUniversitaria;
import vos.HabitacionVivienda;
import vos.Oferta;
import vos.Operador;
import vos.SolicitudAnalisisOperacion;
import vos.Vivienda;

public class DAOAlojamiento {
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
	 * Metodo constructor de la clase DAOAlojamiento
	 */
	public DAOAlojamiento() {
		recursos = new ArrayList<Object>();
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE COMUNICACION CON LA BASE DE DATOS
	//----------------------------------------------------------------------------------------------------------------------------------


	/**
	 * Metodo que agregar la informacion de un nuevo alojamiento  en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param alojamiento- alojamiento que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addAlojamiento(Alojamiento alojamiento) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.ALOJAMIENTOS (ID, TIPO, NOMBRE, UBICACION,DESCRIPCION, COSTO,MINIMOPERIODO) VALUES (%2$s, '%3$s', '%4$s','%5$s''%6$s','%7$s')",
				AlohAndesTransactionManager.USUARIO, alojamiento.getId(), alojamiento.getTipo(), alojamiento.getNombre(),alojamiento.getUbicacion(),alojamiento.getCosto(),alojamiento.getMinimoPeriodo());
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	

	/**
	 * Metodo que agregar la informacion de un nuevo Apartamento  en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param Apartamento- Apartamento que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addApartamento(Apartamento apa) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.APARTAMENTOS (ID, TIPO, NOMBRE, UBICACION,DESCRIPCION, COSTO,MINIMOPERIODO,AMOBLADO,ADMINISTRACION) VALUES (%2$s, '%3$s', '%4$s','%5$s''%6$s','%7$s','%8$s''%9$s')",
				AlohAndesTransactionManager.USUARIO, apa.getId(), apa.getTipo(), apa.getNombre(),apa.getUbicacion(),apa.getCosto(),apa.getMinimoPeriodo(),apa.isAmoblado(),apa.getAdministracion());
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	

	/**
	 * Metodo que agregar la informacion de un nuevo HabitacionUniversitaria  en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param HabitacionUniversitaria- HabitacionUniversitaria que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addHabitacionUniversitaria(HabitacionUniversitaria hu) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.HABITACIONESUNIVERSITARIAS (ID, TIPO, NOMBRE, UBICACION,DESCRIPCION, COSTO,MINIMOPERIODO,COMPARTIDA,NUMEROHABITACION,CAPACIDAD,MENAJE,UBICACIONH) VALUES (%2$s, '%3$s', '%4$s','%5$s''%6$s','%7$s','%8$s','%9$s','%10$s''%11$s','%12$s')",
				AlohAndesTransactionManager.USUARIO, hu.getId(), hu.getTipo(), hu.getNombre(),hu.getUbicacion(),hu.getCosto(),hu.getMinimoPeriodo(),hu.isCompartida(),hu.getNumHabitacion(),hu.getCapacidad(),hu.getMenaje(),hu.getUbicacionH());
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	/**
	 * Metodo que agregar la informacion de un nuevo HabitacionVivienda  en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param HabitacionVivienda- HabitacionVivienda que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addHabitacionesVivienda(HabitacionVivienda viv) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.HABITACIONESVIVIENDA (ID, TIPO, NOMBRE, UBICACION,DESCRIPCION, COSTO,MINIMOPERIODO,COMPARTIDA,URLESQUEMA) VALUES (%2$s, '%3$s', '%4$s','%5$s''%6$s','%7$s','%8$s','%9$s')",
				AlohAndesTransactionManager.USUARIO, viv.getId(), viv.getTipo(), viv.getNombre(),viv.getUbicacion(),viv.getCosto(),viv.getMinimoPeriodo(),viv.isCompartida(),viv.getUrlEsquema());
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * Metodo que agregar la informacion de un nuevo Vivienda  en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param Vivienda- Vivienda que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addVivienda(Vivienda viv) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.VIVIENDAS (ID, TIPO, NOMBRE, UBICACION,DESCRIPCION, COSTO,MINIMOPERIODO,NUMEROHABITACIONES,MENAJE,IDS) VALUES (%2$s, '%3$s', '%4$s','%5$s''%6$s','%7$s','%8$s','%9$s','%10$s')",
				AlohAndesTransactionManager.USUARIO, viv.getId(), viv.getTipo(), viv.getNombre(),viv.getUbicacion(),viv.getCosto(),viv.getMinimoPeriodo(),viv.getNumeroHabitaciones(),viv.getMenaje(),viv.getSeguro());
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	/**
	 * Metodo que agregar la informacion de un nuevo HabitacionHostal  en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param HabitacionHostal- HabitacionHostal que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addHabitacionHostal(HabitacionHostal ha) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.HABITACIONESHOSTAL (ID, TIPO, NOMBRE, UBICACION,DESCRIPCION, COSTO,MINIMOPERIODO,CAPACIDAD,TIPO,NUMEROHABITACION,TAMANO) VALUES (%2$s, '%3$s', '%4$s','%5$s''%6$s','%7$s','%8$s','%9$s','%10$s','%11$s')",
				AlohAndesTransactionManager.USUARIO, ha.getId(), ha.getTipo(), ha.getNombre(),ha.getUbicacion(),ha.getCosto(),ha.getMinimoPeriodo(),ha.getCapacidad(),ha.getTipo(),ha.getNumeroHabitacion(),ha.getTamano());
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	

	/**
	 * Metodo que agregar la informacion de un nuevo HabitacionHotel  en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param HabitacionHotel- HabitacionHotel que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addHabitacionHotel(HabitacionHotel ha) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.HABITACIONESHOTEL (ID, TIPO, NOMBRE, UBICACION,DESCRIPCION, COSTO,MINIMOPERIODO,CAPACIDAD,TIPO,NUMEROHABITACION,TAMANO) VALUES (%2$s, '%3$s', '%4$s','%5$s''%6$s','%7$s','%8$s','%9$s','%10$s','%11$s')",
				AlohAndesTransactionManager.USUARIO, ha.getId(), ha.getTipo(), ha.getNombre(),ha.getUbicacion(),ha.getCosto(),ha.getMinimoPeriodo(),ha.getCapacidad(),ha.getTipo(),ha.getNumeroHabitacion(),ha.getTamano());
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
	/**
	 * Metodo que obtiene la informacion de los alojamientos disponibles 
	 * 	 * <b>Precondicion: </b> la conexion a sido inicializada
	 * @return	lista con lla informacion de los alojamientos que se cumplen las condiciones en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Alojamiento> getAlojamientos() throws SQLException, Exception {
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		StringBuilder sql = new StringBuilder();
		sql.append(String.format("SELECT * FROM %1$s.ALOJAMIENTOS",AlohAndesTransactionManager.USUARIO));
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) {
			alojamientos.add(convertResultSetToAlojamiento(rs));
		}
		return alojamientos;
	}
	
	/**
	 * Metodo que obtiene la informacion de un alojamiento en la Base de Datos que tiene
	 * el identificador dado por parametro 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @param id el identificador del alojamiento
	 * @return la informacion del alojamiento que cumple con los criterios de la
	 *         sentecia SQL Null si no existe el alojamiento con los criterios
	 *         establecidos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public Alojamiento findAlojamientoById(Long id) throws SQLException, Exception {
		Alojamiento alojamiento = null;

		String sql = String.format("SELECT * FROM %1$s.ALOJAMIENTOS WHERE ID = %2$d", AlohAndesTransactionManager.USUARIO,
				id);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		if (rs.next()) {
			alojamiento = convertResultSetToAlojamiento(rs);
		}

		return alojamiento;
	}
	
	//RFC7
	
	public AnalisisOperacion analisisByAlojamiento(SolicitudAnalisisOperacion solicitud) throws SQLException, Exception{
		AnalisisOperacion analisis = new AnalisisOperacion(solicitud.getCategoria(),solicitud.getUnidadTiempo());
		ArrayList<EstadisticaOperacion> estadisticas = new ArrayList<EstadisticaOperacion>();
		
		long startTime = System.currentTimeMillis();
		
		String timeUnit = null;
		if(solicitud.getUnidadTiempo().equalsIgnoreCase("SEMANA")) 
			timeUnit="IW";
		else if(solicitud.getUnidadTiempo().equalsIgnoreCase("DIA"))
			timeUnit="DD";
		else if(solicitud.getUnidadTiempo().equalsIgnoreCase("AÑO"))
			timeUnit="YYYY";
		else if(solicitud.getUnidadTiempo().equalsIgnoreCase("MES"))
			timeUnit="MON";
		
		String sql = String.format("SELECT TO_CHAR(RESERVAS.FECHALLEGADA,'%1$s') as periodo, sum(OFERTAS.PRECIOESTADIA*RESERVAS.CANTIDADDIAS)AS ingresos, count(*) as numreservas " + 
				"FROM %2$s.RESERVAS, %2$s.OFERTAS, %2$s.ALOJAMIENTOS " + 
				"WHERE RESERVAS.OFERTA=OFERTAS.ID " + 
				"AND ALOJAMIENTOS.ID = OFERTAS.ALOJAMIENTOID " + 
				"AND ALOJAMIENTOS.TIPO='%3$s' " + 
				"group by TO_CHAR(RESERVAS.FECHALLEGADA,'%1$s')",timeUnit, AlohAndesTransactionManager.USUARIO,solicitud.getCategoria());
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");
	    
		while (rs.next()) {
			estadisticas.add(convertResultSetToEstadisticaOperacion(rs));
		}
		
		if (!estadisticas.isEmpty()) {
			EstadisticaOperacion mayorIngreso = estadisticas.get(0);
			EstadisticaOperacion mayorDemanda = estadisticas.get(0);
			EstadisticaOperacion menorOcupacion = estadisticas.get(0);

			for (int i = 0; i < estadisticas.size(); i++) {
				EstadisticaOperacion actual = estadisticas.get(i);
				if (actual.getIngresos() > mayorIngreso.getIngresos()) {
					mayorIngreso = actual;
				}
				if (actual.getNumReservas() > mayorDemanda.getNumReservas()) {
					mayorDemanda = actual;
				}
				if (menorOcupacion.getNumReservas() > actual.getNumReservas()) {
					menorOcupacion = actual;
				}
			}

			analisis.setMayorDemanda(mayorDemanda);
			analisis.setMayorIngreso(mayorIngreso);
			analisis.setMenorOcupacion(menorOcupacion);
			analisis.setEstadisticas(estadisticas);
		}
		System.out.println(sql);
		return analisis;
	}
	
	//RFC8
	
	/**
	 * Metodo que obtiene la informacion de los clientes frecuentes de un alojamiento cuyo id es dado en la Base de Datos
	 * <b>Precondicion: </b> la conexion a sido inicializada
	 * @param id - Identificador del alojamiento a la cual se buscan sus clientes frecuente
	 * @return	lista con la informacion de los clientes que cumplen las condiciones en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<ClienteFrecuente> findClienteFrecuentes(Long id)throws SQLException, Exception {
		ArrayList<ClienteFrecuente> clientes = new ArrayList<ClienteFrecuente>();
		long startTime = System.currentTimeMillis();
		String sql = String.format("SELECT \"A1\".\"NOMBRE\" \"NOMBRE\",\"A1\".\"VINCULO\" \"VINCULO\",\"A1\".\"NUMUSOS\" \"NUMUSOS\",\"A1\".\"NUMDIAS\" \"NUMDIAS\""
				+ " FROM  (SELECT \"A5\".\"NOMBRE\" \"NOMBRE\",\"A5\".\"VINCULO\" \"VINCULO\",COUNT(*) \"NUMUSOS\",SUM(\"A3\".\"CANTIDADDIAS\") \"NUMDIAS\" "
				+ "FROM \"%1$s\".\"CLIENTES\" \"A5\",\"%1$s\".\"RESERVASCLIENTE\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\",\"%1$s\".\"OFERTAS\" \"A2\" "
				+ "WHERE \"A5\".\"DOCUMENTO\"=\"A4\".\"CLIENTEID\" AND \"A4\".\"RESERVAID\"=\"A3\".\"ID\" AND \"A3\".\"OFERTA\"=\"A2\".\"ID\" AND \"A2\".\"ALOJAMIENTOID\"= %2$d "
				+ "GROUP BY \"A5\".\"NOMBRE\",\"A5\".\"VINCULO\") \"A1\" "
				+ "WHERE \"A1\".\"NUMUSOS\">=3 OR \"A1\".\"NUMDIAS\">=15", AlohAndesTransactionManager.USUARIO,
				id);
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");
	    
		while (rs.next()) {
			clientes.add(convertResultSetToClienteFrecuente(rs));
		}
		
		return clientes;	
	}
	
	//RFC4
	
	/**
	 * Metodo que obtiene la informacion de los alojamientos disponibles en un rango de fechas dado por el usuario en la Base de Datos,
	 * estos alojamientos tambien deben cumplir con ciertos servicios.
	 * <b>Precondicion: </b> la conexion a sido inicializada
	 * @return	lista con lla informacion de los alojamientos que se cumplen las condiciones en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<Alojamiento> getAlojamientosByFechaAndServicios(Date inicio, Date fin, String servicios) throws SQLException, Exception {
		ArrayList<Alojamiento> alojamientos = new ArrayList<Alojamiento>();
		
		long startTime = System.currentTimeMillis();
		
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String[] parts=servicios.split(",");
		StringBuilder sql = new StringBuilder();
		
		
		sql.append(String.format("SELECT ALOJAMIENTOS.* FROM %1$s.ALOJAMIENTOS,%1$s.OFERTAS,%1$s.SERVICIOSDEALOJAMIENTOS,%1$s.SERVICIOS",AlohAndesTransactionManager.USUARIO));
		sql.append(String.format(" WHERE OFERTAS.FECHAPUBLICACION BETWEEN TO_DATE('%1$s','DD/MM/YYYY')",formatter.format(inicio)));
		sql.append(String.format(" AND TO_DATE('%1$s','DD/MM/YYYY')",formatter.format(fin))); 
		sql.append(" AND OFERTAS.ALOJAMIENTOID=ALOJAMIENTOS.ID");
		sql.append(" AND ALOJAMIENTOS.ID=SERVICIOSDEALOJAMIENTOS.ALOJAMIENTO");
		sql.append(" AND SERVICIOS.ID=SERVICIOSDEALOJAMIENTOS.SERVICIO");
		
		for(int i=0;i<parts.length;i++) {
			if(i==0) {
				sql.append(String.format(" AND SERVICIOS.NOMBRE IN ('%1$s'", parts[i]));
			}
			else
			sql.append(String.format(",'%1$s'", parts[i]));
		}
		sql.append(")");
		
		System.out.println(sql.toString());
		
		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

		while (rs.next()) {
			alojamientos.add(convertResultSetToAlojamiento(rs));
		}
		return alojamientos;
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
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla ALOJAMIENTO) en una instancia de la clase Alojamiento.
	 * @param resultSet ResultSet con la informacion de un cliente que se obtuvo de la base de datos.
	 * @return Alojamiento cuyos atributos corresponden a los valores asociados a un registro particular de la tabla ALOJAMIENTO.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public Alojamiento convertResultSetToAlojamiento(ResultSet resultSet) throws SQLException {
		Integer id= resultSet.getInt("ID");
		String tipo= resultSet.getString("TIPO");
		String nombre= resultSet.getString("NOMBRE");
		String ubicacion = resultSet.getString("UBICACION");
		String descripcion = resultSet.getString("DESCRIPCION");
		Double costo=resultSet.getDouble("COSTO");
		Integer min= resultSet.getInt("MINIMOPERIODO");
		Alojamiento alojamiento=new Alojamiento(id,nombre,tipo,ubicacion,descripcion,costo,min);

		return alojamiento;
	}
	
	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL en una instancia de la clase ClienteFrecuente.
	 * @param resultSet ResultSet con la informacion de un cliente que se obtuvo de la base de datos.
	 * @return ClienteFrecuente cuyos atributos corresponden a los valores asociados a un registro particular de la tabla CLIENTE.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public ClienteFrecuente convertResultSetToClienteFrecuente(ResultSet resultSet) throws SQLException {
		String nombre= resultSet.getString("NOMBRE");
		String vinculo= resultSet.getString("VINCULO");
		Integer usos= resultSet.getInt("NUMUSOS");
		Integer dias= resultSet.getInt("NUMDIAS");
		ClienteFrecuente cliente = new ClienteFrecuente(nombre,vinculo,usos,dias);

		return cliente;
	}
	
	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL en una instancia de la clase ClienteFrecuente.
	 * @param resultSet ResultSet con la informacion de un cliente que se obtuvo de la base de datos.
	 * @return ClienteFrecuente cuyos atributos corresponden a los valores asociados a un registro particular de la tabla CLIENTE.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public EstadisticaOperacion convertResultSetToEstadisticaOperacion(ResultSet resultSet) throws SQLException {
		String periodo= resultSet.getString("PERIODO");
		Integer numReservas= resultSet.getInt("NUMRESERVAS");
		Double ingresos= resultSet.getDouble("INGRESOS");
		EstadisticaOperacion estadistica = new EstadisticaOperacion(periodo,ingresos,numReservas);

		return estadistica;
	}
}
