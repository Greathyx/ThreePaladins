package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.GameController;
import ui.button.Button;

/**
 * 获胜画面
 */
public class PanelWin extends JPanel implements MouseListener {

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
	 * 点击前鼠标图片和传入cursor方法中的name参数
	 */
	Image cursorBefore = new ImageIcon("graphics/cursor/cursor0.png").getImage();
	String nameBefore = "cursor/cursor0.png";

	/**
	 * 点击后鼠标图片和传入cursor方法中的name参数
	 */
	Image cursorAfter = new ImageIcon("graphics/cursor/cursor1.png").getImage();
	String nameAfter = "cursor/cursor1.png";
	private final int[] EXIT = { 955, 25, 40, 41 };

	Image imgExit = new ImageIcon("graphics/button/EXIT.png").getImage();
	Image imgExit0 = new ImageIcon("graphics/button/EXIT0.png").getImage();
	// Image imgExit = new ImageIcon("graphics/button/ChooseLV.png").getImage();
	// Image imgExit0 = new
	// ImageIcon("graphics/button/ChooseLV2.png").getImage();
	private Image imgRestart1 = new ImageIcon("graphics/button/NextLV1.png").getImage();
	private Image imgRestart2 = new ImageIcon("graphics/button/NextLV2.png").getImage();
	private Image imgChooseLV1 = new ImageIcon("graphics/button/Exit1.png").getImage();
	private Image imgChooseLV2 = new ImageIcon("graphics/button/Exit2.png").getImage();

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
	 * 游戏控制器
	 */
	private GameController gameController;

	public PanelWin(GameController gameController) {
		this.gameController = gameController;
		this.setLayout(null);
		// 设置panel为透明
		this.setOpaque(false);
		initButton();
	}

	/**
	 * 绘制开始界面背景
	 */
	@Override
	public void paintComponent(Graphics g) {

		Image imgRestartBackground = new ImageIcon("graphics/background/end.png").getImage();
		g.drawImage(imgRestartBackground, 0, 0, null);
	}

	/**
	 * 初始化按钮
	 */
	private void initButton() {
		// 初始化音乐控制按钮
		Button music = new Button(MUSIC[0], MUSIC[1], MUSIC[2], MUSIC[3], imgMusic, imgMusic2, 929, 0, gameController);
		this.add(music);
		Button restartButton = new Button(310, 367, 203, 56, imgRestart1, imgRestart2, 51, 0, gameController);
		this.add(restartButton);
		Button exitButton = new Button(514, 367, 203, 56, imgChooseLV1, imgChooseLV2, 1002, 0, gameController);
		this.add(exitButton);
		// 初始化退出游戏按钮
		Button exit = new Button(EXIT[0], EXIT[1], EXIT[2], EXIT[3], imgExit, imgExit0, 1, 0, gameController);
		this.add(exit);
		// 初始化返回主菜单按钮
		Button returnMenu = new Button(ReturnMenu[0], ReturnMenu[1], ReturnMenu[2], ReturnMenu[3], imgReturnMenu,
				imgReturnMenu2, 31, 0, gameController);
		this.add(returnMenu);
	}

	/**
	 * @param 游戏控制器
	 */
	public void setController(GameController gameController) {
		this.gameController = gameController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 设置按住鼠标的图片
		gameController.frame.cursor(cursorAfter, nameAfter);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// 设置松开鼠标的图片
		gameController.frame.cursor(cursorBefore, nameBefore);
	}

}