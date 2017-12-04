package game.yubeijun.mario;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * ”Œœ∑‘À––
 * @author JAVA
 *
 */
public class World extends JPanel {

	public static final int WIDTH = 768;
	public static final int HEIGHT = 720;
//	public static BufferedImage temp;
	private Background background = new Background();
	private Mario mario = new Mario();
//	static {
//		temp = TheObject.loadImage("background.png");
//	}
	
	public void action() {
		KeyAdapter l = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				case 65:
					mario.stepLeft();
					background.stepRight();
					break;
				case 68:
					mario.stepRight();
					background.stepLeft();
					break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (mario.state==GameObject.MOVE_RIGHT) {
					mario.setState(Mario.RIGHT_STAND);
				}
				else if (mario.state==GameObject.MOVE_LEFT) {
					mario.setState(Mario.LEFT_STAND);
				}
			}
		};
		this.addKeyListener(l);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		}, 60, 60);
	}
	
	@Override
	public void paint(Graphics g) {
//		g.drawImage(temp, 0, 0, null);
		background.paintObject(g);
		mario.paintObject(g);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mario!");
		World world = new World();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.add(world);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		world.action();
		world.requestFocus();
	}

}
