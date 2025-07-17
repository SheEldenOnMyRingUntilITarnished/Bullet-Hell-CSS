//Why are you here this is last years project this is not for this thing
/**
 * A Grand tale 
 *
 * Zachary Quinn
 * 08/02/2024
 */
import java.util.Scanner;
public class EpicTextAdventure
{
    public static void main(String[] args)
    {
        
        
        
        
        
        int MissNoraBerryRizzMeter = 80;
        int MissGabrielRizzMeter = 16;
        
        
        
        
        
        Scanner Keyboard = new Scanner(System.in);
        System.out.println("What is your name");
        String Name = Keyboard.nextLine();
        System.out.println("To answear use yes or no anythying else will auto correct to yes");
        System.out.println("");
        System.out.println("");
        System.out.println("You wake up in an unfamiler place. As you look around you see tables full of food and beer.");
        System.out.println("You come to the realisation that you are in a tavern and it seems you are the owner. A lady calls out to you \"What are you Doing "+Name+"\"?");
        System.out.println("Your mind rushs around and you think of asking her what shes doing do you want to ask her this.");
        String Answear = Keyboard.nextLine();
        if (Answear == "no")
        {
            MissNoraBerryRizzMeter -= 2;
            System.out.println("As you attempt to stutter out an answear she slaps you in the face \"Wake up!\"");
        }else
        {
            MissNoraBerryRizzMeter -= 4;
            System.out.println("\"What are you doing?\" You ask with a stern face. \"Wha, what do you mean you asked me to go grab the turnips\"!");
        }
    }
    
}
