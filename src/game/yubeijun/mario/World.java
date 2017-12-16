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
	private Brick[] bricks = new Brick[3];  //ȫ��һ����30��שͷ
	
//	public void initGameObject() {
//		bricks[0] = new Brick(192, 432, 1);
//		bricks[1] = new Brick(288, 432, 1);
//		bricks[2] = new Brick(384, 432, 1);
//	}
	
	public void initGameObject() {
		bricks[0] = new Brick(1);
		bricks[1] = new Brick(1);
		bricks[2] = new Brick(1);
		bricks[3] = new Brick(5);
		bricks[4] = new Brick(5);
		bricks[5] = new Brick(5);
		bricks[6] = new Brick(5);
		bricks[7] = new Brick(5);
		bricks[8] = new Brick(5);
		bricks[9] = new Brick(5);
		bricks[10] = new Brick(5);
		bricks[11] = new Brick(5);
		bricks[12] = new Brick(5);
	}
	
	public void hideObject() {
		for (int i = 0; i < bricks.length; i++) {
			Brick b = bricks[i];
			if (b.getScene() != background.getBackgroundNum()) {
				b.setY(-10);
			}
		}
	}

	/**
	 * ��������ӳٺ�ͬʱ��2������
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
	
	public void standOnBrick() {
		for (int i = 0; i < bricks.length; i++) {
			Brick b = bricks[i];
			if (mario.isOnBrick(b)) {
				if (mario.getState()==Mario.LEFT_JUMP || mario.getState()==Mario.LEFT_SPAN_JUMP) {
					mario.setState(Mario.LEFT_STAND);
				}
				if (mario.getState()==Mario.RIGHT_JUMP || mario.getState()==Mario.RIGHT_SPAN_JUMP) {
					mario.setState(Mario.RIGHT_STAND);
				}
				mario.setySpeed(0);
				mario.setY(b.getY() - 96);
			}
		}
	}

	public void action() {
		
		initGameObject();
		
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
				case KeyEvent.VK_ESCAPE:  //��esc�˳�
					System.exit(0);
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
				standOnBrick();
				repaint();
			}
		}, 60, 60);
	}
	
//	public void moveObject() {
//		if (background.getBackgroundNum() != 1) {
//			bricks[0].setY(1000);
//		}
//	}

	@Override
	public void paint(Graphics g) {
		background.paintObject(g);
		mario.paintObject(g);
//		if (background.getBackgroundNum() == 1) {
//			bricks[0].paintObject(g);
//		}
		for (int i = 0; i < bricks.length; i++) {
			Brick b = bricks[i];
			if (b.getScene() == background.getBackgroundNum()) {
				b.paintObject(g);
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Mario!");
		World world = new World();
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.add(world);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);  //���ش��ڱ߿�
		frame.setVisible(true);

		world.action();
		world.requestFocus();

	}

}
