/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.scene.shape.Polygon;

/**
 *
 * @author Otwarte
 */
public class Ship extends Character {
    
    public Ship(int x, int y) {
        super(new Polygon(-5, -5, -5, 5, 10, 0), x, y);
    }
    
    //the speed of ship must be slowly reduced when acceleration button is not pushed
    public void reduceSpeed() {
               
        double reductionX = this.movement.getX() * - 0.02;
        double reductionY = this.movement.getY() * - 0.02;
        
        this.movement = this.movement.add(reductionX, reductionY);
        
    }
}
