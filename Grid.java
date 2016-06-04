import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grid extends JPanel {

    protected void paintComponent(Graphics g) {
//	Graphics2D g2d = (Graphics2D)g;
	super.paintComponent(g);
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, 800, 550);
	g.setColor(Color.PINK);
 	g.fillOval(Snake.head.x * 10, Snake.head.y * 10, 10, 10);
	g.setColor(Color.WHITE);
	g.fillRect(0, 550, 800, 600);
	if (!Snake.SnakeBody.isEmpty()) {
	    for (int i = 0; i < Snake.SnakeBody.size(); i++) {
	        g.fillOval(Snake.SnakeBody.get(i).x * 10, Snake.SnakeBody.get(i).y * 10, 10, 10);
	    }
	}
	g.setColor(Color.BLUE);
	g.fillRect(Snake.fruit.x * 10, Snake.fruit.y * 10, 10, 10);

	if (Snake.sizedown != null) {
	    g.setColor(Color.GREEN);
	    g.fillRect(Snake.sizedown.x * 10, Snake.sizedown.y * 10, 10, 10);
	}
	
        if (Snake.speedup != null) {
	    g.setColor(Color.RED);
	    g.fillRect(Snake.speedup.x * 10, Snake.speedup.y * 10, 10, 10);
	}

	if (Snake.slowdown != null) {
	    g.setColor(Color.YELLOW);
	    g.fillRect(Snake.slowdown.x * 10, Snake.slowdown.y * 10, 10, 10);
	}

	String score = "Score: " + Snake.score;
	String info = "s = resume game, p = pause, r = restart"; 
	g.setColor(Color.BLACK);
	g.drawString(score, 30, 580);
	g.drawString(info, 250, 580);
	
	g.setColor(Color.RED);
	String Status;
	if (Snake.gameover) {
	    Status = "Gameover!!!";
	    g.drawString(Status, 365, 250);
	}
	else if (Snake.paused) {
	    Status = "Paused!";
	    g.drawString(Status, 370, 250);
	}

    }
}
