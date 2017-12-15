package game.yubeijun.mario;

import java.awt.image.BufferedImage;

/**
 * ±≥æ∞¿‡
 * @author JAVA
 *
 */
public class Background extends GameObject {
	private static BufferedImage[] images;
	static {
		images = new BufferedImage[2];
		for (int i = 0; i < images.length; i++) {
			images[i] = loadImage("background" + i +".png");
		}
	}
	private int backgroundNum;
	private int speed;
	public Background() {
		super(World.WIDTH, World.HEIGHT, 0, 0);
		backgroundNum = 0;
	}
	

	@Override
	public BufferedImage getImage() {
		return images[backgroundNum];
	}

	public int getX() {
		return this.x;
	}

	public void nextBackground() {
		backgroundNum++;
	}

	@Override
	public void stepLeft() {}


	@Override
	public void stepRight() {}
}
