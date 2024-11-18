package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private GameWorld gameWorld;
    private Camera camera;
    private InputHandler inputHandler;
    
    @Override
    public void start(Stage stage) {
        double sceneWidth = 1280;
        double sceneHeight = 720;
        
        gameWorld = new GameWorld(3840, 720);
        camera = new Camera(sceneWidth, sceneHeight);
        inputHandler = new InputHandler();
        
        Group gameRoot = new Group();
        Group viewport = new Group(gameRoot);

        gameWorld.getDecorations().forEach(decoration -> 
        	gameRoot.getChildren().add(decoration.getNode()));
        	gameRoot.getChildren().add(gameWorld.getGround());
    	gameWorld.getObstacles().forEach(obstacle -> 
        	gameRoot.getChildren().add(obstacle));
    	gameWorld.getEnemies().forEach(enemy -> 
        	gameRoot.getChildren().add(enemy));
    	gameRoot.getChildren().add(gameWorld.getPlayer());
        
        Scene scene = new Scene(viewport, sceneWidth, sceneHeight, Color.SKYBLUE);
        inputHandler.setupInput(scene, () -> {
        	if(gameWorld.isGameOver()) {
        		gameWorld.resetGame();
        	}
        });
        
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                camera.update(gameRoot, gameWorld.getPlayer());
            }
        }.start();
        
        stage.setTitle("PENS Simulator");
        Image icon = new Image("file:src/application/assets/logo.png");
	    stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    private void update() {
        gameWorld.update(inputHandler);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}