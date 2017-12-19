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
	private Brick[] bricks = new Brick[30];  //全关一共有30块砖头
	
	/** 初始化游戏中所有砖块 */
	public void initGameObject() {
		bricks[0] = new Brick(192, 432, 1);
		bricks[1] = new Brick(288, 432, 1);
		bricks[2] = new Brick(384, 432, 1);
		bricks[3] = new Brick(576, 432, 4);
		bricks[4] = new Brick(672, 432, 4);
		bricks[5] = new Brick(0, 240, 5);
		bricks[6] = new Brick(48, 240, 5);
		bricks[7] = new Brick(96, 240, 5);
		bricks[8] = new Brick(144, 240, 5);
		bricks[9] = new Brick(192, 240, 5);
		bricks[10] = new Brick(240, 240, 5);
		bricks[11] = new Brick(288, 240, 5);
		bricks[12] = new Brick(336, 240, 5);
		bricks[13] = new Brick(528, 240, 5);
		bricks[14] = new Brick(576, 240, 5);
		bricks[15] = new Brick(624, 240, 5);
		bricks[16] = new Brick(672, 432, 5);
		bricks[17] = new Brick(192, 432, 6);
		bricks[18] = new Brick(240, 432, 6);
		bricks[19] = new Brick(288, 432, 7);
		bricks[20] = new Brick(144, 240, 7);
		bricks[21] = new Brick(192, 240, 7);
		bricks[22] = new Brick(240, 240, 7);
		bricks[23] = new Brick(0, 240, 8);
		bricks[24] = new Brick(144, 240, 8);
		bricks[25] = new Brick(48, 432, 8);
		bricks[26] = new Brick(96, 432, 8);
		bricks[27] = new Brick(384, 432, 10);
		bricks[28] = new Brick(432, 432, 10);
		bricks[29] = new Brick(528, 432, 10);
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
	 * 解决按键延迟和同时按2键以上
	 */
	public void flexibly() {
		if (isA && !isD && !isW) {  //按下A键
			mario.stepLeft();
		} else if (!isA && isD && !isW) {  //按下D键
			mario.stepRight();
		} else if (!isA && !isD && isW) {  //按下W键
			mario.setOnGround(false);
			mario.jump();
		} else if (isA && !isD && isW) {
			mario.jumpSpanLeft();
		} else if (!isA && isD && isW) {
			mario.jumpSpanRight();
		}
	}
	
	/**
	 * 切换场景(当Mario走到屏幕最右边)
	 */
	public void switchBackground() {
		if (mario.getX()+48>=World.WIDTH) {
			mario.setX(0);
			background.nextBackground();
		}
	}
	
	/**
	 * Mario的跳跃（受到重力，站在地面时y坐标为528）
	 */
	public void marioJumpAction() {
		if (!mario.isOnGround()) {
			mario.gravity();
		} else {
			mario.setySpeed(3);
			mario.setY(528);
		}
	}
	
	/** Mario站在砖块上 */
	public void standOnBrick() {
		for (int i = 0; i < bricks.length; i++) {
			Brick b = bricks[i];
			if (b.getScene() != background.getBackgroundNum()) {  //如果不是该场景的砖块，不进行碰撞检测
				continue;
			}
			if (mario.isOnBrick(b) && b.isLife()) {
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
	
	/** 马里奥顶砖块 */
	public void hitBrick() {
		for (int i = 0; i < bricks.length; i++) {
			Brick b = bricks[i];
			if (b.getScene() != background.getBackgroundNum()) {  //如果不是该场景的砖块，不进行碰撞检测
				continue;
			}
			if (b.isLife() && mario.hitBrick(b)) {
				b.goDead();
				isW = false;
				mario.setY(b.getY()+48);
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
				case 65: // 按A向左走
					isA = true;
					break;
				case 68: // 按D向右走
					isD = true;
					break;
				case 87: // 按W跳跃
					isW = true;
					break;
				case KeyEvent.VK_ESCAPE:  //按esc退出
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				case 65: // 释放A键
					isA = false;
					mario.setState(Mario.LEFT_STAND);
					break;
				case 68: // 释放D键
					isD = false;
					mario.setState(Mario.RIGHT_STAND);
					break;
				case 87: // 释放W键
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
				hitBrick();
//				System.out.println(bricks[0].isLife());
				repaint();
			}
		}, 60, 60);
	}

	@Override
	public void paint(Graphics g) {
		background.paintObject(g);
		mario.paintObject(g);

		for (Brick b : bricks) {  //画存在于当前场景的砖块
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
		frame.setUndecorated(true);  //隐藏窗口边框
		frame.setVisible(true);

		world.action();
		world.requestFocus();

	}

}
