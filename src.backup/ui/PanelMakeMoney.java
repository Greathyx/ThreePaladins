package ui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.GameController;
import role.Dice;
import ui.button.Button;
import ui.button.CasinoCoin;

/**
 * 掷骰子赚取金币界�?
 * 
 * @author Hyx
 */
public class PanelMakeMoney extends JPanel {

	/**
	 * 游戏控制�?
	 */
	private GameController gameController;

	/**
	 * 赚取金币界面
	 */
	private Image imgMakeMoney = new ImageIcon("graphics/casino/casino.png").getImage();

	/**
	 * 关闭金钱面板按钮图片
	 */
	Image imgOut = new ImageIcon("graphics/store/exit.png").getImage();
	Image imgOut2 = new ImageIcon("graphics/store/exit2.png").getImage();

	/**
	 * 商店按钮图片
	 */
	private Image imgStore = new ImageIcon("graphics/store/shop1.png").getImage();

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
	 * �?出游戏按钮参�?(x坐标，y坐标，宽，高)
	 */
	private final int[] EXIT = { 955, 25, 40, 41 };

	/**
	 * 加号按钮图片
	 */
	private Image imgPlus = new ImageIcon("graphics/casino/plus.png").getImage();
	private Image imgPlus2 = new ImageIcon("graphics/casino/plus2.png").getImage();

	/**
	 * 减号按钮图片
	 */
	private Image imgMinus = new ImageIcon("graphics/casino/minus.png").getImage();
	private Image imgMinus2 = new ImageIcon("graphics/casino/minus2.png").getImage();

	/**
	 * 押大按钮图片
	 */
	private Image imgBetBig = new ImageIcon("graphics/casino/betBig.png").getImage();
	private Image imgBetBig2 = new ImageIcon("graphics/casino/betBig2.png").getImage();

	/**
	 * 押小按钮图片
	 */
	private Image imgBetSmall = new ImageIcon("graphics/casino/betSmall.png").getImage();
	private Image imgBetSmall2 = new ImageIcon("graphics/casino/betSmall2.png").getImage();

	/**
	 * �?始赌博图�?
	 */
	private Image imgGo = new ImageIcon("graphics/casino/go.png").getImage();
	private Image imgGo2 = new ImageIcon("graphics/casino/go2.png").getImage();

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
	 * 骰子gif图片�?
	 */
	// JLabel dice = new JLabel(new ImageIcon("graphics/casino/1.gif"));
	// JLabel dice1 = new JLabel(new ImageIcon("graphics/casino/1.gif"));
	// JLabel dice2 = new JLabel(new ImageIcon("graphics/casino/2.gif"));
	// JLabel dice3 = new JLabel(new ImageIcon("graphics/casino/3.gif"));
	// JLabel dice4 = new JLabel(new ImageIcon("graphics/casino/4.gif"));
	// JLabel dice5 = new JLabel(new ImageIcon("graphics/casino/5.gif"));
	// JLabel dice6 = new JLabel(new ImageIcon("graphics/casino/6.gif"));

	/**
	 * 是否押大
	 */
	boolean isBetBig = false;

	/**
	 * 是否押小
	 */
	boolean isBetSmall = false;

	/**
	 * 是否启动掷骰子线�?
	 */
	public boolean isGambling = false;

	/**
	 * 创建骰子对象
	 */
	public Dice dice;

	public PanelMakeMoney(GameController gameController) {
		// 设置panel为�?�明
		this.setOpaque(false);
		this.gameController = gameController;
		dice = new Dice(gameController);
		this.setLayout(null);
		initButton();
		initLabel();
		Thread thread = new Thread(new RunDice());
		thread.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 清屏
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(imgMakeMoney, 0, 0, null);
		dice.DrawDice(g, this);
		// System.out.println(dice.diceID);
	}

	private void initButton() {
		// 初始化返回主菜单按钮
		Button returnMenu = new Button(ReturnMenu[0], ReturnMenu[1], ReturnMenu[2], ReturnMenu[3], imgReturnMenu,
				imgReturnMenu2, 33, 0, gameController);
		this.add(returnMenu);
		// 初始化商店按�?
		Button store = new Button(888, 461, 89, 89, imgStore, imgStore, 996, 4, gameController);
		this.add(store);
		// 初始化�??出游戏按�?
		Button exit = new Button(EXIT[0], EXIT[1], EXIT[2], EXIT[3], imgExit, imgExit2, 1, 0, gameController);
		this.add(exit);
		// 初始化关闭赚取金钱面板按�?
		Button exitMakeMoney = new Button(403, 487, 50, 50, imgOut, imgOut2, 82, 0, gameController);
		this.add(exitMakeMoney);
		// 初始化增加赌注按�?
		Button plus = new Button(597, 205, 28, 28, imgPlus, imgPlus2, 83, 0, gameController);
		this.add(plus);
		// 初始化减少赌注按�?
		Button minus = new Button(637, 205, 28, 28, imgMinus, imgMinus2, 84, 0, gameController);
		this.add(minus);
		// 初始化押大按�?
		Button betBig = new Button(356, 384, 124, 42, imgBetBig, imgBetBig2, 85, 0, gameController);
		this.add(betBig);
		// 初始化押小按�?
		Button betSmall = new Button(356, 435, 124, 42, imgBetSmall, imgBetSmall2, 86, 0, gameController);
		this.add(betSmall);
		// 初始化开赌按�?
		Button go = new Button(508, 393, 76, 77, imgGo, imgGo2, 87, 0, gameController);
		this.add(go);
		// 初始化音乐控制按�?
		Button music = new Button(MUSIC[0], MUSIC[1], MUSIC[2], MUSIC[3], imgMusic, imgMusic2, 929, 0, gameController);
		this.add(music);
	}

	private void initLabel() {
		CasinoCoin casinoCoin = new CasinoCoin(gameController);
		this.add(casinoCoin);
	}

	public void setIsBetBig(boolean isBetBig) {
		this.isBetBig = isBetBig;
	}

	public boolean getIsBetBig() {
		return this.isBetBig;
	}

	public void setIsBetSmall(boolean isBetSmall) {
		this.isBetSmall = isBetSmall;
	}

	public boolean getIsBetSmall() {
		return this.isBetSmall;
	}

	public void setIsGambling(boolean isGambling) {
		this.isGambling = isGambling;
	}

	public boolean getIsGambling() {
		return this.isGambling;
	}

	public Dice getDice() {
		return dice;
	}

	// 骰子线程
	class RunDice implements Runnable {

		@Override
		public void run() {

			while (true) {
				dice.updateDice();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}
	}// end RunDice

}
