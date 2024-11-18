package application;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class MovingDecoration extends Decoration {

	public MovingDecoration(String imageName, double x, double y, double width, double height, int time, int range) {
		super(imageName, x, y, width, height);
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(super.getNode());
		translate.setDuration(Duration.millis(time));
		translate.setCycleCount(TranslateTransition.INDEFINITE);
		translate.setByX(range);
		translate.setAutoReverse(true);
		translate.play();
	}
}