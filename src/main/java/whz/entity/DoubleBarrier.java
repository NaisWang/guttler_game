package whz.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import whz.util.Global;

public class DoubleBarrier{
	public LinkedList<Barriers> barriers = new LinkedList();
	
	public class Barriers extends Point{
		private int from;
		
		public int getFrom() {
			return from;
		}
		
		public Barriers(int from, Point p) {
			this.from = from;
			x = p.x;
			y = p.y;
		}
		
		public void drawMyself(Graphics g) {
			if(from == 1){
				g.setColor(Color.GRAY);
			}else{
				g.setColor(Color.green);
			}
			g.fill3DRect((int)this.getX() * Global.CELL_SIZE, (int) this.getY() * Global.CELL_SIZE, Global.CELL_SIZE,
					Global.CELL_SIZE, true);
		}
	}
}
