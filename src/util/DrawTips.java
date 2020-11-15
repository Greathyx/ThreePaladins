package util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ui.PanelGame;

/**
 * 显示提示
 * @author qzh
 *
 */
public class DrawTips {
	/**
	 * 帧数
	 */
	private int imgID;
	
	/**
	 * 能量不足图片
	 */
	Image[] imgENE = new Image[15];
	
	/**
	 * 能量溢出图片
	 */
	Image[] imgEF = new Image[15];
	
	/**
	 * 行动点数不足图片
	 */
	Image[] imgANE = new Image[15];
	
	/**
	 * 存在其他单位图片
	 */
	Image[] imgEO = new Image[15];
	
	/**
	 * 游戏界面
	 */
	PanelGame panel;
	
	/**
	 * 提示初始位置
	 */
	
	/**
	 * 构造函数
	 */
	public DrawTips(PanelGame panel){
		//初始化游戏界面
		this.panel = panel;
		//初始化图片
		for(int i=0;i<15;i++){
			imgENE[i] = new ImageIcon("graphics/tips/not"+i+".png").getImage();
			imgEF[i] = new ImageIcon("graphics/tips/EnergyFull"+i+".png").getImage();
			imgANE[i] = new ImageIcon("graphics/tips/ActionNotEnough"+i+".png").getImage();
			imgEO[i] = new ImageIcon("graphics/tips/exist"+i+".png").getImage();
		}
	}
	
	/**
	 * 能量不足 TODO
	 */
	public void energyNotEnough(Graphics g){
		g.drawImage(imgENE[imgID], 300, 200, null);
		imgID++;
		if (imgID == 14) {
			imgID = 0;
			panel.needUpdateTips = 0;
		}

	}
	
	/**
	 * 能量溢出 TODO
	 */
	public void energyFull(Graphics g){
		g.drawImage(imgEF[imgID], 300, 200, null);
		imgID++;
		if (imgID == 14) {
			imgID = 0;
			panel.needUpdateTips = 0;
		}

	}
	
	/**
	 * 行动点数不足 TODO
	 */
	public void actionPointNotEnough(Graphics g){
		g.drawImage(imgANE[imgID], 200, 200, null);
		imgID++;
		if (imgID == 14) {
			imgID = 0;
			panel.needUpdateTips = 0;
		}

	}
	
	/**
	 * 存在其他单位
	 */
	public void existOther(Graphics g){
		g.drawImage(imgEO[imgID], 200, 200, null);
		imgID++;
		if (imgID == 14) {
			imgID = 0;
			panel.needUpdateTips = 0;
		}
	}
}
