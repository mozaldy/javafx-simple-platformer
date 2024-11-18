package application;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class HostileObject extends GameObject2D {
	public HostileObject(String imageName, double x, double y, double width, double height) {
		super(imageName, x, y, width, height);
    }

    public boolean checkCollision(Player player) {
        Bounds objectBounds = getBoundsInParent();
        Bounds playerBounds = player.getBoundsInParent();
        return objectBounds.intersects(playerBounds);
    }
}
