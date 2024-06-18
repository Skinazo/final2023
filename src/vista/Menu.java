package vista;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;


import java.awt.Color;



import DAO.PersonaDAO;

import modelo.Persona;
import javax.swing.SwingConstants;



public class Menu extends JPanel {
private ArrayList<Persona> personas;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;

	public Menu() {
		setBackground(new Color(255, 128, 0));
		setLayout(null);

		JButton btnSiguiente = new JButton("Agregar");
		btnSiguiente.setBackground(new Color(255, 128, 128));
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource()); //getWindowAncestor
				marco.setContentPane(new PersonaABM());
				marco.validate();
			}
		});
		btnSiguiente.setBounds(353, 37, 89, 23);
		add(btnSiguiente);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 11, 321, 222);
		add(scrollPane);

		JTable tabla = new JTable();
		scrollPane.setViewportView(tabla);

		modelo = new DefaultTableModel();
		modelo.addColumn("Nombre");
		modelo.addColumn("Edad");
		modelo.addColumn("Nivel de estudios");
		modelo.addColumn("Hijoas");

		tabla.setModel(modelo);

		datos();

		JButton btnNuevo = new JButton("Modificar");
		btnNuevo.setVerticalAlignment(SwingConstants.TOP);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaElegida = tabla.getSelectedRow();
				Persona persona = personas.get(filaElegida);
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new PersonaABM(persona));
				marco.validate();
			}
		});
		btnNuevo.setBounds(353, 82, 89, 23);
		add(btnNuevo);
	
	
	JButton btnBaja = new JButton("Eliminar");
	btnBaja.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int filaEligida = tabla.getSelectedRow();
			String fila = (String) modelo.getValueAt(filaEligida, 0);
			PersonaDAO persona = new PersonaDAO();
			String mensaje = "";
			if (persona.eliminar(fila)) {
				mensaje = "Eliminada";
				modelo.removeRow(filaEligida);
			} else {
				mensaje = "Nada seleccionado";
			}

			JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
			JOptionPane.showMessageDialog(marco, mensaje);
		}
	});
	btnBaja.setBounds(353, 131, 89, 23);
	add(btnBaja);
	
	}

	private void datos() {
		PersonaDAO pDAO = new PersonaDAO();
		personas = pDAO.traerTodo();
		modelo.setRowCount(0);
		for (Persona per : personas) {
			Object[] fila = new Object[] { per.getNombre(), per.edad(), per.getNivelDeEstudios() ,per.getHijoas() };
			modelo.addRow(fila);
		}

	}

}
