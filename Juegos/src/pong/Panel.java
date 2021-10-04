package pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -611362756280132908L;
	//---------------------- VARIABLES -----------------------
	
	private static final int PANEL_ANCHO = 1000;
	private static final int PANEL_ALTO = (int) (PANEL_ANCHO*(0.555));//Le doy los tamaños de las mesas de ping pong
	private static final Dimension PANEL_DIMENSION = new Dimension(PANEL_ANCHO,PANEL_ALTO);
	private static final int PELOTA_DIAMETRO = 20;
	private static final int PALETA_ANCHO = 5;
	private static final int PALETA_ALTO = 100;
	private Paletas paleta1,paleta2;
	private Pelota pelota;
	private Marcador marcador;
	private int Puntos_para_ganar = 5 ;
	
	//---------------------- CONSTRUCTOR -----------------------
	
	public Panel() {
		setPreferredSize(PANEL_DIMENSION);
		Inicia_juego();
		addKeyListener(new Escucha_teclas());
		setFocusable(true);
		Thread hilo_inicio = new Hilo_juego();
		hilo_inicio.start();
	}
	
	//---------------------- METODOS -----------------------
	
	public void Inicia_juego() { //inicia el juego 
		Crea_paletas();
		Crea_marcador();
		Crea_pelota();
	}
	
	private void Mueve() { // Actualiza las posiciones de todo lo que se mueve
		paleta1.Mueve_paletas();
		paleta2.Mueve_paletas();
		pelota.Mueve_pelota();
	}
	private void resta_punto (int jugador) {
		if (jugador == 1) {
			if (Marcador.jugador1 != 0 ) {
				Marcador.jugador1 --;
			}
		}else {
			if (Marcador.jugador2 != 0 ) {
				Marcador.jugador2 --;
			}
		}
	}
	private void Hay_colision() {
		
		//------------------------ PALETA VS TECHO/PISO ------------------------
		
		if(paleta1.y <= 0) 
			paleta1.y = 0;
		else if (paleta1.y >= (PANEL_ALTO - PALETA_ALTO))
			paleta1.y = PANEL_ALTO - PALETA_ALTO;
		if(paleta2.y <= 0) 
			paleta2.y = 0;
		else if (paleta2.y >= (PANEL_ALTO - PALETA_ALTO)) 
			paleta2.y = PANEL_ALTO - PALETA_ALTO;
		
		//------------------------ PELOTA VS TECHO/PISO ------------------------
		
		if (pelota.y <= 0 || pelota.y >= PANEL_ALTO - PELOTA_DIAMETRO) 
			pelota.aumY = -pelota.aumY;
		
		//------------------------ PELOTA VS PALETAS ------------------------
		
		if(paleta1.intersects(pelota) || paleta2.intersects(pelota)) {		
				pelota.aumX = -pelota.aumX;
				pelota.velocidad ++;			
		}
		
		//------------------------ PUNTO ------------------------
		
		if (pelota.x < -PELOTA_DIAMETRO - 20) {
			Marcador.Actualiza_marcador(2);
			Inicia_juego();
		}else if (pelota.x > PANEL_ANCHO + 20) {
			Marcador.Actualiza_marcador(1);
			Inicia_juego();
		}
		
	}
	
	private void Crea_paletas() {
		paleta1 = new Paletas(0,(PANEL_ALTO/2)-(PALETA_ALTO/2),PALETA_ANCHO,PALETA_ALTO,1);
		paleta2 = new Paletas(PANEL_ANCHO-PALETA_ANCHO,(PANEL_ALTO/2)-(PALETA_ALTO/2),PALETA_ANCHO,PALETA_ALTO,2);	
	}
	
	private void Crea_pelota() {
		pelota = new Pelota((PANEL_ANCHO/2)-(PELOTA_DIAMETRO/2),(PANEL_ALTO/2) - (PELOTA_DIAMETRO/2),PELOTA_DIAMETRO);
	}
	
	private void Crea_marcador() {
		
		marcador= new Marcador(PANEL_ANCHO,PANEL_ALTO);

	}
	public void paint(Graphics g) {
		Image image = createImage(getWidth(),getHeight());
		Graphics graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		paleta1.draw(g);
		paleta2.draw(g);
		pelota.draw(g);
		marcador.draw(g);
		
		Toolkit.getDefaultToolkit().sync();

	}
	
	//---------------------- CLASES INTERNAS -----------------------
	
	private class Escucha_teclas extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				pelota.velocidad = 5;
			}else if(e.getKeyCode() == KeyEvent.VK_1) {
				resta_punto(1);
			}else if(e.getKeyCode() == KeyEvent.VK_2) {
				resta_punto(2);
			}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}else if(e.getKeyCode() == KeyEvent.VK_R){
				Marcador.reinicia_marcador ();
				Inicia_juego();
			}else if (e.getKeyCode() == KeyEvent.VK_8){
				paleta1.setId(3);
			}else {
			paleta1.keyPressed(e);
			paleta2.keyPressed(e);
			}
		}
		public void keyReleased(KeyEvent e) {
			paleta1.keyReleased(e);
			paleta2.keyReleased(e);
		}
		
	}
	private class Hilo_juego extends Thread {
		public void run() {
			long lastTime = System.nanoTime();
			double amountOfTicks =60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			while(!(Puntos_para_ganar == Marcador.jugador1 || Puntos_para_ganar == Marcador.jugador2)) {
				long now = System.nanoTime();
				delta += (now -lastTime)/ns;
				lastTime = now;
				if(delta >=1) {
					Hay_colision();
					Mueve();
					repaint();
					delta--;
				}
			}
			if(Puntos_para_ganar == Marcador.jugador1) {
				JOptionPane.showMessageDialog(null, "Felicidades jugador rojo, llegaste a los " + Puntos_para_ganar + " puntos");
			}else {
				JOptionPane.showMessageDialog(null, "Felicidades jugador azul, llegaste a los " + Puntos_para_ganar + " puntos");
			}
		}
		
	}
}
