package game.yubeijun.mario;

import java.awt.image.BufferedImage;

/**
 * ������
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
	 * ���������ƶ����൱�����������ߣ�
	 */
	@Override
	public void stepLeft() {
		x -= speed;
	}

	/**
	 * ���������ƶ����൱�����������ߣ�
	 */
	@Override
	public void stepRight() {
		x += speed;
		if (x>0) {  //�Ƶ���ͷ���������ƶ�
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
