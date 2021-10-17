package whz.entity;

import java.awt.Point;

import whz.util.Global;

public class Snake_2 extends Snake {
	public static final int type =2;

	public Snake_2(){
		init();
	}

	public void init() {
		food = 0;
		body.clear();
		life = true;
		int x = 76;
		int y = 0;
		for (int i = 0; i < 4; i++) {
			body.add(new Point(x + i, y));
		}
		oldDirection = newDirection = Global.LEFT;
	}

	public void start() {
		new Snake_2Drive().start();
	}

	private class Snake_2Drive extends Thread {
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
				snakeListener.snakeMove(Snake_2.this);
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
