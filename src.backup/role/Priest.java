package role;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import data.Data;
import ui.PanelGame;

public class Priest extends Hero{
	
	
	/**
	 * 大招ID
	 */
	private int skillID=0;
	
	/**
	 * 大招图片数组
	 */
	Image[] pictureSkill;
	
	
	public Priest(int ImageNumbers, String roleName, int xl, int yl, Data data) {
		super(ImageNumbers, roleName, xl, yl, data);
		HeroID=0;
		x=x+50;
		y=y+40;
		this.ID = 1;
		this.heroAttack = 5;
		this.maxHP = 30;
		this.heroHP = maxHP;
		this.groupAttack = data.setStatistics("priestAttack", data.levelOfPriest);
		this.groupHeal = data.setStatistics("priestHeal", data.levelOfPriest);
		
		//牧师的大招数�?
		pictureSkill = new Image[12];
		for (int i = 0; i < 12; i++)
			pictureSkill[i] = new ImageIcon("graphics/skill/2/"+ i + ".png").getImage();
		}
	
	public void moveImg(Graphics g, JPanel panel) {
		if(HeroID ==21){
			HeroID=19;
			deathFinish=true;
		}
		g.drawImage(pictureHero[HeroID], x, y, (ImageObserver) panel);
		//�?�?
		g.drawImage(backHP, x+46, y +8, (ImageObserver) panel);
		//�?�?
		if (heroHP >= 0)
			g.drawImage(imgHP, x + 46, y +8, imgHP.getWidth(null) * heroHP
					/ maxHP, imgHP.getHeight(null), (ImageObserver) panel);
		// 不攻击时
		if (!data.canAttack(this)) {
			if (x < 140 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH)
					* xl - PanelGame.GAP_WIDTH * yl
					&& data.canMove(this))
				HeroID++;
			else
				HeroID = 0;
			if (HeroID == 3)
				HeroID = 0;
		}
		
		if(data.canAttack(this)&&(x >= 140 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH)
				* xl - PanelGame.GAP_WIDTH * yl
				&& data.canMove(this))){
			HeroID++;
			if (HeroID == 3)
				HeroID = 0;
		}
		
		if(hasMoved&&(x >= 140 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) * xl
				- PanelGame.GAP_WIDTH * yl))
		HeroID = 0;
		
		//可以攻击�?
		if(data.canAttack(this)&&attack&&HeroID>=4){
			HeroID++;
			if(HeroID == 7){
				HeroID = 0;
				attack = false;
			}
		}
		
		
		//牧师挂掉�?
		if(heroHP<=0&&setID){
			HeroID = 8;
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
	
	@Override
	public void UpdateHero() {
		if (mFacus == true&&(x<140 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl))
			x = x + 10;
	}
	
	@Override
	public void moveXY() {
		// TODO Auto-generated method stub
		super.moveXY();
	}
	
	@Override
	public boolean moveFinish() {
		return !(x<140 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl&&data.canMove(this));
	}
	
	public void ultimateSkill(Graphics g, JPanel panel){
		if(xl==4||xl==5||data.getMap()[xl+3][yl]==9||data.getMap()[xl+2][yl]==9||data.getMap()[xl+1][yl]==9){
		g.drawImage(pictureSkill[skillID], x+50, y-150, (ImageObserver) panel);
		skillID++;
		if(skillID==11){
			skillID=0;
			skillJudge=false;
		}
	}
	}
	
	public void startNewRound(){
		super.startNewRound();
	}

}
