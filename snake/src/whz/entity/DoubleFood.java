package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import whz.util.Global;

public class DoubleFood extends Point{

	//所有食物的坐标
	private LinkedList<Point> food = new LinkedList();
	
	public LinkedList<Point> getFood() {
		return food;
	}

	// 绘制所有食物
	public void drawMyself(Graphics g) {
		// 遍历所有食物
		for(Point f:food) {
			// 绘制食物
			g.setColor(Color.red);
			g.fill3DRect(f.x*Global.CELL_SIZE, f.y*Global.CELL_SIZE,Global.CELL_SIZE , Global.CELL_SIZE, true);
		}
	}

	// 添加食物
	public void addFood(ArrayList<Point> p) {
		for(Point f: p) {
			food.addLast(f);
		}
	}

	// 判断食物是否被蛇吃掉
	public boolean isEatenBySnake(Point p){
		for(Point f:food) {
			if(f.x==p.x&&f.y==p.y) {
				// 如果蛇头与食物坐标相同，则说明吃到了，则去掉该食物的坐标
				food.remove(f);
				return true;
			}
		}
		return false;
	}
}
