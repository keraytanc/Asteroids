/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;
import asteroids.AsteroidsApplication;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Otwarte
 */

//Abstract class to represent various objects of the game: ship, asteroids, bullets etc. Each object has a shape, position and direction of the movement
public abstract class Character {
    
    private Polygon character;
    protected Point2D movement;

    
    public Character(Polygon polygon, int x, int y) {
        this.character = polygon;
        this.character.setTranslateX(x);
        this.character.setTranslateY(y);
        
        this.movement = new Point2D(0, 0);
        
    }
    
    //rotates object to the left by 5 degrees
    public void turnLeft() {
        this.character.setRotate(this.character.getRotate() - 5);
    }
    
    //rotates object to the right by 5 degrees
    public void turnRight() {
        this.character.setRotate(this.character.getRotate() + 5);
    }
    
    //returns the figure representing ship to move it on a scene
    public Polygon returnCharacter() {
        return this.character;
    }
    
    //function describes the direction, acceleration and the movement. Movement variable is used to define direction. 
    public void accelerate() {
        
        //Based on the current rotation sinus and cosinus functions direct movement on XY axis
        double changeX = Math.cos(Math.toRadians(this.character.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.character.getRotate()));
        
        changeX *= 0.1;
        changeY *= 0.1;
                
        this.movement = this.movement.add(changeX, changeY);
    }
    
    //function moves the ship according to its direction
    public void move() {
        this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
        this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
        
        //in case the object moves out of the window it's moved on the opposite side
        if (this.character.getTranslateX() < 0 ) {
            this.character.setTranslateX(this.character.getTranslateX() + AsteroidsApplication.WIDTH);
        }
        if (this.character.getTranslateX() > AsteroidsApplication.WIDTH) {
            this.character.setTranslateX(this.character.getTranslateX() - AsteroidsApplication.WIDTH);
        }
        if (this.character.getTranslateY() < 0) {
            this.character.setTranslateY(this.character.getTranslateY() + AsteroidsApplication.HEIGHT);
        }
        if (this.character.getTranslateY() > AsteroidsApplication.HEIGHT) {
            this.character.setTranslateY(this.character.getTranslateY() - AsteroidsApplication.HEIGHT);
        }
    }
    
    
    //returns Polygon of the object.
    public Polygon getCharacter() {
        return this.character;
    }
    
    //function checks the colision of two Character objects
    public boolean collide(Character object) {
        Shape collisionArea = Shape.intersect(this.character, object.character);
        return !(collisionArea.getBoundsInLocal().isEmpty());
    }
    
}
