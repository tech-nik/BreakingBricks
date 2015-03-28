/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 *
 * @author Nikhil
 */
public class Ball extends Ellipse2D.Float
{
    public int x_speed, y_speed ;
    public int dia;
    private final int width = Mygame.WIDTH;
    private final int height = Mygame.HEIGHT;
    
    public Ball(int diameter)
    {
        super((int)(Math.random()*(Mygame.WIDTH - 20)+1),0,diameter,diameter);
        this.dia = diameter;
        this.x_speed = (int) (Math.random()*5 + 5);
        this.y_speed = (int) (Math.random()*5 + 5);
        
        
    }
    
    public void move()
    {
        if((super.x <0) || super.x > (width-dia))
            x_speed = -(x_speed);
        if((super.y <0) || super.y > (height-dia))
            y_speed = -(y_speed);
        super.x += x_speed;
        super.y += y_speed;
    }
    
}
