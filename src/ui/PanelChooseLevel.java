package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.GameController;
import data.Data;
import role.Girl;
import role.RunningMan;
import ui.button.Button;

public class PanelChooseLevel extends JPanel implements Runnable {

	/**
	 * 返回主菜单图标
	 */
	Image imgReturnMenu = new ImageIcon("graphics/button/ReturnMenu.png").getImage();
	Image imgReturnMenu2 = new ImageIcon("graphics/button/ReturnMenu2.png").getImage();

	/**
	 * 返回主菜单按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] ReturnMenu = { 903, 25, 40, 41 };

	/**
	 * 游戏控制器
	 */
	private GameController gameController;
	Data data;
	/**
	 * 选择等级界面中的女孩
	 */
	Girl girl;

	/**
	 * 退出游戏图标
	 */
	Image imgExit = new ImageIcon("graphics/button/EXIT.png").getImage();
	Image imgExit2 = new ImageIcon("graphics/button/EXIT0.png").getImage();

	/**
	 * 商店按钮图片
	 */
	Image imgStore = new ImageIcon("graphics/store/shop1.png").getImage();

	Image imgNormalLevel = new ImageIcon("graphics/level/normal.png").getImage();
	Image imgChoosedLevel = new ImageIcon("graphics/level/choose.png").getImage();
	Image imgLockedLevel = new ImageIcon("graphics/level/locked.png").getImage();

	/**
	 * 音乐控制按钮图标
	 */
	Image imgMusic = new ImageIcon("graphics/button/musicc.png").getImage();
	Image imgMusic2 = new ImageIcon("graphics/button/musicc2.png").getImage();

	public static int Level = 0;

	/**
	 * 退出游戏按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] EXIT = { 955, 25, 40, 41 };

	/**
	 * BGM控制按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] MUSIC = { 851, 25, 40, 41 };

	public PanelChooseLevel(GameController gameController) {
		this.gameController = gameController;
		 this.data = gameController.getData();
		this.setLayout(null);
		// 设置panel为透明
		this.setOpaque(false);
		initButton();
		// 创建奔跑的小人对象
		girl = new Girl(755, 15);
		Thread t = new Thread(this);
		// 启动线程
		t.start();
	}

	// 初始化背景。
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		// 清屏
		// g.clearRect(0, 0, this.getWidth(), this.getHeight());
		Image imgChooseLevelBackground = new ImageIcon("graphics/background/ChooseLevel.png").getImage();
		g.drawImage(imgChooseLevelBackground, 0, 0, null);
		girl.DrawGirl(g, this);

	}

	// 初始化按钮。
	private void initButton() {
		// 已通关的按钮。
		switch (data.level) {
		case 1: {
			Button normolLevel1Button = new Button(340, 343, 80, 80, imgNormalLevel, imgChoosedLevel, 111, 0,
					gameController);
			this.add(normolLevel1Button);
			// 未通关的按钮。
			Button lockedLevelButton = new Button(438, 246, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevelButton);
			Button lockedLevel2Button = new Button(395, 107, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel2Button);
			Button lockedLevel3Button = new Button(584, 78, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel3Button);
			Button lockedLevel4Button = new Button(745, 109, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel4Button);
		}

		case 2: {
			Button normolLevel1Button = new Button(340, 343, 80, 80, imgNormalLevel, imgChoosedLevel, 111, 0,
					gameController);
			this.add(normolLevel1Button);
			Button normolLevel2Button = new Button(438, 246, 80, 80, imgNormalLevel, imgChoosedLevel, 222, 0,
					gameController);
			this.add(normolLevel2Button);
			// 未通关的按钮。

			Button lockedLevel2Button = new Button(395, 107, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel2Button);
			Button lockedLevel3Button = new Button(584, 78, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel3Button);
			Button lockedLevel4Button = new Button(745, 109, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel4Button);
		}
		case 3: {
			Button normolLevel1Button = new Button(340, 343, 80, 80, imgNormalLevel, imgChoosedLevel, 111, 0,
					gameController);
			this.add(normolLevel1Button);
			Button normolLevel2Button = new Button(438, 246, 80, 80, imgNormalLevel, imgChoosedLevel, 222, 0,
					gameController);
			this.add(normolLevel2Button);
			Button normolLevel3Button = new Button(395, 107, 80, 80, imgNormalLevel, imgChoosedLevel, 333, 0,
					gameController);
			this.add(normolLevel3Button);
			// 未通关的按钮。

			Button lockedLevel3Button = new Button(584, 78, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel3Button);
			Button lockedLevel4Button = new Button(745, 109, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel4Button);
		}
		case 4: {
			Button normolLevel1Button = new Button(340, 343, 80, 80, imgNormalLevel, imgChoosedLevel, 111, 0,
					gameController);
			this.add(normolLevel1Button);
			Button normolLevel2Button = new Button(438, 246, 80, 80, imgNormalLevel, imgChoosedLevel, 222, 0,
					gameController);
			this.add(normolLevel2Button);
			Button normolLevel3Button = new Button(395, 107, 80, 80, imgNormalLevel, imgChoosedLevel, 333, 0,
					gameController);
			this.add(normolLevel3Button);
			Button normolLevel4Button = new Button(584, 78, 80, 80, imgNormalLevel, imgChoosedLevel, 444, 0,
					gameController);
			this.add(normolLevel4Button);

			// 未通关的按钮。

			Button lockedLevel4Button = new Button(745, 109, 80, 80, imgLockedLevel, imgLockedLevel, 0, 0,
					gameController);
			this.add(lockedLevel4Button);
		}
		case 5: {
			Button normolLevel1Button = new Button(340, 343, 80, 80, imgNormalLevel, imgChoosedLevel, 111, 0,
					gameController);
			this.add(normolLevel1Button);
			Button normolLevel2Button = new Button(438, 246, 80, 80, imgNormalLevel, imgChoosedLevel, 222, 0,
					gameController);
			this.add(normolLevel2Button);
			Button normolLevel3Button = new Button(395, 107, 80, 80, imgNormalLevel, imgChoosedLevel, 333, 0,
					gameController);
			this.add(normolLevel3Button);
			Button normolLevel4Button = new Button(584, 78, 80, 80, imgNormalLevel, imgChoosedLevel, 444, 0,
					gameController);
			this.add(normolLevel4Button);
			Button normolLevel5Button = new Button(745, 109, 80, 80, imgNormalLevel, imgChoosedLevel, 555, 0,
					gameController);
			this.add(normolLevel5Button);
		}

		}

		// 初始化返回主菜单按钮
		Button returnMenu = new Button(ReturnMenu[0], ReturnMenu[1], ReturnMenu[2], ReturnMenu[3], imgReturnMenu,
				imgReturnMenu2, 36, 0, gameController);
		this.add(returnMenu);
		// 初始化退出游戏按钮
		Button exit = new Button(EXIT[0], EXIT[1], EXIT[2], EXIT[3], imgExit, imgExit2, 1, 0, gameController);
		this.add(exit);
		// 初始化商店按钮
		Button store = new Button(888, 461, 89, 89, imgStore, imgStore, 96, 4, gameController);
		this.add(store);
		// 初始化音乐控制按钮
		Button music = new Button(MUSIC[0], MUSIC[1], MUSIC[2], MUSIC[3], imgMusic, imgMusic2, 929, 0, gameController);
		this.add(music);
	}

	public void setController(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void run() {
		while (true) {
			girl.UpdateGirl();
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

}