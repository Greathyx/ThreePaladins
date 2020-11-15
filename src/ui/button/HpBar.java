package ui.button;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.GameController;

/**
 * 血条
 * @author qzh
 *
 */
public class HpBar extends JLabel{
	/**
	 * 血条左上角x坐标
	 */
	public static final int Hp_X = 30;
	
	/**
	 *  血条左上角y坐标
	 */
	public static final int Hp_Y = 470;
	
	/**
	 *  血条类的图片帧数
	 */
//	private int ImageNums = 16;
	
	/**
	 *  血条的图片数组
	 */
	private Image pictureHp[] =  new Image[16];
	
	/**
	 *  当前帧的ID
	 */
	private int HpID;
	
	/**
	 *  是否更新绘制血条
	 */
	boolean mFacus = true;
	
	/**
	 * 游戏控制器
	 */
	GameController gameController;
		
	/**
	 * 构造函数
	 */
	public HpBar(GameController gameController) {
		this.gameController = gameController;
		gameController.setHPBar(this);
		this.HpID = 15;
		
		//将能量条图片装入数组
		for (int i = 0; i < 16; i++)
			pictureHp[15-i] = new ImageIcon("graphics/action/life" + i + ".png").getImage();
		this.setBounds(Hp_X, Hp_Y, pictureHp[0].getWidth(null), pictureHp[0].getHeight(null));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(pictureHp[HpID], 0, 0, null);
		
	}
	
	public void UpdateHp(int i) {
		this.HpID = i;
		mFacus=true;
		this.setVisible(true);
		this.repaint();
	}
}
