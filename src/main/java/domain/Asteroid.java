/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Random;

/**
 *
 * @author Otwarte
 */
public class Asteroid extends Character {
    
    private double rotation;
    
    //randomly shaped Asteroid is created using PolygonFactory class
    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);
        
        //random numbers generator to add randomness
        Random randomer = new Random();
             
        //Asteroid is turned into random direction
        this.returnCharacter().setRotate(randomer.nextInt(360));
        
        //random  slight rotation to the movement of Asteroid
        this.rotation = 0.5 - randomer.nextDouble();
        
        //random speed of each asteroid is defined by random number of using "accelerate" function
        int acceleration = randomer.nextInt(7) + 1;
        for (int i = 0; i < acceleration; i++) {
            this.accelerate();          
        }
    }
    
    //each move slight rotation takes place too        
    @Override
    public void move() {
        super.move();
        this.getCharacter().setRotate(this.getCharacter().getRotate() + this.rotation);
    }
}
