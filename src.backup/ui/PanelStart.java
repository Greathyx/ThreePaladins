package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.GameController;
import role.RunningMan;
import sound.MusicThread;
import ui.button.Button;

/**
 * �?始界�?
 */
public class PanelStart extends JPanel implements Runnable {
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

	private Image imgStart1 = new ImageIcon("graphics/button/Start.png").getImage();
	private Image imgStart2 = new ImageIcon("graphics/button/Start2.png").getImage();
	private Image imgAboutUs1 = new ImageIcon("graphics/button/AboutUs.png").getImage();
	private Image imgAboutUs2 = new ImageIcon("graphics/button/AboutUs2.png").getImage();

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
	 * 奔跑的小�?
	 */
	private RunningMan man;

	/**
	 * 游戏背景音乐
	 */
	static MusicThread musicPlay = new MusicThread("sounds/backgroundMusic.wav", true);
	static boolean isplay = false;

	/**
	 * 游戏控制�?
	 */
	private GameController gameController;

	/**
	 * BGM控制按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] MUSIC = { 851, 25, 40, 41 };

	public PanelStart(GameController gameController) {
		// 设置panel为�?�明
		this.setOpaque(false);
		this.gameController = gameController;
		this.setLayout(null);
		initButton();
		// 设置背景音乐
		setMusic();
		// 创建奔跑的小人对�?
		man = new RunningMan(255, 450);
		Thread t = new Thread(this);
		// 启动线程
		t.start();
	}

	/**
	 * 绘制�?始界面背�?
	 */
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		// 清屏
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		Image imgStartBackground = new ImageIcon("graphics/background/bg01.png").getImage();
		g.drawImage(imgStartBackground, 0, 0, null);
		man.DrawMan(g, this);

	}

	/**
	 * 初始化按�?
	 */
	private void initButton() {
		// 初始化返回主菜单按钮
		Button returnMenu = new Button(ReturnMenu[0], ReturnMenu[1], ReturnMenu[2], ReturnMenu[3], imgReturnMenu,
				imgReturnMenu2, 3333, 0, gameController);
		this.add(returnMenu);
		Button startButton = new Button(399, 290, 235, 76, imgStart1, imgStart2, 0, 0, gameController);
		this.add(startButton);
		Button aboutusButton = new Button(393, 370, 235, 76, imgAboutUs1, imgAboutUs2, 12, 0, gameController);
		this.add(aboutusButton);
		// 初始化�??出游戏按�?
		Button exit = new Button(EXIT[0], EXIT[1], EXIT[2], EXIT[3], imgExit, imgExit2, 1, 0, gameController);
		this.add(exit);
		// 初始化音乐控制按�?
		Button music = new Button(MUSIC[0], MUSIC[1], MUSIC[2], MUSIC[3], imgMusic, imgMusic2, 929, 0, gameController);
		this.add(music);
	}

	public MusicThread getMusicPlay() {
		return musicPlay;
	}

	public void setMusic() {
		// 创建线程
		if (!isplay) {
			isplay = true;
			musicPlay.start();
		}
	}

	/**
	 * @param 游戏控制�?
	 */
	public void setController(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void run() {
		while (true) {
			man.UpdateMan();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

}
