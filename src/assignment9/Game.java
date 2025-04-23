package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	private Snake snake;
	private Food food;
	private PowerUp powerUp;
	private int powerUpSpawnTimer;
	public static final int SPAWN_INTERVAL = 200;
	private static final double SEGMENT_SIZE = 0.02;
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		this.snake = new Snake();
		this.food = new Food();
	}
	
	public void play() {
		while (snake.isInbounds()) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			//Testing only: you will eventually need to do more work here
			System.out.println("Keypress: " + dir);
			
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
			
			if (getKeypress() != -1) {
				snake.changeDirection(dir);
			}
			snake.move();
			snake.updatePowerUp();
			if (snake.eatFood(food)) {
				food = new Food();
			}
			if (powerUp != null) {
				BodySegment head = snake.getSegments().getFirst();
				double dx = head.getX() - powerUp.getX();
				double dy = head.getY() - powerUp.getY();
				double distance = Math.sqrt((dx * dx) + (dy * dy));
				if (distance < (this.SEGMENT_SIZE + PowerUp.POWERUP_SIZE)) {
					snake.applyPowerUp(powerUp.getType());
					powerUp = null;
		            }
		        }
		        
		        powerUpSpawnTimer++;
		        if (powerUpSpawnTimer >= SPAWN_INTERVAL && powerUp == null) {
		            if (Math.random() < 0.3) {
		                powerUp = new PowerUp();
		            }
		            powerUpSpawnTimer = 0;
		        }
		        this.updateDrawing();
				StdDraw.pause(100);
			}
	}
	
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
		
		StdDraw.clear();
		snake.draw();
		if (powerUp != null) {
			powerUp.draw();
		}
		food.draw();
		StdDraw.show();
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
