package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Insert {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			
			String sql = "INSERT INTO tb_cidade(nome, estado) VALUES (?, ?)";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, "Magé");
			ps.setInt(2, 2);
			
			int linhasAfetadas = ps.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				
				while(rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Feito! Id: " + id);
				}
			}
			else {
				System.out.println("Nenhuma linha foi afetada.");
			}
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