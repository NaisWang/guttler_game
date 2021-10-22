//package whz.test;
//
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import whz.entity.*;
//
//import java.awt.*;
//import java.awt.event.KeyEvent;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DoubleControllerTest {
//
//
//    @Test
//    void start() {
//    }
//
//    @Test
//    void getPoint() {
//        // 创建食物对象
//        DoubleFood food = new DoubleFood();
//
//        // 创建蛇对象
//        Snake_1 snake_1 = new Snake_1();
//        Snake_2 snake_2 = new Snake_2();
//
//        //创建子弹对象
//        Bullets bullets = new Bullets();
//
//        //创建障碍物对象
//        DoubleBarrier doubleBarrier = new DoubleBarrier();
//
//        // 创建游戏面板
//        DoubleGamePanel doubleGamePanle = new DoubleGamePanel();
//        // 创建游戏控制器
//        DoubleController doubleController = new DoubleController(snake_1, snake_2, food, bullets, doubleGamePanle, doubleBarrier);
//
//        int result = doubleController.getPoint().size();
//        Assertions.assertEquals(5,result);
//
//
//    }
//
//    @Test
//    void keyPressed() {
//    }
//
//    @Test
//    void keyReleased() {
//    }
//
//    @Test
//    void snakeMove() {
//
//
//
//
//
//    }
//}