import javax.swing.JPanel;
import java.awt.Font;

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
    public boolean gameMenu = true;
    public boolean playerStartingGame = false;
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
        boolean gamePaused = true;
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
                if(gamePaused != true) if(volleysShot < waveListHolder.WaveList.size() && currentPause <= 0){volleysShot++; currentPause = Integer.parseInt(waveListHolder.PauseTimeList().get(volleysShot - 1));}
                delta--;
                drawCount++;
                if(gameMenu == true) playerCollideIntoNormal(GlobalData.SCREENWIDTH/2, GlobalData.SCREENHEIGHT/2, GlobalData.SCREENWIDTH/4, 100);
            }
            
            //Updates every second
            if(timer >= 1000000000)
            {
                if(gamePaused != true)
                {                    
                    if(GlobalData.Reset == true && volleysShot >= waveListHolder.WaveList.size())
                    {
                        System.out.println("volleysShot: " + volleysShot);
                        //System.out.println("waveListHolder.WaveList.size(): " + waveListHolder.WaveList.size());
                        for(int x = 0; x < volleysShot; x++){
                            List<BulletTemplate> TempBulletHolder = waveListHolder.WaveList().get(x);
                            System.out.println("X: " + x);
                            for(int i = 0; i < TempBulletHolder.size(); i++)
                            {
                                //System.out.println("I: " + i);
                                TempBulletHolder.remove(i);
                            }
                            System.out.println("TempBulletHolder: " + TempBulletHolder);
                        }
                        GlobalData.Reset = false; 
                    }
                    //System.out.println("FPS:" + drawCount);
                    //System.out.println("currentPause:" + currentPause);
                }
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
        
        //Paints Menu
        if(gameMenu == true)paintMenu(g2);
        
        //Paints the bullets
        if(gameMenu != true) paintBullets(g2);
        
        //Paints the player
        paintPlayer(g2);
        
        //Paints UI
        if(gameMenu != true)paintUI(g2);
        if(playerStartingGame == true) {startingNextWave(g2);}
        
        //Disposes of the graphics2D
        g2.dispose();
    }
    
    //This function sets the players color and paints them onto the screen
    public void paintPlayer(Graphics g2)
    {
        if(PlayerCode.currentDashCooldown < 0){
            g2.setColor(Color.blue);
        }else{
            g2.setColor(Color.green);
        }
        g2.fillRect(playerCode.playerX, playerCode.playerY, playerCode.playerSize, playerCode.playerSize);
    }
    
    //Paints the ui
    public void paintUI(Graphics g2)
    {
        Font LargerFont = new Font("Arial", Font.BOLD, 40);
        g2.setFont(LargerFont);
        g2.setColor(Color.white);
        g2.drawString("Life: " + playerCode.playerHealth, 100, 100);
    }
    
    //Paints the main menu
    public void paintMenu(Graphics g2)
    {
        g2.setColor(Color.white);
        g2.fillRect(GlobalData.SCREENWIDTH/4, 80, GlobalData.SCREENWIDTH/2, 200);
        g2.setColor(Color.gray);
        g2.fillRect(GlobalData.SCREENWIDTH/4 + 10, 90, GlobalData.SCREENWIDTH/2 - 20, 180);
        
        Font LargerFont = new Font("Arial", Font.BOLD, 40);
        g2.setFont(LargerFont);
        g2.setColor(Color.black);
        g2.drawString("VoidDash", GlobalData.SCREENWIDTH/3, 190);
        
        g2.setColor(Color.white);
        g2.fillRect(GlobalData.SCREENWIDTH/3+30, GlobalData.SCREENHEIGHT/2, GlobalData.SCREENWIDTH/4, 100);
        g2.setColor(Color.gray);
        g2.fillRect(GlobalData.SCREENWIDTH/3+40, GlobalData.SCREENHEIGHT/2 + 10, GlobalData.SCREENWIDTH/4 - 20, 80);
    
        g2.setFont(LargerFont);
        g2.setColor(Color.black);
        g2.drawString("Start", GlobalData.SCREENWIDTH/2 - 30, GlobalData.SCREENHEIGHT/2 + 70);
    }
    
    //This function takes the data we recive from the bulletlist reader and the converts it 
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
    
    //This function paints the 
    public void startingNextWave(Graphics g2)
    {
        Font LargerFont = new Font("Arial", Font.BOLD, 50);
        g2.setFont(LargerFont);
        g2.setColor(Color.red);
        g2.drawString("3", GlobalData.SCREENWIDTH/2, GlobalData.SCREENHEIGHT/3);
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
    
    public void playerCollideIntoNormal(int x, int y,int width, int height)
    {
        if(playerCode.playerY > y - height/2 && playerCode.playerY < y + height/2 &&
           playerCode.playerX > x - width/2 && playerCode.playerX < x + width/2)
        {
            playerStartingGame = true;
            System.out.println("Player Starting Game");
        }else
        {
            playerStartingGame = false;
            System.out.println("Player Not Starting Game");
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
