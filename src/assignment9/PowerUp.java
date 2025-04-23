package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class PowerUp {

	public static final double POWERUP_SIZE = 0.02;
	private double x, y;
	private String type;
	
	public PowerUp() {
		this.type = "speed";
		this.x = POWERUP_SIZE + Math.random() * (1.0 - (2 * POWERUP_SIZE));
		this.y = POWERUP_SIZE + Math.random() * (1.0 - (2 * POWERUP_SIZE));
	}
	
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(x, y, POWERUP_SIZE);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
    public String getType() {
        return type;
    }
	
}
