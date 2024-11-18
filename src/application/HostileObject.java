package application;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class HostileObject extends ImageView {
	public HostileObject(String spritePath, double x, double y, double width, double height) {
        Image sprite = new Image(spritePath);
        setImage(sprite);
        setX(x);
        setY(y);
        setFitWidth(width);    
        setFitHeight(height);
        setPreserveRatio(true);
        setSmooth(true);
    }

    public boolean checkCollision(Player player) {
        Bounds objectBounds = getBoundsInParent();
        Bounds playerBounds = player.getBoundsInParent();
        return objectBounds.intersects(playerBounds);
    }
}
