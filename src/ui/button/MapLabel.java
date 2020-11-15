package ui.button;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.GameController;
import role.Archer;
import role.Hero;
import role.Priest;
import ui.PanelGame;

/**
 * 对地图进行监听
 */
public class MapLabel extends JLabel implements MouseListener {

	/**
	 * 游戏控制器
	 */
	GameController gameController;

	/**
	 * 地图块m坐标
	 */
	int m;

	/**
	 * 地图块n坐标
	 */
	int n;

	/**
	 * 游戏主界面
	 */
	PanelGame panelGame;

	/**
	 * 建造的塔的类型
	 */
	public static int type;

	/**
	 * 是否要建造士兵
	 */
	boolean setHero;
	
	
	/**
	 * 剑士需要的能量 TODO
	 */
	private static final int VALUE_FENCER = 2;

	/**
	 * 弓箭手需要的能量 TODO
	 */
	private static final int VALUE_ARCHER = 4;

	/**
	 * 牧师需要的能量 TODO
	 */
	private static final int VALUE_PRIEST = 3;
	
	/**
	 * 获得地图信息
	 */
	private int[][] map;

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
	 * 地图构造函数
	 * 
	 * @param m
	 *            m坐标
	 * @param n
	 *            n坐标
	 * @param map_x
	 *            地图左上角x坐标
	 * @param map_y
	 *            地图左上角y坐标
	 */
	public MapLabel(int m, int n, int map_x, int map_y, PanelGame panelGame, GameController gameController) {
		this.setBounds(map_x + m * (PanelGame.MAP_WIDTH + PanelGame.GAP_WIDTH) - n * PanelGame.GAP_WIDTH,
				map_y + n * PanelGame.GAP_HEIGHT, PanelGame.MAP_WIDTH, PanelGame.MAP_HEIGHT);
		this.addMouseListener(this);
		this.m = m;
		this.n = n;
		this.panelGame = panelGame;
		this.type = 0;
		this.setHero = false;
		this.gameController = gameController;
		this.map = gameController.getData().getMap();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		map = gameController.getData().getMap();
		// this.setHero = true;
		gameController.getData().setIsChoosedFencer(false);
		gameController.getData().setIsChoosedAcher(false);
		gameController.getData().setIsChoosedPriest(false);
		//造兵
		if(type==1){
			if(m==1&&gameController.getData().enoughEnergy(VALUE_FENCER)&&gameController.getData().enoughActionPoint(1)&&map[m][n]==0){
				//耗能
				gameController.useEnergy(VALUE_FENCER);
				gameController.useActionPoint(1);
				//造兵
				Hero hero;
				gameController.fencerHeros.add(hero = new Hero(31, "Fencer", m, n, gameController.getData()));
				//更新地图数组
				gameController.getData().setHero(m, n, 1);

				switch (n) {
				case 0:
					gameController.roadOne.add(gameController.fencerHeros.get(gameController.fencerHeros.size() - 1));
					break;
				case 1:
					gameController.roadTwo.add(gameController.fencerHeros.get(gameController.fencerHeros.size() - 1));
					break;
				case 2:
					gameController.roadThree.add(gameController.fencerHeros.get(gameController.fencerHeros.size() - 1));
					break;
				default:
					break;
				}
			}
			else if(!gameController.getData().enoughEnergy(VALUE_FENCER)){
				panelGame.needUpdateTips = 1;
			}
			else if(!gameController.getData().enoughActionPoint(1)){
				panelGame.needUpdateTips = 3;
			}
			else if(map[m][n]!=0){
				panelGame.needUpdateTips = 4;
			}
		}

		if(type==2){
			if(m==0&&gameController.getData().enoughEnergy(VALUE_ARCHER)&&gameController.getData().enoughActionPoint(1)&&map[m][n]==0){
				//耗能
				gameController.useEnergy(VALUE_ARCHER);
				gameController.useActionPoint(1);
				// 造兵
				gameController.archerHeros.add(new Archer(1, "箭", m + 1, n, gameController.getData()));
				// 将该路设置成存在弓箭手
				gameController.getData().archerExist[n] = true;
				// 更新地图数组
				gameController.getData().setHero(0, n, 2);

			}else if(!gameController.getData().enoughEnergy(VALUE_ARCHER)){
				panelGame.needUpdateTips = 1;
			}
			else if(!gameController.getData().enoughActionPoint(1)){
				panelGame.needUpdateTips = 3;
			}
			else if(map[m][n]!=0){
				panelGame.needUpdateTips = 4;
			}
		}
		if(type==3&&gameController.placePriest){
			if(m==1&&gameController.getData().enoughEnergy(VALUE_PRIEST)&&gameController.getData().enoughActionPoint(1)&&map[m][n]==0){
				//耗能
				gameController.useEnergy(VALUE_PRIEST);
				gameController.useActionPoint(1);
				//造兵
				gameController.priestHeros.add(new Priest(20, "Priest", m, n, gameController.getData()));
				gameController.priestHeros.get(gameController.priestHeros.size()-1).kind=gameController.pkind;
				//更新地图数组
				gameController.getData().setHero(m, n, 1);
				
				gameController.placePriest=false;

				switch (n) {
				case 0:
					gameController.roadOne.add(gameController.priestHeros.get(gameController.priestHeros.size() - 1));
					break;
				case 1:
					gameController.roadTwo.add(gameController.priestHeros.get(gameController.priestHeros.size() - 1));
					break;
				case 2:
					gameController.roadThree.add(gameController.priestHeros.get(gameController.priestHeros.size() - 1));
					break;
				default:
					break;
				}
			}else if(!gameController.getData().enoughEnergy(VALUE_PRIEST) && ((gameController.getData().getIsChoosedPriest1()) || (gameController.getData().getIsChoosedPriest2()))){
				panelGame.needUpdateTips = 1;
			}
			else if(!gameController.getData().enoughActionPoint(1)){
				panelGame.needUpdateTips = 3;
			}
			else if(map[m][n]!=0){
				panelGame.needUpdateTips = 4;
			}
		}

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
		// 设置按住鼠标的图片
		gameController.frame.cursor(cursorAfter, nameAfter);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// 设置松开鼠标的图片
		gameController.frame.cursor(cursorBefore, nameBefore);
	}

}
