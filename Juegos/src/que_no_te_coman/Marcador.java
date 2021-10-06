package que_no_te_coman;

import java.awt.*;

public class Marcador extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6252813124913243838L;

	//---------------------- VARIABLES -----------------------
	
	private static int PANEL_ANCHO;
	private static int puntos;
	private static int vidas;
	
	//---------------------- CONSTRUCTOR -----------------------

	public Marcador (int ancho) {
		PANEL_ANCHO = ancho;
	}
	
	//---------------------- METODOS -----------------------
	
	public static void Quita_vidas () {
		vidas --;
	}
	
	public static void Suma_puntos () {
		puntos ++;
	}
	
	public static void Suma_vidas () {
		vidas ++;
	}
	
	public void draw (Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.PLAIN,30));
		g.drawString(String.valueOf(puntos/10)+String.valueOf(puntos%10), (PANEL_ANCHO/2)-85, 50);
		g.drawString("Puntos", (PANEL_ANCHO/2)-120, 80);
		g.drawString(String.valueOf(vidas%10), (PANEL_ANCHO/2)+55, 50);
		g.drawString("Vidas", (PANEL_ANCHO/2)+20, 80);
	}

	public static int getPuntos() {
		return puntos;
	}

	public static void setPuntos(int puntos) {
		Marcador.puntos = puntos;
	}

	public static int getVidas() {
		return vidas;
	}

	public static void setVidas(int vidas) {
		Marcador.vidas = vidas;
	}
}