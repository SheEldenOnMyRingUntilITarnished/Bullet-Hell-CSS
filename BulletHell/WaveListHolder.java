import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class BulletHolder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WaveListHolder
{
    WaveSystem waveSystem = new WaveSystem();
    public List<List> WaveList = waveSystem.RunWaveSystem();
    public List<List> WaveList()
    {
        //System.out.println("WaveList Contains :" + WaveList);
        return(WaveList);
    }
}
