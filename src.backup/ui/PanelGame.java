package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ui.button.ActionPointsBar;
import ui.button.Button;
import ui.button.CoinBar;
import ui.button.HpBar;
import ui.button.MapLabel;
import ui.button.MonsterBar;
import ui.button.RoundLabel;
import ui.button.SaveEnergy;
import util.DrawTips;
import util.DrawTransition;
import control.GameController;
import data.Data;

/**
 * 游戏窗口
 */

public class PanelGame extends JPanel implements MouseListener {
	int xyz = 0;
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
	 * 蓄能按钮图片
	 */
	private Image imgSaveEnergy = new ImageIcon("graphics/action/Energy.png").getImage();
	/**
	 * 攻击按钮图片
	 */

	private Image imgAttack = new ImageIcon("graphics/action/Attack.png").getImage();
	
	/**
	 * 飓风按钮图片
	 */
	private Image imgWind = new ImageIcon("graphics/action/wind.png").getImage();


	// /**
	// * 回合结束图标
	// */
	// Image imgGo = new ImageIcon("graphics/button/Go.png").getImage();

	/**
	 * �?出游戏图�?
	 */
	Image imgExit = new ImageIcon("graphics/button/EXIT.png").getImage();
	Image imgExit2 = new ImageIcon("graphics/button/EXIT0.png").getImage();

	/**
	 * 音乐控制按钮图标
	 */
	Image imgMusic = new ImageIcon("graphics/button/musicc.png").getImage();
	Image imgMusic2 = new ImageIcon("graphics/button/musicc2.png").getImage();

	/**
	 * 返回主菜单图�?
	 */
	Image imgReturnMenu = new ImageIcon("graphics/button/ReturnMenu.png").getImage();
	Image imgReturnMenu2 = new ImageIcon("graphics/button/ReturnMenu2.png").getImage();

	/**
	 * 背景图片数组
	 */
	Image[] imgGameBackground = new Image[5];

	/**
	 * 文字介绍�?
	 */
	private Image imgIntroduction1 = new ImageIcon("graphics/action/introduction1.png").getImage();
	private Image imgIntroduction2 = new ImageIcon("graphics/action/introduction2.png").getImage();
	private Image imgIntroduction3 = new ImageIcon("graphics/action/introduction3.png").getImage();
	private Image imgIntroduction4 = new ImageIcon("graphics/action/introduction4.png").getImage();

	/**
	 * 剑士卡牌图标
	 */
	Image imgCardFencer = new ImageIcon("graphics/button/fencer.png").getImage();
	/**
	 * 弓箭手卡牌图�?
	 */
	Image imgCardArcher = new ImageIcon("graphics/button/archer.png").getImage();
	/**
	 * 牧师卡牌图标
	 */
	Image imgCardPriest = new ImageIcon("graphics/button/priest.png").getImage();
	Image imgPriest1 = new ImageIcon("graphics/button/cure.png").getImage();
	Image imgPriest2 = new ImageIcon("graphics/button/cure2.png").getImage();
	Image imgPriest9 = new ImageIcon("graphics/button/control.png").getImage();
	Image imgPriest8 = new ImageIcon("graphics/button/control2.png").getImage();
	/**
	 * 剑士卡牌图标亮晶�?
	 */
	Image imgCardFencer2 = new ImageIcon("graphics/button/fencer2.png").getImage();
	/**
	 * 弓箭手卡牌图标亮晶晶
	 */
	Image imgCardArcher2 = new ImageIcon("graphics/button/archer2.png").getImage();
	/**
	 * 牧师卡牌图标亮晶�?
	 */
	Image imgCardPriest2 = new ImageIcon("graphics/button/priest2.png").getImage();
	/**
	 * 介绍人物的种�?
	 */
	public static int type;
	/**
	 * 剑士介绍
	 */
	private Image imgFencerIntro = new ImageIcon("graphics/Introduction/Fencer.png").getImage();
	/**
	 * 弓箭手介�?
	 */
	private Image imgArcherIntro = new ImageIcon("graphics/Introduction/Archer.png").getImage();
	/**
	 * 牧师介绍
	 */
	private Image imgPriestIntro = new ImageIcon("graphics/Introduction/Priest.png").getImage();
	/**
	 * 点击剑士后地图上显示的安置人物的范围图片
	 */
	private Image img_FencerRange = new ImageIcon("graphics/background/label01.png").getImage();

	/**
	 * 点击弓箭手后地图上显示的安置人物的范围图�?
	 */
	private Image img_ArcherRange = new ImageIcon("graphics/background/label01.png").getImage();

	/**
	 * 点击魔法师后地图上显示的安置人物的范围图�?
	 */
	private Image img_PriestRange = new ImageIcon("graphics/background/label01.png").getImage();

	/**
	 * 地图左上角x坐标
	 */
	private static final int MAP_X = 201;

	/**
	 * 地图左上角y坐标
	 */
	private static final int MAP_Y = 277;

	/**
	 * 两个地图label左右间距
	 */
	public static final int GAP_WIDTH = 30;

	/**
	 * 两个地图label上下间距
	 */
	public static final int GAP_HEIGHT = 55;

	/**
	 * 地图块宽�?
	 */
	public static final int MAP_WIDTH = 73;

	/**
	 * 地图块高�?
	 */
	public static final int MAP_HEIGHT = 51;

	/**
	 * 地图数组
	 */
	private int[][] map;

	/**
	 * 地图宽度（坐标）
	 */
	private int width;

	/**
	 * 地图高度（坐标）
	 */
	private int height;

	/**
	 * Go按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] GO = { 840, 430, 184, 126 };

	/**
	 * �?出游戏按钮参�?(x坐标，y坐标，宽，高)
	 */
	private final int[] EXIT = { 955, 25, 40, 41 };

	/**
	 * 返回主菜单按钮参�?(x坐标，y坐标，宽，高)
	 */
	private final int[] ReturnMenu = { 903, 25, 40, 41 };

	/**
	 * BGM控制按钮参数(x坐标，y坐标，宽，高)
	 */
	private final int[] MUSIC = { 851, 25, 40, 41 };

	/**
	 * 游戏控制�?
	 */
	GameController gameController;

	/**
	 * 游戏数据
	 */
	Data data;

	/**
	 * TIPS
	 */
	DrawTips drawTips;

	/**
	 * 过场动画
	 */
	DrawTransition drawTransiton;

	/**
	 * 剑士ID
	 */
	public int[] i = { -1, -1, -1 };

	/**
	 * 弓箭手ID
	 */
	public int[] a = { -1, -1, -1 };

	/**
	 * 怪物ID
	 */
	public int e = -1;

	/**
	 * 大招刷新id
	 */
	public int skillID = 0;

	/**
	 * 是否释放大招
	 */
	public boolean realse = false;

	/**
	 * 是否释放完毕大招
	 */
	public boolean realseFinish = false;

	/**
	 * 是否绘制能量�?
	 */
	public boolean judgeSaveEnergy = false;

	/**
	 * 是否�?要更新血�?(剑士与牧�?)
	 */
	public boolean needUpdateHPFenAndPri = false;

	/**
	 * 是否�?要更新血�?(弓箭�?)
	 */
	public boolean needUpdateHPArcher = false;
	/**
	 * 是否出现牧师选择�?
	 */
	public static boolean choosePriest = false;

	/**
	 * 全屏大招
	 */
	public Image[] bigSkill;

	/**
	 * �?要更新tip 0-�? 
	 * 1-能量不足 
	 * 2-能量溢出 
	 * 3-行动点数不足
	 * 4-存在其他单位
	 */
	public int needUpdateTips = 0;

	/**
	 * �?要过场动�? 0-�? 1-win 2-lose
	 */
	public int needTransition = 0;

	/**
	 * 游戏界面构�?�函�?
	 * 
	 * @param gameController
	 *            游戏控制�?
	 * @param data
	 *            游戏数据
	 */
	public PanelGame(GameController gameController, Data data) {
		// 布局管理器为null
		this.setLayout(null);
		// 设置panel为�?�明
		this.setOpaque(false);
		// 获得游戏控制�?
		this.gameController = gameController;
		// 获得地图数组
		this.map = data.getMap();
		// 获得地图宽度
		this.width = map.length;
		// 获得地图长度
		this.height = map[0].length;
		// 初始化地图Label
		initMapLabel();
		// 初始化按�?
		initButton();
		// 初始化回合数面板
		initRound();
		// 初始化Tips
		needUpdateTips = 0;
		drawTips = new DrawTips(this);
		// 初始化过场动�?
		drawTransiton = new DrawTransition(this);
		// 初始化背景图�?
		for (int i = 1; i <= imgGameBackground.length; i++) {
			imgGameBackground[i - 1] = new ImageIcon("graphics/background/bgLv" + i + ".png").getImage();
		}
		// 初始化全屏大招图�?
		bigSkill = new Image[25];
		for (int i = 0; i < 25; i++) {
			bigSkill[i] = new ImageIcon("graphics/skill/1/" + i + ".png").getImage();
		}

		// 刷新线程启动
		Thread thread = new Thread(new RunFresh());
		thread.start();
		// 武士线程启动
		Thread thread1 = new Thread(new Run1());
		thread1.start();
		// 弓箭手线程启�?
		Thread thread2 = new Thread(new Run2());
		thread2.start();
		// 牧师线程启动
		Thread thread3 = new Thread(new Run3());
		thread3.start();
		// 怪物线程启动
		Thread thread4 = new Thread(new Run4());
		thread4.start();
		// 死亡线程启动
		// Thread thread5 = new Thread(new JudgeDeath(this.gameController));
		// thread5.start();
		this.addMouseListener(this);

	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		g.drawImage(imgGameBackground[gameController.getNowLevel() - 1], 0, 0, null);
		this.setLayout(null);

		/**
		 * 判断剑士是否死亡
		 */
		int lengthFencer = gameController.fencerHeros.size();
		for (int i = 0; i < lengthFencer; i++) {
			if (gameController.fencerHeros.get(i).heroHP <= 0 && gameController.fencerHeros.get(i).deathFinish) {
				gameController.getData().setHero(gameController.fencerHeros.get(i).xl,
						gameController.fencerHeros.get(i).yl, 0);
				gameController.fencerHeros.remove(i);
				i--;
				lengthFencer--;
				gameController.recoverActionPoint(1);
			}
		}

		/**
		 * 判断牧师是否死亡
		 */
		int lengthPriest = gameController.priestHeros.size();
		for (int i = 0; i < lengthPriest; i++) {
			if (gameController.priestHeros.get(i).heroHP <= 0 && gameController.priestHeros.get(i).deathFinish) {
				gameController.getData().setHero(gameController.priestHeros.get(i).xl,
						gameController.priestHeros.get(i).yl, 0);
				gameController.priestHeros.remove(i);
				i--;
				lengthPriest--;
				gameController.recoverActionPoint(1);
			}
		}

		/**
		 * 判断弓箭手是否死�?
		 */
		int lengthArcher = gameController.archerHeros.size();
		for (int i = 0; i < lengthArcher; i++) {
			if (gameController.archerHeros.get(i).heroHP <= 0 && gameController.archerHeros.get(i).deathFinish) {
				gameController.getData().setHero(0, gameController.archerHeros.get(i).yl, 0);
				gameController.archerHeros.remove(i);
				i--;
				lengthArcher--;
				gameController.recoverActionPoint(1);
			}
		}

		/**
		 * 判断怪物是否死亡并时时更新是否存在�?�物的布尔数�? 顺便让我判断下有没有走到底�?��??
		 */
		int lengthEnemy = gameController.enemies.size();
		for (int i = 0; i < lengthEnemy; i++) {
			if (gameController.enemies.get(i).heroHP <= 0 && gameController.enemies.get(i).deathFinish) {
				// 打�?�得�?
				gameController.addMoney(gameController.enemies.get(i).value);
				// 删除死亡怪物后暂时设置有怪为false
				gameController.getData().enemyExist[gameController.enemies.get(i).yl] = false;
				gameController.enemies.remove(i);

				// 更新数组大小
				i--;
				lengthEnemy--;
			} else if (gameController.enemies.get(i).xl != 0) {
				// 若有其他怪则变回true
				gameController.getData().enemyExist[gameController.enemies.get(i).yl] = true;
			} else if (gameController.enemies.get(i).heroHP > 0 && gameController.enemies.get(i).xl == 0
					&& gameController.moveFinish()) {
				gameController.HpRemove(gameController.enemies.get(i).damage);
				gameController.enemies.remove(i);
				i--;
				lengthEnemy--;
			}
		}

		/**
		 * de完bug的方�?
		 */

		int length1 = gameController.roadOne.size();
		for (int i = 0; i < length1; i++) {
			// 武士和牧�?
			if (gameController.roadOne.get(i).heroHP <= 0 && gameController.roadOne.get(i).deathFinish
					&& !gameController.roadOne.get(i).isEnemy) {
				gameController.getData().setHero(gameController.roadOne.get(i).xl, gameController.roadOne.get(i).yl, 0);
				gameController.roadOne.remove(i);
				i--;
				length1--;
				break;
			}

			// 怪物
			if (gameController.roadOne.get(i).heroHP <= 0 && gameController.roadOne.get(i).deathFinish
					&& gameController.roadOne.get(i).isEnemy) {
				// 打�?�得�?
				gameController.addMoney(gameController.roadOne.get(i).value);
				// 删除死亡怪物后暂时设置有怪为false
				gameController.getData().enemyExist[gameController.roadOne.get(i).yl] = false;
				gameController.roadOne.remove(i);

				// 更新数组大小
				i--;
				length1--;
			} else if (gameController.roadOne.get(i).xl != 0 && gameController.roadOne.get(i).isEnemy) {
				// 若有其他怪则变回true
				gameController.getData().enemyExist[gameController.roadOne.get(i).yl] = true;
			} else if (gameController.roadOne.get(i).heroHP > 0 && gameController.roadOne.get(i).xl == 0
					&& gameController.moveFinish() && gameController.roadOne.get(i).isEnemy) {
				gameController.HpRemove(gameController.roadOne.get(i).damage);
				gameController.roadOne.remove(i);
				i--;
				length1--;
			}

		}

		int length2 = gameController.roadTwo.size();
		for (int i = 0; i < length2; i++) {
			// 武士和牧�?
			if (gameController.roadTwo.get(i).heroHP <= 0 && gameController.roadTwo.get(i).deathFinish
					&& !gameController.roadTwo.get(i).isEnemy) {
				gameController.getData().setHero(gameController.roadTwo.get(i).xl, gameController.roadTwo.get(i).yl, 0);
				gameController.roadTwo.remove(i);
				i--;
				length2--;
				break;
			}

			// 怪物
			if (gameController.roadTwo.get(i).heroHP <= 0 && gameController.roadTwo.get(i).deathFinish
					&& gameController.roadTwo.get(i).isEnemy) {
				// 打�?�得�?
				gameController.addMoney(gameController.roadTwo.get(i).value);
				// 删除死亡怪物后暂时设置有怪为false
				gameController.getData().enemyExist[gameController.roadTwo.get(i).yl] = false;
				gameController.roadTwo.remove(i);

				// 更新数组大小
				i--;
				length2--;
			} else if (gameController.roadTwo.get(i).xl != 0 && gameController.roadTwo.get(i).isEnemy) {
				// 若有其他怪则变回true
				gameController.getData().enemyExist[gameController.roadTwo.get(i).yl] = true;
			} else if (gameController.roadTwo.get(i).heroHP > 0 && gameController.roadTwo.get(i).xl == 0
					&& gameController.moveFinish() && gameController.roadTwo.get(i).isEnemy) {
				gameController.HpRemove(gameController.roadTwo.get(i).damage);
				gameController.roadTwo.remove(i);
				i--;
				length2--;
			}

		}

		int length3 = gameController.roadThree.size();
		for (int i = 0; i < length3; i++) {
			// 武士和牧�?
			if (gameController.roadThree.get(i).heroHP <= 0 && gameController.roadThree.get(i).deathFinish
					&& !gameController.roadThree.get(i).isEnemy) {
				gameController.getData().setHero(gameController.roadThree.get(i).xl, gameController.roadThree.get(i).yl,
						0);
				gameController.roadThree.remove(i);
				i--;
				length3--;
				break;
			}

			// 怪物
			if (gameController.roadThree.get(i).heroHP <= 0 && gameController.roadThree.get(i).deathFinish
					&& gameController.roadThree.get(i).isEnemy) {
				// 打�?�得�?
				gameController.addMoney(gameController.roadThree.get(i).value);
				// 删除死亡怪物后暂时设置有怪为false
				gameController.getData().enemyExist[gameController.roadThree.get(i).yl] = false;
				gameController.roadThree.remove(i);

				// 更新数组大小
				i--;
				length3--;
			} else if (gameController.roadThree.get(i).xl != 0 && gameController.roadThree.get(i).isEnemy) {
				// 若有其他怪则变回true
				gameController.getData().enemyExist[gameController.roadThree.get(i).yl] = true;
			} else if (gameController.roadThree.get(i).heroHP > 0 && gameController.roadThree.get(i).xl == 0
					&& gameController.moveFinish() && gameController.roadThree.get(i).isEnemy) {
				gameController.HpRemove(gameController.roadThree.get(i).damage);
				gameController.roadThree.remove(i);
				i--;
				length3--;
			}

		}

		/**
		 * 绘制卡牌发光效果
		 */
		switch (MapLabel.type) {
		case 1:
			g.drawImage(imgCardFencer2, 27, 17, null);
			break;
		case 2:
			g.drawImage(imgCardArcher2, 151, 17, null);
			break;
		case 3:
			g.drawImage(imgCardPriest2, 276, 17, null);
			break;
		case 0:
			// repaint();
			break;
		}
		/**
		 * 绘制牧师种类按钮效果
		 */
		switch (gameController.pkind) {
		case 1:
			g.drawImage(imgPriest2, 399, 37, null);
			break;
		case 9:
			g.drawImage(imgPriest8, 399, 87, null);
			break;
		case 0:
			break;
		}

		/**
		 * 绘制人物介绍
		 */
		switch (PanelGame.type) {
		case 1:
			g.drawImage(imgFencerIntro, 429, 0, null);
			break;
		case 2:
			g.drawImage(imgArcherIntro, 429, 0, null);
			break;
		case 3:
			g.drawImage(imgPriestIntro, 429, 0, null);
			break;
		case 0:
			// repaint();
			break;
		}

		/**
		 * 绘制游戏数据介绍
		 */
		if (gameController.getData().getIsEntered1()) {
			g.drawImage(imgIntroduction1, 852, 126, null);
		}
		if (gameController.getData().getIsEntered2()) {
			g.drawImage(imgIntroduction2, 665, 126, null);
		}
		if (gameController.getData().getIsEntered3()) {
			g.drawImage(imgIntroduction3, 490, 24, null);
		}
		if (gameController.getData().getIsEntered4()) {
			g.drawImage(imgIntroduction4, 620, 454, null);
		}

		// 如果点击剑士卡牌则显示其可以安置的位�?
		if (gameController.getData().getIsChoosedFencer())
			g.drawImage(img_FencerRange, 114 + GAP_WIDTH + MAP_WIDTH, 276, null);

		// 如果点击弓箭手卡牌则显示其可以安置的位置
		if (gameController.getData().getIsChoosedAcher())
			g.drawImage(img_ArcherRange, 114, 276, null);

		// 如果点击牧师卡牌则显示其可以安置的位�?
		if (gameController.getData().getIsChoosedPriest())
			g.drawImage(img_PriestRange, 114 + GAP_WIDTH + MAP_WIDTH, 276, null);

		// 牧师大招
		// for(int i=0;i<gameController.priestHeros.size();i++){
		// if(gameController.priestHeros.get(i).kind==9&&gameController.priestHeros.get(i).skillJudge){
		// gameController.priestHeros.get(i).ultimateSkill(g, this);
		// }
		// }

		// �?路一路刷�?
		for (int i = 0; i < 3; i++) {
			// 第一�?
			if (i == 0) {
				for (int j = 0; j < gameController.roadOne.size(); j++) {
					gameController.roadOne.get(j).moveImg(g, this);
					if (gameController.roadOne.get(j).kind == 9 && gameController.roadOne.get(j).skillJudge) {
						gameController.roadOne.get(j).ultimateSkill(g, this);
					}
				}
			}
			// 第二�?
			if (i == 1) {
				for (int j = 0; j < gameController.roadTwo.size(); j++) {
					gameController.roadTwo.get(j).moveImg(g, this);
					if (gameController.roadTwo.get(j).kind == 9 && gameController.roadTwo.get(j).skillJudge) {
						gameController.roadTwo.get(j).ultimateSkill(g, this);
					}
				}
			}
			// 第三�?
			if (i == 2) {
				for (int j = 0; j < gameController.roadThree.size(); j++) {
					gameController.roadThree.get(j).moveImg(g, this);
					if (gameController.roadThree.get(j).kind == 9 && gameController.roadThree.get(j).skillJudge) {
						gameController.roadThree.get(j).ultimateSkill(g, this);
					}
				}
			}
		}

		// 弓箭�?
		for (int i = 0; i < gameController.archerHeros.size(); i++) {

			if (gameController.archerHeros.get(i).road == 0) {
				gameController.archerHeros.get(i).dead(g, this);

				if (gameController.archerHeros.get(i).mDraw && !gameController.archerHeros.get(i).canDead) {
					gameController.archerHeros.get(i).FreshArcher(g, this);
				}

				if (gameController.archerHeros.get(i).mPlace) {
					gameController.archerHeros.get(i).PlaceArcher(g, this);
				}

				if (gameController.archerHeros.get(i).mDrawArrow) {
					gameController.archerHeros.get(i).moveImg(g, this);
				}

			}
		}

		for (int i = 0; i < gameController.archerHeros.size(); i++) {

			if (gameController.archerHeros.get(i).road == 1) {
				gameController.archerHeros.get(i).dead(g, this);

				if (gameController.archerHeros.get(i).mDraw && !gameController.archerHeros.get(i).canDead) {
					gameController.archerHeros.get(i).FreshArcher(g, this);
				}

				if (gameController.archerHeros.get(i).mPlace) {
					gameController.archerHeros.get(i).PlaceArcher(g, this);
				}

				if (gameController.archerHeros.get(i).mDrawArrow) {
					gameController.archerHeros.get(i).moveImg(g, this);
				}

			}
		}

		for (int i = 0; i < gameController.archerHeros.size(); i++) {

			if (gameController.archerHeros.get(i).road == 2) {
				gameController.archerHeros.get(i).dead(g, this);

				if (gameController.archerHeros.get(i).mDraw && !gameController.archerHeros.get(i).canDead) {
					gameController.archerHeros.get(i).FreshArcher(g, this);
				}

				if (gameController.archerHeros.get(i).mPlace) {
					gameController.archerHeros.get(i).PlaceArcher(g, this);
				}

				if (gameController.archerHeros.get(i).mDrawArrow) {
					gameController.archerHeros.get(i).moveImg(g, this);
				}

			}
		}
		// 提示
		if (needUpdateTips == 1)
			drawTips.energyNotEnough(g);
		else if (needUpdateTips == 2)
			drawTips.energyFull(g);
		else if (needUpdateTips == 3)
			drawTips.actionPointNotEnough(g);
		else if (needUpdateTips == 4)
			drawTips.existOther(g);

		// 过场动画
		if (needTransition == 1)
			drawTransiton.transitionWin(g);
		else if (needTransition == 2)
			drawTransiton.transitionLose(g);

		// 全屏大招
		if (realse) {
			g.drawImage(bigSkill[skillID], 20, 20, (ImageObserver) this);
			skillID++;
			if (skillID == 25) {
				skillID = 0;
				realse = false;
				realseFinish = true;
			}
		}

		if (realseFinish) {
			for (int i = 0; i < gameController.roadOne.size(); i++) {
				if (gameController.roadOne.get(i).isEnemy)
					gameController.roadOne.get(i).heroHP = gameController.roadOne.get(i).heroHP/2;
			}
			for (int i = 0; i < gameController.roadTwo.size(); i++) {
				if (gameController.roadTwo.get(i).isEnemy)
					gameController.roadTwo.get(i).heroHP = gameController.roadTwo.get(i).heroHP/2;
			}
			for (int i = 0; i < gameController.roadThree.size(); i++) {
				if (gameController.roadThree.get(i).isEnemy)
					gameController.roadThree.get(i).heroHP = gameController.roadThree.get(i).heroHP/2;
			}

			realseFinish = false;
		}
	}

	/**
	 * 初始化地图Label
	 */
	private void initMapLabel() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				MapLabel mapLabel = new MapLabel(i, j, MAP_X, MAP_Y, this, gameController);
				this.add(mapLabel);
			}
		}
	}

	/**
	 * 初始化Label
	 */
	public void initRound() {
		// // 初始化回合数
		// RoundLabel roundLabel = new RoundLabel(gameController);
		// this.add(roundLabel);
		// 初始化能量槽
		SaveEnergy saveEnergy = new SaveEnergy(gameController);
		this.add(saveEnergy);
		// 初始化血�?
		HpBar hpBar = new HpBar(gameController);
		this.add(hpBar);
		// 初始化金币条
		CoinBar coinBar = new CoinBar(gameController);
		this.add(coinBar);
		// 初始化行动点数条
		ActionPointsBar actionPointsBar = new ActionPointsBar(gameController);
		this.add(actionPointsBar);
		// 初始化剩余波数条
		MonsterBar monsterBar = new MonsterBar(gameController);
		this.add(monsterBar);
	}

	/**
	 * 初始化按�?
	 */
	private void initButton() {
		// 蓄能按钮
		// Button energyButton = new Button(750, 455,120, 120, imgSaveEnergy,
		// imgSaveEnergy, 20,0, gameController);
		// this.add(energyButton);
		// 初始化回合结束按�?
		Button go = new Button(850, 455, 120, 120, imgAttack, imgAttack, 2, 5, gameController);
		this.add(go);
		//初始化飓风技能按�?
		Button wind = new Button(755, 459, 120, 120, imgWind, imgWind, 9611, 6, gameController);
		this.add(wind);
		// 初始化�??出游戏按�?
		Button exit = new Button(EXIT[0], EXIT[1], EXIT[2], EXIT[3], imgExit, imgExit2, 1, 0, gameController);
		this.add(exit);
		// 初始化返回主菜单按钮
		Button returnMenu = new Button(ReturnMenu[0], ReturnMenu[1], ReturnMenu[2], ReturnMenu[3], imgReturnMenu,
				imgReturnMenu2, 3, 0, gameController);
		this.add(returnMenu);
		// 初始化音乐按�?
		Button restart = new Button(MUSIC[0], MUSIC[1], MUSIC[2], MUSIC[3], imgMusic, imgMusic2, 929, 0,
				gameController);
		this.add(restart);
		// 初始化剑士卡牌按�?
		Button cardFencer = new Button(26, 17, 120, 120, imgCardFencer, imgCardFencer, 5, 1, gameController);
		this.add(cardFencer);
		// 初始化弓箭手卡牌按钮 TODO button的method要改�?
		Button cardArcher = new Button(151, 17, 120, 120, imgCardArcher, imgCardArcher, 6, 2, gameController);
		this.add(cardArcher);
		// 初始化牧师卡牌按�? TODO button的method要改�?
		Button cardPriest = new Button(276, 17, 120, 120, imgCardPriest, imgCardPriest, 7, 3, gameController);
		this.add(cardPriest);

		Button priest1 = new Button(399, 37, 50, 50, imgPriest1, imgPriest1, 933, 0, gameController);
		this.add(priest1);
		Button priest9 = new Button(399, 87, 50, 50, imgPriest9, imgPriest9, 934, 0, gameController);
		this.add(priest9);

	}

	/**
	 * @param data
	 *            游戏数据
	 */
	public void setData(Data data) {
		this.data = data;
	}

	/**
	 * �?要过场动�?
	 */
	public void setNeedTranstion(int i) {
		this.needTransition = i;
	}

	/**
	 * 是否完成过程动画的播�?
	 */
	public boolean getDrawFinish() {
		return drawTransiton.drawFinish();
	}

	/**
	 * Run1对应武士线程
	 */
	class Run1 implements Runnable {

		public void run() {
			while (true) {
				// 更新fencer的x，y坐标
				for (int i = 0; i < gameController.fencerHeros.size(); i++) {
					gameController.fencerHeros.get(i).UpdateHero();
				}

				try {
					// 线程休眠50ms
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// repaint();
			}
		}
	}

	/**
	 * Run2对应弓箭手线�?
	 */
	class Run2 implements Runnable {

		public void run() {
			while (true) {
				// 更新archer弓箭的x，y坐标
				for (int i = 0; i < gameController.archerHeros.size(); i++) {
					gameController.archerHeros.get(i).UpdateHero();
					if (gameController.archerHeros.get(i).heroHP <= 0)
						gameController.archerHeros.get(i).canDead = true;
				}

				try {
					// 线程休眠50ms
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// repaint();

			}
		}
	}

	/**
	 * Run3对应牧师线程
	 */
	class Run3 implements Runnable {

		public void run() {
			while (true) {
				// 更新archer弓箭的x，y坐标
				for (int i = 0; i < gameController.priestHeros.size(); i++) {
					gameController.priestHeros.get(i).UpdateHero();
					// if (gameController.moveFinish() && needUpdateHPFenAndPri)
					// {
					// gameController.updateHP();
					// needUpdateHPFenAndPri = false;
					// }
				}

				try {
					// 线程休眠50ms
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// repaint();

			}
		}
	}

	/**
	 * Run4对应怪物线程
	 */
	class Run4 implements Runnable {

		public void run() {
			while (true) {
				// 更新怪物的x，y坐标
				for (int i = 0; i < gameController.enemies.size(); i++) {
					gameController.enemies.get(i).UpdateHero();
					if (gameController.moveFinish() && needUpdateHPFenAndPri) {
						gameController.updateHP();
						needUpdateHPFenAndPri = false;
					}

				}

				try {
					// 线程休眠50ms
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// repaint();

			}
		}
	}

	/**
	 * 刷新线程
	 */
	class RunFresh implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		MapLabel.type = 0;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// 设置按住鼠标的图�?
		gameController.frame.cursor(cursorAfter, nameAfter);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// 设置松开鼠标的图�?
		gameController.frame.cursor(cursorBefore, nameBefore);
	}

}
