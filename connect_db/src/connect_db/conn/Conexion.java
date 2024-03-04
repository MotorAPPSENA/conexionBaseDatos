package connect_db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import connect_db.util.Credentials;

/**
 * @author MotorAPP
 */
public class Conexion {

	private static final Logger log = Logger.getLogger(Conexion.class.getName());

	Connection cx;

	String myConnection = "jdbc:mysql://%s:%s/%s?serverTimezone=UTC";

	public Connection getConnection() {

		try {
			var connect = String.format(myConnection, Credentials.HOST.getValue(), Credentials.PORT.getValue(),
					Credentials.DATABASE.getValue());
			cx = DriverManager.getConnection(connect, Credentials.USERNAME.getValue(), Credentials.PASSWORD.getValue());
			log.info(" ¡¡ conexión exitosa !! ");
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return cx;

	}
	
	public void desconectar(){
		try {
			cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Conexion cx=new Conexion();
		cx.getConnection();
	}
}
