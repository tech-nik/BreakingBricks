/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Nikhil
 */
public class Brick extends Rectangle2D.Float
{
    
    
    public Brick()
    {
        super((int)(Math.random()*1000),(int)(Math.random()*500),50,20);
        
    }
}