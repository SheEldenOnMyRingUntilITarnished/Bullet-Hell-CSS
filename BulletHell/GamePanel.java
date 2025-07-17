import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.Rectangle2D;

import java.util.List;
import java.util.ArrayList;

/**
 * The game panel
 *
 * Zachary Quinn
 * 09/02/2024
 */
public class GamePanel extends JPanel implements Runnable
{   
    GlobalData globalData = new GlobalData();
    
    Thread gameThread;
    
    PlayerCode playerCode = new PlayerCode();
    
    WaveListHolder waveListHolder = new WaveListHolder();
    public int volleysShot = 0;
    public int currentPause = 5;
    public GamePanel() 
    {
        this.setPreferredSize(new Dimension(globalData.SCREENWIDTH, globalData.SCREENHEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(playerCode.keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run()
    {
        
        double drawInterval = 1000000000/globalData.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        String targetWave = "";
        
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            //Updates everything on a frame
            if(delta >= 1)
            {
                playerCode.update();
                currentPause--;
                repaint();
                if(volleysShot != waveListHolder.WaveList(targetWave).size() && currentPause <= 0){volleysShot++; currentPause = Integer.parseInt(waveListHolder.PauseTimeList().get(volleysShot - 1));}
                delta--;
                drawCount++;
            }
            
            //Updates every second
            if(timer >= 1000000000)
            {
                if(GlobalData.Reset = true)
                {

                    for(int x = 0; x < volleysShot; x++){
                         //waveListHolder.WaveList().remove(x);
                    }
                    GlobalData.Reset = false; 
                }
                System.out.println("FPS:" + drawCount);
                //System.out.println("currentPause:" + currentPause);
                drawCount = 0;
                timer = 0;
                PlayerCode.currentDashCooldown--;
            }
        }
        
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        //Paints the player
        paintPlayer(g2);
        
        //Paints the bullets
        paintBullets(g2);
        
        //Disposes of the graphics2D
        g2.dispose();
        
    }
    public void paintPlayer(Graphics g2)
    {
        g2.setColor(Color.green);
        g2.fillRect(playerCode.playerX, playerCode.playerY, playerCode.playerSize, playerCode.playerSize);
    }
    public void paintBullets(Graphics2D g2)
    {
        //System.out.println("waveListHolder.WaveList().size() == " + waveListHolder.WaveList().size());
        //System.out.println(waveListHolder.WaveList().get(x));
        
        //System.out.println("TempBulletHolder.size() == " + TempBulletHolder.size());
        for(int x = 0; x < volleysShot; x++){
            List<BulletTemplate> TempBulletHolder = waveListHolder.WaveList().get(x);
            for(int i = 0; i < TempBulletHolder.size(); i++)
            {
                //System.out.println("Current i is : " + i);
                g2.setColor(Color.red);
                BulletTemplate tempBullet = TempBulletHolder.get(i);                
                double Angle = Math.toRadians(tempBullet.Rotation);
                
                //System.out.println("tempBullet.Angle: " + Math.cos(Angle));
                //System.out.println("tempBullet.Angle: " + Math.sin(Angle));
                
                //System.out.println("tempBullet.x Before: " + tempBullet.x);
                //System.out.println("tempBullet.y Before: " + tempBullet.y);
                
                tempBullet.x += tempBullet.Speed * Math.cos(Angle);
                tempBullet.y += tempBullet.Speed * Math.sin(Angle);
                
                //System.out.println("tempBullet.x After: " + tempBullet.x);
                //System.out.println("tempBullet.y After: " + tempBullet.y);
                //System.out.println("TempBulletHolder.size: " + TempBulletHolder.size());
                
                bulletCollideWithEdge(tempBullet);
                
                g2.fillRect((int)tempBullet.x, (int)tempBullet.y, tempBullet.Size, tempBullet.Size);
            }
            //System.out.println("Bullet Layer Complete now pause");
        }
    }
    
    public void bulletCollideWithEdge(BulletTemplate tempBullet)
    {
        if(tempBullet.y > GlobalData.SCREENHEIGHT - 10){
            tempBullet.y = 0;
            //System.out.println("player touching bottom wall");
        }
        if(tempBullet.y < 0){
            tempBullet.y = GlobalData.SCREENHEIGHT - 10;
            //System.out.println("player touching top wall");
        }
        if(tempBullet.x > GlobalData.SCREENWIDTH - 10){
            tempBullet.x = 0;
            //System.out.println("player touching right wall");
        }
        if(tempBullet.x < 0){
            tempBullet.x = GlobalData.SCREENWIDTH - 10;
            //System.out.println("player touching left wall");
        }
    }
}
