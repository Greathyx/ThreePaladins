package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.GameController;
import ui.button.Button;

/**
 * 重新开始画面
 */
public class PanelRestart  extends JPanel{
	private Image imgRestart1 = new ImageIcon("graphics/button/Restart1.png").getImage();
	private Image imgRestart2 = new ImageIcon("graphics/button/Restart2.png").getImage();
	private Image imgExit1 = new ImageIcon("graphics/button/Exit1.png").getImage();
	private Image imgExit2 = new ImageIcon("graphics/button/Exit2.png").getImage();
	private Image imgMusic = new ImageIcon("graphics/button/musicc.png").getImage();
	private Image imgMenu = new ImageIcon("graphics/button/menuu.png").getImage();
	/**
	 * 游戏控制器
	 */
	private GameController gameController;
	
	public PanelRestart(GameController gameController) {
		this.gameController = gameController;
		this.setLayout(null);
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
		Button restartButton = new Button(310, 367, 203, 56, imgRestart1, imgRestart2, 927,gameController);
		this.add(restartButton);
		Button exitButton = new Button(514, 367, 203, 56, imgExit1, imgExit2, 1,gameController);
		this.add(exitButton);
		Button musicButton = new Button(305, 254, 51, 53, imgMusic, imgMusic, 927,gameController);//TODO
		this.add(musicButton);
		Button menuButton = new Button(665, 254, 51, 53, imgMenu, imgMenu, 928,gameController);
		this.add(menuButton);
	}
	
	/**
	 * @param 游戏控制器
	 */
	public void setController(GameController gameController){
		this.gameController = gameController;
	}

}
