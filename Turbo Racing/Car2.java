import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class Car2 extends Actor
{
    MyWorld thisGame;
    private boolean touchingLine;
    
     /**
     * @author Shumaima
     */
    public Car2()
    {
        //Controls the size of the car
        GreenfootImage myImage = getImage();
        int myNewHeight = (int)myImage.getHeight()/16;
        int myNewWidth = (int)myImage.getWidth()/16;
        myImage.scale(myNewWidth,myNewHeight);
    }
    
    
     /**
     * @author Monis
     */
    public void act() 
    {
      //Controls of the car
        {
        int ds = 0;
        if (Greenfoot.isKeyDown("Up")) ds++;
        if (Greenfoot.isKeyDown("Down")) ds--;
        if (ds != 0)
        {
            move(6*ds);
            int dr = 0;
            if (Greenfoot.isKeyDown("Right")) dr++;
            if (Greenfoot.isKeyDown("Left")) dr--;
            turn(dr*(6*ds));
            if (isTouching(Tire.class))
            {
                //Stops the car if it touches the tire class
                turn(-dr*(6*ds));
                move(-6*ds);
            }
            if(isTouching(Oil.class))
            {
                //Increases the turning if the car goes over the oil class
                turn(dr*(10*ds));
                move(1*ds/4);
            }
        }
      }
      
      if(Greenfoot.isKeyDown("1"))
            {
                //If 1 is pressed, it will reset the cars position back to the start
                setLocation(350,565);
            }
      if(touchingLine != isTouching(FinishLine.class))
        {
            //Adds 1 score to the leaderboard if the car touches the FinishLine class
            touchingLine = ! touchingLine;
            if (touchingLine) thisGame.score2++;
        }
      /**
       * @author Shumaima
       */
      //Editable textfile which changes the car name 
      if (Greenfoot.mouseClicked(this))
        {
            String strName = JOptionPane.showInputDialog("Car 1 Name:");
            try
            {
                BufferedWriter writer = new BufferedWriter(new FileWriter("names.txt", true));
                writer.write(strName + "/n");
                writer.close();
                JOptionPane.showMessageDialog(new JInternalFrame(), "Name added.");
            }
            catch (IOException e)
            {
                System.out.println("File not Found.");
            }
            }
    }
}
