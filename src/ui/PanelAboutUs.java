package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.GameController;
import ui.button.Button;

public class PanelAboutUs extends JPanel{
	private Image imgReturn1 = new ImageIcon("graphics/button/Return.png").getImage();
	private Image imgReturn2 = new ImageIcon("graphics/button/Return2.png").getImage();
	/**
	 * 游戏控制器
	 */
	private GameController gameController;
	
	public PanelAboutUs(GameController gameController) {
		// 设置panel为透明
		this.setOpaque(false);
		this.gameController = gameController;
		this.setLayout(null);
		initButton();
	}

	/**
	 * 绘制关于我们界面背景
	 */
	public void paintComponent(Graphics g) {
		
		Image imgAboutUsBackground = new ImageIcon("graphics/background/AboutUs.png").getImage();
		g.drawImage(imgAboutUsBackground, 0, 0, null);
	}
	
	/**
	 * 初始化按钮
	 */
	private void initButton() {
		Button returnButton = new Button(832, 47, 125, 53, imgReturn1, imgReturn2, 13,0,gameController);
		this.add(returnButton);
	}
	
	/**
	 * @param 游戏控制器
	 */
	public void setController(GameController gameController){
		this.gameController = gameController;
	}
}
