import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Write a description of class LevelMaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LevelMaker
{
    //THIS IS NOT FOR PLAYER USE THIS IS PURELEY FOR MY OWN USAGE TO QUICKLY DEVELOPE LEVELS
    public static void main()
    {
        String tempString = "";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What do you want to call the wave?");
        tempString = keyboard.nextLine();
        createNewWave(tempString);
    }
    public static void createNewWave(String waveName)
    {
        if (waveName == "" || waveName == " ")
        {
            waveName = "MissingWaveName";
        }
        try {
          File myObj = new File("Waves/" + waveName + ".txt");
          if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            FileWriter myWriter = new FileWriter("Waves/" + waveName + ".txt");
            try {
                boolean waveFinished = false;
                Scanner keyboard = new Scanner(System.in);
                myWriter.write("**"+waveName+"**" + "\n");
                while(!waveFinished)
                {
                    String tempString = "";
                    System.out.println("What new attack do you want to add to the wave?");
                    tempString = keyboard.nextLine();
                    System.out.println("tempString: " + tempString);
                    switch(tempString)
                    {
                        case"Spin":
                        break;
                    }
                    if(tempString.equals("Spin"))
                    {
                        ReadAttack(myWriter,"SpinAttack");
                        System.out.println("Added Spin Attack");
                    }else if(tempString.equals("Cross"))
                    {
                        ReadAttack(myWriter,"CrossAttack");
                        System.out.println("Added Cross Attack");
                    }else if(tempString.equals("Done"))
                    {
                        waveFinished = true;
                        System.out.println("Finished new wave");
                    }else
                    {
                        System.out.println("Missing refrence in code, did you spell the attack right?");
                    }
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
                
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
          } else {
            System.out.println("*WARNING*");
            System.out.println("File already exists.");
            System.out.println("*WARNING*");
          }
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    public static void ReadAttack(FileWriter myWriter, String attackType)
    {
        try {
            File myObj = new File("Attacks/" + attackType + ".txt");
            Scanner myReader2 = new Scanner(myObj);
            while (myReader2.hasNextLine()) {
                String data = myReader2.nextLine();
                System.out.println(data);
                try
                {
                    myWriter.write(data + "\n");
                }
                catch (IOException ioe)
                {
                    System.out.println("An error occured missing Writer");
                    ioe.printStackTrace();
                }
            }
            myReader2.close();
        }catch (FileNotFoundException e) {
          System.out.println("An error occurred we couldnt find your attack, did you make sure to spell it right?.");
          e.printStackTrace();
        }
    }
}