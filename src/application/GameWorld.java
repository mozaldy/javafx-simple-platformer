package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GameWorld {
    private Player player;
    private final Platform ground;
    private final List<Decoration> decorations;
    private final List<Hostile> hostiles;
    private final double worldWidth;
    private final double worldHeight;
    private final Goal goal;
    private boolean gameOver = false;
    private boolean gameWon = false;
    
    
    public GameWorld(double width, double height) {
        this.worldWidth = width;
        this.worldHeight = height;
        
        goal = new Goal(width - 300, height - 350);
        ground = new Platform(0, height - 50, width, 50);
        decorations = createDecorations();
        hostiles = createHostiles();
    }
    
    protected void addPlayer(Player player) {
    	this.player = player;
    }
    
    private List<Hostile> createHostiles() {
        List<Hostile> hostileList = new ArrayList<>();
        Random random = new Random();
        
        int numberOfHostiles = (int)(worldWidth / 1000);
        for (int i = 0; i < numberOfHostiles; i++) {
            double x = 700 + i * 800 + random.nextDouble() * 200;
            double y = worldHeight - ground.getHeight() - 45;
            
            hostileList.add(new Hostile("obstacle.png", x, y, 50, 60));
        }
        
        for (int i = 0; i < numberOfHostiles; i++) {
            double x = 500 + i * 1000;
            double y = worldHeight - ground.getHeight() - 50;
            double patrolDistance = 200 + random.nextDouble() * 300;
            
            hostileList.add(new Enemy(x, y, 45, 60, patrolDistance));
        }
        
        return hostileList;
    }
    
    private List<Decoration> createDecorations() {
        List<Decoration> decors = new ArrayList<>();
        Random random = new Random();
        
        int numberOfTrees = (int)(worldWidth / 800);
        
        for (int i = 0; i < numberOfTrees; i++) {
            double x = i * (worldWidth / numberOfTrees);
            double y = worldHeight - ground.getHeight() - 275;
            
            decors.add(new Decoration("tree.png", x, y, 400, 600));
        }
        
        for (int i = 0; i < numberOfTrees * 4; i++) {
            double scale = 0.8 + random.nextDouble() * 0.4;
            double xRand = 200 + random.nextDouble() * (worldWidth - 400);
            double yRand = worldHeight - 600 * scale;
            
            decors.add(new MovingDecoration("cloud.png", xRand, yRand, 300 * scale, 400 * scale, 5000, 100 * scale, MovingDecoration.MovementPattern.FLOATING));
        }
        return decors;
    }
    
    public void update(InputHandler input) {
        if (!gameOver && !gameWon) {
            player.update(input, this);
            
            if (goal.checkCollision(player)) {
            	goal.onCollision();
            	handleWin();
            }
            
            for (Hostile hostile : hostiles) {
            	if (hostile instanceof Enemy) {
            		((Enemy) hostile).update();
            	}
                if (hostile.checkCollision(player)) {           
                    handlePlayerDeath();
                }
            }
        }
    }
    
    private void handlePlayerDeath() {
        gameOver = true;
        player.die();
    }
    
    private void handleWin() {
        gameWon = true;
    }
    
    
    public void resetGame() {
    	gameOver = false;
        gameWon = false;
        player.reset(50, 100);
        goal.reset();
        goal.stopSound();
        player.stopSound();
    }
    
    public Player getPlayer() { return player; }
    public Platform getGround() { return ground; }
    public List<Decoration> getDecorations() { return decorations; }
    public List<Hostile> getHostiles() { return hostiles; }
    public double getWorldWidth() { return worldWidth; }
    public double getWorldHeight() { return worldHeight; }
    public boolean isGameOver() { return gameOver; }
    public Goal getGoal() { return goal; }
    public boolean isGameWon() { return gameWon; }
}