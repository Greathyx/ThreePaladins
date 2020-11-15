package util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ui.PanelGame;

/**
 * 在胜负界面出现前过渡�?�?
 * @author qzh
 *
 */
public class DrawTransition {
	/**
	 * 帧数
	 */
	private int imgID;
	
	/**
	 * 胜利过渡画面
	 */
	Image[] transitionWin = new Image[9];
	
	/**
	 * 失败过渡画面
	 */
	Image[] transitionLose = new Image[9];
	
	/**
	 * 游戏界面
	 */
	PanelGame panelGame;
	
	/**
	 * 是否完成绘制
	 */
	private boolean drawFinish;
	
	public DrawTransition(PanelGame panelGame){
		this.panelGame = panelGame;
		drawFinish = false;
		imgID = 0;
		//载入图片
		for(int i=0;i<transitionWin.length;i++){
			transitionWin[i] = new ImageIcon("graphics/background/transition" + i + ".png").getImage();
			transitionLose[i] = new ImageIcon("graphics/background/lose" + i + ".png").getImage();
		}
	}
	
	/**
	 * 绘制胜利过场
	 */
	public void transitionWin(Graphics g){
		g.drawImage(transitionWin[imgID], 0, 0, null);
		imgID++;
		if(imgID==9){
			drawFinish = true;
			imgID = 0;
		}
	}
	
	/**
	 * 绘制失败过场
	 */
	public void transitionLose(Graphics g){
		g.drawImage(transitionLose[imgID], 0, 0, null);
		imgID++;
		if(imgID==9){
			drawFinish = true;
			imgID = 0;
		}
	}
	/**
	 * 是否完成绘制
	 */
	public boolean drawFinish(){
		return drawFinish;
	}
	
}
