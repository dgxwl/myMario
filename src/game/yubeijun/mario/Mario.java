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
	/** 向左跨越跳 */
	public static final int LEFT_SPAN_JUMP = 6;
	/** 向右跨越跳 */
	public static final int RIGHT_SPAN_JUMP = 7;

	private int state = RIGHT_STAND;
	/** 是否在地面上 */
	private boolean isOnGround = true;
	/** 是否在砖上 */
	private boolean isOnBrick = false;
	
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
		super(48, 96, 190, 528);
		state = RIGHT_STAND;
		gravity = 2;
		ySpeed = 3;
		xSpeed = 8;
	}

	/** 向左走 */
	public void stepLeft() {
		state = MOVE_LEFT;
		xSpeed = 8;
		x -= xSpeed;
		if (x<0) {
			x = 0;
		}
	}

	/** 向右走 */
	public void stepRight() {
		state = MOVE_RIGHT;
		xSpeed = 8;
		x += xSpeed;
	}
	
	/** 向上跳 */
	public void jump() {
		y  -= 30;
		if (state == LEFT_STAND) {
			state = LEFT_JUMP;
		}
		if (state == RIGHT_STAND) {
			state = RIGHT_JUMP;
		}
	}
	
	/** 向左跨越跳 */
	public void jumpSpanLeft() {
		state = LEFT_SPAN_JUMP;
		xSpeed = 13;
		x -= xSpeed;
		if (x<0) {
			x = 0;
		}
		y  -= 30;
	}
	
	/** 向右跨越跳 */
	public void jumpSpanRight() {
		state = RIGHT_SPAN_JUMP;
		xSpeed = 13;
		x += xSpeed;
		y  -= 30;
	}
	
	/**
	 * 马里奥受到重力的作用
	 */
	public void gravity() {
		y += ySpeed;
		ySpeed += gravity;
//		if (y>528) {
//			y = 528;
//		}
	}
	
	public boolean isOnGround() {
		isOnGround = y>=500;
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

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
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
		case LEFT_SPAN_JUMP:
			if (y==528) {
				state = LEFT_STAND;
				return lImages[3];
			}
			return ljump;
		case RIGHT_JUMP:
		case RIGHT_SPAN_JUMP:
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

	/** 判断马里奥是否站在砖上 */
	public boolean isOnBrick(Brick brick) {
		int x1 = brick.x - this.width;
		int x2 = brick.x + brick.width;
		int y1 = brick.y - this.height;
		return x>x1 && x<x2 && y<=y1+30 && y>=y1-5;
	}
	
	/** 马里奥是否顶到砖块 */
	public boolean hitBrick(Brick brick) {
		int x1 = brick.x - this.width;
		int x2 = brick.x + brick.width;
		int y1 = brick.y + brick.height;
		return x>x1 && x<x2 && y<=y1+5 && y>=y1-5;
	}
}
