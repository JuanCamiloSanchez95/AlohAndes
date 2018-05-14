package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import tm.AlohAndesTransactionManager;
import vos.Hotel;
import vos.Operador;

public class DAOHotel {

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
		 * Metodo constructor de la clase DAOHotel
		 */
		public DAOHotel() {
			recursos = new ArrayList<Object>();
		}
		// ----------------------------------------------------------------------------------------------------------------------------------
		// METODOS DE COMUNICACION CON LA BASE DE DATOS
		// ----------------------------------------------------------------------------------------------------------------------------------

		/**
		 * Metodo que agregar la informacion de un nuevo hotel en la Base de Datos a
		 * partir del parametro ingresado
		 * <b>Precondicion: </b> la conexion a sido inicializadoa 
		 * @param hotel Hotel que desea agregar a la Base de Datos
		 * @throws SQLException - SQLException Genera excepcion si hay error en la conexion o en la  consulta SQL
		 * @throws Exception Si se genera un error dentro del metodo.
		 */
		public void addHotel(Hotel hotel) throws SQLException, Exception {

			String sql = String.format("INSERT INTO %1$s.HOTELES (ID, NOMBREHOTEL, CATEGORIA, REGISTROSI, REGISTROCAMARA) VALUES (%2$s, '%3$s', '%4$s')",
					AlohAndesTransactionManager.USUARIO, hotel.getId(), hotel.getNombreHotel(), hotel.getCategoria(), hotel.getRegistroST(), hotel.getRegistroCamara());
			System.out.println(sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();

		}
}
