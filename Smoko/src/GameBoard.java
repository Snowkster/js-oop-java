import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable, KeyListener{
	  
	  private static final long serialVersionUID = 1L;
	  public static final int WIDTH = 500, HEIGHT = 500;
	  
	  private Thread thread;
	  private boolean running;
	  
	  private boolean right = true, left = false, 
			  up = false, down = false;
	  
	  private Smoko parts;
	  private ArrayList<Smoko> smoko;
	  
	  private Food dinq;
	  private ArrayList<Food> food;
	  
	  private Random r;
	  
	  private int xCor = 10, yCor = 10, size = 5;
	  private int ticks = 0;
	  
	  public GameBoard() {
		  setFocusable(true);
		  
		  setPreferredSize(new Dimension(WIDTH, HEIGHT));
		  addKeyListener(this);
		  
		  smoko = new ArrayList<Smoko>();
		  food = new ArrayList<Food>();
		  
		  r = new Random();
		  
		  start();
	  }
	  
	  public void start() {
		  running = true;
		  thread = new Thread(this);
		  thread.start();
	  }
	  
	  public void stop() {
		  running = false;
		  try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public void tick() {
		  if (smoko.size() == 0) {
			parts = new Smoko(xCor, yCor, 10);
			smoko.add(parts);
		}
		  ticks++;
		  if (ticks >250000) {
			  if (right) xCor++;
			  if (left) xCor--;
			  if (up) yCor--;
			  if (down) yCor++;
			  
			  ticks = 0;
			  parts = new Smoko(xCor, yCor, 10);
			  
			  smoko.add(parts);
			  
			  if (smoko.size() > size) {
				smoko.remove(0);
			}
		}
		  
		  if (food.size() == 0) {
			int xCorF = r.nextInt(49);
			int yCorF = r.nextInt(49);
			
			dinq = new Food(xCorF, yCorF, 10);
			food.add(dinq);
		}
		  
		  for (int i = 0; i < food.size(); i++) {
				if (xCor == food.get(i).getxCor() && yCor == food.get(i).getyCor()) {
					size++;
					food.remove(i);
					i++;
				}
			}
		  
	  }
	  
	  public void paint(Graphics g) {
		  
		  g.clearRect(0, 0, WIDTH, HEIGHT);
		  
		  g.setColor(Color.BLACK);
		  g.fillRect(0, 0, WIDTH, HEIGHT);
		  
		  for (int i = 0; i < HEIGHT/10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		  for (int i = 0; i < HEIGHT/10; i++) {
				g.drawLine(0, i * 10, HEIGHT, i * 10);
			}
		  for (int i = 0; i < smoko.size(); i++) {
			smoko.get(i).draw(g);
		}
		  
		  for (int i = 0; i < food.size(); i++) {
			  food.get(i).draw(g);
		}
	  }

	@Override
	public void run() {
		while (running) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		
		if (key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		
		if (key == KeyEvent.VK_UP && !down) {
			up = true;
			right = false;
			left = false;
		}
		
		if (key == KeyEvent.VK_DOWN && !up) {
			down = true;
			right = false;
			left = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	  
	  
}
