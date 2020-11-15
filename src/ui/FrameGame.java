package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 游戏框架
 */
public class FrameGame extends JFrame implements MouseListener {

	/**
	 * 窗体宽度
	 */
	static final int WINDOW_WIDTH = 1024;

	/**
	 * 窗体高度
	 */
	static final int WINDOW_HEIGHT = 576;

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
	 * 游戏窗体
	 */
	public FrameGame() {
		// 去掉默认的边框
		this.setUndecorated(true);
		
		// 创建透明色
		this.setBackground(new Color(0, 0, 0, 0.0f));
		
		// 设置标题
		this.setTitle("TODO");

		// 默认关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置大小
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

		// 设置不可改变窗体大小
		this.setResizable(false);

		// 设置位置居中
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = screen.width - this.getWidth() >> 1;
		int y = (screen.height - this.getHeight() >> 1) - 16;
		this.setLocation(x, y);

		// 初始化鼠标
		cursor(cursorBefore, nameBefore);
		addMouseListener(this);
	}

	/**
	 * 设置面板
	 */
	public void setPanel(JPanel panel) {
		this.add(panel);
		this.setVisible(true);
	}

	/**
	 * 设置鼠标
	 */
	public void cursor(Image imgCursor, String name) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor cursor = toolkit.createCustomCursor(imgCursor, new Point(10, 10), name);
		this.setCursor(cursor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.cursor(cursorAfter, nameAfter);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.cursor(cursorBefore, nameBefore);
	}

}
