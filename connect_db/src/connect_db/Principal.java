package connect_db;

import java.util.logging.Logger;
import connect_db.conn.ConnectionDatabase;

/**
 * @author Julian Velasquez
 */
public class Principal {

  private static final Logger log = Logger.getLogger(Principal.class.getName());

  public static void main(String[] args) {

    log.info(".:: Start the test connection ::.");
    
    ConnectionDatabase conn = new ConnectionDatabase();
    
    conn.getConnection();
  }
}
