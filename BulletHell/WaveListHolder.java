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
    
    public List<String> PauseTimeList = waveSystem.ReturnPauseTimes();
    
    public List<List> WaveList(String targetWave)
    {
        //System.out.println("WaveList Contains :" + WaveList);
        List<List> WaveList = waveSystem.RunWaveSystem(targetWave);
        return(WaveList);
    }
    
    public List<String> PauseTimeList()
    {
        //System.out.println("PauseTimeList Contains :" + PauseTimeList);
        return(PauseTimeList);
    }
}
