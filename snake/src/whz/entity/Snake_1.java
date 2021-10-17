package whz.entity;

import java.awt.Point;

import whz.util.Global;

public class Snake_1 extends Snake {
	public static final int type =1;

	public Snake_1(){
		init();
	}

	public void init() {
		food = 0;
		body.clear();
		life = true;
		int x = 2;
		int y = 0;
		for (int i = 0; i < 4; i++) {
			body.add(new Point(x - i, y));
		}
		oldDirection = newDirection = Global.RIGHT;
	}

	public void start() {
		new Snake_1Drive().start();
	}
	
	private class Snake_1Drive extends Thread {
		@Override
		public void run() {
			while (life) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				move();
				snakeListener.snakeMove(Snake_1.this);
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
