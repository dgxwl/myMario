package game.yubeijun.mario;

import java.awt.image.BufferedImage;
/**
 * Mario��ɫ��
 * @author dgxwl
 *
 */
public class Mario extends GameObject {
	private static BufferedImage[] rImages;
	private static BufferedImage[] lImages;
	private static BufferedImage ljump;
	private static BufferedImage rjump;
	/** ������״̬ */
	public static final int MOVE_LEFT = 0;
	/** ������״̬ */
	public static final int MOVE_RIGHT = 1;
	/** ����վ��״̬ */
	public static final int LEFT_STAND = 2;
	/** ����վ��״̬ */
	public static final int RIGHT_STAND = 3;
	/** ����ԭ����״̬ */
	public static final int LEFT_JUMP = 4;
	/** ����ԭ����״̬ */
	public static final int RIGHT_JUMP = 5;

	private int state = RIGHT_STAND;
	
	private boolean isOnGround = true;
	
	static {
		rImages = new BufferedImage[4];
		for (int i = 0; i < rImages.length; i++) {
			rImages[i] = loadImage("RSuperMario" + i + ".png");
		}
		lImages = new BufferedImage[4];
		for (int i = 0; i < lImages.length; i++) {
			lImages[i] = loadImage("LSuperMario" + i + ".png");
		}
		ljump = loadImage("LSuperMarioJumping.png");
		rjump = loadImage("RSuperMarioJumping.png");
	}

	private int xSpeed;
	private int gravity;  //�������ٶ�
	private int ySpeed;  //���ϵ��ٶ�    2

	public Mario() {
		super(48, 96, 300, 528);
		state = RIGHT_STAND;
		gravity = 2;
		ySpeed = 3;
		xSpeed = 8;
	}

	/** ������ */
	public void stepLeft() {
		state = MOVE_LEFT;
		x -= xSpeed;
		if (x<0) {
			x = 0;
		}
	}

	/** ������ */
	public void stepRight() {
		state = MOVE_RIGHT;
		x += xSpeed;
	}
	
	/** ������ */
	public void jump() {
		y  -= 30;
	}
	
	/** ������ */
	public void jumpLeft() {
		state = LEFT_JUMP;
		y = 350;
		y += ySpeed;
		ySpeed += gravity;
		if (y>528) {
			isOnGround = false;
		}
	}
	
	/** ������ */
	public void jumpRight() {
		state = RIGHT_JUMP;
		y = 350;
		y += ySpeed;
		ySpeed += gravity;
		if (y>528) {
			y = 528;
			ySpeed = 2;
			state = RIGHT_STAND;
		}
	}
	
	/**
	 * ������ܵ�����������
	 */
	public void gravity() {
		if (!isOnGround) {
			y += ySpeed;
			ySpeed += gravity;
		} else {
			y = 528;
		}
	}

	public boolean isOnGround() {
		return isOnGround;
	}

	public void setOnGround(boolean isOnGround) {
		this.isOnGround = isOnGround;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	int rIndex = 0;
	int lIndex = 0;
	@Override
	public BufferedImage getImage() {
		switch (state) {
		case MOVE_RIGHT:
			rIndex++;
			rIndex %= 3;
			return rImages[rIndex];
		case MOVE_LEFT:
			lIndex++;
			lIndex %= 3;
			return lImages[lIndex];
		case LEFT_JUMP:
			if (y==528) {
				state = LEFT_STAND;
				return lImages[3];
			}
			return ljump;
		case RIGHT_JUMP:
			if (y==528) {
				state = RIGHT_STAND;
				return rImages[3];
			}
			return rjump;
		default:
			if (state==RIGHT_STAND) {
				return rImages[rImages.length - 1];
			}
			else {
				return lImages[lImages.length - 1];
			}
		}
	}

	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

}
