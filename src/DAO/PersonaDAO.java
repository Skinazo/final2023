package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Persona;


public class PersonaDAO {
	
	private Connection conn = null;
	
	public void conectar() {
		
		String url="jdbc:mysql://localhost:3306/recuperacion", usuario = "root", contra = "admin";
		
		try {
			conn = DriverManager.getConnection(url, usuario, contra);
			
			if(conn == null) {
				System.out.println("No conecta");
			}
			
			System.out.println("conecta");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Persona> traerTodo() {
		conectar();
		ArrayList<Persona> personas = new ArrayList<>();
		
		String select = "SELECT `idpersona`, `nombre`, `nacimiento`, `hijoas`, `idestudio` FROM `personas`;";
		Statement stmn = null;

		try {
			stmn = conn.createStatement();

			ResultSet rs = stmn.executeQuery(select);
			while (rs.next()) {
				Persona persona = new Persona();
				EstudioDAO estudio = new EstudioDAO();


				String nombre = rs.getString("nombre");
				LocalDate nacimiento = rs.getDate("nacimiento").toLocalDate();
				Boolean hijoas = rs.getBoolean("hijoas");
				int idestudio = rs.getInt("idestudio");

				
				
				persona.setNombre(nombre);
				persona.setNacimiento(nacimiento);
				persona.setHijoas(hijoas);
				persona.setNivelDeEstudios(estudio.traerNombreEstudio(idestudio));
				
				personas.add(persona);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return personas;
	}
	
	
	public boolean guardar(Persona pe) {
		conectar();

	    try {
	        PreparedStatement pstm = conn.prepareStatement(
	                "INSERT INTO `personas` (`nombre`, `nacimiento`, `hijoas`, `idestudio`) VALUES (?, ?, ?, ?);",
	                Statement.RETURN_GENERATED_KEYS);
	        pstm.setString(1, pe.getNombre());
	        pstm.setDate(2, java.sql.Date.valueOf(pe.getNacimiento()));
	        pstm.setBoolean(3, pe.getHijoas());
	        EstudioDAO estdao = new EstudioDAO();
	        pstm.setInt(4, estdao.traerIdEstudio(pe.getNivelDeEstudios()));

	        int affectedRows = pstm.executeUpdate();

	        if (affectedRows > 0) {
	            ResultSet generatedKeys = pstm.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                long id = generatedKeys.getLong(1);
	                pe.setIdpersona((int) id);  
	            }

	            System.out.println("Persona guardada: " + pe);
	            return true;
	        }

	        return false;

	    } catch (SQLException f) {
	        f.printStackTrace();
	        return false;
	    }
	}
	
	public boolean actualizar(Persona pe, String nombre) {
	    conectar();
   
	    try {
	        PreparedStatement pstm = conn.prepareStatement(
	                "UPDATE `personas` SET `nombre`=?, `nacimiento`=?, `hijoas`=?, `idestudio`=? WHERE `nombre`=?;");
	        pstm.setString(1, pe.getNombre());
	        pstm.setDate(2, java.sql.Date.valueOf(pe.getNacimiento()));
	        pstm.setBoolean(3, pe.getHijoas());
	        EstudioDAO estdao = new EstudioDAO();
	        pstm.setInt(4, estdao.traerIdEstudio(pe.getNivelDeEstudios()));
	        pstm.setString(5, nombre);

	        int affectedRows = pstm.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Persona actualizada: " + pe);
	            return true;
	        }

	        return false;

	    } catch (SQLException f) {
	        f.printStackTrace();
	        return false;
	    }
	}
	
	public boolean eliminar(String fila) {
	    conectar();

	    try {
	        PreparedStatement pstm = conn.prepareStatement("DELETE FROM `personas` WHERE `nombre`=?;");
	        pstm.setString(1, fila);

	        int affectedRows = pstm.executeUpdate();

	        if (affectedRows > 0) {
	            System.out.println("Persona eliminada con ID: " + fila);
	            return true;
	        }

	        return false;

	    } catch (SQLException f) {
	        f.printStackTrace();
	        return false;
	    }
	}

}
