package que_no_te_coman;

import java.awt.*;
import java.awt.event.*;

public class Personaje extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3338199572872239027L;
	//---------------------- VARIABLES -----------------------
	
	private int puntos;
	private static int vidas = 3;
	private int aumY = 0; //aumento en y
	private int aumX = 0; //aumento en x
	private int velocidad; //modulo de la velocidad
			
	//---------------------- CONSTRUCTOR -----------------------

	public Personaje(int x,int y,int PERSONAJE_ANCHO,int PERSONAJE_ALTO) {
			
		super(x,y,PERSONAJE_ANCHO,PERSONAJE_ALTO);
		puntos = 0;
		velocidad = 0;
	}
		
	//---------------------- METODOS -----------------------
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);	
	}
	
	public void keyPressed (KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			aumY = -1;		
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			aumX = -1;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			aumX = 1;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			aumY = 1;
		}
	}
	
	/*public void keyReleased (KeyEvent e) {
		aumY = 0;
		aumX = 0;
	}*/
	
	public void Mueve_personaje() {	
		x += aumX * velocidad;
		y += aumY * velocidad ;
	}
	
	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getAumY() {
		return aumY;
	}

	public void setAumY(int aumY) {
		this.aumY = aumY;
	}

	public int getAumX() {
		return aumX;
	}

	public void setAumX(int aumX) {
		this.aumX = aumX;
	}
	public static void setVidas(int vidas) {
		Personaje.vidas = vidas;
	}

	public static int getVidas() {
		return vidas;
	}
}
