package whz.view;
import whz.test.SnakeGameTest;

import org.junit.After;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.junit.Before;
import org.junit.Test;
import whz.util.Global;

public class MainGameTest {

    private FrameFixture frameTest;

    private SnakeGameTest mySnakeGame = new SnakeGameTest();

    @Before
    public void setUp() {
        frameTest = new FrameFixture(mySnakeGame);
//        frameTest.show(); // 不要加这行代码，会使得整个frame变回原始大小（没有长宽）
    }

    @Test
    public void testSingleMode() {
//        监听单人模式按钮
        JButtonFixture singleBtn=frameTest.button("singleBtn");
        singleBtn.click();
        singleBtn.requireNotVisible();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testDoubleMode() {
        frameTest.button("doubleBtn").click();
    }

    @After
    public void tearDown() {
        frameTest.cleanUp();
    }
}