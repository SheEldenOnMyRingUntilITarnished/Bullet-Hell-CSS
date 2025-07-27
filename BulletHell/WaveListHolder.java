import java.util.List;
import java.util.ArrayList;
import java.io.File;
/**
 * Write a description of class BulletHolder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WaveListHolder
{
    WaveSystem waveSystem = new WaveSystem();
    List<List> WaveList = waveSystem.RunWaveSystem(ReturnTargetWave());
    public List<String> PauseTimeList = waveSystem.ReturnPauseTimes();
    
    public String ReturnTargetWave()
    {
        String targetWave = "";
        //uhhhhh i think you may need to update this for the game to run properly... idk
        String directoryPath = "C:/Users/Zacha/Bullet-Hell-CSS/BulletHell/Waves";
        File directory = new File(directoryPath);
        File[] waves = directory.listFiles();
        targetWave = waves[0].getName();
        System.out.println("targetWave: "+targetWave);
        return targetWave;
    }
    
    public List<List> WaveList()
    {
        //System.out.println("WaveList Contains :" + WaveList);
        return(WaveList);
    }
    
    public List<String> PauseTimeList()
    {
        //System.out.println("PauseTimeList Contains :" + PauseTimeList);
        return(PauseTimeList);
    }
}
