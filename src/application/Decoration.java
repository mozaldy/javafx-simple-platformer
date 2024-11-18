package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Decoration {
    private final ImageView imageView;
    
    public Decoration(String imageName, double x, double y, double width, double height) {
        Image image = new Image("file:src/application/assets/" + imageName);
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
    }
    
    public ImageView getNode() {
        return imageView;
    }
}