package whz.entity;

import whz.util.Global;

import java.awt.*;
import java.awt.geom.Point2D;

public class Entity extends Point {
    private Color color;

    public Entity() {

    }

    public Entity(int x, int y) {
        super(x, y);
    }

    // 渲染该实体
    public void drawMyself(Graphics g) {
        g.setColor(color);
        g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE , Global.CELL_SIZE, true);
    }

    // 判断两点是否相同
    public boolean isHit(Point point) {
        return point.equals(this);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
