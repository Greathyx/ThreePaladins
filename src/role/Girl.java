package role;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 选择关卡界面中女孩
 * @author Hyx
 *
 */
public class Girl {

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
	public Image pictureGirl[] = null;

	/**
	 * 当前帧的ID
	 */
	public int girlID;

	/**
	 * 是否更新绘制人物
	 */
	public boolean mFacus = true;

	public Girl(int x, int y) {
		this.ImageNums = 3;
		pictureGirl = new Image[ImageNums];
		// 将图片装入数组中
		for (int i = 0; i < ImageNums; i++)
			pictureGirl[i] = new ImageIcon("graphics/role/Girl/girl" + i + ".png").getImage();
		this.x = x;
		this.y = y;
	}
	
	public void DrawGirl(Graphics g, JPanel panel) {
		g.drawImage(pictureGirl[girlID], x, y, (ImageObserver)panel);
		girlID ++;
		if(girlID == 3)
			girlID = 0;
	}
	
	public void UpdateGirl () {
		x = x;
		y = y;
	}
}
