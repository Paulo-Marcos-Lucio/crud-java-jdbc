package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Delete {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement("DELETE FROM tb_cidade WHERE id = ?");

			ps.setInt(1, 2);

			int linhasAfetadas = ps.executeUpdate();

			System.out.println("Feito! Linhas afetadas: " + linhasAfetadas);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closePreparedStatement(ps);
			DB.closeConnection();
		}

	}

}
