package application;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.AnimationTimer;

import java.net.URL;
import java.util.HashMap;

class Player extends GameObject2D implements SoundEmitter {
    private double velocityY = 0;
    private boolean isJumping = false;
    private static final double MOVE_SPEED = 5;
    private static final double JUMP_FORCE = -20;
    private static final double GRAVITY = 1;
    private static final double PLAYER_WIDTH = 100;
    private static final double PLAYER_HEIGHT = 100;
    private boolean isDead = false;
    private MediaPlayer mediaPlayer;
    
    private HashMap<String, Image[]> sprites;
    private int currentFrame = 0;
    private long lastFrameTime = 0;
    private static final long FRAME_DURATION = 100_000_000; // 100ms in nanoseconds
    private String currentState = "neutral";
    private AnimationTimer animationTimer;
    
    public Player(double x, double y) {
        super("player/neutral.png", x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        loadSprites();
        setupAnimationTimer();
        setupAudio();
    }
    
    private void loadSprites() {
        sprites = new HashMap<>();
        
        sprites.put("neutral", new Image[]{
            new Image("file:src/application/assets/player/neutral.png")
        });
        
        sprites.put("jumping", new Image[]{
            new Image("file:src/application/assets/player/jumping.png")
        });
        
        sprites.put("running", new Image[]{
            new Image("file:src/application/assets/player/running1.png"),
            new Image("file:src/application/assets/player/running2.png")
        });
    }
    
    private void setupAnimationTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastFrameTime >= FRAME_DURATION) {
                    updateAnimation();
                    lastFrameTime = now;
                }
            }
        };
        animationTimer.start();
    }
    
    private void updateAnimation() {
        Image[] currentSprites = sprites.get(currentState);
        if (currentSprites.length > 1) {
            currentFrame = (currentFrame + 1) % currentSprites.length;
            setImage(currentSprites[currentFrame]);
        }
    }
    
    private void setState(String newState) {
        if (!currentState.equals(newState)) {
            currentState = newState;
            currentFrame = 0;
            setImage(sprites.get(currentState)[0]);
        }
    }
    
    public void update(InputHandler input, GameWorld gameWorld) {
        if (isDead) return;
        
        if (getY() < gameWorld.getWorldHeight() - gameWorld.getGround().getHeight() - getFitHeight()) {
            velocityY += GRAVITY;
            setState("jumping");
        } else {
            if (isJumping) {
                isJumping = false;
            }
            velocityY = 0;
            setY(gameWorld.getWorldHeight() - gameWorld.getGround().getHeight() - getFitHeight());
        }
        
        boolean isMoving = false;
        
        if (input.isMovingLeft() && getX() > 0) {
            setX(getX() - MOVE_SPEED);
            setScaleX(-1);
            isMoving = true;
        }
        if (input.isMovingRight() && getX() < gameWorld.getWorldWidth() - getFitWidth()) {
            setX(getX() + MOVE_SPEED);
            setScaleX(1);
            isMoving = true;
        }
        
        if (isJumping) {
            setState("jumping");
        } else if (isMoving) {
            setState("running");
        } else {
            setState("neutral");
        }
        
        if (input.isJumping() && !isJumping) {
            isJumping = true;
            velocityY = JUMP_FORCE;
            setState("jumping");
        }
        
        setY(getY() + velocityY);
        
        if(gameWorld.isGameWon()) {
   
        }
    }
    
    public void die() {
        isDead = true;
        setOpacity(0.5);
        animationTimer.stop();
        playSound();
    }
    
    public void reset(double x, double y) {
        isDead = false;
        setX(x);
        setY(y);
        setOpacity(1.0);
        velocityY = 0;
        isJumping = false;
        setState("neutral");
        animationTimer.start();
    }
    
    public double getWidth() { return getFitWidth(); }
    public double getHeight() { return getFitHeight(); }

    @Override
    public void setupAudio() {
    	String path = AUDIO_PATH + "death.mp3";
		 URL soundUrl = getClass().getResource(path);
        if (soundUrl == null) {
            System.err.println("Sound file not found: " + path);
            return;
        }
        
        String soundPath = soundUrl.toString();
        Media sound = new Media(soundPath);
        mediaPlayer = new MediaPlayer(sound);
        
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            mediaPlayer.seek(mediaPlayer.getStartTime());
        });
    }
	@Override
	public void playSound() {
		
            mediaPlayer.play();
  
		
	}

	@Override
	public void stopSound() {
		mediaPlayer.stop();
	}
}