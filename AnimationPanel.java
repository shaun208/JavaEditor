import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

class AnimationPanel extends JPanel
{
    //Start with two static final constants.  "final" is a keyword that
    //means you can't ever change a value.
    public static final int FRAME = 500;

    int x = 0;
    private static final Color BACKGROUND = new Color(204, 204, 204);
    public static boolean isDone = false;
    //fields
    private int aex;
    private BufferedImage myImage;  //temporary storage area for the graphics
    private Graphics myBuffer;
    private Timer t;
    private ArrayList<Animatable> animationObjects;
   /*
      Finally, add a new field, an int variable, to count the frames.
   */

    //We just need one constructor - initialize all the fields and start the animation!
    public AnimationPanel()
    {
        myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);    //The next two lines draw the background rectangle for the first time
        myBuffer.fillRect(0,0,FRAME,FRAME);

        animationObjects = new ArrayList<Animatable>();  //Instantiate the Animatable ArrayList

        Animatable cr = new InflatingCircle(340,200,70,Color.BLACK,3,70,240);
        Animatable cr1 = new InflatingCircle(150,360,100,Color.BLACK,5,100,240);
        animationObjects.add(cr);
        animationObjects.add(cr1);

        //...and add it to the ArrayList.  You can do this since InflatingCircle implements Animatable.

      /*
      Instantiate at least 4 different
      InflatingCircles.  Use lots of different values for color, dR, minR, and maxR.
      (You can leave the original InflatingCircle if you want, or you can comment it
      out and just make 4 new ones � up to you).  Add all of the new ones to the ArrayList
      of Animatable objects.  Run AnimationDriver and make sure you see all of your circles
      animating.
      */




     /*
        Add a new field, an int variable, to AnimationPanel to count the frames.
        In the constructor, initialize the new field to 0. In animate, increase the value
        of this new variable by 1 each time a new frame is drawn.  Display the value of
        the variable in the top right corner of the animation panel.  You will need to set
        the font, set the color to black, and call drawString.  When the value of the
        variable reaches 500 frames of animation, call the Timer object�s stop method to
        end the animation.
     */

        x = 0;


     /*
       Write a new class called ShiftingSquare.  ShiftingSquare should extend the Square
       class and implement Animatable.  It should feature all of the fields, constructors,
       accessors, modifiers, and instance methods necessary so it can be animated (follow
       InflatingCircle as a template).  Give it a default constructor and a 7-arg constructor.
       A ShiftingSquare should store its own dX value, a minX value, and a maxX value.  In
       step it should modify its x value by adding a positive dX.  Adding a positive dX will
       shift the square smoothly to the right until it reaches maxX, when the dX is set to
       negative, making the square shift smoothly to the left until it reaches minX, etc.

       Instantiate at least four ShiftingSquares of various sizes, x-positions, y-positions,
       colors, dX values, minX values, and maxX values, taking care that the initial
       x-position is in between the minX and the maxX.  Add the ShiftingSquares to the
       ArrayList of Animatable objects.
       */


      /*
         In the constructor, initialize the new field to 0.
      */

        t = new Timer(5, new AnimationListener());
        //The object must implement an interface - ActionListener - that demands
        //a single method: actionPerformed().  This class is going to be defined
        //within the AnimationPanel class - look below for "private classes".
        //The Timer waits the given time delay, then calls actionPerformed(), over
        //and over.  It begins when you call .start() and ends when you call .stop().
        t.start();  //Starts the timer
    }


    //no accessor or modifier methods; we won't need to access this class's fields from anywhere external to this class

    //overridden methods

    public void paintComponent(Graphics g)  //Required method to paint to the screen
    {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
        //Draw the buffered image we've stored as a field
    }

    //instance methods

    public void animate()
    {
        //This performs the next animation step by drawing to myImage using commands on myBuffer
        //Then, we'll call repaint(), which calls paintComponent(), which draws myImage to the screen

        //Clear the current state of myImage by writing over it with a new blank background
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0,0,FRAME,FRAME);
        myBuffer.setColor(Color.BLACK);
        myBuffer.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));






        //Loop through the ArrayList of Animatable objects; do an animation step on each one & draw it
        for(Animatable animationObject : animationObjects)
        {
            animationObject.step();  //do this object�s animation step
            animationObject.drawMe(myBuffer);
            if(aex == 170){
                isDone = true;
                t.stop();//the object draws itself

            }
        }
    /*
       In animate(), increase the value of this new variable by 1 each time a new frame is drawn.
       Display the value of the variable in the top right corner of the animation panel.  You
       will need to set the font, set the color to black, and call drawString.  When the value
       of the variable reaches 500 frames of animation, call the Timer object�s stop method
       to end the animation.
    //Call built-in JFrame method repaint(), which calls paintComponent, which puts the next frame on screen
     */
        repaint();
    }

    //private classes

    private class AnimationListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)  //Timer calls this every 5 ms
        {
            animate();  //...hence animation!

            String s = "Test";
            s.indexOf("AS");
        }
    }
}
