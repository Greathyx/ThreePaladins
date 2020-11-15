package ui.button;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.GameController;
import sound.SoundsCtrl;

public class MonsterBar extends JLabel implements MouseListener{
	/**
	 * 剩余怪物波数条左上角x坐标
	 */
	public static final int MONSTER_X = 852;

	/**
	 * 剩余怪物波数条左上角y坐标
	 */
	public static final int MONSTER_Y = 76;

	/**
	 * 行动点数条图片
	 */
	private Image imgMonster = new ImageIcon("graphics/action/leftMonsters.png").getImage();

	/**
	 * 数字图片
	 */
	private Image[] imgNum = new Image[10];

	/**
	 * 数字在面板中右上角上角x坐标
	 */
	private static final int NUM_X = 85;

	/**
	 * 数字在面板中右上角y坐标
	 */
	private static final int NUM_Y = 20;

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
	 * 剩余波数
	 */
	private int waveLeft;
	
	public MonsterBar(GameController gameController){
		this.addMouseListener(this);
		this.gameController = gameController;
		gameController.setMonsterBar(this);
		this.setBounds(MONSTER_X, MONSTER_Y, imgMonster.getWidth(null),
				imgMonster.getHeight(null));
		for (int i = 0; i < imgNum.length; i++) {
			imgNum[i] = new ImageIcon("graphics/number/" + i + ".png").getImage();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imgMonster, 0, 0, null);
		waveLeft = gameController.getData().waveLeft();
		//绘制当前剩余波数
		for(int i=String.valueOf(waveLeft).length()-1;i>=0;i--){
			g.drawImage(imgNum[String.valueOf(waveLeft).charAt(i)-'0'], NUM_X-NUM_WIDTH*(String.valueOf(waveLeft).length()-1-i), NUM_Y, NUM_WIDTH, NUM_HEIGHT, null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		new SoundsCtrl("sounds/光标.wav").start();
		gameController.getData().setIsEntered1(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gameController.getData().setIsEntered1(false);
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
