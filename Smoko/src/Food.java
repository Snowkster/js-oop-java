import java.awt.Color;
import java.awt.Graphics;

public class Food {
	
	private int xCor, yCor, width, height;
	
	public Food(int xCor, int yCor, int tileSize){
		
		this.xCor = xCor;
		this.yCor = yCor;
		width = tileSize;
		height = tileSize;
}
		  
	public void tick() {
		
}
		  
public void draw(Graphics g) {
	
	g.setColor(Color.RED);
	g.fillRect(xCor * width, yCor * height, width, height);
}

public int getxCor() {
	return xCor;
}

public void setxCor(int xCor) {
	this.xCor = xCor;
}

public int getyCor() {
	return yCor;
}

public void setyCor(int yCor) {
	this.yCor = yCor;
}
		
	
}
