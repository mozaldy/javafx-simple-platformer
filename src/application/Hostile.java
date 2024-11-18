package application;

public class Hostile extends CollidableObject {
	public Hostile(String imageName, double x, double y, double width, double height) {
        super(imageName, x, y, width, height);
    }

	 @Override
	 public void onCollision() {
		 isCollided = true;
	 }
}
