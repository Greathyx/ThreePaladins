package ui.button;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.GameController;
import sound.SoundsCtrl;

public class ActionPointsBar extends JLabel implements MouseListener{

	/**
	 * 行动点数条左上角x坐标
	 */
	public static final int ACTIONPOINT_X = 707;

	/**
	 * 行动点数条左上角y坐标
	 */
	public static final int ACTIONPOINT_Y = 87;

	/**
	 * 行动点数条图�?
	 */
	private Image imgActionPointsBar = new ImageIcon("graphics/action/actionPoints.png").getImage();

	/**
	 * 数字图片
	 */
	private Image[] imgNum = new Image[10];

	/**
	 * 数字在面板中右上角上角x坐标
	 */
	private static final int NUM_X = 71;

	/**
	 * 数字在面板中右上角y坐标
	 */
	private static final int NUM_Y = 8;

	/**
	 * 数字图片的宽
	 */
	private static final int NUM_WIDTH = 18;

	/**
	 * 数字图片的高
	 */
	private static final int NUM_HEIGHT = 23;

	/**
	 * 游戏控制�?
	 */
	GameController gameController;

	/**
	 * 行动点数
	 */
	private int actionPoint;

	public ActionPointsBar(GameController gameController) {

		this.addMouseListener(this);
		this.gameController = gameController;
		gameController.setActionPointsBar(this);
		
		this.setBounds(ACTIONPOINT_X, ACTIONPOINT_Y, imgActionPointsBar.getWidth(null),
				imgActionPointsBar.getHeight(null));
		for (int i = 0; i < imgNum.length; i++) {
			imgNum[i] = new ImageIcon("graphics/number/" + i + ".png").getImage();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		actionPoint = gameController.getData().actionPoint;
		g.drawImage(imgActionPointsBar, 0, 0, null);
		// 绘制剩余行动点数
		for (int i = String.valueOf(actionPoint).length() - 1; i >= 0; i--) {
			g.drawImage(imgNum[String.valueOf(actionPoint).charAt(i) - '0'],
					NUM_X - NUM_WIDTH * (String.valueOf(actionPoint).length() - 1 - i), NUM_Y, NUM_WIDTH, NUM_HEIGHT,
					null);
		}
	}

	/**
	 * 更新行动点数
	 * 
	 * @param i
	 */
	public void updateActionPoints() {
		// 获取行动点数
		actionPoint = gameController.getData().actionPoint;
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		new SoundsCtrl("sounds/光标.wav").start();
		gameController.getData().setIsEntered2(true);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gameController.getData().setIsEntered2(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
