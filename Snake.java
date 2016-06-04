import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Snake extends JPanel{

    private Grid grid;

    private JFrame frame;

    public int skip_draw = 1000/Window.FPS;
    
    public int skip_speed = 1000/Window.SNAKESPEED; 

    public Timer drawtimer;

    public Timer speedtimer;

    public static ArrayList <Point> SnakeBody;

    public static Point head;

    private Point next;

    public int tail;

    public static Point fruit;

    public static Point speedup;

    public static Point slowdown;

    public static Point sizedown;

    public static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;

    public int dir = DOWN;

    public static int score; 

    public static boolean gameover, paused;

    public Random ran = new Random();

    public Random powerup = new Random();

    public Snake (JFrame f) {
    frame = f;
    frame.getContentPane().removeAll();
    grid = new Grid();
    frame.add(grid);
    frame.setSize(800, 600);
    frame.setResizable(false); 
    frame.setVisible(true); // show the window
    frame.setLocationRelativeTo(null);
	
    grid.setFocusable(true);
    grid.requestFocus();
    grid.addKeyListener(new KeyControl());    

    SnakeBody = new ArrayList <Point>();

    tail = 0;
    score = 0;
    dir = ran.nextInt(4)+1;
    next  = null;
    speedup = null;
    slowdown = null;
    sizedown = null;
    head = new Point(ran.nextInt(59)+10, ran.nextInt(34)+10);
    fruit = new Point(ran.nextInt(79), ran.nextInt(54));
    gameover = false;
    paused = false;
    StartTimer(skip_draw, skip_speed);
    }

    public void StartTimer(int drawinterval, int updateinterval) {
	drawtimer = new Timer();
	speedtimer = new Timer();
 
        drawtimer.schedule(new TimerTask() {
	public void run() {
	    if (!gameover) {
	        grid.repaint();  //draw the game
		Toolkit.getDefaultToolkit().sync();
	    }
	    else {
		grid.repaint(); //draw gameover
		drawtimer.cancel();
		drawtimer.purge();
	    }
	}
        }, 0, drawinterval);
        speedtimer.schedule(new TimerTask() {
	public void run() {
	    if (!gameover && !paused) {
	        gameUpdate();
	    }
	    if (gameover) {
		speedtimer.cancel();
		speedtimer.purge();
	    }
	}
        }, 0, updateinterval);
    }

    public void gameUpdate() {
	 SnakeBody.add(new Point(head.x, head.y));
	    if (dir == UP) {
		next = new Point(head.x, head.y-1);
		if (head.y-1 >= 0 && notCollided(next)){ 
		    head = next;
		}
		else {
		gameover = true;
		}
	    }
	    if (dir == DOWN) {
		next = new Point(head.x, head.y+1);
		if (head.y+1 < 55 && notCollided(next)){ 
		    head = next;
		}
		else {
		gameover = true;
		}
    	    }
	    if (dir == LEFT) {
		next = new Point(head.x-1, head.y);
  		if (head.x-1 >= 0 && notCollided(next)){ 
		    head = next;
		}
		else {
		gameover = true;
		}
    	    }
	    if (dir == RIGHT) {
		next = new Point(head.x+1, head.y);
		if (head.x+1 < 80 && notCollided(next)){ 
		    head = next;
		}
		else {
		gameover = true;
		}
	    }
	    if (SnakeBody.size() > tail)
	    {
	    	SnakeBody.remove(0);
	    }
	    if (fruit != null && head.equals(fruit)) {
		tail++;
		score++;
		fruit = RandomPoint();
	    }

    	    //power ups
	    int chance = powerup.nextInt(1000);
	    if (chance < 3 && speedup == null) {
		speedup = RandomPoint();
	    }
	    else if (chance < 6 && slowdown == null) {
	        slowdown = RandomPoint();
	    }
	    else if (chance < 9 && sizedown == null) {
		sizedown = RandomPoint();
	    }	
	    if (speedup != null && head.equals(speedup)) {
    		if (Window.SNAKESPEED < 10) {
		    int newspeed = Window.SNAKESPEED+1;
		    drawtimer.cancel();
		    drawtimer.purge();
		    speedtimer.cancel();
		    speedtimer.purge();
    		    StartTimer(skip_draw, (1000/newspeed));
		}
		speedup = null;
	    }
	    if (slowdown != null && head.equals(slowdown)) {
    		if (Window.SNAKESPEED >= 2) {
		    int newspeed = Window.SNAKESPEED-1;
		    drawtimer.cancel();
		    drawtimer.purge();
		    speedtimer.cancel();
		    speedtimer.purge();
    		    StartTimer(skip_draw, (1000/newspeed));
		}
		slowdown = null;
	    }
	    if (sizedown != null && head.equals(sizedown)) {
		int temp = (tail*4)/5;
		for (int i = temp; i < tail; i++) {
		    SnakeBody.remove(0);
		}
		tail = temp;
		sizedown = null;
	    }
    }

    public Point RandomPoint() {
	Point temp = new Point(ran.nextInt(79), ran.nextInt(54));;
	while (!notCollided(temp) || head.equals(temp)) {
	    temp = new Point(ran.nextInt(79), ran.nextInt(54));;
	} 
 	return temp;
    }

    public boolean notCollided(Point next) {
	for (int i = 0; i < SnakeBody.size(); i++) {
	    if (SnakeBody.get(i).equals(next)) {
		return false;
	    }
	}
	return true;
    }
    public class KeyControl extends KeyAdapter {

	public void keyPressed(KeyEvent e) {
	    int i = e.getKeyCode();
	if (i == KeyEvent.VK_UP && dir != DOWN) {
	    dir = UP;
	}
	if (i == KeyEvent.VK_DOWN && dir != UP) {
	    dir = DOWN;
	}
	if (i == KeyEvent.VK_LEFT && dir != RIGHT) {
	    dir = LEFT;
	}
	if (i == KeyEvent.VK_RIGHT && dir != LEFT) {
	    dir = RIGHT;
	}
	if (i == KeyEvent.VK_S && paused && !gameover) {
	    paused = false;
	}
	if (i == KeyEvent.VK_P && !gameover) {
	    paused = true;
	}
	if (i == KeyEvent.VK_R) {
		gameover = true;
		paused = false;
		drawtimer.cancel();
		drawtimer.purge();
		speedtimer.cancel();
		speedtimer.purge();
	   	Snake snake = new Snake(frame);
	}
        }
    }	
}
