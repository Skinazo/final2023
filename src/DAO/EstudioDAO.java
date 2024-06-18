package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class EstudioDAO {

	private Connection conn = null;
	
		public void conectar() {
		
			String url="jdbc:mysql://localhost:3306/recuperacion", usuario = "root", contra = "admin";
		
			try {
				conn = DriverManager.getConnection(url, usuario, contra);
			
				if(conn == null) {
					System.out.println("No conecta");
				}
			
				System.out.println("conecta");
			} 	catch(Exception e) {
				e.printStackTrace();
			}
		
	}
		
	public int traerIdEstudio(String a) {
		conectar();
		
		String select = "SELECT `idestudio` FROM `estudios` WHERE nombre = ?;";
		int idest = 0;
		
		try {
			PreparedStatement pstm = conn.prepareStatement(select);
			pstm.setString(1, a);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				idest = rs.getInt("idestudio");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return idest;
	}
	
	public String traerNombreEstudio(int id) {
		conectar();
		String nombre = "";
		
		String select = "SELECT `nombre` FROM `estudios` WHERE idestudio = ?;";
		
		try {
			PreparedStatement pstm = conn.prepareStatement(select);
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				nombre = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return nombre;
	}
	
	public ArrayList<String> traerTodo() {
	    conectar();
	    ArrayList<String> nombresEstudios = new ArrayList<>();

	    String select = "SELECT `nombre` FROM `estudios`;";
	    Statement stmn = null;

	    try {
	        stmn = conn.createStatement();

	        ResultSet rs = stmn.executeQuery(select);
	        while (rs.next()) {
	            String nombreEstudio = rs.getString("nombre");
	            nombresEstudios.add(nombreEstudio);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stmn != null) {
	            try {
	                stmn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return nombresEstudios;
	}
	
}