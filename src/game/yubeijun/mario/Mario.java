package game.yubeijun.mario;

import java.awt.image.BufferedImage;
/**
 * Mario½ÇÉ«Àà
 * @author dgxwl
 *
 */
public class Mario extends GameObject {
	private static BufferedImage[] rImages;
	private static BufferedImage[] lImages;
	public static final int LEFT_STAND = 3;
	public static final int RIGHT_STAND = 4;
	static {
		rImages = new BufferedImage[4];
		for (int i = 0; i < rImages.length; i++) {
			rImages[i] = loadImage("RSuperMario" + i + ".png");
		}
		lImages = new BufferedImage[4];
		for (int i = 0; i < lImages.length; i++) {
			lImages[i] = loadImage("LSuperMario" + i + ".png");
		}
	}
	// private boolean isLeft = false;
	// private boolean isRight = false;
	// private boolean isDown = false;
	// private boolean isUp = false;

	// private int xSpeed;

	public Mario() {
		super(48, 96, 144, 528);
		state = RIGHT_STAND;
		// xSpeed = 8;
	}

	public void stepLeft() {
		state = MOVE_LEFT;
		// x -= xSpeed;
	}

	public void stepRight() {
		state = MOVE_RIGHT;
		// x += xSpeed;
	}

	int rIndex = 0;
	int lIndex = 0;
	long startTime = System.currentTimeMillis();
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
}
