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
 * å¼?å§çé?
 */
public class PanelStart extends JPanel implements Runnable {
	/**
	 * è¿åä¸»èåå¾æ ?
	 */
	Image imgReturnMenu = new ImageIcon("graphics/button/ReturnMenu.png").getImage();
	Image imgReturnMenu2 = new ImageIcon("graphics/button/ReturnMenu2.png").getImage();

	/**
	 * è¿åä¸»èåæé®åæ?(xåæ ï¼yåæ ï¼å®½ï¼é«)
	 */
	private final int[] ReturnMenu = { 903, 25, 40, 41 };

	/**
	 * é?åºæ¸¸æå¾æ ?
	 */
	Image imgExit = new ImageIcon("graphics/button/EXIT.png").getImage();
	Image imgExit2 = new ImageIcon("graphics/button/EXIT0.png").getImage();

	private Image imgStart1 = new ImageIcon("graphics/button/Start.png").getImage();
	private Image imgStart2 = new ImageIcon("graphics/button/Start2.png").getImage();
	private Image imgAboutUs1 = new ImageIcon("graphics/button/AboutUs.png").getImage();
	private Image imgAboutUs2 = new ImageIcon("graphics/button/AboutUs2.png").getImage();

	/**
	 * é³ä¹æ§å¶æé®å¾æ 
	 */
	Image imgMusic = new ImageIcon("graphics/button/musicc.png").getImage();
	Image imgMusic2 = new ImageIcon("graphics/button/musicc2.png").getImage();

	/**
	 * é?åºæ¸¸ææé®åæ?(xåæ ï¼yåæ ï¼å®½ï¼é«)
	 */
	private final int[] EXIT = { 955, 25, 40, 41 };

	/**
	 * å¥è·çå°äº?
	 */
	private RunningMan man;

	/**
	 * æ¸¸æèæ¯é³ä¹
	 */
	static MusicThread musicPlay = new MusicThread("sounds/backgroundMusic.wav", true);
	static boolean isplay = false;

	/**
	 * æ¸¸ææ§å¶å?
	 */
	private GameController gameController;

	/**
	 * BGMæ§å¶æé®åæ°(xåæ ï¼yåæ ï¼å®½ï¼é«)
	 */
	private final int[] MUSIC = { 851, 25, 40, 41 };

	public PanelStart(GameController gameController) {
		// è®¾ç½®panelä¸ºé?æ
		this.setOpaque(false);
		this.gameController = gameController;
		this.setLayout(null);
		initButton();
		// è®¾ç½®èæ¯é³ä¹
		setMusic();
		// åå»ºå¥è·çå°äººå¯¹è±?
		man = new RunningMan(255, 450);
		Thread t = new Thread(this);
		// å¯å¨çº¿ç¨
		t.start();
	}

	/**
	 * ç»å¶å¼?å§çé¢èæ?
	 */
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		// æ¸å±
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		Image imgStartBackground = new ImageIcon("graphics/background/bg01.png").getImage();
		g.drawImage(imgStartBackground, 0, 0, null);
		man.DrawMan(g, this);

	}

	/**
	 * åå§åæé?
	 */
	private void initButton() {
		// åå§åè¿åä¸»èåæé®
		Button returnMenu = new Button(ReturnMenu[0], ReturnMenu[1], ReturnMenu[2], ReturnMenu[3], imgReturnMenu,
				imgReturnMenu2, 3333, 0, gameController);
		this.add(returnMenu);
		Button startButton = new Button(399, 290, 235, 76, imgStart1, imgStart2, 0, 0, gameController);
		this.add(startButton);
		Button aboutusButton = new Button(393, 370, 235, 76, imgAboutUs1, imgAboutUs2, 12, 0, gameController);
		this.add(aboutusButton);
		// åå§åé??åºæ¸¸ææé?
		Button exit = new Button(EXIT[0], EXIT[1], EXIT[2], EXIT[3], imgExit, imgExit2, 1, 0, gameController);
		this.add(exit);
		// åå§åé³ä¹æ§å¶æé?
		Button music = new Button(MUSIC[0], MUSIC[1], MUSIC[2], MUSIC[3], imgMusic, imgMusic2, 929, 0, gameController);
		this.add(music);
	}

	public MusicThread getMusicPlay() {
		return musicPlay;
	}

	public void setMusic() {
		// åå»ºçº¿ç¨
		if (!isplay) {
			isplay = true;
			musicPlay.start();
		}
	}

	/**
	 * @param æ¸¸ææ§å¶å?
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
