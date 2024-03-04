package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;



import connect_db.conn.Conexion;
import modelo.Usuario;

public class DaoUsuario {
	Conexion cx;
	

	public DaoUsuario() {
		cx = new Conexion();
	}
	
	public boolean insertarUsuaruio(Usuario usuario) {
		PreparedStatement ps=null;
		try {
			ps=cx.getConnection().prepareStatement("INSERT INTO usuario VALUES(null,?,?,?)");
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getConstraseña());
			ps.setString(3, usuario.getNombre());
			ps.executeUpdate();
			cx.desconectar();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}



