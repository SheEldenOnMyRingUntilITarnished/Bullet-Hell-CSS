
/**
 * Write a description of class GlobalData here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GlobalData
{
    final int originalTileSize=16; //16*16 tile
    final int scale=3; 
    
    final int tileSize=originalTileSize*scale;//48*48 tile
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeight = tileSize*maxScreenRow;
    
    //FPS
    int FPS = 60;
}
