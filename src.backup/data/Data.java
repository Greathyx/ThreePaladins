package data;


import java.io.IOException;
import java.util.Random;

import role.Hero;

/**
 * 游戏数据（和游戏逻辑）�?��?�架构师。�?�没架构好，混在�?起吧�?
 */
public class Data {
	/**
	 * 剑士基础属�?�（1级）
	 * (life,attack)
	 */
	private int[] BASE_FENCER = {100,15};
	
	/**
	 * 弓箭手基�?属�?�（1级）
	 * (life,attack)
	 */
	private int[] BASE_ARCHER = {40,15};

	/**
	 * 牧师基础属�?�（1级） 
	 * (life.heal&attack&heal)
	 */
	private int[] BASE_PRIEST = {30,20,10};
	
	/**
	 * 怪物基础属�??
	 * (life,attack,gold)
	 */
	private int[][] BASE_ENEMIES = {{180,12,120},
			{160,15,100},
			{120,10,80}
	};
	
	/**
	 * 玩家�?�?
	 */
	int life;
	
	/**
	 * 当前回合�?
	 */
	public int round ;
	
	/**
	 * 金币面板里的随机�?(1~6)
	 */
	public int randomNum = 0;
	
	/**
	 * 地图数组 
	 * 0-没东�?
	 * 1-英雄
	 * 9-怪物【我不知道有几种怪物�? TODO
	 */
	int[][] map = new int[7][3];
	
	/**
	 * 当前金钱 TODO
	 */
	public int gold;

	/**
	 * 能量�?
	 */
	int energyValue;
	
	/**
	 * 怪物总的波数 TODO
	 */
	int wavesTotal = 10;
	
	/**
	 * 当前波数
	 */
	int waveNow;
	/**
	 * 是否点击了剑士图�?
	 */
	boolean isChoosedFencer;
	
	/**
	 * 是否点击了弓箭手图片
	 */
	boolean isChoosedArcher;
	
	/**
	 * 是否点击了魔法师图片
	 */
	boolean isChoosedPriest;
	boolean isChoosedPriest1;
	boolean isChoosedPriest2;

	/**
	 * 是否绘制游戏说明
	 * 1代表剩余怪物波数
	 * 2代表行动点数
	 */
	boolean isEntered1;
	boolean isEntered2;
	boolean isEntered3;
	boolean isEntered4;
	
	/**
	 * 设置�?个布尔数组，存放对应路是否有弓箭手存�?
	 */
	public boolean[] archerExist = new boolean[3];

	/**
	 * 设置�?个布尔数组，存放对应路是否有敌人存在
	 */
	public boolean[] enemyExist = new boolean[3];
	
	/**
	 * 当前行动点数
	 */
	public int actionPoint;
	
	/**
	 * 每回合初始行动点�?
	 */
	private int actionPointOrigin = 7;
	
	/**
	 * 当前可以进行的关卡数
	 */
	public int level ;
	
	/**
	 * 当前关卡
	 */
	public int levelNow;
	
	/**
	 * 当前剑士等级
	 */
	public int levelOfFencer;
	
	/**
	 * 当前弓箭手等�?
	 */
	public int levelOfArcher;
	
	/**
	 * 当前牧师等级
	 */
	public int levelOfPriest;
	
	/**
	 * 存档�?
	 */
	private Saver saver;
	
    /**
	 * 是否可以按go按钮
	 */
	boolean canPressedGo = false;
	
	/**
	 * 剑士属�??
	 * (life,attack)
	 */
	private int[] statisticsOfFencer = new int[2];
	
	/**
	 * 弓箭手属�?
	 * (life,attack)
	 */
	private int[] statisticsOfArcher = new int[2];

	/**
	 * 牧师属�?? TODO
	 * 【暂时还不知道有些什么属性�??
	 */
	private int[] statisticsOfPriest = new int[2];
	
	/**
	 * 怪物属�??
	 * (life,attack,gold)
	 */
	private int[][] statisticsOfEnemies = new int[2][2];
	
	/**
	 * 数据初始化[在开�?时重置游戏数据]
	 * @throws IOException 
	 */
	public Data() {
		initdata();
		try {
			saver = new Saver(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		archerExist[0] = false;
//		archerExist[1] = false;
//		archerExist[2] = false;
	}
	
	/**
	 * 重新�?�?
	 */
	public void initdata(){
		this.MapZero();
		actionPoint = 7;
		round = 0;
		life = 15;
		isChoosedArcher = false;
		isChoosedPriest = false;
		isChoosedFencer = false;
		energyValue = 7;
		waveNow = 0;
	}

	/**
	 * @return 地图数组
	 */
	public int[][] getMap() {
		return map;
	}

	/**
	 * @param 地图数组 
	 * TODO 可以设置几张地图
	 */
	public void setMap(int[][] map) {
		this.map = map;
	}
	

	
	/**
	 * 回合结束，回合数�?1
	 */
	public void endRound(){
		this.round++;
	}
	
	/**
	 * 地图数据清零
	 */
	public void MapZero(){
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				map[i][j] = 0;
			}
		}
	}
	

	/**
	 * 得到�?蓄的能量�?
	 * 
	 * @return
	 */
	public int getEnergyValue() {
		return energyValue;
	}

	/**
	 * @param 设置能量槽�??
	 */
	public void setEnergyValue(int energyValue) {
		this.energyValue = energyValue;
	}
	

	public boolean getIsChoosedFencer() {
		return isChoosedFencer;
	}
	public void setIsChoosedFencer(boolean isChoosedFencer) {
		this.isChoosedFencer = isChoosedFencer;
	}
	
	public boolean getIsChoosedAcher() {
		return isChoosedArcher;
	}
	public void setIsChoosedAcher(boolean isChoosedAcher) {
		this.isChoosedArcher = isChoosedAcher;
	}
	
	public boolean getIsChoosedPriest() {
		return isChoosedPriest;
	}
	public void setIsChoosedPriest(boolean isChoosedPriest) {
		this.isChoosedPriest = isChoosedPriest;
	}
	
	public boolean getIsChoosedPriest1() {
		return isChoosedPriest1;
	}
	public void setIsChoosedPriest1(boolean isChoosedPriest1) {
		this.isChoosedPriest1 = isChoosedPriest1;
	}
	
	public boolean getIsChoosedPriest2() {
		return isChoosedPriest;
	}
	public void setIsChoosedPriest2(boolean isChoosedPriest2) {
		this.isChoosedPriest2 = isChoosedPriest2;
	}
	
	/**
	 * 获得英雄（或怪物）坐�?
	 * @param type
	 * 种类
	 * 1-英雄
	 * 9-怪物
	 */
	public void setHero(int xl, int yl, int type){
		if(xl<7)
			this.map[xl][yl] = type;
	}
	
	public void setEnermy(int xl, int yl, int type){
		if(xl<7)
			this.map[xl][yl] = type;
	}

	/**
	 * 
	 * @return 
	 * 剩余波数
	 */
	public int waveLeft(){
		return wavesTotal-waveNow;
	}

	//这里�?始写游戏逻辑�?
	/**
	 * 是否可以攻击
	 */
	public boolean canAttack(Hero hero){
		if (hero.ID == 1) {
			if (map[hero.xl + 1][hero.yl] == 9)
				return true;
			else
				return false;
		}
		else if(hero.ID==9){
			if(hero.xl>=1){
			if (map[hero.xl - 1][hero.yl] == 1)
				return true;
			else if (map[hero.xl - 1][hero.yl]==2)
				return true;
			else
				return false;
			}
			else{
				return true;
			}
		}
		else{
			for(int i=0;i<7;i++){
				if(map[i][hero.yl] == 9){
					return true;
				}
			}
			return false;
		}
	}
	
	/**
	 * 是否准备🐓
	 */
	public boolean readyAttack(Hero hero){
		if(map[hero.xl+2][hero.yl]==9)
			return true;
		else 
			return false;
	}
	
	/**
	 * 是否可以移动
	 */
	public boolean canMove(Hero hero){
		//英雄判断
		if (hero.ID == 1) {
			if(canAttack(hero)){
				return false;
			}
			//在�?�数第二格停�?
			if (hero.xl < 5) {
				if(hero.xl==1)
					return true;
				else if (map[hero.xl + 1][hero.yl] == 9 || (map[hero.xl + 1][hero.yl] == 1
						&&!canMove(hero.xl +1, hero.yl, 1)
						))
					return false;
				else
					return true;
			} else {
				return false;
			}
		}
		//怪物判断
		else{
			if (hero.xl  >= 2) {
				if(hero.xl==7)
					return true;
				else if (map[hero.xl - 1][hero.yl] == 1 ||
//						map[hero.xl - 2][hero.yl] == 1|| 
						(map[hero.xl - 1][hero.yl] == 9&&!canMove(hero.xl - 1, hero.yl, 9)))
					return false;
				else if (map[hero.xl - 1][hero.yl]==2)
					return false;
				else
					return true;
			} else if(hero.xl==1&&map[0][hero.yl]==0){
				return true;
			}else
				return false;
		}
		
	}
	
	/**
	 * 是否可以移动重载（传入坐标与ID�?
	 */
	public boolean canMove(int xl, int yl, int ID){
		//英雄判断
		if (ID == 1) {
			
			if(xl==1)
				return true;
			else if (xl < 5) {
				if (map[xl + 1][yl] == 9 
						|| (map[xl + 1][yl] == 1&&!canMove(xl +1, yl, 1))
						)
					return false;
	
				else
					return true;
			} else {
				return false;
			}
		}
		//怪物判断
		else{
			if (xl  >= 2) {
				if(xl==7)
					return true;
				else if (map[xl - 1][yl] == 1 || 
//						map[xl - 2][yl] == 1||
						(map[xl - 1][yl] == 9&&!canMove(xl - 1, yl, 9)))
					return false;
				else if (map[xl - 1][yl]==2)
					return false;
				else
					return true;
			} else if(xl==1&&map[0][yl]==0){
				return true;
			}else
				return false;
		}
		
	}
	
	/**
	 * 消�?�能�?
	 * @param i
	 * 消�?�的能量
	 */
	public void useEnergy(int i){
		this.energyValue-=i;
	}
	
	/**
	 * 消�?�能�?
	 * @param i
	 * 消�?�的能量
	 */
	public void useActionPoint(int i){
		this.actionPoint-=i;
	}
	
	public void recoverActionPoint(int i){
		this.actionPoint+=i;
	}
	/**
	 * 能量足够
	 * @param i
	 * 消�?�的能量
	 */
	public boolean enoughEnergy(int i){
		boolean res = (this.energyValue-i)>= 0;
		return res;
	}
	
	/**
	 * 行动点数足够
	 * @param i
	 * 消�?�的行动点数
	 */
	public boolean enoughActionPoint(int i){
		boolean res = (this.actionPoint-i)>=0;
		return res;
	}
	/**
	 * 扣血
	 * @param i
	 * 减少的血�?
	 */
	public void removeHp(int i){
		this.life-=i;
	}
	
	/**
	 * �?怪得�?
	 * @param i
	 * 得到的钱
	 */
	public void addMoney(int i){
		this.gold+=i;
	}

	public int getLife() {
		return life;
	}
	
	public void setHP(int i){
		this.life = i;
	}
	
	public int getActionPoint(){
		return actionPoint;
	}
	
	/**
	 * 刷完怪后波数�?1
	 */
	public void waveFresh(){
		this.waveNow+=1;
	}
	
	/**
	 * 怪物是否刷完
	 */
	public boolean enemyFinish(){
		return waveNow>=wavesTotal;
	}

	/**
	 * 是否Lose
	 */
	public boolean Lose(){
		return life<=0;
	}
	
	/**
	 * 读档
	 */
	public void load(){
		try {
			saver.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 存档
	 */
	public void save(){
		try {
			saver.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 重新刷新行动点数
//	 */
//	public void refreshActionPoint(){
//		this.actionPoint = actionPointOrigin;
//	}
	
	public void setRandomNum() {
		Random random = new Random();
		this.randomNum = random.nextInt(6) + 1;
	}
	public int getRandomNum() {
		return this.randomNum;
	}
	
	public void setCanPressedGo(boolean canPressedGo) {
		this.canPressedGo = canPressedGo;
	}
	public boolean getCanPressedGo() {
		return this.canPressedGo;
	}
	
	public void setIsEntered1(boolean isEntered1) {
		this.isEntered1 = isEntered1;
	}
	public boolean getIsEntered1() {
		return this.isEntered1;
	}
	
	public void setIsEntered2(boolean isEntered2) {
		this.isEntered2 = isEntered2;
	}
	public boolean getIsEntered2() {
		return this.isEntered2;
	}
	
	public void setIsEntered3(boolean isEntered3) {
		this.isEntered3 = isEntered3;
	}
	public boolean getIsEntered3() {
		return this.isEntered3;
	}
	
	public void setIsEntered4(boolean isEntered4) {
		this.isEntered4 = isEntered4;
	}
	public boolean getIsEntered4() {
		return this.isEntered4;
	}
	
	/**
	 * 设置人物攻击
	 * @param object
	 * 对象
	 * @param levelNow
	 * 当前等级
	 */
	public int setStatistics(String object, int levelNow){
		if(object.equals("fencer")){
			return BASE_FENCER[0] + BASE_FENCER[0] * levelNow / 4;
		}
		else if(object.equals("archer")){
			return BASE_ARCHER[1] + BASE_ARCHER[1] * levelNow / 4;
		}
		else if(object.equals("priestAttack")){
			return BASE_PRIEST[1] + BASE_PRIEST[1] * levelNow / 4;
		}
		else if(object.equals("priestHeal")){
			return BASE_PRIEST[2] + BASE_PRIEST[2] * levelNow / 4;
		}
		else {
			return 0;
		}
		
	}
	
	/**
	 * 设置怪物
	 */
	public int setEnemies(String enemyName,int dataKind, int levelNow){
		if(enemyName.equals("werewolf")){
			return BASE_ENEMIES[0][dataKind] + BASE_ENEMIES[0][dataKind] *levelNow/ 5;
		}
		else if(enemyName.equals("whitewolf")){
			return BASE_ENEMIES[1][dataKind] + BASE_ENEMIES[1][dataKind] *levelNow/ 5;
		}
		else if(enemyName.equals("penguin")){
			return BASE_ENEMIES[2][dataKind] + BASE_ENEMIES[2][dataKind] *levelNow/ 5;
		}
		else{
			return 0;
		}
	}
}
