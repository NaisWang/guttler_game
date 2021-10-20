package whz.control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import whz.entity.*;
import whz.view.DoubleGamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class DoubleControllerTest {

    @Test
    void keyPressed() {
        DoubleFood food = new DoubleFood();
        // 创建蛇对象
        Snake_1 snake_1 = new Snake_1();
        snake_1.eatFood();
        snake_1.eatFood();
        Snake_2 snake_2 = new Snake_2();
        //创建子弹对象
        Bullets bullets = new Bullets();
        //创建障碍物对象
        DoubleBarrier doubleBarrier = new DoubleBarrier();
        // 创建游戏面板
        DoubleGamePanel doubleGamePanle = new DoubleGamePanel();
        // 创建游戏控制器
        DoubleController doubleController = new DoubleController(snake_1, snake_2, food, bullets, doubleGamePanle, doubleBarrier);

        KeyEvent keyEvent = new KeyEvent(new Component() { }, 0, 0, 0, 0);

        int sizeBefore_1 =  snake_1.body.size();
        keyEvent.setKeyCode(KeyEvent.VK_F);
        doubleController.keyPressed( keyEvent );
        int sizeAfter_1 =  snake_1.body.size();
        Assertions.assertEquals(sizeAfter_1,sizeBefore_1 - 1);

        int sizeBefore_2 =  snake_2.body.size();
        keyEvent.setKeyCode(KeyEvent.VK_N);
        doubleController.keyPressed( keyEvent );
        int sizeAfter_2 =  snake_2.body.size();
        Assertions.assertNotEquals(sizeAfter_2,sizeBefore_2 - 1);

    }
}