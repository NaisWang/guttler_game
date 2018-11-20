package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import whz.util.Global;

public class DoubleFood extends Point{
	private LinkedList<Point> food = new LinkedList();
	
	public LinkedList<Point> getFood() {
		return food;
	}

	public void drawMyself(Graphics g) {

		for(Point f:food) {
			g.setColor(Color.red);
			g.fill3DRect(f.x*Global.CELL_SIZE, f.y*Global.CELL_SIZE,Global.CELL_SIZE , Global.CELL_SIZE, true);

		}
	}
	
	public void addFood(ArrayList<Point> p) {
		for(Point f: p) {
			food.addLast(f);
		}
	}
	
	public boolean isEatenBySnake(Point p){
		for(Point f:food) {
			if(f.x==p.x&&f.y==p.y) {
				food.remove(f);
				return true;
			}
		}
		return false;
	}
}
