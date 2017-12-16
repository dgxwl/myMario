package game.yubeijun.mario;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * ��Ϸ����
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
	 * ��������ӳ�
	 */
	public void flexibly() {
		if (isA && !isD && !isW) {  //����A��
			mario.stepLeft();
		} else if (!isA && isD && !isW) {  //����D��
			mario.stepRight();
		} else if (!isA && !isD && isW) {  //����W��
			mario.setOnGround(false);
			mario.jump();
		} else if (isA && !isD && isW) {
			mario.jumpSpanLeft();
		} else if (!isA && isD && isW) {
			mario.jumpSpanRight();
		}
	}
	
	/**
	 * �л�����(��Mario�ߵ���Ļ���ұ�)
	 */
	public void switchBackground() {
		if (mario.getX()+48>=World.WIDTH) {
			mario.setX(0);
			background.nextBackground();
		}
	}
	
	/**
	 * Mario����Ծ���ܵ���������������ʱY����Ϊ528��
	 */
	public void marioJumpAction() {
		if (!mario.isOnGround()) {
			mario.gravity();
		} else {
			mario.setySpeed(3);
			mario.setY(528);
		}
	}

	public void action() {
		KeyAdapter l = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				case 65: // ��A������
					isA = true;
					break;
				case 68: // ��D������
					isD = true;
					break;
				case 87: // ��W��Ծ
					isW = true;
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				case 65: // �ͷ�A��
					isA = false;
					mario.setState(Mario.LEFT_STAND);
					break;
				case 68: // �ͷ�D��
					isD = false;
					mario.setState(Mario.RIGHT_STAND);
					break;
				case 87: // �ͷ�W��
					isW = false;
					break;
				}
			}
		};
		this.addKeyListener(l);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				flexibly();
				switchBackground();
				marioJumpAction();
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
