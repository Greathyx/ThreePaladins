package util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 绘制塔工具类
 */
public class DrawTower {
	/**
	 * 斧图片 TODO
	 */
	private static final Image IMAGE_AX = new ImageIcon("graphics/tower/方块1.png").getImage();
	
	/**
	 * 剑图片 TODO
	 */
	private static final Image IMAGE_SWORD = new ImageIcon("graphics/tower/方块2.png").getImage();
	
	/**
	 * 矛图片 TODO
	 */
	private static final Image IMAGE_SPEAR = new ImageIcon("graphics/tower/方块3.png").getImage();
	
	/**
	 * 武士宽度 TODO
	 */
	private static final int ROLE_X = 40;
	
	/**
	 * 武士高度 TODO
	 */
	private static final int ROLE_Y = 40;
	
	/**
	 * 绘制塔工具类构造函数
	 * @param x
	 * x坐标
	 * @param y
	 * y坐标
	 * @param type
	 * 塔类型
	 * 0-斧，1-剑，2-矛
	 * @param level
	 * 等级
	 */
	public void draw(int x, int y, int type, int level,Graphics g){
		//绘制斧武士
		if(type==0){
			g.drawImage(IMAGE_AX, x, y, ROLE_X, ROLE_Y, null);
		}
		//绘制剑武士
		if(type==1){
			g.drawImage(IMAGE_SWORD, x, y, ROLE_X, ROLE_Y, null);
		}
		//绘制矛武士
		if(type==2){
			g.drawImage(IMAGE_SPEAR, x, y, ROLE_X, ROLE_Y, null);
		}
	}
	
	/**
	 * 睡眠方法
	 * 
	 * @param time
	 *            睡眠时间
	 */
	public static void sleep (long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
