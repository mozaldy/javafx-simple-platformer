package application;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class MovingDecoration extends Decoration {
    private TranslateTransition animation;
    private MovementPattern pattern;
    private double range;
    private int duration;

    public enum MovementPattern {
        HORIZONTAL,
        VERTICAL,
        FLOATING
    }

    public MovingDecoration(String imageName, double x, double y, double width, double height, 
                          int duration, double range, MovementPattern pattern) {
        super(imageName, x, y, width, height);
        this.pattern = pattern;
        this.range = range;
        this.duration = duration;
        initializeAnimation();
    }

    private void initializeAnimation() {
        animation = new TranslateTransition();
        animation.setNode(this);
        animation.setDuration(Duration.millis(duration));
        animation.setCycleCount(TranslateTransition.INDEFINITE);
        
        switch (pattern) {
            case HORIZONTAL:
                animation.setByX(range);
                break;
            case VERTICAL:
                animation.setByY(range);
                break;
            case FLOATING:
                animation.setByY(range * 0.5);
                animation.setRate(0.5);
                break;
        }
        
        animation.setAutoReverse(true);
        animation.play();
    }

    public void pauseAnimation() {
        animation.pause();
    }

    public void resumeAnimation() {
        animation.play();
    }

    public void setAnimationSpeed(double speed) {
        animation.setRate(speed);
    }
}