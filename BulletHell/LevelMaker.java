import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Write a description of class LevelMaker here.
 *
 * @author (Zachary Quinn)
 * @version (0.3)
 */
public class LevelMaker
{
    //THIS IS NOT FOR PLAYER USE THIS IS PURELEY FOR MY OWN USAGE TO QUICKLY DEVELOPE LEVELS
    public LevelMaker()
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
                            ReadAttack(myWriter,"SpinAttack");
                            System.out.println("Added Spin Attack");
                            break;
                        
                        case"Cross":
                            ReadAttack(myWriter,"CrossAttack");
                            System.out.println("Added Cross Attack");
                            break;
                        
                        case"Circle":
                            ReadAttack(myWriter,"CircleAttack");
                            System.out.println("Added Circle Attack");
                            break;

                        case"HalfCircleUpwards":
                            ReadAttack(myWriter,"HalfCircleUpwardsAttack");
                            System.out.println("Added Half Circle Upwards Attack");
                            break;

                        case"HalfCircleDownwards":
                            ReadAttack(myWriter,"HalfCircleDownwardsAttack");
                            System.out.println("Added Circle Attack");
                            break;

                        case"HalfCircleLeftwards":
                            ReadAttack(myWriter,"HalfCircleLeftwardsAttack");
                            System.out.println("Added Circle Attack");
                            break;

                        case"HalfCircleRightwards":
                            ReadAttack(myWriter,"HalfCircleRightwardsAttack");
                            System.out.println("Added Circle Attack");
                            break;

                        case"Pause:1":
                            ReadAttack(myWriter,"Pause1");
                            System.out.println("Added Pause 1");
                            break;

                        case"Pause:5":
                            ReadAttack(myWriter,"Pause5");
                            System.out.println("Added Pause 5");
                            break;

                        case"Done":
                            waveFinished = true;
                            System.out.println("Finished new wave");
                            break;
                        
                        default:
                            System.out.println("Missing refrence in code, did you spell the attack right?");
                            System.out.println("Your input is: " + tempString);
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