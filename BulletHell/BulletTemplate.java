
/**
 * Write a description of class BulletData here.
 *
 * @author Zachary Quinn
 * @version (a version number or a date)
 */
public class BulletTemplate
{
    GlobalData globalData = new GlobalData();
    int x = globalData.SCREENWIDTH/2;
    int y = globalData.SCREENHEIGHT/2;
    int Speed = 2;
    double Rotation = 0;
    int Size = 4;
    
    public void SetSize(int size)
    {
        Size = size;
    }
    
    public void SetSpeed(int speed)
    {
        Speed = speed;
    }
    
    public void SetRotation(int rotation)
    {
        Rotation = rotation;
    }
}
