package application;

public interface SoundEmitter {
	static final String AUDIO_PATH = "assets/audio/";
	void playSound();
	void stopSound();
	void setupAudio();
}
