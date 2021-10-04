package ta_te_ti;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;



public class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2536988910137550383L;
	//---------------------- VARIABLES -----------------------
	
	private final int PANEL_ANCHO = 600;
	private final int PANEL_ALTO = 500;
	private final Dimension PANEL_DIMENSION = new Dimension (PANEL_ANCHO,PANEL_ALTO);
	private Random random = new Random();
	private static boolean Turno_circulo;
	
	private JPanel Panel_botones;
	private JLabel Frase = new JLabel();
	private JButton[] Botones = new JButton[9];
	

	//---------------------- CONSTRUCTOR -----------------------
	
	public Panel() {
		setLayout(new BorderLayout());
		setPreferredSize(PANEL_DIMENSION);
		setBackground(Color.black);
		Frase.setForeground(Color.white);
		Frase.setFont(new Font("Ink Free",Font.PLAIN,70));
		Frase.setHorizontalAlignment(JLabel.CENTER);
		Frase.setVerticalAlignment(JLabel.CENTER);
		Frase.setText("Ta te ti");
		add(Frase,BorderLayout.NORTH);
		Panel_botones = new JPanel();
		Panel_botones.setLayout(new GridLayout(3,3));
		for(int i = 0; i < 9;i++) {
			Crea_botones (i);
		}
		add(Panel_botones,BorderLayout.CENTER);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_SPACE) {
					Inicia_juego();
				}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
				
			}			
		});
		setFocusable(true);
	}
	
	//---------------------- METODOS -----------------------
	
	private void Inicia_juego() {
		Frase.setForeground(Color.white);
		Botones_reinicio();
		Turno_circulo = (random.nextInt(2)<1);
		if(Turno_circulo) 
			Frase.setText("Turno del circulo");
		else
			Frase.setText("Turno de las X");
	}
	
	private void Crea_botones (int i) {
		JButton boton = new JButton();
		Botones[i] = boton;
		boton.setFocusable(false);
		boton.setFont(new Font("Arial",Font.BOLD,80));
		boton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(Turno_circulo) {
					if(boton.getText().equals("")) {
						boton.setForeground(Color.GREEN);
						boton.setText("O");
						Turno_circulo = false;
						Frase.setText("Turno de las X");
					}
				}else{
					if(boton.getText().equals("")) {
						boton.setForeground(Color.RED);
						boton.setText("X");
						Turno_circulo = true;
						Frase.setText("Turno del circulo");
					}
				}
				check();
			}
			
		});
		Panel_botones.add(boton);
	}
	
	private void Botones_reinicio() {
		for(JButton b: Botones) {
			b.setText("");
			b.setEnabled(true);
			b.setBackground(null);
		}
	}
	
	private void check() {//chequea si alguien gano. si gano circulo
		if ((Botones[2].getText().equals("O") || Botones[2].getText().equals("X")) && (Botones[2].getText().equals(Botones[1].getText())) && (Botones[1].getText().equals(Botones[0].getText()))){
				if(Botones[2].getText().equals("O"))
					oGana(0,1,2);
				else
					xGana(0,1,2);
		}else if ((Botones[5].getText().equals("O") || Botones[5].getText().equals("X")) && (Botones[5].getText().equals(Botones[4].getText())) && (Botones[4].getText().equals(Botones[3].getText()))) {
			if(Botones[5].getText().equals("O"))
				oGana(3,4,5);
			else
				xGana(3,4,5);
		}else if ((Botones[8].getText().equals("O") || Botones[8].getText().equals("X")) && (Botones[8].getText().equals(Botones[7].getText())) && (Botones[7].getText().equals(Botones[6].getText()))) {
			if(Botones[8].getText().equals("O"))
				oGana(6,7,8);
			else
				xGana(6,7,8);
		}else if ((Botones[6].getText().equals("O") || Botones[6].getText().equals("X")) && (Botones[6].getText().equals(Botones[3].getText())) && (Botones[3].getText().equals(Botones[0].getText()))) {
			if(Botones[6].getText().equals("O"))
				oGana(6,3,0);
			else
				xGana(6,3,0);
		}else if ((Botones[7].getText().equals("O") || Botones[7].getText().equals("X")) && (Botones[7].getText().equals(Botones[4].getText())) && (Botones[4].getText().equals(Botones[1].getText()))) {
			if(Botones[7].getText().equals("O"))
				oGana(7,4,1);
			else
				xGana(7,4,1);
		}else if ((Botones[8].getText().equals("O") || Botones[8].getText().equals("X")) && (Botones[8].getText().equals(Botones[5].getText())) && (Botones[5].getText().equals(Botones[2].getText()))) {
			if(Botones[8].getText().equals("O"))
				oGana(8,5,2);
			else
				xGana(8,5,2);
		}else if ((Botones[8].getText().equals("O") || Botones[8].getText().equals("X")) && (Botones[8].getText().equals(Botones[4].getText())) && (Botones[4].getText().equals(Botones[0].getText()))) {
			if(Botones[8].getText().equals("O"))
				oGana(8,4,0);
			else
				xGana(8,4,0);
		}else if ((Botones[6].getText().equals("O") || Botones[6].getText().equals("X")) && (Botones[6].getText().equals(Botones[4].getText())) && (Botones[4].getText().equals(Botones[2].getText()))) {
			if(Botones[6].getText().equals("O"))
				oGana(6,4,2);
			else
				xGana(6,4,2);
		}
	}
	
	private void oGana(int a,int b,int c) {
		for(JButton b1: Botones) {
			b1.setEnabled(false);
		}
		Botones[a].setBackground(Color.green);
		Botones[b].setBackground(Color.green);
		Botones[c].setBackground(Color.green);
		Frase.setForeground(Color.green);
		Frase.setText("Ganan las O");
		
	}
	
	private void xGana(int a,int b,int c) {
		for(JButton b2: Botones) {
			b2.setEnabled(false);
		}
		Botones[a].setBackground(Color.red);
		Botones[b].setBackground(Color.red);
		Botones[c].setBackground(Color.red);
		Frase.setForeground(Color.red);
		Frase.setText("Ganan las X");
	}	
	
}