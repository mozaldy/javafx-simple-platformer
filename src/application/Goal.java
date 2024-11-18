package application;

public class Goal extends CollidableObject{
	private static final String GOAL_IMAGE = "goal.png";
    private static final double DEFAULT_WIDTH = 300;
    private static final double DEFAULT_HEIGHT = 450;
    
    public Goal(double x, double y) {
        super(GOAL_IMAGE, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
	
	@Override
    public boolean checkCollision(Player player) {
        if (isCollided) return false;
        return super.checkCollision(player);
    }
    
    @Override
    public void onCollision() {
        isCollided = true;
        setOpacity(0.5);
    }
    
    public boolean isReached() {
        return isCollided;
    }
}
