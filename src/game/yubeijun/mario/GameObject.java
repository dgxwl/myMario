package game.yubeijun.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
/**
 * 游戏中所有物体的父类
 * 
 * @author JAVA
 *
 */
public abstract class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public static final int LIFE = 0;
	public static final int DEAD = 1;
	protected int state = LIFE;

	public GameObject(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
//	public abstract void stepLeft();
//	public abstract void stepRight();

	/**
	 * 读取图片文件
	 * @param fileName
	 * @return img
	 */
	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(GameObject.class.getResource(fileName));
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/** 获取图片 */
	public abstract BufferedImage getImage();
	
	/** 画对象 g:画笔 */
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);  //画对象
	}
	
	public boolean isLife() {
		return state == LIFE;
	}
	
	public boolean isDead() {
		return state == DEAD;
	}
	
	public void goDead() {
		state = DEAD;
	}
}
