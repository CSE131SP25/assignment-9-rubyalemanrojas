package assignment9;

import java.util.LinkedList;

public class Snake {

    private static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;

    public Snake() {
        this.segments = new LinkedList<BodySegment>();
        this.deltaX = MOVEMENT_SIZE;
        this.deltaY = 0;
        BodySegment first = new BodySegment(.5, .5, SEGMENT_SIZE);
        segments.add(first);
    }

    public void changeDirection(int direction) {
        if (direction == 1) { // up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2) { // down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3) { // left
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4) { // right
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }

    /**
     * Moves the snake by updating the position of each of the segments
     * based on the current direction of travel
     */
    public void move() {
        if (segments.isEmpty()) {
            return;
        }

        BodySegment head = segments.getFirst();
        double newX = head.getX() + deltaX;
        double newY = head.getY() + deltaY;

        BodySegment newHead = new BodySegment(newX, newY, SEGMENT_SIZE);

        segments.addFirst(newHead);
        segments.removeLast();
    }

    /**
     * Draws the snake by drawing each segment
     */
    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }

    /**
     * The snake attempts to eat the given food, growing if it does so successfully
     * @param f the food to be eaten
     * @return true if the snake successfully ate the food
     */
    public boolean eatFood(Food f) {
        BodySegment head = segments.getFirst();
        double dx = head.getX() - f.getX();
        double dy = head.getY() - f.getY();
        double distance = Math.sqrt((dx * dx) + (dy * dy));

        if (distance < (SEGMENT_SIZE + Food.FOOD_SIZE)) {
            BodySegment tail = segments.getLast();
            BodySegment newSegment = new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE);
            segments.addLast(newSegment);
            return true;
        }

        return false;
    }

    /**
     * Returns true if the head of the snake is in bounds
     * @return whether or not the head is in the bounds of the window
     */
    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        double x = head.getX();
        double y = head.getY();
        return x >= 0 && y >= 0 && x <= 1 && y <= 1;
    }

    public LinkedList<BodySegment> getSegments() {
        return segments;
    }
}
