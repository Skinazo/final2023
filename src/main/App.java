package main;


import javax.swing.JFrame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import vista.Menu;

class App {
	
	public static void main(String[] args) {
		JFrame marco = new JFrame();
		marco.setVisible(true);
		
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(10, 10, 1280, 720);
		marco.setContentPane(new Menu());
		marco.validate();
	}
	
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
