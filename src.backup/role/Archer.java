package role;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import data.Data;
import ui.PanelGame;


/**
 * 弓箭手类，直接继承Hero，只是改写部分方法，具体改动会注�?
 * @author mac
 *
 */

public class Archer extends Hero {
	
	//放置弓手对象的图�?
	private Image imgArcher[];
	
	//设置ArcherID
	public int archerID=0;
	
	//是否重绘弓箭手人�?
	public boolean mDraw=false;
	
	//是否重绘弓箭
	public boolean mDrawArrow=false;
	
	//是否放置弓箭手人�?
	public boolean mPlace=true;
	
	//射箭计数
	public int arrowNumber=0;
	
	//对应�?
	public int road=-1;
	
	//是否死亡
	public boolean canDead = false;
	
	
	

	public Archer(int ImageNumbers, String roleName, int x, int y, Data data) {
		super(ImageNumbers, roleName, x, y, data);
		this.imgArcher=new Image[20];
		this.road=y;
		this.ID = 2;

		this.maxHP = 40;
		this.heroHP = maxHP;
		

		this.heroAttack = data.setStatistics("archer", data.levelOfArcher);
		for (int i = 0; i < 20; i++){
			imgArcher[i]=new ImageIcon("graphics/role/Archer/"+"Archer" + i + ".png").getImage();
		}
		
		//由于图片的原因，加一条语句调整一下位�?
			this.y=this.y+60;
	}

	public void moveImg(Graphics g, JPanel panel) {
		g.drawImage(pictureHero[0], x, y + 40, (ImageObserver) panel);
		//显示伤害
				if(needDrawDamage){
					drawNumber.drawDamage(g, xl, yl, ID);
					if(drawNumber.showFinish()){
						needDrawDamage = false;
						drawNumber.biasZero();
					}
				}

	}

	public void FreshArcher(Graphics g, JPanel panel) {
		// �?�?
		g.drawImage(backHP, 60 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH)
				* xl - PanelGame.GAP_WIDTH * yl + 30, y, (ImageObserver) panel);
		// �?�?
		if (heroHP >= 0)
			g.drawImage(imgHP, 60 + (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH)
					* xl - PanelGame.GAP_WIDTH * yl + 30, y,
					imgHP.getWidth(null) * heroHP / maxHP,
					imgHP.getHeight(null), (ImageObserver) panel);
		g.drawImage(imgArcher[archerID], 60 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl, y-30,  (ImageObserver)panel);
		
		if(heroHP >= 0)
		this.archerID++;
		
		mPlace=false;
		if(archerID==10&&heroHP>0){
			archerID=0;
			mDraw=false;
			mDrawArrow=true;
			mPlace=true;
		}
		
		
		
	}
	
	public void PlaceArcher(Graphics g, JPanel panel){
		// �?�?
		g.drawImage(backHP, 60 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl + 30, y, (ImageObserver) panel);
		// �?�?
		if (heroHP >= 0)
			g.drawImage(imgHP, 60 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl +30, y, imgHP.getWidth(null) * heroHP
					/ maxHP, imgHP.getHeight(null), (ImageObserver) panel);
		if (heroHP >= 0)
		g.drawImage(imgArcher[0], 60 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl, y-30, (ImageObserver) panel);
	}
	
	@Override
	public void UpdateHero() {
		if(heroHP >= 0){
		if(mDrawArrow&&x<100 + 103*(xl+4)){
			x = x + 40;
		}else {
			mDrawArrow=false;
			
		}
		}
	}
	
	public void dead(Graphics g, JPanel panel){
		
		if(heroHP<=0&&setID)	{
			archerID = 10;
			setID = false;
		}
		
		if(heroHP<=0){
		g.drawImage(imgArcher[archerID], 60 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl, y-30,  (ImageObserver)panel);
		}
		
		if(heroHP <= 0){
			archerID++;
			if(archerID == 19){
				deathFinish = true;
			}
		}
	}
	
	/**
	 * 是否射完�?
	 */
	public boolean arrowFinish(){
		return !(x<100 + 103*(xl+4));
	}
	
	/**
	 * 得到攻击
	 */
	public int getAttack(){
		return this.heroAttack;
	}
	
	/**
	 * 重设➹坐�?
	 */
	public void refreshArrow(){
		x = 100 + (PanelGame.MAP_WIDTH+PanelGame.GAP_WIDTH)*xl - PanelGame.GAP_WIDTH*yl;
	}
}
