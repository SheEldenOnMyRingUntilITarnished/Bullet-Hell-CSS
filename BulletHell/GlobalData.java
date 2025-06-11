
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
    final int maxScreenCol=20;
    final int maxScreenRow=20;
    final int SCREENWIDTH = tileSize*maxScreenCol;
    final int SCREENHEIGHT = tileSize*maxScreenRow;
    
    //FPS
    final int FPS = 60;
}
