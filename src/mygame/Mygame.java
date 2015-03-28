/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

 

/**
 *
 * @author Nikhil
 */
public class Mygame extends JFrame implements KeyListener
{

    public static final int WIDTH = 1366;
    public static final int HEIGHT = 700;
    private boolean sound;
    private PaintSurface canvas;
    
    AudioClip music;
    
    public Mygame()
    {
        this.addKeyListener(this);
        this.setTitle("A simple ball game");
        sound = true; 
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new PaintSurface();
        this.add(canvas,BorderLayout.CENTER);
        Thread t = new AnimationThread(this);
        t.start();
        this.setVisible(true);
        
         try
        {
            URL url = Mygame.class.getResource("DigitalStream.wav");
            music = Applet.newAudioClip(url);
            music.loop();
            
            
        }
        catch(Exception e)
        {
            System.err.println("Audio error " + e.getMessage());
        }
    }     
    public static void main(String[] args) 
    {
        Mygame m = new Mygame();
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
       
    }

    @Override
    public void keyPressed(KeyEvent ke) 
    {
        char ch = ke.getKeyChar();
        if (ch == 'm')
        {
            if(sound == true)
            {
                sound = false;
                music.stop();
            }
            else
            {
                sound = true;
                music.loop();
            }
        }
       
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       
    }
}
