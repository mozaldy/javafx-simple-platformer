package application;

import javafx.scene.Scene;

class InputHandler {
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;
    private boolean isJumping = false;
    
    public void setupInput(Scene scene, Runnable onGameOver) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    isMovingLeft = true;
                    break;
                case RIGHT:
                    isMovingRight = true;
                    break;
                case SPACE:
                    isJumping = true;
                    break;
                case R:
                	onGameOver.run();
                	break;
            }
        });
        
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:
                    isMovingLeft = false;
                    break;
                case RIGHT:
                    isMovingRight = false;
                    break;
                case SPACE:
                    isJumping = false;
                    break;
            }
        });
    }
    
    public boolean isMovingLeft() { return isMovingLeft; }
    public boolean isMovingRight() { return isMovingRight; }
    public boolean isJumping() { return isJumping; }

}