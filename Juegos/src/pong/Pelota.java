package pong;

import java.awt.*;
import java.util.*;

public class Pelota extends Rectangle{
	
	//---------------------- VARIABLES -----------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = -7783621173727345062L;
	private Random random;
	int aumY; //aumento en y
	int aumX; //aumento en x
	int velocidad = 0; //modulo del aumento
	
	//---------------------- CONSTRUCTOR -----------------------
	
	public Pelota (int x,int y,int Diametro){
		
		super(x,y,Diametro,Diametro);
		random = new Random();
		int num = random.nextInt(4);
		if (num == 0) {
			aumY = 1;
			aumX = 1;
		}else if (num == 1) {
			aumY = -1;
			aumX = 1;
		}else if (num == 2) {
			aumY = 1;
			aumX = -1;
		}else {
			aumY = -1;
			aumX = -1;
		}
	}
	
	//---------------------- METODOS -----------------------
	
	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);	
	}
	public void Mueve_pelota() {
		x += aumX * velocidad;
		y += aumY * velocidad;
	}

}
