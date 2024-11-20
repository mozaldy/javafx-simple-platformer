package application;

public class Enemy extends Hostile {
private double startX;
private double patrolDistance;
private double currentDirection = 1;
private static final double SPEED = 2;

public Enemy(double x, double y, double width, double height, double patrolDistance) {
super("enemy.png", x, y, width, height);
this.startX = x;
this.patrolDistance = patrolDistance;
}

public void update() {
setX(getX() + SPEED * currentDirection);

if (getX() > startX + patrolDistance) {
currentDirection = -1;
setScaleX(-1);
} else if (getX() < startX) {
currentDirection = 1;
setScaleX(1);
}
}
}