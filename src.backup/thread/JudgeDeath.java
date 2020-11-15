package thread;

import control.GameController;

/**
 * 判断地图上人物和怪物是否应该消失
 * @author mac
 *
 */

public class JudgeDeath implements Runnable {
	
	/**
	 * 游戏控制�?
	 */
	public GameController gameController;
	
	public JudgeDeath(GameController gameController) {
		// TODO Auto-generated constructor stub
		this.gameController = gameController;
	}
		
	@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				/**
				 * 判断弓箭手是否死亡（只射三箭�?
				 */
				for(int i=0;i<gameController.archerHeros.size();i++){
					//用一个整数变量同步更新数组大�?
//					int length
					if(!gameController.archerHeros.get(i).existence){
						gameController.archerHeros.remove(i);
					}
				}
				
				//测试代码	
				for(int i=0;i<gameController.archerHeros.size();i++){
//					System.out.println("");
				}
//				System.out.print("");
				
			
				
				/**
				 * 判断怪物是否死亡
				 */
				for(int i=0;i<gameController.enemies.size();i++){
					if(gameController.enemies.get(i).heroHP<=0){
						gameController.enemies.remove(i);
						
					}
				}
				
			}
			
		}
}
