package game.yubeijun.mario;

import java.awt.image.BufferedImage;
/**
 * Mario角色类
 * @author dgxwl
 *
 */
public class Mario extends GameObject {
	private static BufferedImage[] rImages;
	private static BufferedImage[] lImages;
	private static BufferedImage ljump;
	private static BufferedImage rjump;
	/** 向左走状态 */
	public static final int MOVE_LEFT = 0;
	/** 向右走状态 */
	public static final int MOVE_RIGHT = 1;
	/** 向左站立状态 */
	public static final int LEFT_STAND = 2;
	/** 向右站立状态 */
	public static final int RIGHT_STAND = 3;
	/** 向左原地跳状态 */
	public static final int LEFT_JUMP = 4;
	/** 向右原地跳状态 */
	public static final int RIGHT_JUMP = 5;

	private int state = RIGHT_STAND;
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
	private int gravity;  //重力加速度
	private int ySpeed;  //向上的速度    2

	public Mario() {
		super(48, 96, 300, 528);
		state = RIGHT_STAND;
		gravity = 2;
		ySpeed = 0;
		xSpeed = 0;
	}

	/** 向左走 */
	public void stepLeft() {
		xSpeed = 0;
		state = MOVE_LEFT;
	}
	
	public void stepRealLeft() {
		state = MOVE_LEFT;
		xSpeed = 6;
		x -= xSpeed;
		if (x<0) {
			x = 0;
		}
	}
	
	public void stepRealRight() {
		state = MOVE_RIGHT;
		xSpeed = 6;
		x += xSpeed;
	}

	/** 向右走 */
	public void stepRight() {
		xSpeed = 0;
		state = MOVE_RIGHT;
	}
	
	/** 向左跳 */
	public void jumpLeft() {
		state = LEFT_JUMP;
		y = 350;
		y += ySpeed;
		ySpeed += gravity;
		if (y>528) {
			y = 528;
			ySpeed = 2;
			state = RIGHT_STAND;
		}
	}
	
	/** 向右跳 */
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
	 * 马里奥受到重力的作用
	 */
	public void gravity() {
		y += ySpeed;
		ySpeed += gravity;
		if (y>528) {
			y = 528;
			ySpeed = 2;
		}
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

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

}
