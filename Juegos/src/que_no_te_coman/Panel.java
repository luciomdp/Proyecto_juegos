package que_no_te_coman;

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
	
	private final int PANEL_ANCHO = 1000;
	private final int PANEL_ALTO = 600;
	private final int ENEMIGOS_ANCHO = 25;
	private final int ENEMIGOS_ALTO = 25;
	private final int PERSONAJE_ANCHO = 25;
	private final int PERSONAJE_ALTO = 25;
	private final int POWERUP_ANCHO = 25;
	private final int POWERUP_ALTO = 25;
	private final int Puntos_para_ganar = 20;
	private final Dimension PANEL_DIMENSION = new Dimension (PANEL_ANCHO,PANEL_ALTO);
	private boolean Game_over = false;
	private boolean Condicion5 = true;
	private boolean Condicion10 = true;
	private boolean Condicion15 = true;
	private Marcador marcador;
	private Random rnd;
	private Personaje personaje;
	private Enemigos enemigo1,enemigo2,enemigo3;
	private PowerUP powerUP;
	
	
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
	
	private void Inicia_juego() {
		Enemigos.setVelocidad(5);
		Marcador.setVidas(3);
		Personaje.setVidas(3);
		crea_enemigos();
		crea_powerUp();
		crea_personaje();
		crea_marcador();
		personaje.setPuntos(0);
		Marcador.setPuntos(0);
	}
	
	private void Reinicia_juego () {
		Enemigos.setVelocidad(0);
		crea_personaje();
		personaje.setPuntos(0);
		Marcador.setPuntos(0);
		powerUP.setVelocidad(0);
	}
	
	private void Mueve() {
		enemigo1.Mueve_enemigo();
		enemigo2.Mueve_enemigo();
		enemigo3.Mueve_enemigo();	
		powerUP.Mueve_powerUP();
		personaje.Mueve_personaje();
	}
	
	private void Condiciones() {
		if(Condicion5 && personaje.getPuntos() == 5) {
			Enemigos.setVelocidad(Enemigos.getVelocidad() + 1);
			Personaje.setVidas(Personaje.getVidas() + 1);
			Marcador.Suma_vidas();
			
			Condicion5 = false;
		}else if(Condicion10 && personaje.getPuntos() == 10) {
			Enemigos.setVelocidad(Enemigos.getVelocidad() + 1);
			Personaje.setVidas(Personaje.getVidas() + 1);
			Marcador.Suma_vidas();
			Condicion10 = false;
		}else if (Condicion15 && personaje.getPuntos() == 15) {
			Enemigos.setVelocidad(Enemigos.getVelocidad() + 1);
			Personaje.setVidas(Personaje.getVidas() + 1);
			Marcador.Suma_vidas();
			Condicion15 = false;
		}
	}
	
	private void Hay_colision() {
		
		//Enemigos con paredes vertical
		
		if(enemigo1.y <= 0 || enemigo1.y >= PANEL_ALTO-ENEMIGOS_ALTO) {
			enemigo1.setAumY(-enemigo1.getAumY());
		}
		if(enemigo2.y <= 0 || enemigo2.y >= PANEL_ALTO-ENEMIGOS_ALTO) {
			enemigo2.setAumY(-enemigo2.getAumY());
		}
		if(enemigo3.y <= 0 || enemigo3.y >= PANEL_ALTO-ENEMIGOS_ALTO) {
			enemigo3.setAumY(-enemigo3.getAumY());
		}
		
		//Enemigos con paredes horizontal
		
		if(enemigo1.x <= 0 || enemigo1.x >= PANEL_ANCHO-ENEMIGOS_ANCHO) {
			enemigo1.setAumX(-enemigo1.getAumX());
		}
		if(enemigo2.x <= 0 || enemigo2.x >= PANEL_ANCHO-ENEMIGOS_ANCHO) {
			enemigo2.setAumX(-enemigo2.getAumX());
		}
		if(enemigo3.x <= 0 || enemigo3.x >= PANEL_ANCHO-ENEMIGOS_ANCHO) {
			enemigo3.setAumX(-enemigo3.getAumX());
		}
		
		//Enemigos con personaje (reinicia juego, pierde 1 vida)
		
		if(personaje.intersects(enemigo1) || personaje.intersects(enemigo2) || personaje.intersects(enemigo3)) {
			Personaje.setVidas(Personaje.getVidas() - 1);
			Marcador.Quita_vidas();
			if(Personaje.getVidas() == 0) {
				Game_over = true;
			}
			Reinicia_juego ();
		}
		
		//Personaje con paredes ancho
		
		if (personaje.x < 0) {
			personaje.x = 0;
		}else if(personaje.x > (PANEL_ANCHO - PERSONAJE_ANCHO)) {
			personaje.x = PANEL_ANCHO - PERSONAJE_ANCHO;
		}
		
		//Personaje con paredes alto
		
		if (personaje.y < 0) {
			personaje.y = 0;
		}else if(personaje.y > (PANEL_ALTO - PERSONAJE_ALTO)) {
			personaje.y = PANEL_ALTO - PERSONAJE_ALTO;
		}
		
		//Power up con paredes vertical
		
		if(powerUP.y <= 0 || powerUP.y >= PANEL_ALTO-POWERUP_ALTO) {
			powerUP.setAumY(-powerUP.getAumY());
		}
		
		//Power up con paredes horizontal
		
		if(powerUP.x <= 0 || powerUP.x >= PANEL_ANCHO-POWERUP_ANCHO) {
			powerUP.setAumX(-powerUP.getAumX());
		}
		
		//Power up con personaje
		
		if(powerUP.intersects(personaje)) {
			crea_powerUp();
			powerUP.setVelocidad(5);
			personaje.setPuntos(personaje.getPuntos() + 1);
			Marcador.setPuntos(Marcador.getPuntos() + 1 );
		}
		
	}
	
	private void crea_enemigos() {
		rnd = new Random();
		enemigo1 = new Enemigos(25 + rnd.nextInt(PANEL_ANCHO-50), 25 + rnd.nextInt(PANEL_ALTO-50),ENEMIGOS_ANCHO,ENEMIGOS_ALTO);
		enemigo2 = new Enemigos(25 + rnd.nextInt(PANEL_ANCHO-50), 25 + rnd.nextInt(PANEL_ALTO-50),ENEMIGOS_ANCHO,ENEMIGOS_ALTO);
		enemigo3 = new Enemigos(25 + rnd.nextInt(PANEL_ANCHO-50), 25 + rnd.nextInt(PANEL_ALTO-50),ENEMIGOS_ANCHO,ENEMIGOS_ALTO);
	}
	
	private void crea_powerUp() {
		rnd = new Random();
		powerUP = new PowerUP(25 + rnd.nextInt(PANEL_ANCHO-50), 25 + rnd.nextInt(PANEL_ALTO-50),POWERUP_ANCHO,POWERUP_ALTO);
	}
	
	private void crea_personaje() {
		personaje = new Personaje((PANEL_ANCHO/2)-(PERSONAJE_ANCHO/2),(PANEL_ALTO/2)-(PERSONAJE_ALTO/2),PERSONAJE_ANCHO,PERSONAJE_ALTO);
	}
	
	private void crea_marcador() {
		marcador = new Marcador(PANEL_ANCHO);
	}
	
	public void paint(Graphics g) {
		Image image = createImage(getWidth(),getHeight());
		Graphics graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	
	private void draw(Graphics g) {
		enemigo1.draw(g);
		enemigo2.draw(g);
		enemigo3.draw(g);
		personaje.draw(g);
		powerUP.draw(g);
		marcador.draw(g);
		Toolkit.getDefaultToolkit().sync();
	}
	//---------------------- CLASES INTERNAS -----------------------
	
	private class Escucha_teclas extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				Enemigos.setVelocidad(5);
				powerUP.setVelocidad(5);
				personaje.setVelocidad(5);
				Condicion5 = true;
				Condicion10 = true;
				Condicion15 = true;
			}else
				personaje.keyPressed(e);
		}
		/*public void keyReleased(KeyEvent e) {
				
			personaje.keyReleased(e);
		}*/
			
	}
	private class Hilo_juego extends Thread {
		public void run() {
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;//FPS maximo al que corre el juego
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			while(!Game_over && !(Puntos_para_ganar == personaje. getPuntos())) {
				long now = System.nanoTime();
				delta += (now -lastTime)/ns;
				lastTime = now;
				if(delta >=1) {
					Hay_colision();
					Mueve();
					Condiciones();
					repaint();
					delta--;
				}
			}
			if(Game_over) {
				JOptionPane.showMessageDialog(null, "Perdiste");
			}else {
				JOptionPane.showMessageDialog(null, "Felicidades, ganaste! llegaste al nivel " + Puntos_para_ganar);
			}
				
		}
			
	}
}
