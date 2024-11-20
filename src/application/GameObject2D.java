package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject2D extends ImageView {
	 protected static final String ASSETS_PATH = "file:src/application/assets/";
	    protected double width;
	    protected double height;

	    public GameObject2D(String imageName, double x, double y, double width, double height) {
	        Image image = new Image(ASSETS_PATH + imageName);
	        setImage(image);
	        setPosition(x, y);
	        setDimensions(width, height);
	        configureImageView();
	    }

	    protected void setPosition(double x, double y) {
	        setX(x);
	        setY(y);
	    }	

	    protected void setDimensions(double width, double height) {
	        this.width = width;
	        this.height = height;
	        setFitWidth(width);    
	        setFitHeight(height);
	    }

	    private void configureImageView() {
	        setPreserveRatio(true);
	        setSmooth(true);
	    }
}