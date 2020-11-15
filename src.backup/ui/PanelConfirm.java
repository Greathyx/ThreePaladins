package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.GameController;
import ui.button.Button;

public class PanelConfirm extends JPanel {
	private GameController gameController;

	/**
	 * 返回主菜单图�?
	 */
	Image imgReturnMenu = new ImageIcon("graphics/button/ReturnMenu.png").getImage();
	Image imgReturnMenu2 = new ImageIcon("graphics/button/ReturnMenu2.png").getImage();

	/**
	 * 返回主菜单按钮参�?(x坐标，y坐标，宽，高)
	 */
	private final int[] ReturnMenu = { 903, 25, 40, 41 };

	/**
	 * �?出游戏图�?
	 */
	Image imgExit = new ImageIcon("graphics/button/EXIT.png").getImage();
	Image imgExit2 = new ImageIcon("graphics/button/EXIT0.png").getImage();

	/**
	 * 商店按钮图片
	 */
	Image imgStore = new ImageIcon("graphics/store/shop1.png").getImage();

	Image imgYES = new ImageIcon("graphics/button/yes.png").getImage();
	Image imgYES2 = new ImageIcon("graphics/button/yes2.png").getImage();
	Image imgNO = new ImageIcon("graphics/button/no.png").getImage();
	Image imgNO2 = new ImageIcon("graphics/button/no2.png").getImage();

	/**
	 * 音乐控制按钮图标
	 */
	Image imgMusic = new ImageIcon("graphics/button/musicc.png").getImage();
	Image imgMusic2 = new ImageIcon("graphics/button/musicc2.png").getImage();

	/**
	 * �?出游戏按钮参�?(x坐标，y坐标，宽，高)
	 */
	private final int[] EXIT = { 955, 25, 40, 41 };

	/**
	 * BGM控制按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] MUSIC = { 851, 25, 40, 41 };

	public PanelConfirm(GameController gameController) {
		// 设置panel为�?�明
		this.setOpaque(false);
		this.gameController = gameController;
		this.setLayout(null);
		initButton();
	}

	// 初始化背景�??
	public void paintComponent(Graphics g) {

		Image imgChooseLevelBackground = new ImageIcon("graphics/background/Confirm.png").getImage();
		g.drawImage(imgChooseLevelBackground, 0, 0, null);
	}

	// 初始化按钮�??
	private void initButton() {
		// 初始化返回主菜单按钮
		Button returnMenu = new Button(ReturnMenu[0], ReturnMenu[1], ReturnMenu[2], ReturnMenu[3], imgReturnMenu,
				imgReturnMenu2, 35, 0, gameController);
		this.add(returnMenu);
		Button yesButton = new Button(428, 313, 70, 70, imgYES, imgYES2, 931, 0, gameController);
		this.add(yesButton);
		Button noButton = new Button(523, 313, 70, 70, imgNO, imgNO2, 932, 0, gameController);
		this.add(noButton);
		// 初始化�??出游戏按�?
		Button exit = new Button(EXIT[0], EXIT[1], EXIT[2], EXIT[3], imgExit, imgExit2, 1, 0, gameController);
		this.add(exit);
		// 初始化商店按�?
		Button store = new Button(888, 461, 89, 89, imgStore, imgStore, 996, 4, gameController);
		this.add(store);
		// 初始化音乐控制按�?
		Button music = new Button(MUSIC[0], MUSIC[1], MUSIC[2], MUSIC[3], imgMusic, imgMusic2, 929, 0, gameController);
		this.add(music);
	}
}
