package ui.button;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.GameController;
import sound.SoundsCtrl;

/**
 * 蓄能功能
 * @author Hyx
 *
 */
public class SaveEnergy extends JLabel implements MouseListener{
	
	/**
	 * 能量条左上角x坐标
	 */
	public static final int Energy_X = 660;
	
	/**
	 *  能量条左上角y坐标
	 */
	public static final int Energy_Y = 23;
	
	/**
	 *  能量条类的图片帧数
	 */
//	private int ImageNums = 16;
	
	/**
	 *  能量条的图片数组
	 */
	private Image pictureEnergy[] =  new Image[16];
	
	/**
	 *  当前帧的ID
	 */
	private int EnergyID;
	
	/**
	 *  是否更新绘制能量条
	 */
	boolean mFacus = true;
	
	/**
	 * 游戏控制器
	 */
	GameController gameController;
		
	/**
	 * 构造函数
	 */
	public SaveEnergy(GameController gameController) {
		this.addMouseListener(this);
		this.gameController = gameController;
		gameController.setSaveEnergy(this);
		this.EnergyID = 7;
		
		//将能量条图片装入数组
		for (int i = 0; i < 16; i++)
			pictureEnergy[i] = new ImageIcon("graphics/action/energy" + i + ".png").getImage();
		this.setBounds(Energy_X, Energy_Y, pictureEnergy[0].getWidth(null), pictureEnergy[0].getHeight(null));
	}
	
	public boolean getmFacus() {
		return mFacus;
	}
	
	public void setmFacus(boolean mFacus) {
		this.mFacus = mFacus;
	}
	
	/**
	 * 画能量进度条
	 * @param g
	 * 画笔
	 * @param panel
	 * 游戏界面
	 */
	@Override
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		if(mFacus){
//			this.repaint();
//			mFacus = false;
//		}
		g.drawImage(pictureEnergy[EnergyID], 0, 0, null);
		
	}
		
	/**
	 * 更新能量进度条
	 * @param i
	 * 当前能量点数
	 */
	public void UpdateEnergy(int i) {
		this.EnergyID = i;
//		System.out.println(EnergyID);
//		this.setVisible(true);
		mFacus=true;
		this.setVisible(true);
		this.repaint();
//		new Thread(new PaintThread()).start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		new SoundsCtrl("sounds/光标.wav").start();
		gameController.getData().setIsEntered3(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		gameController.getData().setIsEntered3(false);
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
