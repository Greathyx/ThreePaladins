package role;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import data.Data;
import ui.PanelGame;

public class Enemy extends Hero {

	/**
	 * 移动�?�?图片
	 */
	public int moveID;
	
	/**
	 * 攻击时刷到的图片
	 */
	public int attackID;
	
	/**
	 * 临时加两个调整企鹅位置的变量
	 */
	public int p=0;
	public int q=0;
	public int z=0;
	
	/**
	 * 到底后扣的血�?
	 */
	public int damage;

	/**
	 * 击杀怪物后获得的金钱
	 */
	public int value;
	
	/**
	 * 判断回合是否�?始，否则不能攻击
	 */
	private boolean newRound;

	
	public Enemy(int ImageNumbers, String roleName, int xl, int yl, Data data,int moveID,int attackID) {
		super(ImageNumbers, roleName, xl, yl, data);
		newRound = false;
		this.HeroID=1;
		this.moveID=moveID;
		this.attackID=attackID;
		this.ID = 9;
		//this.damage = 3;
		this.isEnemy=true;
		x=x+130;
		y=y+35;
		//小企鹅比较草蛋，要调�?下图片位�?
		//分类，对每个怪物进行微调
		if(roleName.equals("Penguin")){
			this.damage = 1;
			x=x+50;
			y=y+20;
			p=+50;
			q=20;
			z = 5;
			this.maxHP = data.setEnemies("penguin", 0, data.levelNow);
			this.heroHP = maxHP;
			value = data.setEnemies("penguin", 2, data.levelNow);
			this.heroAttack=data.setEnemies("penguin", 1, data.levelNow);
		}
		else if(roleName.equals("Werewolf")){
			this.damage = 3;
			q = -20;
			x = x - 40;
			p = -40;
			z = 38;
			this.maxHP = data.setEnemies("werewolf", 0, data.levelNow);
			this.heroHP = maxHP;
			value = data.setEnemies("werewolf", 2, data.levelNow);
			this.heroAttack=data.setEnemies("werewolf", 1, data.levelNow);
		}
		else if(roleName.equals("WhiteWolf")){
			this.damage = 2;
			q = 7;
			z = 23; 
			this.maxHP = data.setEnemies("whitewolf", 0, data.levelNow);
			this.heroHP = maxHP;
			value = data.setEnemies("whitewolf", 2, data.levelNow);
			this.heroAttack=data.setEnemies("whitewolf", 1, data.levelNow);
		}
		
		
		
	}
	
	public void moveImg(Graphics g, JPanel panel) {
		g.drawImage(pictureHero[HeroID], x, y, (ImageObserver) panel);
		//画血�?
		g.drawImage(backHP, x+90-q*2, y+q + z, (ImageObserver) panel);
		if(heroHP>=0)
			g.drawImage(imgHP, x+90-q*2, y+q+z, imgHP.getWidth(null)*heroHP/maxHP, imgHP.getHeight(null),(ImageObserver) panel);
		
		// 不攻击时
		if (!data.canAttack(this)&&heroHP>0) {
			if (x > 230 + p + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH)
					* (xl - 1) - PanelGame.GAP_WIDTH * yl)
				HeroID++;
			else{
				HeroID = 1;
				newRound = false;
			}
			if (HeroID == moveID)
				HeroID = 1;
		}
		if(data.canAttack(this)&&x > 230 + p + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH)
					* (xl - 1) - PanelGame.GAP_WIDTH * yl){
			HeroID++;
			if (HeroID == moveID)
				HeroID = 1;
		}

		if(heroHP>0&&hasMoved&&(x <= 230 + p + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * (xl-1)
				- PanelGame.GAP_WIDTH * yl))
		HeroID = 1;
		// 可以攻击�?
		if (data.canAttack(this) && attack&&heroHP>0&&newRound&&HeroID>=moveID) {
			HeroID++;
			if (HeroID == attackID) {
				HeroID = 1;
				attack = false;
				newRound = false;
			}
		}
		
		//怪物挂掉�?
		if(heroHP<=0&&setID){
			HeroID = attackID+1;
			setID = false;
		}
		
		
		if(heroHP<=0){
			HeroID++;
			if(HeroID == ImageNums){
				HeroID = 1;
				deathFinish = true;
			}
		}
		//显示伤害
		if(needDrawDamage){
			drawNumber.drawDamage(g, xl, yl, ID);
			if(drawNumber.showFinish()){
				needDrawDamage = false;
				drawNumber.biasZero();
			}
		}
		
	}
	
	public void moveXY(){
		if(data.canMove(this)){
			this.xl--;
			this.hasMoved = true;
		}
	}
	
	public void UpdateHero() {
		if (mFacus == true&&(x>230 + p + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*(xl-1) - PanelGame.GAP_WIDTH*yl))
			x = x - 10;
		if(!attack&&this.heroHP>0&&x <= 230 + p + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH))
			HeroID = 1;
	}
	
	/**
	 * 是否完成移动
	 */
	public boolean moveFinish(){
		return !(x>230 + p + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*(xl-1) - PanelGame.GAP_WIDTH*yl);
	}

	/**
	 * �?始新回合
	 */
	public void startNewRound(){
		this.newRound = true;
		this.hasMoved = false;
	}
}
