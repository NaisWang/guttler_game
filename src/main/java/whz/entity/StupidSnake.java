package whz.entity;

import whz.util.Global;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class StupidSnake extends Entity {
    //�ߵĵ�ǰ������Ҫ����ķ���
    public int direction = Global.DOWN;

    // ��β������
//    public Point tail;
    // �Ե�ʳ��ĸ���
    public int food = 0;

    //���Ƿ���
    public boolean life = true;

//    public boolean key = true;

    // �߼������������ߵ��ƶ�
//    public SnakeListener snakeListener;

    // ����������нڵ��Ӧ������
    public LinkedList<Point> body = new LinkedList();

    // ��ÿ���ƶ��ļ��ʱ��
    public int interval = 150;

    // �ߵ�����
    public String name = "snake0";

    // �����Ӧ�İ���
    public int LEFT_KEY = KeyEvent.VK_LEFT;
    public int RIGHT_KEY = KeyEvent.VK_RIGHT;
    public int UP_KEY = KeyEvent.VK_UP;
    public int DOWN_KEY = KeyEvent.VK_DOWN;



    public StupidSnake(String name, Color color, Point pos) {
        setColor(color);
        this.name = name;
        body.push(pos);
    }

    // ��������
    public KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {

            var key = e.getKeyCode();
            System.out.println("�㰴����:" + key);
            var newDirection = direction;

            if (key == LEFT_KEY) {
                newDirection = Global.LEFT;
            } else if (key == RIGHT_KEY) {
                newDirection = Global.RIGHT;
            } else if (key == UP_KEY) {
                newDirection = Global.UP;
            } else if (key == DOWN_KEY) {
                newDirection = Global.DOWN;
            }

            // ����ͻȻ������Ե��Լ�
            if (body.size() > 1 && getNext(newDirection).equals(body.get(1))) {
              return;
            }

            setDirection(newDirection);
        }
    };


    // ���ƶ�
    public void move(boolean eatFood) {
        // ��ȡ��ͷ��(x, y)����
        var head = getNext();
        body.addFirst(head);

        // ���Ե�ʳ�����β��
        if (!eatFood) {
//            tail = body.removeLast();
            body.removeLast();
        } else {
            food++;
        }

    }

    // ��ȡ��ͷ����
    public Point getHead() {
        return body.getFirst();
    }

    // ��ȡҪ�ƶ�����һ�����꣬�Ƿ�ײǽ��ײ�������Controller�ж�
    public Point getNext() {
        return getNext(direction);
    }

    public Point getNext(int direction) {
        var head = getHead();
        var nextHead = head;
        var x = head.x;
        var y = head.y;
        switch (direction) {
            case Global.UP:
                y--;
                break;
            case Global.DOWN:
                y++;
                break;
            case Global.LEFT:
                x--;
                break;
            case Global.RIGHT:
                x++;
                break;
        }
        x = (x + Global.DOUBLE_WIDTH) % Global.DOUBLE_WIDTH;
        y = (y + Global.DOUBLE_HEIGHT) % Global.DOUBLE_HEIGHT;

        nextHead = new Point(x, y);
        return nextHead;
    }

    // �ж��Ƿ�Ե��Լ�
    public boolean isEatSelf() {
        var head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(head)) {
//                System.out.println("Snake is eaten by itself");
                return true;
            }
        }
        return false;
    }

    // �жϱ�����Ƿ���������
    @Override
    public boolean isHit(Point point) {
        for (var b : body) {
            if (b.equals(point)) {
                return true;
            }
        }
        return false;
    }

    // ������
    @Override
    public void drawMyself(Graphics graphics) {
        graphics.setColor(Color.blue);
        // �����ߵ����нڵ�����
        for (int i = 0; i < body.size(); i++) {
            // �����ߵĽڵ�
            if (body.get(i) != null) {
                graphics.fillRoundRect((int) body.get(i).x * Global.CELL_SIZE, (int) body.get(i).y * Global.CELL_SIZE, Global.CELL_SIZE,
                        Global.CELL_SIZE, 10, 10);
            }
        }

        if (!body.isEmpty()) {
            graphics.setColor(getColor());
            graphics.fillRoundRect((int) body.getFirst().getX() * Global.CELL_SIZE,
                    (int) body.getFirst().getY() * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, 10, 10);
        }

    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


}

