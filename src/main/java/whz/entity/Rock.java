package whz.entity;

import whz.util.Global;

import java.awt.*;

public class Rock extends Entity {
    private int[][] rocks = new int[Global.DOUBLE_WIDTH][Global.DOUBLE_HEIGHT];

    public Rock() {
        setColor(Color.yellow);
    }

}
