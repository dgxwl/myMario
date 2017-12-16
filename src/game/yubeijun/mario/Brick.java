package game.yubeijun.mario;

import java.awt.image.BufferedImage;
/**
 * שͷ
 */
public class Brick extends GameObject {
	private static BufferedImage image;
	static {
		image = loadImage("brick.png");
	}
	
	/** Ӧ�����ֵĳ����� */
	private int scene;
	
	public Brick(int scene) {
		super(48, 48, 0, 0);
		this.scene = scene;
	}

	@Override
	public BufferedImage getImage() {
		return image;
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
