package application;

import javafx.scene.Group;

class Camera {
    private final double viewportWidth;
    private final double viewportHeight;
    
    public Camera(double width, double height) {
        this.viewportWidth = width;
        this.viewportHeight = height;
    }
    
    public void update(Group gameRoot, Player player) {
        // Center the camera on the player
        double targetX = -player.getX() + viewportWidth / 2 - player.getWidth() / 2;
        
        // Limit camera movement to world bounds
        targetX = Math.min(0, targetX); // Don't show past left edge
        targetX = Math.max(-gameRoot.getBoundsInLocal().getWidth() + viewportWidth, targetX); // Don't show past right edge
        
        // Apply camera transform
        gameRoot.setTranslateX(targetX);
    }
}