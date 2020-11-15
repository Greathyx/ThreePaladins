package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.button.Button;
import control.GameController;
import data.Data;

/**
 * 游戏人物升级界面
 * 
 * @author Hyx
 */
public class PanelLevelUp extends JPanel {
	/**
	 * 数字在面板中右上角上角x坐标
	 */
	private static final int NUM_X = 600;

	/**
	 * 数字在面板中右上角y坐标
	 */
	private static final int NUM_Y = 160;

	/**
	 * 数字图片的宽
	 */
	private static final int NUM_WIDTH = 18;

	/**
	 * 数字图片的高
	 */
	private static final int NUM_HEIGHT = 23;

	/**
	 * 游戏控制器
	 */
	private GameController gameController;

	/**
	 * 武士升级界面背景图
	 */
	private Image bgLevelUp = new ImageIcon("graphics/store/bgStore.png").getImage();

	/**
	 * 商店按钮图片
	 */
	private Image imgStore = new ImageIcon("graphics/store/shop1.png").getImage();

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
	 * 加号按钮图片
	 */
	private Image imgplus = new ImageIcon("graphics/store/plus.png").getImage();
	private Image imgplus2 = new ImageIcon("graphics/store/plus2.png").getImage();
	
	/**
	 * 音乐控制按钮图标
	 */
	Image imgMusic = new ImageIcon("graphics/button/musicc.png").getImage();
	Image imgMusic2 = new ImageIcon("graphics/button/musicc2.png").getImage();

	/**
	 * BGM控制按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] MUSIC = { 851, 25, 40, 41 };

	/**
	 * 剑士升级金钱图片
	 */
	private Image imgFencerCoin = new ImageIcon("graphics/store/fencerBuy.png").getImage();
	private Image imgFencerCoin2 = new ImageIcon("graphics/store/fencerBuy2.png").getImage();

	/**
	 * 弓箭手升级金钱图片
	 */
	private Image imgArchcerCoin = new ImageIcon("graphics/store/archerBuy.png").getImage();
	private Image imgArchcerCoin2 = new ImageIcon("graphics/store/archerBuy2.png").getImage();

	/**
	 * 牧师升级金钱图片
	 */
	private Image imgPriestCoin = new ImageIcon("graphics/store/priestBuy.png").getImage();
	private Image imgPriestCoin2 = new ImageIcon("graphics/store/priestBuy2.png").getImage();

	/**
	 * 关闭升级面板按钮图片
	 */
	Image imgOut = new ImageIcon("graphics/store/exit.png").getImage();
	Image imgOut2 = new ImageIcon("graphics/store/exit2.png").getImage();

	/**
	 * 退出游戏图标
	 */
	Image imgExit = new ImageIcon("graphics/button/EXIT.png").getImage();
	Image imgExit2 = new ImageIcon("graphics/button/EXIT0.png").getImage();

	/**
	 * 等级格图片
	 */
	Image imgLV = new ImageIcon("graphics/store/colorFill.png").getImage();

	/**
	 * 数字图片
	 */
	private Image[] imgNum = new Image[10];

	/**
	 * 游戏数据
	 */
	Data data;

	/**
	 * 退出游戏按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] EXIT = { 955, 25, 40, 41 };

	public PanelLevelUp(GameController gameController) {
		// 设置panel为透明
		this.setOpaque(false);
		this.gameController = gameController;
		this.setLayout(null);
		initButton();
		this.data = gameController.getData();
		for (int i = 0; i < imgNum.length; i++) {
			imgNum[i] = new ImageIcon("graphics/number/" + i + ".png").getImage();
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(bgLevelUp, 0, 0, null);
		String gold;
		gold = String.valueOf(data.gold);
		for (int i = gold.length() - 1; i >= 0; i--) {
			g.drawImage(imgNum[gold.charAt(i) - '0'], NUM_X - (NUM_WIDTH - 5) * (gold.length() - 1 - i), NUM_Y,
					NUM_WIDTH, NUM_HEIGHT, null);
		}
		drawLV(g);
	}

	// 初始化按钮
	private void initButton() {
		// 初始化返回主菜单按钮
		Button returnMenu = new Button(ReturnMenu[0], ReturnMenu[1], ReturnMenu[2], ReturnMenu[3], imgReturnMenu,
				imgReturnMenu2, 34, 0, gameController);
		this.add(returnMenu);
		// 初始化商店按钮
		Button store = new Button(888, 461, 89, 89, imgStore, imgStore, 996, 4, gameController);
		this.add(store);
		// 初始化退出游戏按钮
		Button exit = new Button(EXIT[0], EXIT[1], EXIT[2], EXIT[3], imgExit, imgExit2, 1, 0, gameController);
		this.add(exit);
		// 初始化加号按钮
		Button plus = new Button(621, 157, 24, 25, imgplus, imgplus2, 77, 0, gameController);
		this.add(plus);
		// 初始化升级剑士按钮
		Button fencerLevelUp = new Button(570, 232, 100, 50, imgFencerCoin, imgFencerCoin2, 78, 0, gameController);
		this.add(fencerLevelUp);
		// 初始化升级弓箭手按钮
		Button archerLevelUp = new Button(570, 328, 100, 50, imgArchcerCoin, imgArchcerCoin2, 79, 0, gameController);
		this.add(archerLevelUp);
		// 初始化升级牧师按钮
		Button priestLevelUp = new Button(570, 424, 100, 50, imgPriestCoin, imgPriestCoin2, 80, 0, gameController);
		this.add(priestLevelUp);
		// 初始化关闭升级面板按钮
		Button exitLevelUp = new Button(403, 487, 50, 50, imgOut, imgOut2, 81, 0, gameController);
		this.add(exitLevelUp);
		// 初始化音乐控制按钮
		Button music = new Button(MUSIC[0], MUSIC[1], MUSIC[2], MUSIC[3], imgMusic, imgMusic2, 929, 0, gameController);
		this.add(music);
	}

	/**
	 * 绘制等级格 TODO [有点累。。编码写得很硬。。有空再说]
	 */
	private void drawLV(Graphics g) {
		for (int i = 0; i < data.levelOfFencer; i++) {
			g.drawImage(imgLV, 428 + 25 * i, 255, null);
		}
		for (int i = 0; i < data.levelOfArcher; i++) {
			g.drawImage(imgLV, 428 + 25 * i, 352, null);
		}
		for (int i = 0; i < data.levelOfPriest; i++) {
			g.drawImage(imgLV, 428 + 25 * i, 449, null);
		}
	}

	/**
	 * 刷新界面
	 */
	public void refresh() {
		this.repaint();
	}
}
