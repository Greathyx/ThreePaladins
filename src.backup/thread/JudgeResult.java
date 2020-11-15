package thread;

import control.GameController;

/**
 * 判断胜负的线�?
 * @author qzh
 *
 */
public class JudgeResult implements Runnable{
	/**
	 * 游戏控制�?
	 */
	GameController gameController;
	
	/**
	 * 是否已经响应
	 */
	boolean hasResponded = false;
	
	/**
	 * 构�?�函�?
	 */
	public JudgeResult(GameController gameController){
		this.gameController = gameController;
	}

	@Override
	public void run() {
		while (true) {
			if(gameController.getData().enemyFinish()&&gameController.enemies.size()==0&&!hasResponded){
				//过场动画
				gameController.setNeedTransition(1);
				if (gameController.getDrawFinish()) {
					// 显示获胜界面
					gameController.win();
					// 已经响应
					hasResponded = true;
					gameController.restart();
					gameController.setNeedTransition(0);
				}
			}
			else if(gameController.getData().Lose()&&!hasResponded){
				gameController.setNeedTransition(2);
				if (gameController.getDrawFinish()) {
					// 显示Lose界面
					gameController.lose();
					// 已经响应
					hasResponded = true;
					gameController.restart();
					gameController.setNeedTransition(0);
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
	/**
	 * 重新�?�?
	 */
	public void restart(){
		hasResponded = false;
	}

}
