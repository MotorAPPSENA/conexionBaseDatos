package connect_db;

import java.util.logging.Logger;
import connect_db.conn.Conexion;

/**
 * @author Motor_APP
 */
public class Principal {

  private static final Logger log = Logger.getLogger(Principal.class.getName());

  public static void main(String[] args) {

    log.info(" Inicia el test de conexion ");
    
    Conexion conn = new Conexion();
    
    conn.getConnection();
  }
}
