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
public class Bonus extends Rectangle2D.Float
{
    public int mode;
    
    
    public Bonus()
    {
        super((int)(Math.random()*1000),(int)(Math.random()*500),20,20);
        mode = (int) (Math.random()*10);
        
        
    }
    
    
    
}
