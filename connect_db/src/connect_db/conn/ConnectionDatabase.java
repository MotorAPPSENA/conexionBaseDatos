package connect_db.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import connect_db.util.Credentials;

/**
 * @author Julian Velasquez
 */
public class ConnectionDatabase {

  private static final Logger log = Logger.getLogger(ConnectionDatabase.class.getName());

  Connection conn;

  String myConnection = "jdbc:mysql://%s:%s/%s?serverTimezone=UTC";

  public Connection getConnection() {

    try {
      var connect = String.format(myConnection, Credentials.HOST.getValue(),
          Credentials.PORT.getValue(), Credentials.DATABASE.getValue());

      conn = DriverManager.getConnection(connect, Credentials.USERNAME.getValue(),
          Credentials.PASSWORD.getValue());

      log.info(".:: Connection to database is successfully ::.");

      return conn;

    } catch (SQLException e) {

      log.info(e.getMessage());
    }
    return null;
  }
}
