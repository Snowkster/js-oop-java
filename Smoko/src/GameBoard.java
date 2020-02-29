import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GameBoard extends JPanel implements Runnable{
	  
	  private static final long serialVersionUID = 1L;
	  public static final int WIDTH = 500, HEIGHT = 500;
	  
	  private Thread thread;
	  private boolean running;
	  
	  private boolean right = true, left = false, 
			  up = false, down = false;
	  
	  private Smoko parts;
	  private ArrayList<Smoko> smoko;
	  
	  private int xCor = 10, yCor = 10, size = 5;
	  private int ticks = 0;
	  
	  public GameBoard() {
		  
		  setPreferredSize(new Dimension(WIDTH, HEIGHT));
		  
		  smoko = new ArrayList<Smoko>();
		  
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
	  }

	@Override
	public void run() {
		while (running) {
			tick();
			repaint();
		}
		
	}
	  
	  
}
