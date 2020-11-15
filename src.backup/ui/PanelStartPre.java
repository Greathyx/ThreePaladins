package ui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import control.GameController;
import util.DrawTower;

/**
 * 游戏�?�?始的动画
 * 
 * @author Hyx
 *
 */
public class PanelStartPre extends JPanel implements Runnable {

	/**
	 * pre图片的宽
	 */
	private int preWidth = 10;
	/**
	 * pre图片的高
	 */
	private int preHeight = 0;
	/**
	 * pre图片的左上角x坐标
	 */
	private int imgPreX = 0;
	/**
	 * pre图片左上角y坐标
	 */
	private int imgPreY = 0;

	/**
	 * 背景图片的宽
	 */
	private int bgWidth = 0;
	/**
	 * 背景图片的高
	 */
	private int bgHeight = 0;
	/**
	 * 背景图片的左上角x坐标
	 */
	private int imgBgX = 0;
	/**
	 * 背景图片左上角y坐标
	 */
	private int imgbgY = 0;
	/**
	 * 两张图片的间�?
	 */
	private int delay = 0;

	/**
	 * 图片移动速度
	 */
	private int mvSpeed = 4;

	/**
	 * 画第二张图片
	 */
	private boolean drawImg2 = false;

	/**
	 * 游戏控制�?
	 */
	GameController gameController;

	/**
	 * 游戏框架
	 */
	FrameGame frameGame;

	/**
	 * 游戏�?�?始动画的图片
	 */
	private Image picStart = new ImageIcon("graphics/background/bg01.png").getImage();

	/**
	 * �?始界面的背景�?
	 */
	private Image picStartPanel = new ImageIcon("graphics/background/bg01.png").getImage();

	public PanelStartPre(FrameGame frameGame, GameController gameController) {

		// 设置panel为�?�明
		this.setOpaque(false);
		this.gameController = gameController;
		this.frameGame = frameGame;
		this.imgPreX = (frameGame.getWidth() >> 1) - 5;
		this.imgPreY = (frameGame.getHeight() >> 1);
		this.imgBgX = (frameGame.getWidth() >> 1);
		this.imgbgY = (frameGame.getHeight() >> 1);
		new Thread(this).start();
		new Start().start();
	}

	public void paintComponent(Graphics g) {
		if (!drawImg2) {
			g.drawImage(picStart, imgPreX, imgPreY, imgPreX + preWidth, imgPreY + preHeight, imgPreX, imgPreY,
					imgPreX + preWidth, imgPreY + preHeight, null);
		}
		//g.drawImage(picStartPanel, imgBgX, imgbgY, imgBgX, bgHeight, 0, 0, bgWidth, bgHeight, null);
	}

	@Override
	public void run() {
		while (true) {
			DrawTower.sleep(10);
			if (preHeight <= frameGame.getHeight()) {
				this.preHeight += (mvSpeed << 1);
				this.imgPreY -= mvSpeed;
			}
			if (preWidth <= frameGame.getWidth() && preHeight > frameGame.getHeight()) {
				this.preWidth += (mvSpeed << 1);
				this.imgPreX -= mvSpeed;
			}
//			if (preWidth > frameGame.getWidth() && delay > 0) {
//				delay --;
//			}
			if (preWidth > frameGame.getWidth() && this.imgBgX > 0 && delay <= 0) {
				this.imgBgX -= mvSpeed;
				this.imgbgY -= mvSpeed / 4 * 3;
				this.bgWidth += (mvSpeed << 1);
				this.bgHeight += (mvSpeed / 4 * 3 << 1);
			}
			// 进入�?始菜单界�?
			if (this.imgBgX <= 0) {
				this.gameController.startMenu();
				break;
			}
			this.repaint();
		}
	}

	/**
	 *睡眠�?
	 */
	private class Start extends Thread {
		public void run() {
			while (true) {
				DrawTower.sleep(80);
			}
		}
	}

}
