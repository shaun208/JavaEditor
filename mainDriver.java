import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class mainDriver {
    static int posX = 0;
    static int posY = 0;
    public static void main(String[] args) {
        JWindow splash = new JWindow();
        splash.setOpacity(1.0f);
        JFrame frame = new JFrame("Code Editor");
        ImageIcon img = new ImageIcon("C:\\Users\\salad\\IdeaProjects\\untitled1\\introPhoto.png");
        Image scaledImage = img.getImage().getScaledInstance(845, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        JLabel splashLabel = new JLabel(scaledImageIcon);

        // Add the label to the window
        splash.getContentPane().add(splashLabel, BorderLayout.CENTER);

        // Set the size of the window to fit the image
        splash.setSize(800, 500);

        // Center the window on the screen
        splash.setLocationRelativeTo(null);

        // Show the splash screen
        splash.setVisible(true);
        // Create an instance of CodePanel
        CodePanel codePanel = new CodePanel();
        try {
            Thread.sleep(2000); // Adjust duration as needed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Dispose of splash screen
        splash.setVisible(false);
        splash.dispose();
        // Set the content pane of the frame to the CodePanel

        frame.setContentPane(codePanel);

        // Set the menu bar of the frame
        frame.setJMenuBar(codePanel.getMenuBar());
        frame.addMouseListener(new MouseAdapter() {


            @Override
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                frame.setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
            }
        });
        // Set frame properties
        frame.setSize(900,600);
        frame.setLocation(100,50);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}