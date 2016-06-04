import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.event.*;

// JComponent is a base class for custom components 
public class Window extends JComponent{

    public static int FPS = 60, SNAKESPEED = 5;
    public Grid grid;

    public Window() {
	JFrame f = new JFrame("Snake"); // jframe is the app window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JButton btn_start = new JButton("Start");
	btn_start.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		    Snake snake = new Snake(f);
		}});

	JButton btn_exit = new JButton("Exit");
	btn_exit.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		System.exit(1);
	}});
	JPanel Splash = SplashScreen();
	JPanel btnPanel = new JPanel();
	btnPanel.add(btn_start);
	btnPanel.add(btn_exit);
	Splash.add(btnPanel, BorderLayout.CENTER);
	f.add(Splash);
 	f.pack();
	f.setSize(800, 600);
	f.setResizable(false); 
	f.setLocationRelativeTo(null);
        f.setVisible(true); // show the window
   }

    private JPanel SplashScreen() {

	JPanel panel = new JPanel();
	
	StringBuilder splash = new StringBuilder();
	splash.append("<html><table>");
	splash.append(String.format("<tr><td align='center'>Arrow keys to move (left/right/up/down)</td></tr>"));
	splash.append(String.format("<tr><td align='center'>s = resume game, p = pause, r = restart</td></tr>"));
	splash.append(String.format("<tr><td align='center'>Created By: Steven Zhu</td></tr>"));
	splash.append(String.format("<tr><td align='center'>Good luck!</td></tr>"));
	
	JLabel description = new JLabel(splash.toString());
	JPanel TopPanel = new JPanel();
	TopPanel.add(description, BorderLayout.CENTER);

	panel.setLayout(new BorderLayout());
	ImageIcon image = new ImageIcon("snake.jpg");
	JLabel picture = new JLabel("", image, JLabel.CENTER);
	panel.add(TopPanel, BorderLayout.NORTH);
	panel.add(picture, BorderLayout.SOUTH);	
	return panel;
    }

    public static void main(String[] args) {
	if (args.length == 2) {
	    FPS = Integer.parseInt(args[0]);
	    SNAKESPEED = Integer.parseInt(args[1]);
	}
	new Window();	
        } 
}
