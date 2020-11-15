package role;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * �?始界面中奔跑的男�?
 * 
 * @author Hyx
 *
 */
public class RunningMan {

	/**
	 * 人物左上角x坐标
	 */
	public int x = 0;

	/**
	 * 人物左上角y坐标
	 */
	public int y = 0;

	/**
	 * RunningMan类的图片数量
	 */
	public int ImageNums;

	/**
	 * RunningMan类的图片数组
	 */
	public Image pictureMan[] = null;

	/**
	 * 当前帧的ID
	 */
	public int manID;

	/**
	 * 是否更新绘制人物
	 */
	public boolean mFacus = true;

	public RunningMan(int x, int y) {
		this.ImageNums = 8;
		pictureMan = new Image[ImageNums];
		// 将图片装入数组中
		for (int i = 0; i < ImageNums; i++)
			pictureMan[i] = new ImageIcon("graphics/role/RunningMan/RunningMan" + i + ".png").getImage();
		this.x = x;
		this.y = y;
	}
	
	public void DrawMan(Graphics g, JPanel panel) {
		g.drawImage(pictureMan[manID], x, y, (ImageObserver)panel);
		manID ++;
		if(manID == 7)
			manID = 0;
	}
	
	public void UpdateMan () {
		if(mFacus == true){
			x = x + 15;
			y = y - 5;
		}
		if(x == 960){
			x = 255;
			y = 450;
		}
	}
	
}
