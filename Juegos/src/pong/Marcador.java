package pong;

import java.awt.*;

public class Marcador extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6252813124913243838L;

	//---------------------- VARIABLES -----------------------
	
	private static int PANEL_ANCHO;
	private static int PANEL_ALTO;
	static int jugador1;
	static int jugador2;
	
	//---------------------- CONSTRUCTOR -----------------------
	
	public Marcador (int ancho,int alto) {
		PANEL_ANCHO = ancho;
		PANEL_ALTO = alto;
	}
	
	//---------------------- METODOS -----------------------
	public static void reinicia_marcador () {
		jugador1 = 0;
		jugador2 = 0;
	}
	
	public static void Actualiza_marcador(int jug) { // jug es jugador, que puede ser 1 o 2
		if (jug == 1) {
			jugador1++;
		}else {
			jugador2++;
		}
	}
	
	public void draw (Graphics g) {
		((Graphics2D) g).addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		g.drawString(String.valueOf(jugador1/10)+String.valueOf(jugador1%10), (PANEL_ANCHO/2)-85, 50);
		g.drawString(String.valueOf(jugador2/10)+String.valueOf(jugador2%10), (PANEL_ANCHO/2)+20, 50);
		g.drawLine(PANEL_ANCHO/2, 0, PANEL_ANCHO/2, PANEL_ALTO);
		g.drawOval((PANEL_ANCHO/2)-100, (PANEL_ALTO/2)-75, 200, 150);
	}

}
