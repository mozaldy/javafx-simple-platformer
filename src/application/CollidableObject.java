package application;

import javafx.geometry.Bounds;

public abstract class CollidableObject extends GameObject2D {
	protected boolean isCollided = false;
	
	public CollidableObject(String imageName, double x, double y, double width, double height) {
		super(imageName, x, y, width, height);
    }

    public boolean checkCollision(Player player) {
        Bounds objectBounds = getBoundsInParent();
        Bounds playerBounds = player.getBoundsInParent();
        return objectBounds.intersects(playerBounds);
    }
    
    public abstract void onCollision();
    
    public void reset() {
    	isCollided = false;
    	setOpacity(1.0);
    }
}