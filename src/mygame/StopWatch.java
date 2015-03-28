/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author Nikhil
 */
public class StopWatch 
{
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    
    public void start() 
    {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    
    public void stop() 
    {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    
    public void reset()
    {
        this.startTime = 0;
        this.stopTime = 0;
        
        
    }
   
    
    
    
    public long getElapsedTimeSecs() 
    {
        long elapsed;
        if (running) 
        {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else 
        {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }
}