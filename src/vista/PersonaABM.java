package vista;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import DAO.EstudioDAO;
import DAO.PersonaDAO;
import modelo.Persona;


public class PersonaABM extends JPanel {

	private JButton botonGuardar;
	private Persona personaNueva;
	private JTextField nombre;
	private JTextField nacimiento;
	private JCheckBox hijoas;
	private JComboBox<String> estudios;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersonaABM(){
		setBackground(new Color(255, 128, 0));
		setLayout(null);
		
		
			botonGuardar = new JButton("Guardar\r\n");
			botonGuardar.setForeground(new Color(0, 0, 0));
			botonGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (personaNueva == null) {

						if (!nombre.getText().isEmpty()) {
							PersonaDAO pDAO = new PersonaDAO();
							Persona crear = new Persona();
							System.out.println("CREAR");
							crear.setNombre(nombre.getText().toString());
							crear.setNacimiento(LocalDate.parse(nacimiento.getText()));
							crear.setHijoas(hijoas.isSelected());
							crear.setNivelDeEstudios(estudios.getSelectedItem().toString());
							pDAO.conectar();
							pDAO.guardar(crear);
						}
					} else {
						String nombreN = personaNueva.getNombre();
						personaNueva.setNombre(nombre.getText());
						personaNueva.setNacimiento(LocalDate.parse(nacimiento.getText()));
						personaNueva.setHijoas(hijoas.isSelected());
						personaNueva.setNivelDeEstudios(estudios.getSelectedItem().toString());
						
						PersonaDAO modificar = new PersonaDAO();

						modificar.actualizar(personaNueva, nombreN);
					}
					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					marco.setContentPane(new Menu());
					marco.validate();

				}
			});
			botonGuardar.setBounds(61, 300, 89, 23);
			add(botonGuardar);
			
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(10, 11, 141, 14);
			add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Nacimiento:");
			lblNewLabel_2.setBounds(10, 39, 141, 14);
			add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Estudios:");
			lblNewLabel_3.setBounds(10, 100, 141, 14);
			add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Hijos?");
			lblNewLabel_4.setBounds(10, 250, 141, 14);
			add(lblNewLabel_4);

			nombre = new JTextField();
			nombre.setBounds(115, 8, 86, 20);
			add(nombre);
			nombre.setColumns(10);

			nacimiento = new JTextField();
			nacimiento.setBounds(115, 36, 86, 20);
			add(nacimiento);
			nacimiento.setColumns(10);
			
			hijoas = new JCheckBox("hijoas");
			hijoas.setFont(new Font("Arial", Font.PLAIN, 11));
			hijoas.setBounds(115, 250, 86, 20);
			add(hijoas);
			
			estudios = new JComboBox<>();
			estudios.setBounds(85, 100, 150, 20);
			add(estudios);
		    EstudioDAO estudioDAO = new EstudioDAO();
		    ArrayList<String> nombresEstudios = estudioDAO.traerTodo();
		    for (String nombreEstudio : nombresEstudios) {
		        estudios.addItem(nombreEstudio);
		    }
			
		
		}
	
		public PersonaABM(Persona p) {
			this();
			personaNueva = p;
			nombre.setText(p.getNombre());
			nacimiento.setText(String.valueOf(p.getNacimiento()));
			this.estudios.setSelectedItem(p.getNivelDeEstudios());
			hijoas.setSelected(p.getHijoas());
			
		}
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
}




