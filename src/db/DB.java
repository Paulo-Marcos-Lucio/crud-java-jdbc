package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
			Properties props = loadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
			System.out.println("Conexão com o Banco de Dados iniciada.");
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
				System.out.println("Conexão com o Banco de Dados encerrada.");
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		try(FileInputStream fis = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fis);
			return props;
		}
		catch(IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	public static void closeStatement(Statement st) {
		try {
			if(st != null) {
				st.close();
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closePreparedStatement(PreparedStatement ps) {
		try {
			if(ps != null) {
				ps.close();
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
