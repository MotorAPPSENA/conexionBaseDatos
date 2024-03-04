package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connect_db.conn.Conexion;
import modelo.Usuario;

public class DaoUsuario {
	Conexion cx;

	public DaoUsuario() {
		cx = new Conexion();
	}

	public boolean insertarUsuaruio(Usuario usuario) {
		PreparedStatement ps = null;
		try {
			ps = cx.getConnection().prepareStatement("INSERT INTO usuario VALUES(null,?,?,?)");
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

	public ArrayList<Usuario> consultaUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = cx.getConnection().prepareStatement("SELECT * FROM usuario");
			rs = ps.executeQuery();
			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getInt("id"));
				user.setUsuario(rs.getString("usuario"));
				user.setConstraseña(rs.getString("contraseña"));
				user.setNombre(rs.getString("nombre"));
				lista.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean eliminarUsuario(int id) {
		PreparedStatement ps = null;
		try {
			ps = cx.getConnection().prepareStatement("DELETE FROM usuario WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			cx.desconectar();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editarUsuaruio(Usuario usuario) {
		PreparedStatement ps = null;
		try {
			ps = cx.getConnection().prepareStatement("UPDATE usuario SET usuario=?,contraseña=?,nombre=? WHERE id=?");
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getConstraseña());
			ps.setString(3, usuario.getNombre());
			ps.setInt(4, usuario.getId());
			ps.executeUpdate();
			cx.desconectar();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}


