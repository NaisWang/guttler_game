package whz.entity;

import whz.util.Global;

import java.awt.*;

public class Rock extends Entity {
    private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];

    public Rock() {
        setColor(Color.yellow);
    }

}
