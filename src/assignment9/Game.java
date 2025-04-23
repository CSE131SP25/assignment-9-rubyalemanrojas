package assignment9;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private ArrayList<Food> foods;
    private int foodEatenCount;
    private static final double SEGMENT_SIZE = 0.02;

    public Game() {
        StdDraw.enableDoubleBuffering();
        this.snake = new Snake();
        this.foods = new ArrayList<>();
        this.foods.add(new Food()); // start with one food
        this.foodEatenCount = 0;
    }

    public void play() {
        while (snake.isInbounds()) {
            int dir = getKeypress();
            if (dir != -1) {
                snake.changeDirection(dir);
            }

            snake.move();

            //  food collisions
            for (int i = 0; i < foods.size(); i++) {
                if (snake.eatFood(foods.get(i))) {
                    foods.set(i, new Food());
                    foodEatenCount++;

                    // every 3 foods eaten- add 1 food 
                    if (foodEatenCount % 3 == 0) {
                        foods.add(new Food());
                    }
                    break;
                }
            }

            this.updateDrawing();
            StdDraw.pause(100);
        }
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
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
     * Clears the screen, draws the snake, all food items, and the score.
     */
    private void updateDrawing() {
        StdDraw.clear();

        
        snake.draw();
        for (Food f : foods) {
            f.draw();
        }

       
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.textLeft(0.02, 0.98, "Score: " + foodEatenCount);

        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
