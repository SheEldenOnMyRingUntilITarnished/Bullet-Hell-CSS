import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class WaveSystem here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WaveSystem
{
    public List<List> TempListArray = new ArrayList<>();
    public List<String> TempPauseList = new ArrayList<>();
    
    public List<String> ReturnPauseTimes()
    {
        return(TempPauseList);
    }
    
    public List<List> RunWaveSystem(){
        BulletPatternReader bulletPatternReader = new BulletPatternReader();
        for(int i = 0; i < bulletPatternReader.main().size();i++)
        {
            List<BulletTemplate> TempBulletHolderArray = new ArrayList<>();
            System.out.println(bulletPatternReader.main().size());
            System.out.println(i);
            boolean NoSpace = true;
            while(NoSpace == true)
            {
                int bulletsCount = 0;
                
                String[] temp = bulletPatternReader.main().get(i).split("[,\\:]");
                System.out.println("temp[0]:"+temp[0]);
                if(temp[0].equals("Bullet"))
                {
                    //System.out.println("BULLET at Read line :" + i);
                    BulletTemplate bulletTemplate = new BulletTemplate();
                    
                    bulletTemplate.SetRotation(Integer.valueOf(temp[1]));
                    //System.out.println(Integer.valueOf(temp[1]));
                    
                    bulletTemplate.SetSpeed(Integer.valueOf(temp[2]));
                    //System.out.println(Integer.valueOf(temp[2]));
                    
                    bulletTemplate.SetSize(Integer.valueOf(temp[3]));
                    //System.out.println(Integer.valueOf(temp[3]));
                    //System.out.println("bulletTemplate Contains :" + bulletTemplate);
                    
                    TempBulletHolderArray.add(bulletTemplate);
                    //System.out.println("TempBulletHolderArray Contains :" + TempBulletHolderArray);
                    
                    bulletsCount++;
                }
                else if(temp[0].equals("Pause"))
                {
                    //System.out.println("Pause at Read line :" + i);
                    //System.out.println("TempBulletHolderArray Contains :" + TempBulletHolderArray);
                    TempListArray.add(TempBulletHolderArray);
                    TempPauseList.add(temp[1]);
                    //System.out.println("TempListArray Contains :" + TempListArray);
                    NoSpace = false;
                }
                i++;
            }
        }
        //System.out.println("TempListArray Contains :" + TempListArray);
        return(TempListArray);
    }
}
