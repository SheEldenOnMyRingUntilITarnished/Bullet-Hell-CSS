import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
        
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1)
            {
                playerCode.update();

                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000)
            {
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
        
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.green);
        g2.fillRect(playerCode.playerX, playerCode.playerY, playerCode.playerSize, playerCode.playerSize);
        //System.out.println("waveListHolder.WaveList().size() == " + waveListHolder.WaveList().size());
        for(int x = 0; x < waveListHolder.WaveList().size(); x++)
        {
            //System.out.println(waveListHolder.WaveList().get(x));
            List<BulletTemplate> TempBulletHolder = waveListHolder.WaveList().get(x);
            //System.out.println("TempBulletHolder.size() == " + TempBulletHolder.size());
            for(int i = 0; i < TempBulletHolder.size(); i++)
            {
                System.out.println(i);
                g2.setColor(Color.red);
                BulletTemplate tempBullet = TempBulletHolder.get(i);
                double Angle = Math.toRadians(tempBullet.Rotation);
                
                tempBullet.x += tempBullet.Speed * Math.cos(Angle);
                tempBullet.y += tempBullet.Speed * Math.sin(Angle);
                
                g2.fillRect(tempBullet.x, tempBullet.y, tempBullet.Size, tempBullet.Size);
            }
        }
        
        
        g2.dispose();
        
    }
    
}
