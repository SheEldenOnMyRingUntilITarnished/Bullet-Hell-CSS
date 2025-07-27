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
    //this is the main Gameplay loop
    public void run()
    {
        
        double drawInterval = 1000000000/globalData.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        String targetWave = "";
        //this runs until the player closes the game
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
                if(volleysShot < waveListHolder.WaveList.size() && currentPause <= 0){volleysShot++; currentPause = Integer.parseInt(waveListHolder.PauseTimeList().get(volleysShot - 1));}
                delta--;
                drawCount++;
            }
            
            //Updates every second
            if(timer >= 1000000000)
            {
                if(GlobalData.Reset == true && volleysShot == waveListHolder.WaveList.size())
                {
                    System.out.println("volleysShot: " + volleysShot);
                    System.out.println("waveListHolder.WaveList.size(): " + waveListHolder.WaveList.size());
                    for(int x = 0; x < volleysShot; x++){
                        if(waveListHolder.WaveList().get(x) != null)
                        {
                            waveListHolder.WaveList().remove(x);
                        }else
                        {
                            System.out.println("its null");
                        }
                        System.out.println("x: " + x);
                        System.out.println("waveListHolder.WaveList().remove(x); = " + waveListHolder.WaveList().remove(x));
                    }
                    GlobalData.Reset = false; 
                }
                //System.out.println("FPS:" + drawCount);
                //System.out.println("currentPause:" + currentPause);
                drawCount = 0;
                timer = 0;
                PlayerCode.currentDashCooldown--;
            }
        }
        
    }
    //This function paints the objects onto the panel
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
    //This function sets the players color and paints them onto the screen
    public void paintPlayer(Graphics g2)
    {
        if(PlayerCode.currentDashCooldown < 0)
        {
            g2.setColor(Color.blue);
        }else
        {
            g2.setColor(Color.green);
        }
        g2.fillRect(playerCode.playerX, playerCode.playerY, playerCode.playerSize, playerCode.playerSize);
    }
    //this function takes the data we recive from the bulletlist reader and the converts it 
    //into bullets that appear on screen
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
                
                bulletCollideWithEdge(tempBullet,TempBulletHolder,i);
                bulletCollideWithPlayer(tempBullet,TempBulletHolder,i);
                
                g2.fillRect((int)tempBullet.x, (int)tempBullet.y, tempBullet.Size, tempBullet.Size);
            }
            //System.out.println("Bullet Layer Complete now pause");
        }
    }
    //This function checks if the bullets collides with the edge of the screen
    public void bulletCollideWithEdge(BulletTemplate tempBullet, List<BulletTemplate> bulletHolder, int i)
    {
        if(tempBullet.y > GlobalData.SCREENHEIGHT - 10){
            tempBullet.y = 0;
            //System.out.println("player touching bottom wall");
            
            //bulletHolder.remove(i);
        }
        if(tempBullet.y < 0){
            tempBullet.y = GlobalData.SCREENHEIGHT - 10;
            //System.out.println("player touching top wall");
            
            //bulletHolder.remove(i);
        }
        if(tempBullet.x > GlobalData.SCREENWIDTH - 10){
            tempBullet.x = 0;
            //System.out.println("player touching right wall");
            
            //bulletHolder.remove(i);
        }
        if(tempBullet.x < 0){
            tempBullet.x = GlobalData.SCREENWIDTH - 10;
            //System.out.println("player touching left wall");
            //bulletHolder.remove(i);
        }
    }
    public void bulletCollideWithPlayer(BulletTemplate tempBullet, List<BulletTemplate> bulletHolder, int i)
    {
        if(tempBullet.y > playerCode.playerY - playerCode.playerSize/2 && tempBullet.y < playerCode.playerY + playerCode.playerSize/2 &&
           tempBullet.x > playerCode.playerX - playerCode.playerSize/2 && tempBullet.x < playerCode.playerX + playerCode.playerSize/2)
        {
            bulletHolder.remove(i);
            //PlayerCode.playerHealth--;
        }
    }
}
