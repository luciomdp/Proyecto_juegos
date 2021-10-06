package pong;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 5683897064708434779L;
	
	//---------------------- VARIABLES -----------------------
	
	private Panel panel;
	
	//---------------------- CONSTRUCTOR -----------------------
	
	public Frame () {
		panel = new Panel();
		add(panel);
		pack();
		setTitle("Ping pong");
		Toolkit MiPantalla = Toolkit.getDefaultToolkit();
		setResizable(false);
		Image imagen = MiPantalla.getImage("Src/pong/imagen.jpg");
		setLocationRelativeTo(null);
		setBackground(Color.darkGray);
		setIconImage(imagen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
