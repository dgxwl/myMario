package game.yubeijun.mario;

import java.awt.image.BufferedImage;

/**
 * 背景类
 * @author JAVA
 *
 */
public class Background extends GameObject {
	private static BufferedImage image;
	static {
		image = loadImage("background.png");
	}
	private int speed;
	public Background() {
		super(0, World.HEIGHT, 0, 0);
		speed = 6;
	}
	
	/**
	 * 背景向左移动（相当于主角向右走）
	 */
	@Override
	public void stepLeft() {
		x -= speed;
	}

	/**
	 * 背景向右移动（相当于主角向左走）
	 */
	@Override
	public void stepRight() {
		x += speed;
		if (x>0) {  //移到尽头，不能再移动
			x = 0;
		}
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getX() {
		return this.x;
	}

	
}
