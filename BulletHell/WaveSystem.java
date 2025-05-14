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
    public List<List> RunWaveSystem(){
        BulletPatternReader bulletPatternReader = new BulletPatternReader();
        for(int i = 0; i < bulletPatternReader.main().size();i++)
        {
            System.out.println(bulletPatternReader.main().size());
            System.out.println(i);
            boolean NoSpace = true;
            while(NoSpace == true)
            {
                List<BulletTemplate> TempBulletHolderArray = new ArrayList<>();
                int bulletsCount = 0;
                
                String[] temp = bulletPatternReader.main().get(i).split("[,\\:]");
                //System.out.println(temp[0]);
                if(temp[0].equals("Bullet"))
                {
                    //System.out.println("BULLET at Read line :" + i);
        
                    TempBulletHolderArray.add(new BulletTemplate());
                    
                    TempBulletHolderArray.get(bulletsCount).SetRotation(Integer.valueOf(temp[1]));
                    //System.out.println(Integer.valueOf(temp[1]));
                    
                    TempBulletHolderArray.get(bulletsCount).SetSpeed(Integer.valueOf(temp[2]));
                    //System.out.println(Integer.valueOf(temp[2]));
                    
                    TempBulletHolderArray.get(bulletsCount).SetSize(Integer.valueOf(temp[3]));
                    //System.out.println(Integer.valueOf(temp[3]));
                    bulletsCount++;
                }
                else if(temp[0].equals("Pause"))
                {
                    //System.out.println("Pause at Read line :" + i);
                    TempListArray.add(TempBulletHolderArray);
                    NoSpace = false;
                }
                i++;
            }
        }
        return(TempListArray);
    }
}
