package ui.button;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.GameController;

/**
 * 显示金钱和赌注的数字
 * 增加减少赌注的方�?
 * 判定输赢的方�?
 * 
 * @author Hyx
 *
 */
public class CasinoCoin extends JLabel {

	/**
	 * 游戏控制�?
	 */
	private GameController gameController;

	/**
	 * 数字图片
	 */
	private Image[] imgNum = new Image[10];

	/**
	 * 数字图片的宽
	 */
	private static final int NUM_WIDTH = 18;

	/**
	 * 数字图片的高
	 */
	private static final int NUM_HEIGHT = 23;

	/**
	 * 数字在当前金币条中右上角上角x坐标
	 */
	private static final int NUM_X_NOW = 548;

	/**
	 * 数字在当前金币条中右上角y坐标
	 */
	private static final int NUM_Y_NOW = 160;

	/**
	 * 赌注（数字）
	 */
	int int_wager = 50;

	/**
	 * 赌注（字符串�?
	 */
	String str_wager = "50";

	/**
	 * 数字在赌注金币条中右上角上角x坐标
	 */
	private static final int NUM_X_WAGER = 548;

	/**
	 * 数字在赌注金币条中右上角y坐标
	 */
	private static final int NUM_Y_WAGER = 208;
	
	/**
	 * 当前金钱�?(字符�?)
	 */
	String strCoin_now = "";

	public CasinoCoin(GameController gameController) {
		this.gameController = gameController;
//		this.coin_now = gameController.getData().gold;
		strCoin_now = String.valueOf(gameController.getData().gold);
		gameController.setCasinoCoin(this);
		this.setBounds(0, 0, 1024, 576);
		for (int i = 0; i < imgNum.length; i++) {
			imgNum[i] = new ImageIcon("graphics/number/" + i + ".png").getImage();
		}
	}

	public void paintComponent(Graphics g) {
		// 画当前金币条里的数字
		for (int i = strCoin_now.length() - 1; i >= 0; i--) {
			g.drawImage(imgNum[strCoin_now.charAt(i) - '0'], NUM_X_NOW - NUM_WIDTH * (strCoin_now.length() - 1 - i),
					NUM_Y_NOW, NUM_WIDTH, NUM_HEIGHT, null);
		}
		// 画赌注金币条里的数字
		for (int i = str_wager.length() - 1; i >= 0; i--) {
			g.drawImage(imgNum[str_wager.charAt(i) - '0'], NUM_X_WAGER - NUM_WIDTH * (str_wager.length() - 1 - i),
					NUM_Y_WAGER, NUM_WIDTH, NUM_HEIGHT, null);
		}
	}

	// 增加赌注方法
	public void addWager() {
		this.int_wager = int_wager + 50;
		this.str_wager = String.valueOf(this.int_wager);
		this.repaint();
	}

	// 减少赌注方法
	public void minusWager() {
		if (this.int_wager >= 50) {
			this.int_wager = int_wager - 50;
			this.str_wager = String.valueOf(this.int_wager);
			this.repaint();
		} else {
			this.int_wager = 0;
			this.str_wager = String.valueOf(this.int_wager);
			this.repaint();
		}
	}

	// 判定结果输赢方法
	public void gamblingOutcome() {

		if ((gameController.getPanelMakeMoney().getIsBetBig() && (gameController.getData().getRandomNum() > 3))
				|| (gameController.getPanelMakeMoney().getIsBetSmall()
						&& (gameController.getData().getRandomNum() <= 3))) {
			gameController.getData().gold = gameController.getData().gold + int_wager;
			this.strCoin_now = String.valueOf(this.gameController.getData().gold);
			this.repaint();
		}
		else {
			if(int_wager > gameController.getData().gold){
				gameController.getData().gold = 0;
			}else{
				gameController.getData().gold = gameController.getData().gold - int_wager;
			}
			this.strCoin_now = String.valueOf(this.gameController.getData().gold);
			this.repaint();
		}
	}
	
}
