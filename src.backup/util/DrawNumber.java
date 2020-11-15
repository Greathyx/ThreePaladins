package util;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import ui.PanelGame;

/**
 * 显示数字(伤害)
 * 
 * @author qzh
 *
 */
public class DrawNumber {
	/**
	 * 数字图片数组
	 */
	Image imgNum[] = new Image[10];

	/**
	 * 减号图片
	 */
	Image imgMinus;

	/**
	 * 绿色数字
	 */
	Image imgGreen[] = new Image[10];

	/**
	 * 加号图片
	 */
	Image imgPlus;

	/**
	 * 数字宽度
	 */
	private static final int NUM_WIDTH = 50;

	/**
	 * 数字高度
	 */
	private static final int NUM_HEIGHT = 50;

	/**
	 * 加血特效
	 */
	private Image[] imgHeal = new Image[24];

	/**
	 * 图片帧数
	 */
	private int healID;

	/**
	 * 要显示的伤害
	 */
	private String damageString = "";

	/**
	 * 要显示的治疗�?
	 */
	private String healString = "";

	/**
	 * x偏移�?
	 */
	private int bias_DamageX = 0;

	/**
	 * y偏移�?
	 */
	private int bias_DamageY = 0;

	private int bias_HealX = 0;

	private int bias_HealY = 0;

	/**
	 * 构�?�函�?
	 */
	public DrawNumber() {
		// 把图片塞进去
		imgMinus = new ImageIcon("graphics/number/-.png").getImage();
		imgPlus = new ImageIcon("graphics/number/+.png").getImage();
		for (int i = 0; i < imgNum.length; i++) {
			imgNum[i] = new ImageIcon("graphics/number/damage" + i + ".png").getImage();
		}
		for (int i = 0; i < imgGreen.length; i++) {
			imgGreen[i] = new ImageIcon("graphics/number/heal" + i + ".png").getImage();
		}
		for (int i = 1; i <= imgHeal.length; i++) {
			imgHeal[i - 1] = new ImageIcon("graphics/skill/3/loading" + i + ".png").getImage();
		}
		healID = 0;
	}

	/**
	 * 显示伤害
	 * 
	 * @param g
	 * @param xl
	 *            显示伤害的单位的逻辑坐标x
	 * @param yl
	 *            显示伤害的单位的逻辑坐标y
	 * @param ID
	 *            判断为英雄还是�?�物
	 */
	public void drawDamage(Graphics g, int xl, int yl, int ID) {
		// 计算伤害显示的位�?
		int x;
		int y;
		if (ID == 1 || ID == 2)
			x = 90 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * xl - PanelGame.GAP_WIDTH * yl - bias_DamageX + 50;
		else
			x = 90 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * xl - PanelGame.GAP_WIDTH * yl + bias_DamageX + 200;
		y = 140 + PanelGame.MAP_HEIGHT * yl - bias_DamageY + 50;
		g.drawImage(imgMinus, x - 25 * (damageString.length()), y, NUM_WIDTH, NUM_HEIGHT, null);
		for (int i = damageString.length() - 1; i >= 0; i--) {
			g.drawImage(imgNum[damageString.charAt(i) - '0'], x - 25 * (damageString.length() - 1 - i), y, NUM_WIDTH,
					NUM_HEIGHT, null);
		}
		// 上升�?
		if (bias_DamageX < 20) {
			bias_DamageX += 5;
			bias_DamageY = bias_DamageX * (60 - bias_DamageX) >> 5;
		}
	}

	public void drawHeal(Graphics g, int xl, int yl) {
		// 计算回血数字显示的位�?
		int x;
		int y;
		x = 220 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * xl - PanelGame.GAP_WIDTH * yl - bias_HealX + 50;
		y = 180 + PanelGame.MAP_HEIGHT * yl - bias_HealY + 50;
		g.drawImage(imgPlus, x - 25 * (healString.length()), y, NUM_WIDTH, NUM_HEIGHT, null);
		for (int i = healString.length() - 1; i >= 0; i--) {
			g.drawImage(imgGreen[healString.charAt(i) - '0'], x - 25 * (healString.length() - 1 - i), y, NUM_WIDTH,
					NUM_HEIGHT, null);
		}
		g.drawImage(imgHeal[healID],
				90 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * xl - PanelGame.GAP_WIDTH * yl + 25,
				130 + PanelGame.MAP_HEIGHT * yl, null);
		if (healID == 23)
			healID = 0;
		healID++;
		// 上升�?
		if (bias_HealX < 20) {
			bias_HealX += 5;
			bias_HealY = bias_HealX * (60 - bias_HealX) >> 5;
		}
	}

	public void setDamage(int damage) {
		// 将伤害转化为字符串，以便绘图
		damageString = String.valueOf(damage);
	}

	public void setHeal(int heal) {
		healString = String.valueOf(heal);
	}

	/**
	 * 偏移量清�?
	 */
	public void biasZero() {
		this.bias_DamageX = 0;
		this.bias_DamageY = 0;
	}

	public void biasZeroHeal() {
		this.bias_HealX = 0;
		this.bias_HealY = 0;
	}

	/**
	 * 是否显示完成
	 */
	public boolean showFinish() {
		return bias_DamageX >= 20;
	}

	public boolean healFinish() {
		return bias_HealX >= 20;
	}
}
