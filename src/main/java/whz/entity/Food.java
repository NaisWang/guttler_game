package whz.entity;

import java.awt.*;

public class Food extends Entity {
    // 判断食物是否被蛇吃掉
    public boolean isEatenBySnake(Point p){
        return isHit(p);
    }

    public Food() {
        setColor(Color.red);

    }

    public Food(int x, int y) {
        super(x, y);
        setColor(Color.red);
    }
}
