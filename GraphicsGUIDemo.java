import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class GraphicsGUIDemo
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("GUI + a graphics JFrame");
        //frame.setSize(600, 430);   //We won't specify a size...
        frame.setLocation(20, 20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GraphicsGUIPanel());
        frame.pack();  //...this will allow GraphicsGUIPanel to do so instead!
        //BUT: if NOTHING calls .setPreferredSize anywhere, this means
        //the window will be as small as possible.  So don't forget!
        frame.setVisible(true);
    }
}

class GraphicsGUIPanel extends JPanel
{
    JTextField r;
    JTextField g;
    JTextField b;
    ExampleGraphicsPanel gfx;  //The subpanel containing graphics is a separate class we define

    public GraphicsGUIPanel()
    {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("GUIs + graphics demonstration!");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        gfx = new ExampleGraphicsPanel();  //Put the graphics panel in the center
        add(gfx);
        gfx.setPreferredSize(new Dimension(500,500)); //The graphics panel sets preferred size; Java builds the rest around it
        //This only works if the driver is built to recognize it; see the driver too

        //Subpanel to specify R, G, B values
        JPanel subpanel = new JPanel();
        subpanel.setLayout(new GridLayout(3, 2));
        JLabel redLabel = new JLabel("Red: ");
        redLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        subpanel.add(redLabel);
        r = new JTextField("0", 10);
        r.setHorizontalAlignment(SwingConstants.CENTER);
        subpanel.add(r);
        JLabel greenLabel = new JLabel("Green: ");
        greenLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        subpanel.add(greenLabel);
        g = new JTextField("0", 10);
        g.setHorizontalAlignment(SwingConstants.CENTER);
        subpanel.add(g);
        JLabel blueLabel = new JLabel("Blue: ");
        blueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        subpanel.add(blueLabel);
        b = new JTextField("0", 10);
        b.setHorizontalAlignment(SwingConstants.CENTER);
        subpanel.add(b);
        add(subpanel, BorderLayout.EAST);

        //(Note: nothing is added to the west region; Java simply ignores that region.)

        //Button to draw a circle.
        JButton button = new JButton("Add a random circle with your color!");
        button.addActionListener(new CircleListener());
        add(button, BorderLayout.SOUTH);
    }

    private class CircleListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int rInt = Integer.parseInt(r.getText());
            int gInt = Integer.parseInt(g.getText());
            int bInt = Integer.parseInt(b.getText());
            Color c = new Color(rInt, gInt, bInt);
            gfx.addRandomCircle(c);  //Action listener in GraphicsGUIPanel calls a method in ExampleGraphicsPanel.
        }
    }
}

class ExampleGraphicsPanel extends JPanel
{
    public static final int FRAME = 500;
    private static final Color BACKGROUND = new Color(204, 204, 204);

    private BufferedImage myImage;
    private Graphics myBuffer;

    public ExampleGraphicsPanel()
    {
        myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0,0,FRAME,FRAME);
    }

    public void paintComponent(Graphics g)  //Required method to paint to the screen
    {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);  //Draw the buffered image we've stored as a field
    }

    public void addRandomCircle(Color c)  //This is called by the GUI panel when the user pushes the button.
    {
        int x = (int)(Math.random() * FRAME);
        int y = (int)(Math.random() * FRAME);
        int r = (int)(Math.random() * FRAME / 10 + 5);
        myBuffer.setColor(c);
        myBuffer.fillOval(x-r, y-r, 2*r, 2*r);

        repaint();  //calls paintComponent
    }
}

