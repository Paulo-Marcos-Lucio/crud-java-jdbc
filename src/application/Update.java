package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Update {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			
			String sql = "UPDATE tb_cidade SET nome = ?, estado = ? WHERE id = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, "Praia Grande");
			ps.setInt(2, 2);
			ps.setInt(3, 6);
			
			int linhasAfetadas = ps.executeUpdate();
			
			System.out.println("Feito! Linhas afetadas: " + linhasAfetadas);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closePreparedStatement(ps);
			DB.closeConnection();
		}
	}

}
