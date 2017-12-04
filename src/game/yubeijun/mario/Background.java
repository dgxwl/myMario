package game.yubeijun.mario;

import java.awt.image.BufferedImage;

/**
 * ±≥æ∞¿‡
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
	
	@Override
	public void stepLeft() {
		x -= speed;
	}

	@Override
	public void stepRight() {
		x += speed;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}



	
	
}
