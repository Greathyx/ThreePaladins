package ui.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.GameController;

/**
 * 金币条
 * @author thinkpad
 *
 */
public class CoinBar extends JLabel{

	/**
	 * 金币条图片
	 */
	private Image imgCoin = new ImageIcon("graphics/action/coin2.png").getImage();
	
	/**
	 * 数字图片
	 */
	private Image[] imgNum = new Image[10];
	
	/**
	 * 金币条左上角x坐标
	 */
	public static final int COIN_X = 30;

	/**
	 * 金币条左上角y坐标
	 */
	public static final int COIN_Y = 518;
	
	/**
	 * 数字在面板中右上角上角x坐标
	 */
	private static final int NUM_X = 138;
	
	/**
	 * 数字在面板中右上角y坐标
	 */
	private static final int NUM_Y = 15;
	
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
	GameController gameController;
	
	/**
	 * 当前金钱（数字）
	 */
	int gold_num;
	/**
	 * 当前金钱数(字符串)
	 */
	String gold;
	
//	/**
//	 * 字体文件
//	 */
//	String pathString = FontLoader.class.getResource("fonts/FZMWFont.ttf").getFile();
//	File fontfile = new File("fonts/FZMWFont.ttf");
	
	/**
	 * 构造函数
	 * @param gameController
	 * 游戏控制器
	 */
	public CoinBar(GameController  gameController){
		this.gameController = gameController;
		gameController.setCoinBar(this);
		this.gold_num = gameController.getData().gold;
		this.gold = String.valueOf(gold_num);
		this.setBounds(COIN_X, COIN_Y, imgCoin.getWidth(null), imgCoin.getHeight(null));
		for (int i = 0; i < imgNum.length; i++) {
			imgNum[i] = new ImageIcon("graphics/number/" + i + ".png")
					.getImage();
		}
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(imgCoin, 0, 0, null);
		for(int i=gold.length()-1;i>=0;i--){
			g.drawImage(imgNum[gold.charAt(i)-'0'], NUM_X-NUM_WIDTH*(gold.length()-i - 1), NUM_Y , NUM_WIDTH, NUM_HEIGHT, null);
		}
		
	}
	
	/**
	 * 更新金钱
	 * @param i
	 * 得到金钱
	 */
	public void addGold(int i){
		gold_num+=i;
		this.gold = String.valueOf(gold_num);
	}
	
	public void setCoin(int coin) {
		this.gold = String.valueOf(coin);
	}

}
