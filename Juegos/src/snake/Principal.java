package snake;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Principal extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2552613928628008261L;
	//------------------------------- DECLARACION DE VARIABLES DE CLASE --------------------------
	
	private int Velocidad = 30;
	private int Direccion = KeyEvent.VK_RIGHT;
	private Panel panel_snake;
	private int Alto_pantalla = 480;
	private int Ancho_pantalla = 640;
	private final int Ancho_puntos = 10;
	private final int Alto_puntos = 10;
	private Point Snake;
	private ArrayList <Point> Cola;
	private Point Comida;
	private boolean Game_over = false;
	
	//------------------------------- CONSTRUCTOR --------------------------

	public Principal(){
		setTitle("Juego de la serpiente");
		Toolkit MiPantalla = Toolkit.getDefaultToolkit();
		Dimension d = MiPantalla.getScreenSize();
		Image imagen = MiPantalla.getImage("Src/snake/imagen.jpg");
		setIconImage(imagen);
		setBounds((int) d.width/2-320,(int) d.height/2-240,Ancho_pantalla,Alto_pantalla);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Evento_teclado eventos = new Evento_teclado();
		addKeyListener(eventos);
		setBackground(Color.black);
		panel_snake = new Panel();
		add(panel_snake);
		panel_snake.setBackground(Color.black);
		Start();
		Actualiza_movimiento thread = new Actualiza_movimiento();
		thread.start();
	}

	//------------------------------- MAIN --------------------------
	
	public static void main(String[] args) {
		Principal frame = new Principal();
		frame.setVisible(true);
	}
	
	//------------------------------- METODOS DE CLASE --------------------------
	
	private void Actualizar_imagen() {
		if (panel_snake.getSize().height != Alto_pantalla) {
			Alto_pantalla = panel_snake.getSize().height;
		}else if(panel_snake.getSize().width != Ancho_pantalla) {
			Ancho_pantalla = panel_snake.getSize().width;
		}
		Cola.add(0,new Point(Snake));//Todo el tiempo se añade un punto correspondiente a la ultima posicion del snake
		Cola.remove(Cola.size()-1);//Y se borra la ultima posicion del snake (para dar la sensacion de movimiento)
		for(int i = 1; i < Cola.size();i++) {
			if (Snake.equals(Cola.get(i))) {
				Game_over = true;
			}
		}
		if ((Snake.x < Comida.x + 10) && (Snake.y < Comida.y + 10) && (Snake.x > Comida.x - 10) && (Snake.y > Comida.y - 10)) {
			Cola.add(0,new Point(Snake));
			Crea_comida();
		}
		
		panel_snake.repaint();
	}
	
	private void Crea_comida() {
		Random rnd = new Random();
		Comida = new Point(20 + rnd.nextInt(Ancho_pantalla-Ancho_puntos-20),20 + rnd.nextInt(Alto_pantalla-Alto_puntos-20));
	}
	
	private void Start() {
		Crea_comida();
		Snake = new Point (Ancho_pantalla/2-Ancho_puntos, Alto_pantalla/2 - Alto_puntos);
		Cola = new ArrayList <Point> (30);
		Cola.add(Snake);
		
	}
	
	//------------------------------- CLASES INTERNAS --------------------------
	private class Panel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7894543569668400844L;

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(218, 247, 166 ));
			g.fillRect(Snake.x, Snake.y, Ancho_puntos, Alto_puntos);
			g.setColor(Color.GREEN);
			for(int i = 1; i < Cola.size();i++) {
				Point p = Cola.get(i);
				g.fillRect(p.x, p.y, Ancho_puntos, Alto_puntos);
			}
			
			g.setColor(Color.red);
			g.fillRect(Comida.x, Comida.y, Ancho_puntos, Alto_puntos);
			
		}
	}
	
	private class Evento_teclado extends KeyAdapter{
		
		public void keyPressed (KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				if (Direccion != KeyEvent.VK_DOWN) {
					
					Direccion = KeyEvent.VK_UP;
				}
				
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (Direccion != KeyEvent.VK_UP) {
					
					Direccion = KeyEvent.VK_DOWN;
				}
				
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (Direccion != KeyEvent.VK_LEFT) {
					
					Direccion = KeyEvent.VK_RIGHT;
				}
				
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (Direccion != KeyEvent.VK_RIGHT) {
					
					Direccion = KeyEvent.VK_LEFT;
				}
				
			}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}else if(e.getKeyCode() == KeyEvent.VK_W) {
				Velocidad -= 5; 
				if(Velocidad == 0) {
					Velocidad = 5;
				}
			}else if(e.getKeyCode() == KeyEvent.VK_S) {
				Velocidad += 5; 
			}
			
		}

	}
	
	/*private class Panel_game_over extends JPanel {
		
		private static final long serialVersionUID = -368012739172847333L;

		Panel_game_over(){
			setBackground(Color.DARK_GRAY);
			JLabel texto_derrota = new JLabel("Has perdido, para volver intentar, apreta R, para volver al menu"
					+ " principal, apreta M");
			texto_derrota.setFont(new Font("Arial", Font.BOLD, 12));
		}
	}*/
	
	private class Actualiza_movimiento extends Thread {
		public void run() {
			while(!Game_over) {
				if(Direccion == KeyEvent.VK_UP) {
					Snake.y -= 5;
					if(Snake.y < 10) {
						Snake.y = Alto_pantalla-10;
					}
					
				}else if(Direccion == KeyEvent.VK_DOWN) {
					Snake.y += 5;
					if(Snake.y > Alto_pantalla-10) {
						Snake.y = 10;
					}
					
				}else if (Direccion == KeyEvent.VK_LEFT) {
					Snake.x -= 5;
					if(Snake.x < 5) {
						Snake.x = Ancho_pantalla-5;
					}
						
				}else {
					Snake.x += 5;
					if(Snake.x > Ancho_pantalla-5) {
						Snake.x = 5;
					}
						
				}
				
				Actualizar_imagen();
				
				try {
					Thread.sleep(Velocidad);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(null, "Game over, tu record fue " + Cola.size() + " colas");
		}
	}


}
