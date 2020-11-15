package control;

import java.util.ArrayList;
import java.util.Random;

import role.Archer;
import role.Enemy;
import role.Hero;
import role.Priest;
import thread.JudgeResult;
import ui.FrameGame;
import ui.PanelAboutUs;
import ui.PanelChooseLevel;
import ui.PanelConfirm;
import ui.PanelGame;
import ui.PanelLevelUp;
import ui.PanelLose;
import ui.PanelMakeMoney;
import ui.PanelStart;
import ui.PanelStartPre;
import ui.PanelWin;
import ui.button.ActionPointsBar;
import ui.button.CasinoCoin;
import ui.button.CoinBar;
import ui.button.HpBar;
import ui.button.MonsterBar;
import ui.button.RoundLabel;
import ui.button.SaveEnergy;
import data.Data;

/**
 * 游戏控制器
 */
public class GameController {
	/**
	 * 游戏框架
	 */
	public FrameGame frame;

	/**
	 * 游戏数据
	 */
	private Data data;

	/**
	 * 界面
	 */
	public PanelStart panelStart = new PanelStart(this);
	private PanelAboutUs panelAboutUs = new PanelAboutUs(this);
	private PanelWin panelWin = new PanelWin(this);
	private PanelChooseLevel panelChooseLevel;
	private PanelConfirm panelConfirm = new PanelConfirm(this);
	private PanelLevelUp panelLevelUp = new PanelLevelUp(this);
	public PanelMakeMoney panelMakeMoney;
	private PanelLose panelLose = new PanelLose(this);
	
	/**
	 * 回合数面板
	 */
	private RoundLabel roundLabel;

	/**
	 * 能量槽
	 */
	private SaveEnergy saveEnergy;

	/**
	 * 血槽
	 */
	private HpBar HPBar;

	/**
	 * 金币槽
	 */
	private CoinBar coinBar;

	/**
	 * 行动点数条
	 */
	private ActionPointsBar actionPointsBar;
	
	/**
	 * 剩余怪物波数条
	 */
	private MonsterBar monsterBar;
	
	/**
	 * 赌场中的金币面板
	 */
	public CasinoCoin casinoCoin;
	
	/**
	 * 当前正在进行的关卡
	 */
	private int nowLevel;
	
	/**
	 * 英雄数组
	 */
	public ArrayList<Hero> fencerHeros = new ArrayList<Hero>();
	public ArrayList<Archer> archerHeros = new ArrayList<Archer>();
	public ArrayList<Priest> priestHeros = new ArrayList<Priest>();

	/**
	 * 怪物数组
	 */
	public ArrayList<Enemy> enemies = new ArrayList<>();

	/**
	 * 英雄ID
	 */
	public int[] heroID = new int[20];

	/**
	 * 人物线程Thread数组
	 */
	public ArrayList<Thread> fencerThreads = new ArrayList<>();
	public ArrayList<Thread> archerThreads = new ArrayList<>();

	/**
	 * 怪物线程
	 */
	public ArrayList<Thread> enemyThreads = new ArrayList<>();
	
	/**
	 * 第1，2，3路线程
	 */
	public ArrayList<Hero> roadOne = new ArrayList<>();
	public ArrayList<Hero> roadTwo = new ArrayList<>();
	public ArrayList<Hero> roadThree = new ArrayList<>();
	
	/**
	 * 可以放置牧师
	 */
	public boolean placePriest=false;
	
	/**
	 * 游戏主界面
	 */
	public PanelGame panelGame;

	/**
	 * 游戏开始动画界面
	 */
	private PanelStartPre panelStartPre = null;
	
	/**
	 * 弓箭手是否攻击
	 */
	public boolean needUpdataArcher = false;
	
	/**
	 * 牧师的种类；
	 */
	public int pkind=0;
	
	/**
	 * 是否需要控怪
	 */
	public boolean stopEnemy = false;
	
	JudgeResult judgeResult;
	/**
	 * 游戏控制器构造函数
	 * 
	 * @param frame
	 *            游戏框架
	 */
	public GameController(FrameGame frame) {
		this.frame = frame;
		this.data = new Data();
		//读档
		data.load();
		this.panelGame = new PanelGame(this, data);
		this.panelChooseLevel = new PanelChooseLevel(this);
		// 设置游戏控制器
		this.panelStart.setController(this);
		//启动判断胜负线程
		Thread judgeWin = new Thread(judgeResult = new JudgeResult(this));
		judgeWin.start();
		//启动判断弓箭手射箭掉血的线程
		Thread archerAttack = new Thread(new ArcherAttack());
		archerAttack.start();

		//启动判断回合结束线程
		Thread roundEnd = new Thread(new RoundEnd())	;
		roundEnd.start();
		
		//初始化当前关卡
		nowLevel = 0;
		//初始化赌场面板
		panelMakeMoney = new PanelMakeMoney(this);
	}

	public int getNowLevel() {
		return nowLevel;
	}

	public void setNowLevel(int nowLevel) {
		this.nowLevel = nowLevel;
	}

	public ArrayList<Hero> getFencerHeros() {
		return fencerHeros;
	}

	public void setFencerHeros(ArrayList<Hero> fencerHeros) {
		this.fencerHeros = fencerHeros;
	}

	/**
	 * 设置游戏最开始动画
	 * 
	 * @param panelStartPre
	 */
	public void setPanelStartPre(PanelStartPre panelStartPre) {
		this.panelStartPre = panelStartPre;
	}

	/**
	 * 画开始界面
	 */
	public void startMenu() {
		this.frame.remove(this.panelStartPre);
		this.frame.setPanel(this.panelStart);
	}

	/**
	 * 选择LEVEL
	 */
	public void startGame() {
		this.frame.remove(panelStart);
		this.frame.setPanel(panelChooseLevel = new PanelChooseLevel(this));
	}

	/**
	 * 进入确认LEVEL界面
	 */
	public void confirmLV() {
		this.frame.remove(panelChooseLevel);
		this.frame.setPanel(panelConfirm = new PanelConfirm(this));
	}

	/**
	 * 确认，根据之前选择小信箱得到的LEVEL决定进入游戏。
	 */
	public void gotoLV() {
		this.frame.remove(panelConfirm);
		this.frame.setPanel(panelGame);
	}

	/**
	 * 取消，返回选择LEVEL
	 */
	public void cancelLV() {
		this.frame.remove(panelConfirm);
		this.frame.setPanel(panelChooseLevel);
	}

	/**
	 * 进入游戏英雄升级界面
	 */
	public void levelUp() {
		this.frame.remove(panelChooseLevel);
		this.frame.setPanel(panelLevelUp = new PanelLevelUp(this));
	}
	
	/**
	 * 退出英雄升级界面
	 */
	public void exitLevelUp() {
		this.frame.remove(panelLevelUp);
		this.frame.setPanel(panelChooseLevel = new PanelChooseLevel(this));
	}

	/**
	 * 进入赚取金币界面
	 */
	public void makeMoney() {
		this.frame.remove(panelLevelUp);
		this.frame.setPanel(panelMakeMoney = new PanelMakeMoney(this));
	}
	
	/**
	 * 退出赚取金币界面
	 */
	public void exitMakeMoney() {
		this.frame.remove(panelMakeMoney);
		this.frame.setPanel(panelLevelUp = new PanelLevelUp(this));
	}
	
	/**
	 * @return 游戏数据
	 */
	public Data getData() {
		return data;
	}

	/**
	 * 获取开始界面
	 */
	public PanelStart getPanelStart() {
		return panelStart;
	}

	public PanelMakeMoney getPanelMakeMoney() {
		return panelMakeMoney;
	}
	
	public void setRoundLabel(RoundLabel roundLabel) {
		this.roundLabel = roundLabel;
	}

	public void setSaveEnergy(SaveEnergy saveEnergy) {
		this.saveEnergy = saveEnergy;
	}

	public void setHPBar(HpBar HPBar) {
		this.HPBar = HPBar;
	}

	public void setCoinBar(CoinBar coinBar) {
		this.coinBar = coinBar;
	}
	
	public void setActionPointsBar(ActionPointsBar actionPointsBar) {
		this.actionPointsBar = actionPointsBar;
	}

	public void setMonsterBar(MonsterBar monsterBar) {
		this.monsterBar = monsterBar;
	}
	
	public void setCasinoCoin(CasinoCoin casinoCoin) {
		this.casinoCoin = casinoCoin;
	}
	public CasinoCoin getCasinoCoin() {
		return casinoCoin;
	}
	
	private CoinBar getCoinbar() {
		return coinBar;
	}
	
	public HpBar getHpBar() {
		return HPBar;
	}
	
	/**
	 * 更新panelGame里的金钱
	 */
	public void updateMoney() {
		this.getCoinbar().setCoin(this.getData().gold);
	}
	

	
	/**
	 * 回合结束 TODO
	 */
	public void endRound() {
		//this.roundLabel.roundEnd();
		this.data.endRound();
		this.panelGame.needUpdateHPFenAndPri = true;
		needUpdataArcher = true;
		allArrowRefresh();
//		data.refreshActionPoint();
		this.saveEnergy(data.actionPoint);
		for(Hero h:fencerHeros){
			h.startNewRound();
		}
		for(Priest p:priestHeros){
			p.startNewRound();
		}
		for (Enemy e:enemies) {
			e.startNewRound();
		}
	}

	/**
	 * 蓄能
	 */
	public void saveEnergy(int i) {
		int nowEnergy = this.data.getEnergyValue();
		if (data.actionPoint>=i&&nowEnergy+i<=15) {
			this.data.setEnergyValue(nowEnergy + i);
			this.saveEnergy.UpdateEnergy(nowEnergy + i);
		}
		else if(data.actionPoint<i){
			panelGame.needUpdateTips = 3;
		}
		else {
			data.setEnergyValue(15);
			this.saveEnergy.UpdateEnergy(15);
			panelGame.needUpdateTips = 2;
		}
	}

	/**
	 * 退出游戏
	 */
	public void exit() {
		data.save();
		System.exit(0);
	}
	

	/**
	 * 返回主菜单
	 */
	public void returnMenu() {
		this.frame.remove(panelGame);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}
	public void returnMenu2() {
		this.frame.remove(panelWin);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}
	public void returnMenu3() {
		this.frame.remove(panelLose);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}
	public void returnMenu4() {
		this.frame.remove(panelMakeMoney);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}
	public void returnMenu5() {
		this.frame.remove(panelLevelUp);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}
	public void returnMenu6() {
		this.frame.remove(panelConfirm);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}
	public void returnMenu7() {
		this.frame.remove(panelChooseLevel);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}
	/**
	 * 重新开始
	 */
	public void restart() {
		this.fencerHeros = new ArrayList<Hero>();
		this.archerHeros = new ArrayList<Archer>();
		this.priestHeros = new ArrayList<Priest>();
		this.enemies = new ArrayList<Enemy>();
		this.roadOne = new ArrayList<>();
		this.roadTwo = new ArrayList<>();
		this.roadThree = new ArrayList<>();
		this.data.initdata();
		this.judgeResult.restart();
	}

	/**
	 * 开始游戏-关于我们
	 */
	public void teamIntroduce() {
		this.frame.remove(panelStart);
		this.frame.setPanel(panelAboutUs = new PanelAboutUs(this));
	}

	/**
	 * 关于我们-开始游戏
	 */
	public void returnStart() {
		this.frame.remove(panelAboutUs);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}

	/**
	 * 胜利结束界面-开始主菜单
	 */
	public void returnStart2() {
		this.frame.remove(panelWin);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}

	/**
	 * 胜利结束界面-游戏界面
	 */
	public void returnGame() {
		this.frame.remove(panelWin);
		this.frame.setPanel(panelGame);
	}
	/**
	 * 失败结束界面-开始主菜单
	 */
	public void losereturnStart2() {
		this.frame.remove(panelLose);
		this.frame.setPanel(panelStart = new PanelStart(this));
	}

	/**
	 * 失败结束界面-游戏界面
	 */
	public void losereturnGame() {
		this.frame.remove(panelLose);
		this.frame.setPanel(panelGame);
	}
	/**
	 * 胜利结束界面-选择关卡
	 */
	public void winReturnChooseLV() {
		this.frame.remove(panelWin);
		this.frame.setPanel(panelChooseLevel = new PanelChooseLevel(this));
	}
	/**
	 * 失败结束界面-选择关卡
	 */
	public void loseReturnChooseLV() {
		this.frame.remove(panelLose);
		this.frame.setPanel(panelChooseLevel = new PanelChooseLevel(this));
	}

	/**
	 * 刷新界面
	 */
	public void refresh() {
		panelGame.repaint();
	}

	/**
	 * 英雄移动
	 */
	public void heroMove() {

		// 武士移动
		for (Hero h : this.fencerHeros) {
			h.moveXY();
		}
		updateMap();
		//弓箭射出
		for (Archer h : this.archerHeros) {
			if (hasEnemy(h.yl)) {
				h.mDraw = true;
			}
		}
		
		//牧师移动
		for(Priest h:this.priestHeros){
			h.moveXY();
		

			//加血牧师
			if(h.kind==1){
				for(Hero tempHero:fencerHeros){
				if(h.roadID==tempHero.roadID){
					if(tempHero.heroHP<tempHero.maxHP){
						//判断一下会不会加超血量
						if(tempHero.heroHP+h.groupAttack<=tempHero.maxHP){
							tempHero.heroHP+=h.groupHeal;
						}
						else {
							tempHero.heroHP=tempHero.maxHP;
						}
						tempHero.needDrawHeal = true;
						tempHero.getDrawNumber().setHeal(h.groupHeal);
					}
				}
			}
			}
			//控怪牧师
			if(h.kind==9){
				h.skillJudge=true;
			}
			
			
			if (data.canAttack(h)) {
				h.HeroID = 4;
				h.attack = true;
			}
		}
	}

	/**
	 * 英雄攻击
	 */
	public void heroAttack() {
		for (Hero h : this.fencerHeros) {
			if (data.canAttack(h) && h.moveFinish()&&!data.canMove(h)) {
				h.HeroID = 10;
				h.attack = true;
			}
		}
		for (Priest p : this.priestHeros){
			if (data.canAttack(p)&& p.moveFinish()) {
				p.HeroID = 4;
				p.attack = true;
			}
		}
		for (Hero e : this.enemies) {
			if (data.canAttack(e)) {
				e.HeroID = 9;
				e.attack = true;
			}
		}
	}
	

	/**
	 * 消耗能量
	 * 
	 * @param i
	 *            消耗的能量数
	 */
	public void useEnergy(int i) {
		int nowEnergy = this.data.getEnergyValue();
		this.data.useEnergy(i);
		this.saveEnergy.UpdateEnergy(nowEnergy - i);
	}
	
	/**
	 * 消耗行动点数
	 * @param i
	 * 消耗的行动点数
	 */
	public void useActionPoint(int i){
		this.data.useActionPoint(i);
	}
	
	public void recoverActionPoint(int i){
		this.data.recoverActionPoint(i);
	}
	/**
	 * 扣血
	 */
	public void HpRemove(int i) {
		int nowHp = this.data.getLife();
		if(nowHp-i>0){
		this.data.removeHp(i);
		this.HPBar.UpdateHp(nowHp - i);
		}
		else{
			this.data.setHP(0);
			this.HPBar.UpdateHp(0);
		}
	}

	// 怪物移动
	public void moveEnemy() {
		for(Priest i:this.priestHeros){
			if(i.kind==8){
				stopEnemy = true;
				break;
			}
			else
				stopEnemy = false;
		}
		
		
		if(!stopEnemy||(stopEnemy&&this.getData().round%3==0)){
		for (Enemy e : this.enemies) {
			e.moveXY();
		}
		}
		
		
	}
	
	/**
	 * 升级方法
	 * @param name
	 * 兵种
	 */
	public void levelUp(String name){
		if(name.equals("fencer")&&canUp(2500)){
			data.levelOfFencer++;
			data.gold-=2500;
			this.refreshLevelUp();
		}
		else if(name.equals("archer")&&canUp(3000)){
			data.levelOfArcher++;
			data.gold-=3000;
			this.refreshLevelUp();
		}
		else if(name.equals("priest")&&canUp(2000)){
			data.levelOfPriest++;
			data.gold-=2000;
			this.refreshLevelUp();
		}
	}

	/**
	 * 是否可以升级
	 */
	public boolean canUp(int cost){
		if(cost<=data.gold)
			return true;
		else{
			//TODO 提示
			return false;
		}
	}
	// 刷怪
	public void freshEnemy() {
		panelGame.e = 1;
		// 设置随机刷怪
		int max = 3;
		int min = 1;
		Random random = new Random();
		int s1 = random.nextInt(max) % (max - min + 1) + min;
		int s2 = random.nextInt(max) % (max - min + 1) + min;

		switch (s1) {
		case 1:

			if(s2==1){
				enemies.add(new Enemy(26, "Werewolf", 7, 0, data,8,15));
				roadOne.add(enemies.get(enemies.size()-1));
				data.enemyExist[0] = true;
				enemies.add(new Enemy(26, "Werewolf", 7, 1, data,8,15));
				roadTwo.add(enemies.get(enemies.size()-1));
				data.enemyExist[1] = true;
			}
			if(s2==2){
				enemies.add(new Enemy(24, "WhiteWolf", 7, 0, data,7,12));
				roadOne.add(enemies.get(enemies.size()-1));
				data.enemyExist[0] = true;
				enemies.add(new Enemy(24, "WhiteWolf", 7, 1, data,7,12));
				roadTwo.add(enemies.get(enemies.size()-1));
				data.enemyExist[1] = true;
			}
			if(s2==3){
				enemies.add(new Enemy(23, "Penguin", 7, 0, data,6,12));
				roadOne.add(enemies.get(enemies.size()-1));
				data.enemyExist[0] = true;
				enemies.add(new Enemy(23, "Penguin", 7, 1, data,6,12));
				roadTwo.add(enemies.get(enemies.size()-1));
				data.enemyExist[1] = true;
			}
			break;

		case 2:

			if(s2==1){
				enemies.add(new Enemy(26, "Werewolf", 7, 0, data,8,15));
				roadOne.add(enemies.get(enemies.size()-1));
				data.enemyExist[0] = true;
				enemies.add(new Enemy(26, "Werewolf", 7, 2, data,8,15));
				roadThree.add(enemies.get(enemies.size()-1));
				data.enemyExist[2] = true;
			}
			if(s2==2){
				enemies.add(new Enemy(24, "WhiteWolf", 7, 0, data,7,12));
				roadOne.add(enemies.get(enemies.size()-1));
				data.enemyExist[0] = true;
				enemies.add(new Enemy(24, "WhiteWolf", 7, 2, data,7,12));
				roadThree.add(enemies.get(enemies.size()-1));
				data.enemyExist[2] = true;
			}
			if(s2==3){
				enemies.add(new Enemy(23, "Penguin", 7, 0, data,6,12));
				roadOne.add(enemies.get(enemies.size()-1));
				data.enemyExist[0] = true;
				enemies.add(new Enemy(23, "Penguin", 7, 2, data,6,12));
				roadThree.add(enemies.get(enemies.size()-1));
				data.enemyExist[2] = true;

			}
			break;
		case 3:

			if(s2==1){
				enemies.add(new Enemy(26, "Werewolf", 7, 1, data,8,15));
				roadTwo.add(enemies.get(enemies.size()-1));
				data.enemyExist[1] = true;
				enemies.add(new Enemy(26, "Werewolf", 7, 2, data,8,15));
				roadThree.add(enemies.get(enemies.size()-1));
				data.enemyExist[2] = true;
			}
			if(s2==2){
				enemies.add(new Enemy(24, "WhiteWolf", 7, 1, data,7,12));
				roadTwo.add(enemies.get(enemies.size()-1));
				data.enemyExist[1] = true;
				enemies.add(new Enemy(24, "WhiteWolf", 7, 2, data,7,12));
				roadThree.add(enemies.get(enemies.size()-1));
				data.enemyExist[2] = true;
			}
			if(s2==3){
				enemies.add(new Enemy(23, "Penguin", 7, 1, data,6,12));
				roadTwo.add(enemies.get(enemies.size()-1));
				data.enemyExist[1] = true;
				enemies.add(new Enemy(23, "Penguin", 7, 2, data,6,12));
				roadThree.add(enemies.get(enemies.size()-1));
				data.enemyExist[2] = true;
			}
			break;
		default:
			break;
		}

	}

	/**
	 * 更新地图数组
	 */
	public void updateMap() {
		this.data.MapZero();
		for (Hero h : fencerHeros) {
			if (h.xl != -1)
				this.data.setHero(h.xl, h.yl, 1);
			else
				this.data.setHero(0, h.yl, 1);
		}
		for (Hero h : priestHeros) {
			if (h.xl != -1)
				this.data.setHero(h.xl, h.yl, 1);
			else
				this.data.setHero(0, h.yl, 1);
		}
		//弓箭手为2
		for(Hero a:archerHeros){
			this.data.setHero(0, a.yl, 2);
		}
		for (Enemy e : enemies) {
			this.data.setEnermy(e.xl, e.yl, 9);
		}
	}

	/**
	 * 回合结束时攻击扣血
	 */
	public void updateHP() {
		for (Hero e : enemies) {
			// 群攻掉血
			for (Hero p : priestHeros) {
				if (p.kind == 9) {
					if ((p.xl == e.xl - 3 || p.xl == e.xl - 2 || p.xl == e.xl - 1)
							&& p.yl == e.yl) {
						e.heroHP -= p.groupAttack;
						e.needDrawDamage = true;
						e.getDrawNumber().setDamage(p.groupAttack);
					}
				}
			}
			
			if (data.canAttack(e) && moveFinish()) {
				for (Hero f:fencerHeros) {
					if (f.yl == e.yl && f.xl == e.xl - 1&&!f.hasMoved&&!e.hasMoved) {
						f.heroHP -= e.heroAttack;
						e.heroHP -= f.heroAttack;
						f.needDrawDamage = true;
						f.getDrawNumber().setDamage(e.heroAttack);
						e.needDrawDamage = true;
						e.getDrawNumber().setDamage(f.heroAttack);
					}
				}
				for (Hero p:priestHeros){
					if (p.yl == e.yl && p.xl == e.xl - 1&&!p.hasMoved&&!e.hasMoved) {
						p.heroHP -= e.heroAttack;
						e.heroHP-=p.heroAttack;
						p.needDrawDamage = true;
						p.getDrawNumber().setDamage(e.heroAttack);
						e.needDrawDamage = true;
						e.getDrawNumber().setDamage(p.heroAttack);
					}
				}
				for(Hero a:archerHeros){
					if(a.yl==e.yl && e.xl ==1){
						a.heroHP-=e.heroAttack;
						a.needDrawDamage = true;
						a.getDrawNumber().setDamage(e.heroAttack);
					}
				}
			}
		}
	}
	
	/**
	 * 弓箭手的伤害
	 * @param i
	 * 所在的路
	 */
	public void updateHPArcher(){
		for(Hero a:archerHeros){
			for(Hero e:enemies){
				if(e.yl == a.yl){
					e.heroHP-=a.heroAttack;
					e.needDrawDamage = true;
					e.getDrawNumber().setDamage(a.heroAttack);
				}
			}
		}
	}
	

	/**
	 * 是否所有角色完成移动
	 */
	public boolean moveFinish() {
		for (Hero h : fencerHeros) {
			if (!h.moveFinish())
				return false;
		}
		// for(Hero h:priestHeros){
		// if(!h.moveFinish())
		// return false;
		// }
		for (Enemy e : enemies) {
			if (!e.moveFinish())
				return false;
		}
		return true;
	}
	
	
	/**
	 * 某一路是否有怪
	 * @param i
	 * 路数
	 */
	private boolean hasEnemy(int i){
		for(Hero e:enemies){
			if(i==e.yl)
				return true;
		}
		return false;
	}
	
	/**
	 * 重置弓箭坐标
	 */
	public void allArrowRefresh(){
		for(Archer a:archerHeros){
			a.refreshArrow();
		}
	}
	
	/**
	 * 弓箭是否射完
	 */
	public boolean arrowFinish(){
		if(archerHeros.size()==0){
			return false;
		}
		else{
			for(Archer a:archerHeros){
				if(a.arrowFinish()){
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * 全屏大招
	 */
	public void Bigskill(){
		this.panelGame.realse=true;
	}
	
	/**
	 * 杀怪得钱
	 * 
	 * @param i
	 *            得到的钱
	 */
	public void addMoney(int i) {
		this.data.addMoney(i);
		this.coinBar.addGold(i);
	}

	/**
	 * 过场动画
	 */
	public void setNeedTransition(int i){
		panelGame.setNeedTranstion(i);
	}
	
	/**
	 * 是否完成过程动画的播放
	 */
	public boolean getDrawFinish(){
		return panelGame.getDrawFinish();
	}
	
	/**
	 * 进入下一关
	 */
	public void nextLV(){
		if(data.levelNow<=5){
			data.levelNow++;
			this.nowLevel++;
		}
		this.restart();
		this.frame.remove(panelWin);
		this.frame.setPanel(panelGame);
	}
	
	/**
	 * 获胜
	 */
	public void win(){
		if(data.level<=5&&data.level==data.levelNow)
			data.level++;
		this.frame.remove(panelGame);
		this.frame.setPanel(panelWin = new PanelWin(this));
	}
	
	/**
	 * Lose
	 */
	public void lose(){
		this.frame.remove(panelGame);
		this.frame.setPanel(panelLose = new PanelLose(this));
	}
	
	public boolean getNeedUpdateArcher(){
		return needUpdataArcher;
	}
	
	public void setNeedUpdateArcher(boolean needUpdateHPArcher){
		this.needUpdataArcher = needUpdateHPArcher;
	}
	
	
	/**
	 * 最右边一列有怪则不能刷怪
	 */
	public boolean canFreshEnemy(){
		for(Hero e:enemies){
			if(e.xl==6){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 刷新LevelUp界面
	 */
	public void refreshLevelUp(){
		this.panelLevelUp.refresh();
	}
	
	/**
	 * 弓箭手射箭掉血线程
	 */
	class ArcherAttack implements Runnable{
		
		@Override
		public void run() {
			while (true) {
				if (getNeedUpdateArcher() &&arrowFinish()) {
					updateHPArcher();
					setNeedUpdateArcher(false);
				}
				try {
					// 线程休眠100ms
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	/**
	 * 回合是否结束线程
	 */
	class RoundEnd implements Runnable{
		@Override
		public void run(){
			while(true){
				if (ui.button.Button.end<9)
					ui.button.Button.end++;
				try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			
		}
	}
	
}
