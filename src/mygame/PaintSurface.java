/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;
import com.sun.org.apache.xml.internal.utils.StopParseException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.applet.*;
import java.net.URL;
import java.util.*;
import java.sql.*;
import javax.swing.*;


/**
 *
 * @author Nikhil
 */
public class PaintSurface extends JComponent implements MouseMotionListener,MouseListener
{
    int paddle_x =0;
    int paddle_y = 650;
    int paddlewidth = 90;
    int paddleheight = 12;
   
    
    int score =0;
    float twist = 1.0f;
  
    int num;
    int ballctr;
    int paintcount;
    boolean flag;
    
    StopWatch timer;
    Image background;
    
    Ball ball;
    Brick brick;
    
    Bonus bonus;
   
    
    int highscore;
    
    Color color[] = {Color.BLUE , Color.CYAN , Color.GRAY , Color.GREEN , Color.MAGENTA , Color.ORANGE , Color.PINK , Color.RED , Color.YELLOW };
    int colorindex ;
    Color brickcolor[] = {Color.cyan , Color.blue , Color.black , Color.RED , Color.PINK , Color.ORANGE , Color.MAGENTA , Color.GREEN , Color.DARK_GRAY };
    int brickcolorindex ;
    
    AudioClip paddlesound;
    AudioClip bricksound;
    AudioClip ballsound;
    Statement stmt;
    
    
    public PaintSurface()
    {
      ImageIcon i1 = new ImageIcon(this.getClass().getResource("BackgroundForAsteroids.png"));
      background = i1.getImage();
      addMouseMotionListener(this);
      ball = new Ball(20);
      brick = new Brick();
      ballctr = 3;
      
      timer = new StopWatch();
      
      paintcount = 1;
      
      flag = false;
            
      try
      {
        URL url1 = Mygame.class.getResource("scifi003.wav");
        bricksound = Applet.newAudioClip(url1);
        
        URL url2 = Mygame.class.getResource("household007.wav");
        paddlesound = Applet.newAudioClip(url2);
        
        URL url3 = Mygame.class.getResource("comic011.wav");
        ballsound = Applet.newAudioClip(url3);
                
      }
      
      catch (Exception e)
      {
          System.err.println("Audio error : "+ e.getMessage());
      }
          
      try
        {
            Connection con;
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gamedb","root","root");   
            stmt = con.createStatement();
            String qry = "SELECT NAME,SCORE FROM gamedb.highscore WHERE score = (SELECT MAX(score) FROM highscore)";
            ResultSet rst = stmt.executeQuery(qry);
            while(rst.next()) 
            {highscore = rst.getInt("SCORE");}
        }
      catch(Exception e)
      {
          System.err.println("Database error! " + e.getMessage());
          highscore = 0;
      }
    }
    
   
    
    public void paint(Graphics g)
    {
        
        paintcount++;
        Graphics2D g2 = (Graphics2D) g ;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.drawImage(background, null, this);
        if (ballctr >= 0)
     {
        
        Shape paddle = new Rectangle2D.Float(paddle_x,paddle_y,paddlewidth,paddleheight);
       
       
        
        if(ball.intersects(paddle_x, paddle_y, paddlewidth,paddleheight) && ball.y_speed >0)
        {
          paddlesound.play();
          
          ball.y_speed = -(ball.y_speed);
          ball.x_speed = (int) (ball.x_speed * twist);
          if (twist != 1.0f)
          {
              colorindex++;
              score+= Math.abs(ball.x_speed * 10);
          }
        }
         
        
       
      
         ball.move();
         g2.setColor(color[colorindex%9]);
         g2.fill(ball);
      
         g2.setColor(Color.RED);
         g2.fill(paddle);
         
         
      
         g2.setColor(Color.WHITE);
         g2.drawString(" SCORE : "+score, 1200, 20);
         g2.drawString(" Balls remaining "+ballctr, 1200, 35);
         
         
         g2.setColor(brickcolor[brickcolorindex%9]);
         g2.fill(brick);
         brickcolorindex++;
         
         if ((paintcount%1100) == 200)
         {
             bonus = new Bonus();
                         
         }
         
         
         if ((paintcount%1100) == 600)
         {
             bonus = null;
         }
         
         if (paintcount == 32000)
         {
             paintcount = 1;
         }
         
         if (bonus != null)
         {
            g2.setColor(Color.yellow);
            g2.fill(bonus);
            if(ball.intersects(bonus))
                 {
                         int startcount = paintcount;
                         int endcount = startcount + 1000;
                                          
                         if ((bonus.mode ==1) || (bonus.mode == 2) || (bonus.mode == 3))
                         {
                              while ((paintcount >= startcount) && (paintcount <= endcount))
                              {
                                  twist = 2.0f;
                              
                              }
                         }
                         else if ((bonus.mode ==4) || (bonus.mode == 5) || (bonus.mode == 6))
                         {
                             ballctr+=1;
                             System.err.println("2");
                         }
                         else if ((bonus.mode ==7) || (bonus.mode == 8) || (bonus.mode == 9))
                         {
                             while ((paintcount >= startcount) && (paintcount <= endcount))
                              {
                                  paddlewidth = 180;
                              }
                             
                         }
                         else
                         {
                             score+=3000;
                             System.err.println("4");
                         }
                     
                    bonus = null;
                 } 
         }
         
         if(ball.intersects(brick))
            {
              bricksound.play();
              brick.height = 0f;
              brick.width = 0f;
              score = score+1000;
              colorindex++;
              brick = new Brick();
            }
         
          if (ball.getY() + ball.getHeight() >= Mygame.HEIGHT)
         {
                   
             ballsound.play();
            
          try
          { 
              Thread.sleep(800);          
          }
          catch(Exception e)
          {
              System.err.println("Error : "+e.getMessage());
          }
          
          
            if (ballctr >=1)
            {       
                ball = new Ball(20);
            
            }
            score -= 1000;
            colorindex = 0;
            ballctr--;
            
            
         
         
         }
         
         
    }    
        else
            
         {
            
             Font myfont = new Font("Garamond" , Font.BOLD , 76);
             g2.setFont(myfont);
             g2.setColor(Color.RED);
             g2.drawString("GAME OVER !", 450, 245);
             this.addMouseListener(this);
             g2.setFont(new Font("Serif" , Font.ITALIC , 15));
             g2.drawString("Click anywhere on the screen to retry.", 499,300 );
             
             while(!flag)
             {
                 if (score > highscore)
             {
                 try
                 {  flag = true;  
                    String name = JOptionPane.showInputDialog(null, "Congrats! You got a high score!!!  Enter your name : ");
                    
                    if (name != null)
                    {
                    String qry2 = "insert into gamedb.highscore values ('"+name+"',"+score+")";
                    stmt.executeUpdate(qry2);
                    }
                 }
                 catch (Exception e)
                 {
                     System.err.println("Unable to write to db! " + e.getMessage());
                 }
             }
             
             }
             
         }
         
    }
    
    
    @Override
    public void mouseDragged(MouseEvent me) 
    {
        
    }

    @Override
    public void mouseMoved(MouseEvent me) 
    {
        if (me.getX()-45-paddle_x > 5)
        {
            twist = 1.5f;
        }
        else if (me.getX()-45-paddle_x < -5)
        {
            twist = -1.5f;
            
        }
        else 
        {
            twist = 1.0f;
        }
        
        paddle_x = me.getX() - 45;
       
    
    }

  

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        ballctr = 3;
        score = 0;
        flag = false;
        repaint();
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

}