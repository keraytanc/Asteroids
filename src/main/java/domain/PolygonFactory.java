/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javafx.scene.shape.Polygon;
import java.util.Random;
/**
 *
 * @author Otwarte
 */

//Class will produce random irregular pentagon figures
public class PolygonFactory {
    
    public Polygon createPolygon() {
        
        //Random numbers generating object
        Random randomer = new Random();

        
        //Five random angles between center point of XY axis and pentagon corners will be picked
        int angle1 = randomer.nextInt(30) + 21;
        int angle2 = randomer.nextInt(30) + 92;
        int angle3 = randomer.nextInt(30) + 165;
        int angle4 = randomer.nextInt(30) + 237;
        int angle5 = randomer.nextInt(30) + 309;
        
        
        
        //next random point of a certain distance(not too big, not too small) from center of axis will be picked
        int size = randomer.nextInt(15);
        int distance1 = randomer.nextInt(5) + 15 + size;
        int distance2 = randomer.nextInt(5) + 15 + size;
        int distance3 = randomer.nextInt(5) + 15 + size;
        int distance4 = randomer.nextInt(5) + 15 + size;
        int distance5 = randomer.nextInt(5) + 15 + size;
        
        //using trigonometric equations to determine X and Y coordinates of each point.
        double x1 = Math.cos(Math.toRadians(angle1)) * distance1;
        double y1 = Math.sin(Math.toRadians(angle1)) * distance1;
        
        double x2 = Math.cos(Math.toRadians(angle2)) * distance2;
        double y2 = Math.sin(Math.toRadians(angle2)) * distance2;
        
        double x3 = Math.cos(Math.toRadians(angle3)) * distance3;
        double y3 = Math.sin(Math.toRadians(angle3)) * distance3;
        
        double x4 = Math.cos(Math.toRadians(angle4)) * distance4;
        double y4 = Math.sin(Math.toRadians(angle4)) * distance4;
        
        double x5 = Math.cos(Math.toRadians(angle5)) * distance5;
        double y5 = Math.sin(Math.toRadians(angle5)) * distance5;
        
        return (new Polygon(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5));
    }
    
}
