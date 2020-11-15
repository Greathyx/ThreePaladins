package role;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.GameController;

public class Dice {

	/**
	 * 游戏控制�?
	 */
	GameController gameController;
	
	/**
	 * 骰子左上角x坐标
	 */
	private  int Dice_X = 468;

	/**
	 * 骰子左上角y坐标
	 */
	private int Dice_Y = 268;

	/**
	 * 当前帧的ID
	 */
	public int diceID = 0;

	/**
	 * Dice类的图片数组
	 */
	public Image picDice[] = null;
	
	/**
	 * 是否继续刷新
	 */
	public boolean ifFresh=false;

	public Dice(GameController gameController) {
		setController(gameController);
		picDice = new Image[21];
		for (int i = 0; i < 20; i++) {
			picDice[i] = new ImageIcon("graphics/casino/" + i + ".png").getImage();
		}
	}

	//随机加入�?后一张图�?
	public void loadOutcome() {
		picDice[20] = new ImageIcon("graphics/casino/0" + gameController.getData().getRandomNum() +".png").getImage();
	}
	
	public void DrawDice(Graphics g, JPanel panel) {
		
		g.drawImage(picDice[diceID], Dice_X, Dice_Y, (ImageObserver)panel);
		if(ifFresh){
			diceID ++;
		if(diceID == 20){
			//diceID = 0;
			ifFresh= false;
			gameController.casinoCoin.gamblingOutcome();
		}
		}
	}
	
	public void updateDice() {
		Dice_X = Dice_X;
		Dice_Y = Dice_Y;
	}
	
	public void setController(GameController gameController) {
		this.gameController = gameController;
	}
}
