import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Write a description of class BulletPatternReader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BulletPatternReader
{
    public List<String> main(String TargetWave){
        List<String> TempArray = new ArrayList<>();
        try{
            Scanner myReader = new Scanner(new File("Waves/"+TargetWave+".txt"));
            while(myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                if(!data.contains("**"))
                {
                    TempArray.add(data);
                }
            }
            myReader.close();
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return(TempArray);
    }
}
