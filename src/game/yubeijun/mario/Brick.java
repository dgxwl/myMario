package game.yubeijun.mario;

import java.awt.image.BufferedImage;
/**
 * 砖块
 */
public class Brick extends GameObject {
	private static BufferedImage image;
	static {
		image = loadImage("brick.png");
	}
	
	/** 应当出现的场景数 */
	private int scene;
	
	public Brick(int x, int y, int scene) {
		super(48, 48, x, y);
		this.scene = scene;
	}

	@Override
	public BufferedImage getImage() {
		if (isLife()) {
			return image;
		}
		return null;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}

	public int getScene() {
		return scene;
	}

	public void setScene(int scene) {
		this.scene = scene;
	}
	
	
}
