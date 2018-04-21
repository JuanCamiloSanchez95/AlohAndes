package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import vos.Operador;
import tm.AlohAndesTransactionManager;

public class DAOOperador {

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
		public DAOOperador() {
			recursos = new ArrayList<Object>();
		}
		//----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE COMUNICACION CON LA BASE DE DATOS
		//----------------------------------------------------------------------------------------------------------------------------------
		
		/**
		 * Metodo que obtiene la informacion de todos los operadores en la Base de Datos <br/>
		 * <b>Precondicion: </b> la conexion a sido inicializadoa <br/>
		 * @return	lista con la informacion de todos los operadores que se encuentran en la Base de Datos
		 * @throws SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public ArrayList<Operador> getOperadores()throws SQLException, Exception{
			ArrayList<Operador> operadores = new ArrayList<Operador>();
			String sql = String.format("SELECT * FROM %1$s.OPERADORES", AlohAndesTransactionManager.USUARIO);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();
			
			while (rs.next()) {
				operadores.add(convertResultSetToOperador(rs,false));
			}
			
			return operadores;
		}
		
		/**
		 * Metodo que obtiene la informacion del operador en la Base de Datos que tiene el identificador dado por parametro
		 * <b>Precondicion: </b> la conexion a sido inicializado
		 * @param id el identificador de la reserva
		 * @return la informacion del operador que cumple con los criterios de la sentecia SQL
		 * 			Null si no existe el operador con los criterios establecidos
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public Operador findOperadorById(int id) throws SQLException, Exception 
		{
			Operador operador = null;

			String sql = String.format("SELECT * FROM %1$s.OPERADORES WHERE ID = %2$d",AlohAndesTransactionManager.USUARIO, id); 

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.next()) {
				operador = convertResultSetToOperador(rs,false);
			}

			return operador;
		}

		
	
		/**
		 * Metodo que obtiene la informacion del diero obtenido por cada operador en la Base de Datos en el año dado por parametro
		 * <b>Precondicion: </b> la conexion a sido inicializado
		 * @param year el año de consulta
		 * @return Lista de cadenas con la informacion por operador del dinero ganado en el año
		 * @throws SQLException SQLException Genera excepcion si hay error en la conexion o en la consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public ArrayList<Operador> getDineroRecibidoOperadores(int year) throws SQLException, Exception {
			ArrayList<Operador> dinero = new ArrayList<Operador>();
			String sql = String.format("SELECT \"A2\".\"ID\" \"ID\",\"A2\".\"NOMBRE\" \"NOMBRE\",\"A2\".\"TIPO\" \"TIPO\",\"A1\".\"PRECIOESTADIA\"*\"A1\".\"DIAS\" \"SUMA\""
					+ " FROM \"%1$s\".\"OPERADORES\" \"A2\", (SELECT \"A4\".\"OPERADOR\" \"OPERADOR\",\"A4\".\"PRECIOESTADIA\" \"PRECIOESTADIA\",SUM(\"A3\".\"CANTIDADDIAS\") \"DIAS\" "
					+ "FROM \"%1$s\".\"OFERTAS\" \"A4\",\"%1$s\".\"RESERVAS\" \"A3\" "
					+ "WHERE \"A3\".\"OFERTA\"=\"A4\".\"ID\" AND EXTRACT(YEAR FROM \"A3\".\"FECHALLEGADA\")=%2$d "
					+ "GROUP BY \"A4\".\"OPERADOR\",\"A4\".\"PRECIOESTADIA\") \"A1\" "
					+ "WHERE \"A1\".\"OPERADOR\"=\"A2\".\"ID\"",AlohAndesTransactionManager.USUARIO,year);
			
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				dinero.add(convertResultSetToOperador(rs,true));
			}
			return dinero;
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
		 * Metodo que transforma el resultado obtenido de una consulta SQL (sobre la tabla BEBEDORES) en una instancia de la clase Bebedor.
		 * @param resultSet ResultSet con la informacion de un bebedor que se obtuvo de la base de datos.
		 * @return Bebedor cuyos atributos corresponden a los valores asociados a un registro particular de la tabla BEBEDORES.
		 * @throws SQLException Si existe algun problema al extraer la informacion del ResultSet.
		 */
		public Operador convertResultSetToOperador(ResultSet resultSet, boolean sum) throws SQLException {
			Integer id = resultSet.getInt("ID");
			String nombre = resultSet.getString("NOMBRE");
			String tipo = resultSet.getString("TIPO");
			Operador op = new Operador(id, nombre,tipo);
			if(sum) {
			double dinero = resultSet.getDouble("SUMA");
			op.setDineroGanado(dinero);
			}
			return op;
		}
		
		

}
