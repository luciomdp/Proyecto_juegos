package que_no_te_coman;

import java.awt.*;
import java.util.Random;

public class PowerUP extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -716093819450201087L;
	//---------------------- VARIABLES -----------------------
	
	private Random random;
	private int aumY; //aumento en y
	private int aumX; //aumento en x
	private int velocidad; //modulo de la velocidad
			
	//---------------------- CONSTRUCTOR -----------------------

	public PowerUP(int x,int y,int ENEMIGOS_ANCHO,int ENEMIGOS_ALTO) {
			
		super(x,y,ENEMIGOS_ANCHO,ENEMIGOS_ALTO);
		velocidad = 0; 
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
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void draw (Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);	
	}

	public void Mueve_powerUP() {	
		x += aumX * velocidad;
		y += aumY * velocidad;
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
}
