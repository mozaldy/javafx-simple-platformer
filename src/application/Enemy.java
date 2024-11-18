package application;

public class Enemy extends HostileObject {
	private double startX;
    private double patrolDistance;
    private double currentDirection = 1; // 1 for right, -1 for left
    private static final double SPEED = 2;

    public Enemy(double x, double y, double width, double height, double patrolDistance) {
        super("file:src/application/assets/enemy.png", x, y, width, height);
        this.startX = x;
        this.patrolDistance = patrolDistance;
    }

    public void update() {
        // Move enemy back and forth
        setX(getX() + SPEED * currentDirection);

        // Check if reached patrol limit
        if (getX() > startX + patrolDistance) {
            currentDirection = -1;
            setScaleX(-1); // Flip sprite left
        } else if (getX() < startX) {
            currentDirection = 1;
            setScaleX(1); // Flip sprite right
        }
    }
}
