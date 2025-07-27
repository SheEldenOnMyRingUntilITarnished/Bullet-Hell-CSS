
/**
 * Write a description of class PlayerCode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayerCode
{
    KeyHandler keyH = new KeyHandler();
    GlobalData globalData = new GlobalData();
    PlayerStats playerStats = new PlayerStats();
    int drag = 1;
    
    int screenWidth = globalData.SCREENWIDTH;
    int screenHeight = globalData.SCREENHEIGHT;
    
    int playerHealth = playerStats.playerHealth;
    
    int playerSize = playerStats.playerSize;
    int playerX = playerStats.playerX;
    int playerY = playerStats.playerY;
    int playerXHOLD = playerStats.playerXHOLD;
    int playerYHOLD = playerStats.playerYHOLD;
    int playerSpeed = playerStats.PLAYERSPEED;
    int playerMaxSpeed = playerStats.PLAYERMAXSPEED;
    int playerDashDistance = playerStats.PLAYERDASHDISTANCE;
    int playerDashCooldown = playerStats.PLAYERDASHCOOLDOWN;
    static int currentDashCooldown = 0;
    
    public void update()
    {
        if(keyH.upPressed == true){
            playerYHOLD -= playerSpeed;
        }
        if(keyH.downPressed == true){
            playerYHOLD += playerSpeed;
        }
        if(keyH.leftPressed == true){
            playerXHOLD -= playerSpeed;
        }
        if(keyH.rightPressed == true){
            playerXHOLD += playerSpeed;
        }
        
        if(playerYHOLD > 0){playerYHOLD -= drag;}
        if(playerYHOLD < 0){playerYHOLD += drag;}
        if(playerXHOLD > 0){playerXHOLD -= drag;}
        if(playerXHOLD < 0){playerXHOLD += drag;}
        //playerMaxSpeed = (int) Math.round(Math.sqrt(playerXHOLD^2 + playerYHOLD^2));
        if(playerYHOLD > playerMaxSpeed){playerYHOLD = playerMaxSpeed;}
        if(playerYHOLD < -playerMaxSpeed){playerYHOLD = -playerMaxSpeed;}
        if(playerXHOLD > playerMaxSpeed){playerXHOLD = playerMaxSpeed;}
        if(playerXHOLD < -playerMaxSpeed){playerXHOLD = -playerMaxSpeed;}
        
        
        if(keyH.dashPressed == true && currentDashCooldown < 0){
            if(keyH.upPressed == true){
                playerYHOLD -= playerDashDistance;
                currentDashCooldown = playerDashCooldown;
            }
            if(keyH.downPressed == true){
                playerYHOLD += playerDashDistance;
                currentDashCooldown = playerDashCooldown;
            }
            if(keyH.leftPressed == true){
                playerXHOLD -= playerDashDistance;
                currentDashCooldown = playerDashCooldown;
            }
            if(keyH.rightPressed == true){
                playerXHOLD += playerDashDistance;
                currentDashCooldown = playerDashCooldown;
            }
        }
        playerY += playerYHOLD;
        playerX += playerXHOLD;
        playerCollideWithEdge();
        //System.out.println("Player XHold is :" + playerXHOLD);
        //System.out.println("Player YHold is :" + playerYHOLD);
        //System.out.println("Player X is :" + playerX);
        //System.out.println("Player Y is :" + playerY);
    }
    public void playerCollideWithEdge()
    {
        if(playerY > screenHeight - 10){
            playerY = 0;
            //System.out.println("player touching bottom wall");
        }
        if(playerY < 0){
            playerY = screenHeight - 10;
            //System.out.println("player touching top wall");
        }
        if(playerX > screenWidth - 10){
            playerX = 0;
            //System.out.println("player touching right wall");
        }
        if(playerX < 0){
            playerX = screenWidth - 10;
            //System.out.println("player touching left wall");
        }
    }
}
