package role;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.GameController;

import ui.PanelGame;
import util.DrawNumber;
import data.Data;

/**
 * 英雄�?
 * @author user
 *
 */
public class Hero {
	/**
	 * �?槽背�?
	 */
	Image backHP = new ImageIcon("graphics/action/LifeCao.png").getImage();
	
	/**
	 * �?�?
	 */
	Image imgHP = new ImageIcon("graphics/action/LifeTiao.png").getImage();
	
	
	/**
	 * 英雄逻辑坐标
	 */
	public int xl;
	public int yl;
	/**
	 * 英雄左上角x坐标
	 */
	public int x = 0;
	/**
	 *  英雄左上角y坐标
	 */
	public int y = 0;
	/**
	 *  Hero类的图片数量
	 */
	public int ImageNums;
	/**
	 *  Hero类的图片数组
	 */
	public Image pictureHero[] = null;
	
	/**
	 *  当前帧的ID
	 */
	public int HeroID;

	/**
	 *  是否更新绘制英雄
	 */
	public boolean mFacus = true;
	
	/**
	 * 英雄�?�?
	 */
	public int heroHP;
	
	/**
	 * 英雄�?量上�?
	 */
	public int maxHP;
	
	/**
	 * 英雄攻击 TODO
	 */
	public int heroAttack;
	
	/**
	 * 群攻攻击
	 */
	public int groupAttack;
	
	/**
	 * 群奶
	 */
	public int groupHeal;
	
	/**
	 * 英雄攻击范围 TODO
	 */
	public int heroRange = 1;
	
	/**
	 * 游戏数据
	 */
	public Data data;
	
	/**
	 * 英雄序号,1为英雄，9为�?�物
	 */
	public int ID = 1;
	
	/**
	 * 是否攻击
	 */
	public boolean attack=true;
	
	/**
	 * 是否还在场上存在，血条空了就消失
	 */
	public boolean existence=true;
	
	/**
	 * 是否播放完死亡动�?
	 */
	public boolean deathFinish=false;
	
	/**
	 * 设置�?个布尔变量用于死亡前设置图片ID
	 */
	public boolean setID=true;
	
	/**
	 * 设置�?个判断是否为怪物的id
	 */
	public boolean isEnemy = false;
	
	/**
	 * 击杀怪物后获得的金钱
	 */
	public int value;
	
	/**
	 * 到底后扣的血�?
	 */
	public int damage;
	
	/**
	 * 是否�?要伤害显�?
	 */
	public boolean needDrawDamage;
	
	/**
	 * 是否�?要回复特效显�?
	 */
	public boolean needDrawHeal;
	
	/**
	 * 绘制伤害数字工具
	 */
	protected DrawNumber drawNumber;
	
	/**
	 * 武士对应�?
	 */
	public int roadID;
	
	/**
	 * 牧师的种类，1代表加血牧师�?9代表群攻牧师
	 */
	public int kind = 0;
	
	/**
	 * 是否释放牧师大招
	 */
	public boolean skillJudge=false;
	
	/**
	 * 是否�?始新回合
	 */
	public boolean newRound = false;
	
	/**
	 * 若移动过就不攻击
	 */
	public boolean hasMoved = false;
	
	/**
	 * 构�?�函�?
	 */
	public Hero(int ImageNumbers, String roleName, int xl, int yl, Data data) {
		drawNumber = new DrawNumber();
		needDrawDamage = false;
		this.ImageNums = ImageNumbers;
		pictureHero = new Image[ImageNums];
		// 将图片装入数组中
		for (int i = 0; i < ImageNumbers; i++)
			pictureHero[i] = new ImageIcon("graphics/role/" + roleName + "/" + roleName + i + ".png").getImage();
		this.xl = xl;
		this.yl = yl;
		this.data = data;
		this.HeroID = 2;
		this.roadID = yl;
		this.heroAttack = 15;
		this.maxHP = data.setStatistics("fencer", data.levelOfFencer);
		this.heroHP = maxHP;
		
		
		x = 90 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*(xl-1) - PanelGame.GAP_WIDTH*yl;
		y = 140 + PanelGame.MAP_HEIGHT*yl;

		
	}

	/**
	 * 英雄移动
	 * @param g
	 * 画笔
	 * @param panel
	 * 游戏界面
	 */
	public void moveImg(Graphics g, JPanel panel) {
		if(needDrawHeal){
			drawNumber.drawHeal(g, xl, yl);
			if(drawNumber.healFinish()){
				needDrawHeal = false;
				drawNumber.biasZeroHeal();
			}
		}
		if(HeroID ==32){
			HeroID=30;
			deathFinish=true;
		}
		g.drawImage(pictureHero[HeroID], x, y, (ImageObserver) panel);
		//�?�?
		g.drawImage(backHP, x+90, y+67, (ImageObserver) panel);
		//�?�?
		if(heroHP>=0)
			g.drawImage(imgHP, x+90, y+67, imgHP.getWidth(null)*heroHP/maxHP, imgHP.getHeight(null),(ImageObserver) panel);
		
		//不攻击时
		if (!data.canAttack(this)&&HeroID != 19) {
			
			if (x < 90 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * xl
					- PanelGame.GAP_WIDTH * yl)
				HeroID++;
			else{
				HeroID = 2;
				newRound = false;
			}
			if (HeroID == 9)
				HeroID = 2;
		}
		
		if(data.canAttack(this)&&(x < 90 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * xl
					- PanelGame.GAP_WIDTH * yl)){
			HeroID++;
			if (HeroID == 9)
				HeroID = 2;
		}
		if(hasMoved&&(x >= 90 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * xl
					- PanelGame.GAP_WIDTH * yl))
			HeroID = 2;
		//可以攻击�?
		if(data.canAttack(this)&&attack&&newRound&&HeroID>=10){
			HeroID++;
			if(HeroID == 18){
				HeroID = 2;
				attack = false;
				newRound = false;
			}
		}
		
		
		
		
		//剑士挂掉�?
		if(heroHP<=0&&setID){
			HeroID = 19;
			setID = false;
		}
				
				
		if(heroHP<=0){
			HeroID++;
			if(HeroID == ImageNums){
				HeroID = 2;
				deathFinish = true;
			}
		}
		
		
		if(needDrawDamage){
			drawNumber.drawDamage(g, xl, yl, ID);
			if(drawNumber.showFinish()){
				needDrawDamage = false;
				drawNumber.biasZero();
			}
		}
		
		
	}
	
	/**
	 * 移动后坐标改�?
	 */
	public void moveXY(){
		if(data.canMove(this)){
			this.xl++;
			this.hasMoved = true;
		}
	}

	public void UpdateHero() {
		if (mFacus == true&&(x<90 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl))
			x = x + 10;
	}
	
	
	
	public int getXL(){
		return xl;
	}
	
	/**
	 * 是否完成移动
	 */
	public boolean moveFinish(){
		return !(x<90 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl&&data.canMove(this));
	}
	
	public DrawNumber getDrawNumber(){
		return this.drawNumber;
	}
	public void ultimateSkill(Graphics g, JPanel panel){
		
	}
	/**
	 * �?始新回合
	 */
	public void startNewRound(){
		this.newRound = true;
		this.hasMoved = false;
	}

}
