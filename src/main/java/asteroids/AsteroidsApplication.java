package asteroids;
import domain.Ship;
import domain.Asteroid;
import domain.Projectile;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.text.Text;

public class AsteroidsApplication extends Application {
        
    public static int WIDTH = 600;
    public static int HEIGHT = 400;
    
    @Override
    public void start(Stage window) {
        
        //Creating a new layout for the game
        Pane layout = new Pane();
        layout.setPrefSize(WIDTH, HEIGHT);
        
        //Creating AtomicInteger object to count points
        AtomicInteger points = new AtomicInteger();
        
        //Text object to depict points
        Text score = new Text(10, 20, "Points: 0");
        layout.getChildren().add(score);
        
        //creating the object that gives random values
        Random randomer = new Random();
        
        //creating ship
        Ship ship = new Ship((WIDTH/2), (HEIGHT/2));
        
        layout.getChildren().add(ship.returnCharacter());
        
        //creating list and adding new asteroids to it
        List<Asteroid> asteroids = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            int x = randomer.nextInt(WIDTH);
            int y = randomer.nextInt(HEIGHT);         
            Asteroid newAsteroid = new Asteroid(x, y);
            if (newAsteroid.collide(ship)) {
                i = i - 1;
            } else {
            asteroids.add(newAsteroid);
            }
        }
        
        //adding each asteroid to a layout
        asteroids.forEach((asteroid) -> {
            layout.getChildren().add(asteroid.returnCharacter());
        });
        
        //creating list for a projectiles
        List<Projectile> projectiles = new ArrayList<>();
        
        //Creating new scene
        Scene scene = new Scene(layout);

        //ADDING ROTATION TO A SHIP
        
        //creating a HashMap that will containt the info about the state of the buttons: whether they are currently pressed or not
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        
        //changing the status of the button with the press and release action
        scene.setOnKeyPressed((event) -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        
        scene.setOnKeyReleased((event) -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
        
        //new Animation functionality to move objects
        new AnimationTimer() {
            
            //variable to be used to give bullets a pace. Unless the difference between last
            //bullet and new one is more than 30, no bullet will be shot.
            int bulletTimer = 30;
            
            //Variable to be used to enhance speed of new random Asteroids
            int speedEnhancer = 1;
        
            @Override
            public void handle(long now) {
                bulletTimer++;
                
                //animating ship's rotation according to the state of the buttons(pressed or not).
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }
                if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
                    ship.accelerate();
                }
                //shooting a projectile. Max 2 per second
                if (pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
                    if (bulletTimer > 30) {
                        Projectile bullet = new Projectile(ship);
                        bulletTimer = 0;
                        bullet.getCharacter().setRotate(ship.getCharacter().getRotate());
                        projectiles.add(bullet);
                        layout.getChildren().add(bullet.getCharacter());
                    }
                }
                
                //movement and speed reduction of the ship
                ship.reduceSpeed();
                ship.move();
                
                //movement of all the asteroids. Collision with the ship stops the game
                asteroids.forEach(asteroid -> {
                    asteroid.move();
                    if (ship.collide(asteroid)) {
                        stop();
                    }
                });
                
                //movement of projectiles
                projectiles.forEach((projectile) ->{
                    projectile.move();
                });
                
                //projectiles hitting the asteroid changing status. Both disappear. Points are given
                projectiles.forEach((projectile) -> {
                    asteroids.forEach((asteroid) -> {
                        if (asteroid.collide(projectile)) {
                            layout.getChildren().remove(asteroid.getCharacter());
                            asteroids.remove(asteroid);
                            layout.getChildren().remove(projectile.getCharacter());
                            projectiles.remove(projectile);
                            score.setText("Points: " + points.addAndGet(1000));
                        }
                    });
                    
                    //removing projectile after about 3 seconds without reaching target
                    projectile.addToLifeSpan();
                    if (projectile.getLifeSpan() > 180) {
                        layout.getChildren().remove(projectile.getCharacter());
                        projectiles.remove(projectile);
                    }
                });
                
                //Adding regulary new random Asteroid from random corners of the Scene and making them progresively faster
                if (Math.random() < 0.005) {
                    int locationX = randomer.nextInt(2) * WIDTH;
                    int locationY = randomer.nextInt(2) * HEIGHT;
                    Asteroid asteroid = new Asteroid(locationX, locationY);
                    for (int i = 0; i < speedEnhancer; i++) {
                        asteroid.accelerate();
                    }
                    asteroids.add(asteroid);
                    layout.getChildren().add(asteroid.getCharacter());
                    speedEnhancer++;
                }
                
            }
        }.start(); 
        
        //adding scene to a window
        window.setTitle("Asteroids!");
        window.setScene(scene);
        window.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    public static int partsCompleted() {
        // State how many parts you have completed using the return value of this method
        return 4;
    }

}
