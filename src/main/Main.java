package main;

import control.GameController;
import ui.FrameGame;
import ui.PanelGame;
import ui.PanelStartPre;

/**
 * 游戏入口
 * 
 * @author BlowHair
 *
 */
public class Main {
	public static void main(String[] args) {
		FrameGame frame = new FrameGame();
		GameController gameController = new GameController(frame);
		// 创建游戏开始动画
		PanelStartPre panelStartPre = new PanelStartPre(frame, gameController);
		gameController.setPanelStartPre(panelStartPre);
		frame.setPanel(panelStartPre);
		
	}
}
