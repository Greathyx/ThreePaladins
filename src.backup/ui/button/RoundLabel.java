package ui.button;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.xml.bind.DatatypeConverter;

import control.GameController;

/**
 * 回合数面�?
 * @author user
 *
 */
public class RoundLabel extends JLabel{
	/**
	 * x坐标
	 */
	private final int x = 520;
	
	/**
	 * y坐标
	 */
	private final int y = -20;
	
//	/**
//	 * 面板图片
//	 */
//	private final Image IMG_PANEL = new ImageIcon("graphics/action/actionPanel.png").getImage();
	
//	/**
//	 * 回合数面板图�?
//	 */
//	Image[] imgNum = new Image[10];
	
	/**
	 * 游戏控制�?
	 */
	private GameController gameController;
	
//	/**
//	 * 当前回合�?
//	 */
//	private int round;
	
//	/**
//	 * 回合数字符串
//	 */
//	private String roundString = "0";
	
	/**
	 * 数字在面板中右上角上角x坐标
	 */
	private static final int NUM_X = 219;
	
	/**
	 * 数字在面板中右上角y坐标
	 */
	private static final int NUM_Y = 52;
	
	/**
	 * 数字图片的宽
	 */
	private static final int NUM_WIDTH = 20;
	
	/**
	 * 数字图片的高
	 */
	private static final int NUM_HEIGHT = 25;
	
	/**
	 * 每行间距
	 */
	private static final int GAP = 58;
	
	/**
	 * 行动点数
	 */
	private int actionPoint;
	
	/**
	 * 剩余波数
	 */
	private int waveLeft;
	
	/**
	 * 回合数面板构造函�?
	 */
	public RoundLabel(GameController gameController){
		
		this.gameController = gameController;
		gameController.setRoundLabel(this);
		actionPoint = gameController.getData().actionPoint;
		waveLeft = gameController.getData().waveLeft();
		//this.setBounds(x, y, IMG_PANEL.getWidth(null), IMG_PANEL.getHeight(null));
		//this.round = 0;
//		for (int i = 0; i < imgNum.length; i++) {
//			imgNum[i] = new ImageIcon("graphics/number/" + i + "" + i +".png")
//					.getImage();
//		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		//获取行动点数
		actionPoint = gameController.getData().actionPoint;
		//获取剩余波数
		waveLeft = gameController.getData().waveLeft();
//		//绘制回合数面板的文字
//		g.drawImage(IMG_PANEL, 0, 0, null);
//		//绘制回合数面板上的数�?
//		for(int i=roundString.length()-1;i>=0;i--){
//			g.drawImage(imgNum[roundString.charAt(i)-'0'], NUM_X-NUM_WIDTH*(roundString.length()-1-i), NUM_Y, NUM_WIDTH, NUM_HEIGHT, null);
//		}
//		绘制剩余行动点数
//		for(int i=String.valueOf(actionPoint).length()-1;i>=0;i--){
//			g.drawImage(imgNum[String.valueOf(actionPoint).charAt(i)-'0'], NUM_X-NUM_WIDTH*(String.valueOf(actionPoint).length()-1-i), NUM_Y + GAP, NUM_WIDTH, NUM_HEIGHT, null);
//		}
//		//绘制当前剩余波数
//		for(int i=String.valueOf(waveLeft).length()-1;i>=0;i--){
//			g.drawImage(imgNum[String.valueOf(waveLeft).charAt(i)-'0'], NUM_X-NUM_WIDTH*(String.valueOf(waveLeft).length()-1-i), NUM_Y + (GAP<<1), NUM_WIDTH, NUM_HEIGHT, null);
//		}
	}
	
//	/**
//	 * 回合结束
//	 */
//	public void roundEnd (){
//		this.round++;
//		roundString = String.valueOf(round);
//		this.repaint();
//	}
//	
}
