
/**
 * Write a description of class GlobalData here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GlobalData
{
    final static int originalTileSize=16; //16*16 tile
    final static int scale=3; 
    
    final static int tileSize=originalTileSize*scale;//48*48 tile
    final static int maxScreenCol=20;
    final static int maxScreenRow=20;
    final static int SCREENWIDTH = tileSize*maxScreenCol;
    final static int SCREENHEIGHT = tileSize*maxScreenRow;
    
    //FPS
    final int FPS = 60;
}
