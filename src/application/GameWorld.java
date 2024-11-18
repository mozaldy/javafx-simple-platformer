package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GameWorld {
    private final Player player;
    private final Platform ground;
    private final List<Decoration> decorations;
    private final List<Enemy> enemies;
    private final List<Obstacle> obstacles;
    private final double worldWidth;
    private final double worldHeight;
    private boolean gameOver = false;
    
    public GameWorld(double width, double height) {
        this.worldWidth = width;
        this.worldHeight = height;
        
        player = new Player(50, 100);
        ground = new Platform(0, height - 50, width, 50);
        decorations = createDecorations();
        enemies = createEnemies();
        obstacles = createObstacles();
    }
    
    private List<Enemy> createEnemies() {
        List<Enemy> enemyList = new ArrayList<>();
        Random random = new Random();
        
        int numberOfEnemies = (int)(worldWidth / 1000);
        for (int i = 0; i < numberOfEnemies; i++) {
            double x = 500 + i * 1000;
            double y = worldHeight - ground.getHeight() - 50;
            double patrolDistance = 200 + random.nextDouble() * 300;
            
            enemyList.add(new Enemy(x, y, 45, 60, patrolDistance));
        }
        
        return enemyList;
    }
    
    private List<Obstacle> createObstacles() {
        List<Obstacle> obstacleList = new ArrayList<>();
        Random random = new Random();
        
        int numberOfObstacles = (int)(worldWidth / 1000);
        for (int i = 0; i < numberOfObstacles; i++) {
            double x = 700 + i * 800 + random.nextDouble() * 200;
            double y = worldHeight - ground.getHeight() - 45;
            
            obstacleList.add(new Obstacle(x, y, 50, 60));
        }
        
        return obstacleList;
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
        if (!gameOver) {
            player.update(input, this);
            
            for (Enemy enemy : enemies) {
                enemy.update();
                if (enemy.checkCollision(player)) {
                    handlePlayerDeath();
                }
            }
            
            for (Obstacle obstacle : obstacles) {
                if (obstacle.checkCollision(player)) {           
                    handlePlayerDeath();
                }
            }
        }
    }
    
    private void handlePlayerDeath() {
        gameOver = true;
        player.die();
    }
    
    public void resetGame() {
        gameOver = false;
        player.reset(50, 100);
    }
    
    public Player getPlayer() { return player; }
    public Platform getGround() { return ground; }
    public List<Decoration> getDecorations() { return decorations; }
    public List<Enemy> getEnemies() { return enemies; }
    public List<Obstacle> getObstacles() { return obstacles; }
    public double getWorldWidth() { return worldWidth; }
    public double getWorldHeight() { return worldHeight; }
    public boolean isGameOver() { return gameOver; }
}