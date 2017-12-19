package game.yubeijun.mario;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
/**
 * ��Ϸ����������ĸ���
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
	 * ��ȡͼƬ�ļ�
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
	
	/** ��ȡͼƬ */
	public abstract BufferedImage getImage();
	
	/** ������ g:���� */
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);  //������
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
