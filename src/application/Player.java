package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Player extends ImageView {
    private double velocityY = 0;
    private boolean isJumping = false;
    private static final double MOVE_SPEED = 5;
    private static final double JUMP_FORCE = -20;
    private static final double GRAVITY = 1;
    private static final double PLAYER_WIDTH = 75;
    private static final double PLAYER_HEIGHT = 150;
    private boolean isDead = false;
    
    public Player(double x, double y) {
        Image sprite = new Image("file:src/application/assets/player.png");
        setImage(sprite);
        
        setX(x);
        setY(y);
        
        setFitWidth(PLAYER_WIDTH);
        setFitHeight(PLAYER_HEIGHT);
        
        setPreserveRatio(true);
        
        setSmooth(true);
    }
    
    public void update(InputHandler input, GameWorld gameWorld) {
    	if (isDead) return;
        if (getY() < gameWorld.getWorldHeight() - gameWorld.getGround().getHeight() - getFitHeight()) {
            velocityY += GRAVITY;
        } else {
            if (isJumping) {
                isJumping = false;
            } else {
                velocityY = 0;
                Image sprite = new Image("file:src/application/assets/player.png");
                setImage(sprite);
            }
            setY(gameWorld.getWorldHeight() - gameWorld.getGround().getHeight() - getFitHeight());
        }
        
        if (input.isMovingLeft() && getX() > 0) {
            setX(getX() - MOVE_SPEED);
            setScaleX(-1);
        }
        if (input.isMovingRight() && getX() < gameWorld.getWorldWidth() - getFitWidth()) {
            setX(getX() + MOVE_SPEED);
            setScaleX(1);
        }
        
        if (input.isJumping() && !isJumping) {
            isJumping = true;
            velocityY = JUMP_FORCE;
            Image sprite = new Image("file:src/application/assets/playerJumping.png");
            setImage(sprite);
            
        }
        
        setY(getY() + velocityY);
    }
    
    public void die() {
        isDead = true;
       
        setOpacity(0.5);
    }
    
    public void reset(double x, double y) {
        isDead = false;
        setX(x);
        setY(y);
        setOpacity(1.0);
        velocityY = 0;
        isJumping = false;
    }
    
    
    public double getWidth() { return getFitWidth(); }
    public double getHeight() { return getFitHeight(); }
}