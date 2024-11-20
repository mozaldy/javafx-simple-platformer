package application;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Goal extends CollidableObject implements SoundEmitter{
	private static final String GOAL_IMAGE = "goal.png";
    private static final double DEFAULT_WIDTH = 300;
    private static final double DEFAULT_HEIGHT = 450;
    private MediaPlayer mediaPlayer;
    
    
    public Goal(double x, double y) {
        super(GOAL_IMAGE, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setupAudio();
    }
    
	@Override
    public boolean checkCollision(Player player) {
        if (isCollided) return false;
        return super.checkCollision(player);
    }
    
    @Override
    public void onCollision() {
        isCollided = true;
        setOpacity(0.5);
        playSound();
    }
    
    public boolean isReached() {
        return isCollided;
    }
    @Override
    public void setupAudio() {
    	String path = AUDIO_PATH + "goal.mp3";
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
