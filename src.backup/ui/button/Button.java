package ui.button;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import sound.MusicThread;
import sound.SoundsCtrl;
import ui.PanelChooseLevel;
import ui.PanelGame;
import ui.PanelStart;
import control.GameController;
import data.Data;
import role.Archer;
import role.Hero;
import role.Priest;

/**
 * 按钮�?
 */
public class Button extends JLabel implements MouseListener {

	/**
	 * 鼠标点击前图�?
	 */
	Image img1;

	/**
	 * 鼠标点击后图�?
	 */
	Image img2;

	/**
	 * 游戏控制�?
	 */
	private GameController gameController;

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

	/**
	 * 不能攻击的图�?
	 */
	private Image imgAttackCannot = new ImageIcon("graphics/action/attackCannot.png").getImage();
	
	/**
	 * lv图片
	 */
	private Image[] imgLv = new Image[6];
	
	/**
	 * 按钮按下后执行的方法 TODO 0-�?始游�? 1-�?出游�? 2-回合结束 3-返回主菜�? 4-重新�?�? 20-蓄能
	 */
	int method;

	/**
	 * 按钮移动后执行的方法
	 */
	int methodEnter;

	/**
	 * 鼠标进入
	 */
	boolean isIn = false;

	/**
	 * 鼠标点击
	 */
	boolean isClicked = false;

	/**
	 * 按钮宽度
	 */
	private int width;

	/**
	 * 按钮高度
	 */
	private int height;

	/**
	 * 点击音乐按钮的次�?
	 */
	int musicClick = 0;

	/**
	 * 为音乐按钮创建音乐对�?
	 */
	MusicThread musicPlay;
	
	/**
	 * 回合在等�?9后结�?
	 */
	public static int end = 0;

	/**
	 * 按钮构�?�函�?
	 * 
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @param img1
	 *            鼠标点击前图�?
	 * @param img2
	 *            鼠标点击后图�?
	 * @param method
	 *            按钮按下后执行的方法
	 * @param methodEnter
	 *            进入后调用的方法
	 * @param gameController
	 *            游戏控制�?
	 */

	public Button(int x, int y, int width, int height, Image img1, Image img2, int method, int methodEnter,
			GameController gameController) {
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
		this.method = method;
		this.methodEnter = methodEnter;
		this.gameController = gameController;
		this.img1 = img1;
		this.img2 = img2;
		this.width = width;
		this.height = height;
		for(int i = 0;i<6;i++){
			imgLv[i] = new ImageIcon("graphics/button/HeroLv/"+ i + ".png").getImage();
		}
	}


	@Override
	public void paintComponent(Graphics g) {

		// if (isIn) {
		// g.drawImage(img1, 0, 0, width, height, null);
		// } else {
		// g.drawImage(img1, width >> 4, height >> 4, width - (width >> 3),
		// height - (height >> 3), null);
		// }
		if(method==2&&this.end<9){
			g.drawImage(imgAttackCannot, 0, 0, null);
		}

		else if (isClicked) {
			g.drawImage(img2, 0, 0, getWidth(), getHeight(), null);
		} else {
			g.drawImage(img1, 0, 0, getWidth(), getHeight(), null);
		}

		if (isIn && this.methodEnter == 4) {
			Image imgStore2 = new ImageIcon("graphics/store/shop2.png").getImage();
			g.drawImage(imgStore2, 0, 0, 89, 89, null);
		}
		
		if (isIn && this.methodEnter == 5&&this.end==9) {
			Image imgAttack = new ImageIcon("graphics/action/Attackk.png").getImage();
			g.drawImage(imgAttack, 0, 0, null);
		}
		if (isIn && this.methodEnter == 6) {
			Image imgWInd = new ImageIcon("graphics/action/wind2.png").getImage();
			g.drawImage(imgWInd, 0, 0, null);
		}
		
		if(method==5){
			g.drawImage(imgLv[gameController.getData().levelOfFencer], 75, 75, null);
		}
		if(method==6){
			g.drawImage(imgLv[gameController.getData().levelOfArcher], 75, 75, null);
		}
		if(method==7){
			g.drawImage(imgLv[gameController.getData().levelOfPriest], 75, 75, null);
		}
	}

	// 创建音乐对象
	private void makeMusic() {
		musicPlay = new MusicThread("sounds/backgroundMusic.wav", true);
	}

	private MusicThread getMusic() {
		return musicPlay;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		// �?始游�?
		if (this.method == 0) {
			gameController.startGame();
		}
		// �?出游�? TODO
		if (this.method == 1) {
			gameController.exit();
		}
		// 回合结束
		if (this.method == 2) {
			if (end==9) {
				this.end = 0;
				// 更新地图数组，以便攻�?
				gameController.updateMap();
				gameController.endRound();
				// 刷�??(偶数回合才会执行，即每两次刷�?次）
				if (gameController.getData().round % 2 != 0
						&& !gameController.getData().enemyFinish()
						&& gameController.canFreshEnemy()) {
					gameController.freshEnemy();
					// 刷�?�后波数�?1
					gameController.getData().waveFresh();
				}
				// 剑士攻击
				gameController.heroAttack();
				// gameController.hasMoved();
				// 英雄移动
				gameController.heroMove();

				gameController.updateMap();
				// 怪物移动
				gameController.moveEnemy();
				// 更新地图数组，以便攻�?
				// gameController.updateMap();

				gameController.updateMap();

				// 全屏大招
//				gameController.Bigskill();
			}


		}

		// panelGame返回主菜�?
		if (this.method == 3) {
			gameController.restart();
			gameController.returnMenu();
		}
		// panelWin返回主菜�?
		if (this.method == 31) {
			gameController.restart();
			gameController.returnMenu2();
		}
		// panelLose返回主菜�?
		if (this.method == 32) {
			gameController.restart();
			gameController.returnMenu3();
		}
		// panelMakeMoney返回主菜�?
		if (this.method == 33) {
			gameController.restart();
			gameController.returnMenu4();
		}
		// panelLevelUp返回主菜�?
		if (this.method == 34) {
			gameController.restart();
			gameController.returnMenu5();
		}
		// panelConfirm返回主菜�?
		if (this.method == 35) {
			gameController.restart();
			gameController.returnMenu6();
		}
		// panelChooseLevel返回主菜�?
		if (this.method == 36) {
			gameController.restart();
			gameController.returnMenu7();
		}
		// 重新�?�?
		if (this.method == 4) {
			gameController.restart();
		}

		// 判断点击卡牌的类�?
		// 剑士
		if (this.method == 5) {
			MapLabel.type = 1;
			gameController.getData().setIsChoosedFencer(true);
			gameController.getData().setIsChoosedAcher(false);
			gameController.getData().setIsChoosedPriest(false);
			gameController.refresh();
		}
		// 弓箭�?
		if (this.method == 6) {
			MapLabel.type = 2;
			gameController.getData().setIsChoosedAcher(true);
			gameController.getData().setIsChoosedFencer(false);
			gameController.getData().setIsChoosedPriest(false);
			gameController.refresh();
		}
		// 牧师
		if (this.method == 7) {
			MapLabel.type = 3;
//			gameController.getData().setIsChoosedPriest(true);
//			gameController.getData().setIsChoosedFencer(false);
//			gameController.getData().setIsChoosedAcher(false);
			gameController.refresh();
			PanelGame.choosePriest = true;
		}

		// 点击AboutUs进入的画�?
		if (this.method == 12) {
			gameController.teamIntroduce();
		}
		// 点击Return返回主菜�?
		if (this.method == 13) {
			gameController.returnStart();
		}

		// 蓄能按钮的方�?
		if (this.method == 20) {
			gameController.saveEnergy(1);
		}

		// 升级界面中加号按钮的方法
		if (this.method == 77) {
			gameController.makeMoney();
		}

		// 升级界面中升级剑士按钮的方法
		if (this.method == 78) {
			gameController.levelUp("fencer");
		}

		// 升级界面中升级弓箭手按钮的方�?
		if (this.method == 79) {
			gameController.levelUp("archer");
		}

		// 升级界面中升级牧师按钮的方法
		if (this.method == 80) {
			gameController.levelUp("priest");
		}

		// 升级界面中�??出升级界面按钮的方法
		if (this.method == 81) {
			gameController.exitLevelUp();
			gameController.updateMoney();
		}

		// 赚取金币界面中�??出赚取金币界面按钮的方法
		if (this.method == 82) {
			gameController.exitMakeMoney();
			gameController.updateMoney();
		}

		// 增加赌注按钮的方�?
		if (this.method == 83) {
			gameController.casinoCoin.addWager();
		}

		// 减少赌注按钮的方�?
		if (this.method == 84) {
			gameController.casinoCoin.minusWager();
		}

		// 押大按钮的方�?
		if (this.method == 85) {
			gameController.getPanelMakeMoney().setIsBetBig(true);
			gameController.getPanelMakeMoney().setIsBetSmall(false);
			gameController.getData().setCanPressedGo(true);
		}

		// 押小按钮的方�?
		if (this.method == 86) {
			gameController.getPanelMakeMoney().setIsBetSmall(true);
			gameController.getPanelMakeMoney().setIsBetBig(false);
			gameController.getData().setCanPressedGo(true);
		}

		// �?始赌博按钮的方法
		if (this.method == 87) {
			// 设置当前金钱�?0时无法再�?
			if (gameController.getData().getCanPressedGo() && gameController.getData().gold > 0) {
				// 设置随机�?
				gameController.getData().setRandomNum();
				// 刷新骰子
				gameController.getPanelMakeMoney().getDice().loadOutcome();
				gameController.getPanelMakeMoney().dice.ifFresh = true;
				gameController.getPanelMakeMoney().dice.diceID = 0;
			}
		}

		// 商店按钮的方�?
		if (this.method == 96) {
			gameController.levelUp();
		}

		// 点击地面，生成武士线�?
		if (this.method == 99) {
			// gameController.fencerHeros.add(0,new Hero(10, "Fencer", -30,
			// 210));
			// gameController.panelGame.i=0;
			// gameController.panelGame.repaint();
			// gameController.threads.add(gameController.panelGame.initThread(1,0));
		}

		// 点击地面，生成弓箭手线程
		if (this.method == 100) {
			// gameController.archerHeros.add(0,new Archer(10, "Fencer", -30,
			// 210));
			// gameController.panelGame.a=0;
			// gameController.threads.add(gameController.panelGame.initThread(2,
			// 0));
		}

		// 从Restart界面重新�?始游�?
		if (this.method == 927) {
			gameController.returnGame();
		}
		if (this.method == 928) {
			gameController.returnStart2();
		}

		// 实现点击按钮播放暂停音乐的功�?
		if (this.method == 929) {
			musicClick++;
			if (musicClick % 2 == 0) {
				// 每次点击均创建一个新的音乐对�?
				this.makeMusic();
				musicPlay.start();
			}
			// 第一次点击关闭panelStart里的音乐
			else if (musicClick == 1) {
				gameController.panelStart.getMusicPlay().stopmusic();
			}
			// 之后的点击关闭的是在这个类中新创建的音乐对象
			else if (musicClick > 2 && musicClick % 2 == 1) {
				this.getMusic().stopmusic();
			}
		}

		// 点击第一个小信箱，即设置选择关卡�?1，跳出确认界面�??
		if (this.method == 111) {
			PanelChooseLevel.Level = 1;
			gameController.setNowLevel(1);
			gameController.getHpBar().UpdateHp(15);
			gameController.getData().levelNow = 1;
			gameController.confirmLV();
		}
		if (this.method == 222) {
			gameController.setNowLevel(2);
			gameController.getHpBar().UpdateHp(15);
			gameController.getData().levelNow = 2;
			gameController.confirmLV();
		}
		if (this.method == 333) {
			PanelChooseLevel.Level = 3;
			gameController.setNowLevel(3);
			gameController.getHpBar().UpdateHp(15);
			gameController.getData().levelNow = 3;
			gameController.confirmLV();
		}
		if (this.method == 444) {
			PanelChooseLevel.Level = 4;
			gameController.setNowLevel(4);
			gameController.getHpBar().UpdateHp(15);
			gameController.getData().levelNow = 4;
			gameController.confirmLV();
		}
		if (this.method == 555) {
			PanelChooseLevel.Level = 5;
			gameController.setNowLevel(5);
			gameController.getHpBar().UpdateHp(15);
			gameController.getData().levelNow = 5;
			gameController.confirmLV();
		}

		// 点击确认，则进入游戏界面�?
		if (this.method == 931) {
			gameController.gotoLV();
		}

		// 点击取消，则返回选择LEVEL界面�?
		if (this.method == 932) {
			gameController.cancelLV();
		}

		// 设置牧师种类933 934
		if (this.method == 933) {
			// 加血牧师
			gameController.pkind = 1;
			gameController.placePriest=true;
			gameController.getData().setIsChoosedPriest(true);
			gameController.getData().setIsChoosedPriest1(true);
			gameController.getData().setIsChoosedFencer(false);
			gameController.getData().setIsChoosedAcher(false);
		}
		if (this.method == 934) {
			// 控�?�牧�?
			gameController.pkind = 9;
			gameController.placePriest=true;
			gameController.getData().setIsChoosedPriest(true);
			gameController.getData().setIsChoosedPriest2(true);
			gameController.getData().setIsChoosedFencer(false);
			gameController.getData().setIsChoosedAcher(false);
		}

		if (this.method == 1000) {
			gameController.losereturnGame();
			gameController.getHpBar().UpdateHp(15);
		}
		if (this.method == 1001) {
			gameController.losereturnStart2();
			gameController.getHpBar().UpdateHp(15);
		}
		if (this.method == 1002) {
			gameController.winReturnChooseLV();
		}
		if (this.method == 1003) {
			gameController.loseReturnChooseLV();
		}
		
		//下一�?
		if(this.method==51){
			gameController.nextLV();
			gameController.getHpBar().UpdateHp(15);
		}
		
		//全屏大招
		if (this.method == 9611) {
			if(gameController.getData().enoughEnergy(5)){
			gameController.Bigskill();
			gameController.useEnergy(5);
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 暂时取消放大效果
		// this.isIn = false;

		new SoundsCtrl("sounds/光标.wav").start();
		// this.repaint();
		if (this.methodEnter == 1) {
			PanelGame.type = 1;
		}
		if (this.methodEnter == 2) {
			PanelGame.type = 2;
		}
		if (this.methodEnter == 3) {
			PanelGame.type = 3;
		}
		if (this.methodEnter == 4) {
			this.isIn = true;
			this.repaint();
		}
		if (this.methodEnter == 5) {
			this.isIn = true;
			this.repaint();
		}
		if (this.methodEnter == 6) {
			this.isIn = true;
			gameController.getData().setIsEntered4(true);
			this.repaint();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.isIn = false;
		gameController.getData().setIsEntered4(false);
		this.repaint();
		PanelGame.type = 0;

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		this.isClicked = true;
		// new SoundsCtrl("sounds/点击.wav").start();
		this.repaint();
		// 设置按住鼠标的图�?
		gameController.frame.cursor(cursorAfter, nameAfter);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.isClicked = false;
		this.repaint();
		// 设置松开鼠标的图�?
		gameController.frame.cursor(cursorBefore, nameBefore);
	}

}
