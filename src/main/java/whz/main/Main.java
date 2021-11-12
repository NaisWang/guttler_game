package whz.main;

import javax.swing.JFrame;
import javax.swing.JTextField;

import whz.entity.*;
import whz.util.Global;
import whz.test.SnakeGameTest;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    private static JTextField grade;

    public static void main(String[] args) {
        SnakeGameTest mainGame=new SnakeGameTest();

        while (true) {
            if (!Global.BEGIN) {
                System.out.println(Global.BEGIN);
                continue;
            }

            if (mainGame.gamePanel.getSnakes().size() < 2 && Global.DOUBLE) {
                mainGame.addSnake2();
//                mainGame.gamePanel.addSnake(snake2);
//                mainGame.addKeyListener(snake2.keyAdapter);
            }

            mainGame.baseController.checkAndPaint();
            try {
                System.out.println(Global.DIFF);
                switch (Global.DIFF) {
                    case 1:
                        Thread.sleep(100);
                        break;
                    case 2:
                        Thread.sleep(50);
                        break;
                    case 0:
                    default:
                        Thread.sleep(150);
                        break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}






