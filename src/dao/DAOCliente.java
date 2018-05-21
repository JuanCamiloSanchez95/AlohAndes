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
import vos.Cliente;
import vos.ClienteBueno;
import vos.ConsultaConsumo;
import vos.Consumo;
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

	//RFC6
	
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
		long startTime = System.currentTimeMillis();
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
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

		while(rs.next()) {
			usos.add(convertResultSetToUsoCliente(rs));
		}

		return usos;
	}
	
	//RFC5

	/**
	 * Metodo que obtiene los usos de la plataforma por todos los tipos de clientes 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @return usos de la plataforma por los diferentes tipos de cliente en la Base de Datos
	 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */	
	public ArrayList<UsoTipo> usosPorVinculo() throws SQLException, Exception {
		ArrayList<UsoTipo> usos = new ArrayList<UsoTipo>();
		long startTime = System.currentTimeMillis();
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
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

		while (rs.next()) {
			usos.add(convertResultSetToUsoTipo(rs));
		}
		return usos;
	}
	
	//RFC13
	
	public ArrayList<ClienteBueno> consultaClientesBuenos()throws SQLException, Exception{
		ArrayList<ClienteBueno> clientes = new ArrayList<ClienteBueno>();
		long startTime = System.currentTimeMillis();
		String sql = String.format(
				"SELECT \"A1\".\"DOCUMENTO\" \"DOCUMENTO\",\"A1\".\"AÑO\" \"AÑO\",\"A1\".\"NUMRESERVAS\" \"NUMRESERVAS\""
				+ " FROM  (SELECT \"A4\".\"DOCUMENTO\" \"DOCUMENTO\",TO_CHAR(\"A3\".\"FECHALLEGADA\",'YYYY') \"AÑO\",COUNT(*) \"NUMRESERVAS\""
				+ " FROM \"%1$s\".\"CLIENTES\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\",\"%1$s\".\"RESERVASCLIENTE\" \"A2\""
				+ " WHERE \"A3\".\"ID\"=\"A2\".\"RESERVAID\" AND \"A2\".\"CLIENTEID\"=\"A4\".\"DOCUMENTO\" GROUP BY \"A4\".\"DOCUMENTO\",TO_CHAR(\"A3\".\"FECHALLEGADA\",'YYYY')"
				+ " ORDER BY \"A4\".\"DOCUMENTO\") \"A1\""
				+ " WHERE \"A1\".\"NUMRESERVAS\">=12",
				AlohAndesTransactionManager.USUARIO);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

		while (rs.next()) {
			clientes.add(convertResultSetToClienteBueno(rs));
		}
		
		sql = String.format(
				" SELECT \"A1\".\"DOCUMENTO\" \"DOCUMENTO\",\"A1\".\"NUMRESERVAS\" \"NUMRESERVAS\",\"A1\".\"ALOJAMIENTOSCAROS\" \"ALOJAMIENTOSCAROS\""
				+ " FROM  (SELECT \"A5\".\"DOCUMENTO\" \"DOCUMENTO\",COUNT(*) \"NUMRESERVAS\",SUM(CASE  WHEN \"A2\".\"PRECIOESTADIA\">150 THEN 1 ELSE 0 END ) \"ALOJAMIENTOSCAROS\" "
				+ "FROM \"%1$s\".\"CLIENTES\" \"A5\",\"%1$s\".\"RESERVAS\" \"A4\",\"%1$s\".\"RESERVASCLIENTE\" \"A3\",\"%1$s\".\"OFERTAS\" \"A2\" WHERE \"A4\".\"ID\"=\"A3\".\"RESERVAID\""
				+ " AND \"A3\".\"CLIENTEID\"=\"A5\".\"DOCUMENTO\" AND \"A4\".\"OFERTA\"=\"A2\".\"ID\""
				+ " GROUP BY \"A5\".\"DOCUMENTO\") \"A1\" "
				+ "WHERE \"A1\".\"NUMRESERVAS\"=\"A1\".\"ALOJAMIENTOSCAROS\"",
				AlohAndesTransactionManager.USUARIO);
		
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		rs = prepStmt.executeQuery();
		
		stopTime = System.currentTimeMillis();
	    elapsedTime = stopTime - startTime;
	    time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");
	    
	    while (rs.next()) {
			clientes.add(convertResultSetToClienteBueno(rs));
		}
	    
	    sql = String.format(
				"SELECT \"A1\".\"DOCUMENTO\" \"DOCUMENTO\",\"A1\".\"NUMRESERVAS\" \"NUMRESERVAS\",\"A1\".\"SUITES\" \"SUITES\""
				+ " FROM  (SELECT \"A6\".\"DOCUMENTO\" \"DOCUMENTO\",COUNT(*) \"NUMRESERVAS\",SUM(CASE  WHEN \"A2\".\"TIPO\"='Suite' THEN 1 ELSE 0 END ) \"SUITES\""
				+ " FROM \"%1$s\".\"CLIENTES\" \"A6\",\"%1$s\".\"RESERVAS\" \"A5\",\"%1$s\".\"RESERVASCLIENTE\" \"A4\",\"%1$s\".\"OFERTAS\" \"A3\",\"%1$s\".\"HABITACIONESHOTEL\" \"A2\""
				+ " WHERE \"A5\".\"ID\"=\"A4\".\"RESERVAID\" AND \"A4\".\"CLIENTEID\"=\"A6\".\"DOCUMENTO\" AND \"A5\".\"OFERTA\"=\"A3\".\"ID\" AND \"A3\".\"ALOJAMIENTOID\"=\"A2\".\"ID\" "
				+ "GROUP BY \"A6\".\"DOCUMENTO\") \"A1\""
				+ " WHERE \"A1\".\"SUITES\"=\"A1\".\"NUMRESERVAS\"",
				AlohAndesTransactionManager.USUARIO);
		
		prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		rs = prepStmt.executeQuery();
		
		stopTime = System.currentTimeMillis();
	    elapsedTime = stopTime - startTime;
	    time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");
	    
	    while (rs.next()) {
			clientes.add(convertResultSetToClienteBueno(rs));
		}
		
		return clientes;
	}
	
	//RFC10
	
	public ArrayList<Consumo> consultaConsumo(ConsultaConsumo consulta) throws SQLException, Exception  {
		ArrayList<Consumo> consumos = new ArrayList<Consumo>();
		long startTime = System.currentTimeMillis();
		String agrupa = consulta.getCriterioAgrupamiento();
		
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");

		String sql = String.format(
				"SELECT \"A5\".\"DOCUMENTO\" \"DOCUMENTO\",\"A5\".\"VINCULO\" \"VINCULO\",\"A5\".\"NOMBRE\" \"NOMBRE\",\"A1\".\"TIPO\" \"TIPOALOJAMIENTO\",\"A3\".\"FECHALLEGADA\" \"FECHALLEGADA\",\"A3\".\"CANTIDADDIAS\" \"CANTIDADDIAS\""
						+ " FROM \"%1$s\".\"CLIENTES\" \"A5\",\"%1$s\".\"OFERTAS\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\",\"%1$s\".\"RESERVASCLIENTE\" \"A2\",\"%1$s\".\"ALOJAMIENTOS\" \"A1\""
						+ " WHERE \"A5\".\"DOCUMENTO\"=\"A2\".\"CLIENTEID\" AND \"A2\".\"RESERVAID\"=\"A3\".\"ID\" AND \"A4\".\"ID\"=\"A3\".\"OFERTA\" AND \"A4\".\"ALOJAMIENTOID\"=\"A1\".\"ID\""
						+ " AND \"A4\".\"ID\"= %2$d AND \"A3\".\"FECHALLEGADA\">=TO_DATE('%3$s','DD/MM/YYYY') "
						+ "AND \"A3\".\"FECHALLEGADA\"<=TO_DATE('%4$s','DD/MM/YYYY')",
				AlohAndesTransactionManager.USUARIO, consulta.getIdOferta(),
				formatter.format(consulta.getFechaInicio()), formatter.format(consulta.getFechaFinal()));
		
		if(agrupa!=null && !agrupa.isEmpty()) {
			sql += String.format(" order by %1$s ",agrupa);
		}
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

		while (rs.next()) {
			consumos.add(convertResultSetToConsumo(rs));
		}
	
		return consumos;
	}
	
	
	//RFC11
	
		public ArrayList<Consumo> consultaNoConsumo(ConsultaConsumo consulta) throws SQLException, Exception  {
			ArrayList<Consumo> consumos = new ArrayList<Consumo>();
			long startTime = System.currentTimeMillis();
			String agrupa = consulta.getCriterioAgrupamiento();
			
			Format formatter = new SimpleDateFormat("dd/MM/yyyy");

			String sql = String.format(
					"SELECT \"A5\".\"DOCUMENTO\" \"DOCUMENTO\",\"A5\".\"VINCULO\" \"VINCULO\",\"A5\".\"NOMBRE\" \"NOMBRE\",\"A1\".\"TIPO\" \"TIPOALOJAMIENTO\",\"A3\".\"FECHALLEGADA\" \"FECHALLEGADA\",\"A3\".\"CANTIDADDIAS\" \"CANTIDADDIAS\",\"A4\".\"ID\" \"ID\""
					+ " FROM \"%1$s\".\"CLIENTES\" \"A5\",\"%1$s\".\"OFERTAS\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\",\"%1$s\".\"RESERVASCLIENTE\" \"A2\",\"%1$s\".\"ALOJAMIENTOS\" \"A1\" "
					+ "WHERE \"A5\".\"DOCUMENTO\"=\"A2\".\"CLIENTEID\" AND \"A2\".\"RESERVAID\"=\"A3\".\"ID\" AND \"A4\".\"ID\"=\"A3\".\"OFERTA\" AND \"A4\".\"ALOJAMIENTOID\"=\"A1\".\"ID\" "
					+ "AND (\"A4\".\"ID\"<> %2$d OR \"A3\".\"FECHALLEGADA\"<TO_DATE('%3$s','DD/MM/YYYY') OR \"A3\".\"FECHALLEGADA\">TO_DATE('%4$s','DD/MM/YYYY'))",
					AlohAndesTransactionManager.USUARIO, consulta.getIdOferta(),
					formatter.format(consulta.getFechaInicio()), formatter.format(consulta.getFechaFinal()));
			
			if(agrupa!=null && !agrupa.isEmpty()) {
				sql += String.format(" ORDER BY %1$s ",agrupa);
			}
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    double time = ((double)elapsedTime/1000);
		    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

			while (rs.next()) {
				consumos.add(convertResultSetToConsumo(rs));
			}
		
			return consumos;
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
	
	
	public ClienteBueno convertResultSetToClienteBueno(ResultSet resultSet) throws SQLException,Exception {
		Long documento= resultSet.getLong("DOCUMENTO");
		Integer numReservas = resultSet.getInt("NUMRESERVAS");
		String ano = resultSet.getString("AÑO");
		Integer alojamientos = resultSet.getInt("ALOJAMIETOSCAROS");
		Integer suites = resultSet.getInt("SUITES");

		Cliente cliente = findClienteByDocument(documento);
		ClienteBueno clienteBueno = new ClienteBueno();
		clienteBueno.setCliente(cliente);
		clienteBueno.setNumReservas(numReservas);
		if(numReservas!=null && ano != null && !ano.isEmpty())
			clienteBueno.setReservasMensuales(numReservas/12);
		if(suites != null) clienteBueno.setReservasSuites(suites);
		if(alojamientos != null) clienteBueno.setReservasCostosas(alojamientos);

		return clienteBueno;
	}
	
	
	public Consumo convertResultSetToConsumo(ResultSet resultSet) throws SQLException {
		Long documento= resultSet.getLong("DOCUMENTO");
		String nombre= resultSet.getString("NOMBRE");
		String vinculo = resultSet.getString("VINCULO");
		String tipo = resultSet.getString("TIPOALOJAMIENTO");
		Date fecha = resultSet.getDate("FECHALLEGADA");
		Integer dias = resultSet.getInt("CANTIDADDIAS");
		Consumo consumo = new Consumo(documento,nombre,vinculo,tipo,fecha,dias);

		return consumo;
	}
}
