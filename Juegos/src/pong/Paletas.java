package pong;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paletas extends Rectangle{
	
	//---------------------- VARIABLES -----------------------
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9003310067606739915L;
	private int Velocidad = 15;
	private int acY = 0; //aceleracion en y
	private int id;
	
	//---------------------- CONSTRUCTOR -----------------------
	
	public void setId(int id) {
		this.id = id;
	}

	public Paletas(int posicion_inicial_x,int posicion_inicial_y,int ancho,int alto, int id) {
		super(posicion_inicial_x,posicion_inicial_y,ancho,alto);
		this.id = id;
	}
	
	//---------------------- METODOS -----------------------
	
	public void setAcel (int acel ) {
		if (acY < 0) {
			acel = -acel;
		}
		acY = acel;
	}

	public void Mueve_paletas() {
		
		y += acY;
		
	}
	public void keyPressed(KeyEvent e) {
		
		if(id == 1) {
			if(e.getKeyCode() == KeyEvent.VK_W) 
					acY = -Velocidad;
			else if (e.getKeyCode() == KeyEvent.VK_S)
					acY = Velocidad;
		}else {
			if(e.getKeyCode() == KeyEvent.VK_UP) 
				acY = -Velocidad;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				acY = Velocidad;
		}
	}	
	public void keyReleased(KeyEvent e) {
		acY = 0;
	}
	public void draw (Graphics g) {
		if(id == 1) {
			g.setColor(new Color(247, 166, 166));
		}else if(id == 2){
			g.setColor(new Color(88, 104, 251));
		}else {
			g.setColor(new Color(40, 249, 232));
		}
		g.fillRect(x, y, width, height); //usa los parametros del Rectangle, iniciados en el constructor
	}
}
