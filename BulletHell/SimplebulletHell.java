import javax.swing.JFrame;
/**
 * TopDown game
 *
 * Zachary Quinn
 * 09/02/2024
 */
public class SimplebulletHell
{
    public static void main(String[] args)  
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Void Dash");
        
        GamePanel gamepanel=new GamePanel();
        window.add(gamepanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamepanel.startGameThread();
    }
}
