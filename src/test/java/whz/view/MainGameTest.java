package whz.view;
import whz.test.SnakeGameTest;

import org.junit.After;
import org.fest.swing.fixture.FrameFixture;
import org.junit.Before;
import org.junit.Test;
import whz.util.Global;

public class MainGameTest {

    private FrameFixture frameTest;

    @Before
    public void setUp() {
        frameTest = new FrameFixture(new SnakeGameTest());
        frameTest.show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSingleMode() {
        System.out.println(frameTest.button("singleBtn"));
        frameTest.button("singleBtn").click();
    }

    @Test
    public void testDoubleMode() {
        frameTest.button("doubleBtn").click();
    }

//    @After
//    public void tearDown() {
//        frameTest.cleanUp();
//    }
}