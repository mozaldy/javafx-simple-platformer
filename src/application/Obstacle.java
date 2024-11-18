package application;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Obstacle extends ImageView {
	
	
    public Obstacle(double x, double y, double width, double height) {
        Image sprite = new Image("file:src/application/assets/obstacle.png");
        setImage(sprite);
        
        setX(x);
        setY(y);
        setFitWidth(width);
        setFitHeight(height);

        setPreserveRatio(true);
        setSmooth(true);
    }
    
    public boolean checkCollision(Player player) {
        Bounds obstacleBounds = getBoundsInParent();
        Bounds playerBounds = player.getBoundsInParent();
        
        return obstacleBounds.intersects(playerBounds);
    }
}