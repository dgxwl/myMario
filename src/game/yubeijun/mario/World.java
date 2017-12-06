package game.yubeijun.mario;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 游戏运行
 * 
 * @author JAVA
 *
 */
public class World extends JPanel {

	public static final int WIDTH = 768;
	public static final int HEIGHT = 720;
	
	private boolean isA = false;
	private boolean isD = false;
	private boolean isW = false;
	private Background background = new Background();
	private Mario mario = new Mario();

	/**
	 * 解决按键延迟
	 */
	public void flexibly() {
		if (isA && !isD && !isW) {  //按下A键
			mario.stepLeft();
			if (mario.getX()>=background.getX() && mario.getX()<=(background.getX()+306)) {
				background.setSpeed(0);
				mario.stepRealLeft();
			} else {
				mario.stepLeft();
			}
			
			background.setSpeed(6);
			background.stepRight();
		} else if (!isA && isD && !isW) {  //按下D键
			mario.stepRight();
			if (mario.getX()>=background.getX() && mario.getX()<=(background.getX()+306)) {
				background.setSpeed(0);
				mario.stepRealRight();
			} else {
				mario.stepRight();
			}
			
			background.setSpeed(6);
			background.stepLeft();
		} else if (!isA && !isD && isW) {  //按下W键
			if (mario.getState() == Mario.MOVE_RIGHT || mario.getState() == Mario.RIGHT_STAND) {
				mario.jumpRight();
			} else if (mario.getState() == Mario.MOVE_LEFT || mario.getState() == Mario.LEFT_STAND) {
				mario.jumpLeft();
			}
		} else if (isA && !isD && isW) {
			mario.stepLeft();
			background.setSpeed(13);
			background.stepRight();
			mario.jumpLeft();
		} else if (!isA && isD && isW) {
			mario.stepRight();
			background.setSpeed(13);
			background.stepLeft();
			mario.jumpRight();
		}
	}

	public void action() {
		KeyAdapter l = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				case 65: // 按A向左走
					isA = true;
					break;
				case 68: // 按D向右走
					isD = true;
					break;
				case 87: // 按W跳跃
					isW = true;
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				case 65: // 按A向左走
					isA = false;
					break;
				case 68: // 按D向右走
					isD = false;
					break;
				case 87: // 按W跳跃
					isW = false;
					break;
				}
				if (mario.getState() == Mario.MOVE_RIGHT) {
					mario.setState(Mario.RIGHT_STAND);
				} else if (mario.getState() == Mario.MOVE_LEFT) {
					mario.setState(Mario.LEFT_STAND);
				}
			}
		};
		this.addKeyListener(l);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				flexibly();
				mario.gravity();
				repaint();
			}
		}, 60, 60);
	}

	@Override
	public void paint(Graphics g) {
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
