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
public class AnimationThread extends Thread
{
    
    JFrame frame;
    
    public AnimationThread(JFrame frame)
    {
        this.frame = frame;
        
    }
    
    public void run()
    {
        while (true)
        {
            frame.repaint();
            try
            {
                Thread.sleep(20);
                
            }
            
            catch(InterruptedException e)
            {
             System.err.println(e.getMessage());
            }
            
        }
    }
}

