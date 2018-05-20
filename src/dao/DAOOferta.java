package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import tm.AlohAndesTransactionManager;
import vos.IndiceOcupacion;
import vos.Oferta;
import vos.OfertaBajaDemanda;
import vos.OfertaPopular;
import vos.OfertaRFC12;
import vos.Operador;
import vos.OperadorRFC12;

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
	 * Metodo que agregar la informacion de una nueva oferta en la Base de Datos a
	 * partir del parametro ingresado<br/>
	 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
	 * 
	 * @param oferta- oferta que desea agregar a la Base de Datos
	 * @throws SQLException
	 *             SQLException Genera excepcion si hay error en la conexion o en la
	 *             consulta SQL
	 * @throws Exception
	 *             Si se genera un error dentro del metodo.
	 */
	public void addOferta(Oferta oferta) throws SQLException, Exception {

		String sql = String.format("INSERT INTO %1$s.OFERTAS (ID, PRECIOESTADIA, NOMBRE, FECHAPUBLICACION,DESCRIPCION,ALOJAMIENDOID,OPERADOR,CAPACIDAD) VALUES (%2$s, '%3$s', '%4$s','%5$s','%6$s','%7$s','%8$s')",
				AlohAndesTransactionManager.USUARIO, oferta.getId(), oferta.getPrecioEstadia(), oferta.getNombre(), oferta.getFechaPublicacion(),oferta.getDescripcion(),oferta.getAlojamiento(),oferta.getOperador(),oferta.getCapacidad());

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	
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
	public Oferta findOfertaById(Long id) throws SQLException, Exception 
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
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	//RFC3
	
	/**
	 * Metodo que obtiene la informacion de los indices de ocupacion de las ofertas.
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @return	lista con la informacion de las  ofertas con su ocupacion actual que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<IndiceOcupacion> getIndicesOcupacion() throws SQLException, Exception {
		ArrayList<IndiceOcupacion> indices = new ArrayList<IndiceOcupacion>();
		long startTime = System.currentTimeMillis();
		String sql = String.format("SELECT \"A2\".\"ID\" \"ID\",\"A2\".\"NOMBRE\" \"NOMBRE\",\"A2\".\"CAPACIDAD\" \"CAPACIDAD\",SUM(CASE  WHEN \"A2\".\"ID\"=\"A1\".\"OFERTA\" THEN 1 ELSE 0 END ) \"NUMRESERVAS\" "
				+ "FROM \"%1$s\".\"OFERTAS\" \"A2\",\"%1$s\".\"RESERVAS\" \"A1\" "
				+ "GROUP BY \"A2\".\"ID\",\"A2\".\"NOMBRE\",\"A2\".\"CAPACIDAD\" "
				+ "ORDER BY \"A2\".\"ID\"", AlohAndesTransactionManager.USUARIO);
				
				PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

		while (rs.next()) {
			indices.add(convertResultSetToIndiceOcupacion(rs));
		}

		return indices;
	}
	
	//RFC2
	
	/**
	 * Metodo que obtiene la informacion de las 20 ofertas mas populares en la Base de Datos 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @return	lista con la informacion de las 20 ofertas mas populares que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<OfertaPopular> getOfertasMasPopulares() throws SQLException, Exception {
		ArrayList<OfertaPopular> ofertas = new ArrayList<OfertaPopular>();
		long startTime = System.currentTimeMillis();
		String sql = String.format("SELECT \"A1\".\"ID\" \"ID\",\"A1\".\"NOMBRE\" \"NOMBRE\",\"A1\".\"NUMRESERVAS\" \"NUMRESERVAS\" "
				+ "FROM  (SELECT \"A3\".\"ID\" \"ID\",\"A3\".\"NOMBRE\" \"NOMBRE\",SUM(CASE  WHEN \"A3\".\"ID\"=\"A2\".\"OFERTA\" THEN 1 ELSE 0 END ) \"NUMRESERVAS\""
				+ " FROM \"%1$s\".\"OFERTAS\" \"A3\",\"%1$s\".\"RESERVAS\" \"A2\" "
				+ "WHERE \"A2\".\"OFERTA\"=\"A3\".\"ID\" GROUP BY \"A3\".\"ID\",\"A3\".\"NOMBRE\" "
				+ "ORDER BY COUNT(\"A2\".\"OFERTA\") DESC) \"A1\""
				+ " WHERE ROWNUM<=20",AlohAndesTransactionManager.USUARIO);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

		while (rs.next()) {
			ofertas.add(convertResultSetToOfertaPopular(rs));
		}

		return ofertas;
	}
	
	//RFC9
	
	/**
	 * Metodo que obtiene la informacion de las ofertas con menor demanda en la Base de Datos 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @return	lista con la informacion de las ofertas con menor demanda que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public ArrayList<OfertaBajaDemanda> getOfertasConBajaDemanda() throws SQLException, Exception {
		ArrayList<OfertaBajaDemanda> ofertas = new ArrayList<OfertaBajaDemanda>();
		long startTime = System.currentTimeMillis();
		String sql = String.format("SELECT \"A3\".\"OFERTA\" \"ID\",\"A1\".\"NOMBRE\" \"NOMBRE\",SUM(CASE  WHEN \"A1\".\"ID\"=\"A3\".\"OFERTA\" THEN 1 ELSE 0 END )+1 \"NUMRESERVAS\",MAX(\"A3\".\"FECHALLEGADA\"-(\"A2\".\"FECHALLEGADA\"+\"A2\".\"CANTIDADDIAS\")) \"DISTANCIA\""
				+ " FROM  (SELECT ROWNUM \"ROWN\",\"A4\".\"FECHALLEGADA\" \"FECHALLEGADA\",\"A4\".\"CANTIDADDIAS\" \"CANTIDADDIAS\",\"A4\".\"OFERTA\" \"OFERTA\" "
				+ "FROM  (SELECT \"A7\".\"FECHALLEGADA\" \"FECHALLEGADA\",\"A7\".\"CANTIDADDIAS\" \"CANTIDADDIAS\",\"A7\".\"OFERTA\" \"OFERTA\" "
				+ "FROM \"%1$s\".\"RESERVAS\" \"A7\" "
				+ "ORDER BY \"A7\".\"OFERTA\",\"A7\".\"FECHALLEGADA\") \"A4\") \"A3\","
				+ " (SELECT ROWNUM \"ROWN\",\"A5\".\"FECHALLEGADA\" \"FECHALLEGADA\",\"A5\".\"CANTIDADDIAS\" \"CANTIDADDIAS\",\"A5\".\"OFERTA\" \"OFERTA\""
				+ " FROM  (SELECT \"A6\".\"FECHALLEGADA\" \"FECHALLEGADA\",\"A6\".\"CANTIDADDIAS\" \"CANTIDADDIAS\",\"A6\".\"OFERTA\" \"OFERTA\""
				+ " FROM \"%1$s\".\"RESERVAS\" \"A6\""
				+ " ORDER BY \"A6\".\"OFERTA\",\"A6\".\"FECHALLEGADA\") \"A5\") \"A2\",\"%1$s\".\"OFERTAS\" \"A1\""
				+ " WHERE \"A3\".\"ROWN\"=\"A2\".\"ROWN\"+1 "
				+ "AND \"A3\".\"OFERTA\"=\"A2\".\"OFERTA\" "
				+ "AND \"A3\".\"OFERTA\"=\"A1\".\"ID\" "
				+ "GROUP BY \"A3\".\"OFERTA\",\"A1\".\"NOMBRE\""
				+ " HAVING MAX(\"A3\".\"FECHALLEGADA\"-(\"A2\".\"FECHALLEGADA\"+\"A2\".\"CANTIDADDIAS\"))>=30",AlohAndesTransactionManager.USUARIO);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    double time = ((double)elapsedTime/1000);
	    System.out.println("Tiempo de Consulta: "+String.format("%.2f", time)+" segundos");

		while (rs.next()) {
			ofertas.add(convertResultSetToOfertaBajaDemanda(rs));
		}

		return ofertas;
	}
	
	//RFC12
	
	/**
	 * Metodo que obtiene la informacion de las ofertas con menor y mayor demanda por semana en la Base de Datos 
	 * <b>Precondicion: </b> la conexion a sido inicializado
	 * @return	lista con la informacion de las ofertas con menor y mayor demanda por semana  que se encuentran en la Base de Datos
	 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
	 * @throws Exception Si se genera un error dentro del metodo.
	 */
	public OfertasRFC12 consultaFuncionamiento() throws SQLException, Exception {
		OfertaRFC12[] ofertasMas = new OfertaRFC12[53];
		OfertaRFC12[] ofertasMenos = new OfertaRFC12[53];
		long startTime = System.currentTimeMillis();
		String sql = String.format(
				"SELECT CAST(TO_CHAR(\"A2\".\"FECHALLEGADA\",'IW') AS int) \"SEMANA\",\"A1\".\"ID\" \"ID\",SUM(\"A1\".\"PRECIOESTADIA\"*\"A2\".\"CANTIDADDIAS\") \"INGRESOS\",COUNT(*) \"NUMRESERVAS\""
						+ " FROM \"%1$s\".\"RESERVAS\" \"A2\",\"%1$s\".\"OFERTAS\" \"A1\""
						+ " WHERE \"A2\".\"OFERTA\"=\"A1\".\"ID\" "
						+ "GROUP BY TO_CHAR(\"A2\".\"FECHALLEGADA\",'IW'),CAST(TO_CHAR(\"A2\".\"FECHALLEGADA\",'IW') AS int),\"A2\".\"FECHALLEGADA\",\"A1\".\"ID\" "
						+ "ORDER BY TO_CHAR(\"A2\".\"FECHALLEGADA\",'IW')",
				AlohAndesTransactionManager.USUARIO);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		double time = ((double) elapsedTime / 1000);
		System.out.println("Tiempo de Consulta: " + String.format("%.2f", time) + " segundos");

		while (rs.next()) {
			Integer semana = rs.getInt("SEMANA");
	    	OfertaRFC12 oferta = convertResultSetToOfertaRFC12(rs);
	    	if(ofertasMas[semana-1]==null)
			ofertasMas[semana-1]=oferta;
	    	else {
	    		if(oferta.getNumReservas()<ofertasMas[semana-1].getNumReservas()) {
	    			ofertasMas[semana-1]=oferta;
	    		}
	    	}
	    	
	    	if(ofertasMenos[semana-1]==null)
			ofertasMenos[semana-1]=oferta;
	    	else {
	    		if(oferta.getNumReservas()<ofertasMenos[semana-1].getNumReservas()) {
	    			ofertasMenos[semana-1]=oferta;
	    		}
	    	}
		}
	    
		return new OfertasRFC12(ofertasMas,ofertasMenos);
		
	}
	
	/**
	 * Clase de Apoyo al RFC12
	 * @author cmama
	 */
	public class OfertasRFC12
	{
	    private OfertaRFC12[] mas;
	    private OfertaRFC12[] menos;
	    public OfertasRFC12(OfertaRFC12[] mas, OfertaRFC12[] menos)
	    {
	        this.mas = mas;
	        this.menos = menos;

	    }
	    public OfertaRFC12[] getMas() { return mas; }
	    public OfertaRFC12[] getMenos() { return menos; }
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
			Long id= resultSet.getLong("ID");
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
	
	
	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla OFERTAS) en una instancia de la clase OfertaPopular.
	 * @param resultSet ResultSet con la informacion de una oferta popular que se obtuvo de la base de datos.
	 * @return OfertaPopular cuyos atributos corresponden a los valores asociados a un registro particular de la tabla OFERTAS.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public OfertaPopular convertResultSetToOfertaPopular(ResultSet resultSet) throws SQLException {

			Long id= resultSet.getLong("ID");
			String nombre = resultSet.getString("NOMBRE");
			Integer numReservas = resultSet.getInt("NUMRESERVAS");
			OfertaPopular oferta = new OfertaPopular(id, nombre, numReservas);

			return oferta;
	}
	
	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla OFERTAS) en una instancia de la clase OfertaPopular.
	 * @param resultSet ResultSet con la informacion de una oferta popular que se obtuvo de la base de datos.
	 * @return OfertaPopular cuyos atributos corresponden a los valores asociados a un registro particular de la tabla OFERTAS.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public OfertaBajaDemanda convertResultSetToOfertaBajaDemanda(ResultSet resultSet) throws SQLException {

			Long id= resultSet.getLong("ID");
			String nombre = resultSet.getString("NOMBRE");
			Integer numReservas = resultSet.getInt("NUMRESERVAS");
			Integer distanciaReservas = resultSet.getInt("DISTANCIA");
			OfertaBajaDemanda oferta = new OfertaBajaDemanda (id, nombre, numReservas, distanciaReservas);

			return oferta;
	}

	/**
	 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla OFERTAS) en una instancia de la clase OfertaPopular.
	 * @param resultSet ResultSet con la informacion de una oferta popular que se obtuvo de la base de datos.
	 * @return OfertaPopular cuyos atributos corresponden a los valores asociados a un registro particular de la tabla OFERTAS.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public IndiceOcupacion convertResultSetToIndiceOcupacion(ResultSet resultSet) throws SQLException {

			Long id= resultSet.getLong("ID");
			String nombre = resultSet.getString("NOMBRE");
			Integer capacidad = resultSet.getInt("CAPACIDAD");
			Integer numReservas = resultSet.getInt("NUMRESERVAS");
			IndiceOcupacion indice = new IndiceOcupacion(id, nombre,capacidad, numReservas);

			return indice;
	}
	
	/**
	 *  Metodo que transforma el resultado obtenido de una consulta SQL en una instancia de la clase OfertaRFC12.
	 * @param resultSet ResultSet con la informacion de una oferta que se obtuvo de la base de datos.
	 * @return OfertaRFC12 cuyos atributos corresponden a los valores asociados a un registro particular de la tabla OFERTAS.
	 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
	 */
	public OfertaRFC12 convertResultSetToOfertaRFC12(ResultSet resultSet) throws SQLException,Exception {
		Long id= resultSet.getLong("ID");
		Double ingresos = resultSet.getDouble("INGRESOS");
		Integer numReservas = resultSet.getInt("NUMRESERVAS");

		OfertaRFC12 oferta = new OfertaRFC12 (ingresos, numReservas);
		Oferta ofertaAsociada = findOfertaById(id);
		
		if(ofertaAsociada!=null)
			oferta.setOferta(ofertaAsociada);

		return oferta;
}

}
