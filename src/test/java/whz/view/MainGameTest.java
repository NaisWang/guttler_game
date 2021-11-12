package whz.view;
import org.junit.jupiter.api.Assertions;
import whz.test.SnakeGameTest;

import org.junit.After;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JButtonFixture;
import org.fest.swing.fixture.JComboBoxFixture;
import org.fest.swing.fixture.JTextComponentFixture;
import org.junit.Before;
import org.junit.Test;
import whz.util.Global;

public class MainGameTest {

    private FrameFixture frameTest;
    private SnakeGameTest mySnakeGame = new SnakeGameTest();
    JButtonFixture singleBtn;
    JButtonFixture doubleBtn;
    JComboBoxFixture comboBox;
    JTextComponentFixture scoreText;
    JTextComponentFixture scoreTextTwo;

    @Before
    public void setUp() {
        frameTest = new FrameFixture(mySnakeGame);
//        frameTest.show(); // 不要加这行代码，会使得整个frame变回原始大小（没有长宽）
//        监听单人模式按钮
        singleBtn=frameTest.button("singleBtn");
        doubleBtn=frameTest.button("doubleBtn");
        comboBox=frameTest.comboBox("diffComboBox");
    }

//    单人模式测试脚本
    @Test
    public void testSingleMode() {
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//      ---------------------------------------------
//        点击单人模式按钮
        singleBtn.click();

//        游戏是否开始
        Assertions.assertTrue(Global.BEGIN);

//        是否处于单人模式
        Assertions.assertFalse(Global.DOUBLE);

//        主页面按钮和难度下拉框是否不可见
        singleBtn.requireNotVisible();
        doubleBtn.requireNotVisible();
        comboBox.requireNotVisible();

//        分数框是否显示
        scoreText=frameTest.textBox("scoreText");
        scoreText.requireVisible();
    }


//   双人模式测试脚本
    @Test
    public void testDoubleMode() {
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//      ---------------------------------------------
//        点击双人模式按钮
        doubleBtn.click();

//        游戏是否开始
        Assertions.assertTrue(Global.BEGIN);

//        是否处于双人模式
        Assertions.assertTrue(Global.DOUBLE);

//        主页面按钮和难度下拉框是否不可见
        singleBtn.requireNotVisible();
        doubleBtn.requireNotVisible();
        comboBox.requireNotVisible();

//        分数框是否显示
        scoreText=frameTest.textBox("scoreText");
        scoreText.requireVisible();
        scoreTextTwo=frameTest.textBox("scoreTextTwo");
        scoreTextTwo.requireVisible();
    }

//    测试单人模式的分数显示是否正确
    @Test
    public void testSingleScore() {
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//      ---------------------------------------------
//        点击双人模式按钮
        singleBtn.click();

//        分数框
        scoreText=frameTest.textBox("scoreText");
        scoreText.requireText("snake1 score: 0");
    }

//    测试双人模式的分数显示是否正确
    @Test
    public void testDoubleScore() {
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//      ---------------------------------------------
//        点击双人模式按钮
        doubleBtn.click();

//        分数框
        scoreText=frameTest.textBox("scoreText");
        scoreText.requireText("snake1 score: 0");
        scoreTextTwo=frameTest.textBox("scoreTextTwo");
        scoreTextTwo.requireText("snake2 score: 0");
    }

//    测试难度设置是否正确-单人模式
     @Test
     public void testDiffOneInSingle(){
//        游戏开始前的状态
         Assertions.assertFalse(Global.BEGIN);
         singleBtn.requireVisible();
         doubleBtn.requireVisible();
         comboBox.requireVisible();

//         设置难度
         comboBox.selectItem(0);
         singleBtn.click();
         Assertions.assertEquals(0,Global.DIFF);
     }

    //    测试难度设置是否正确-单人模式
    @Test
    public void testDiffTwoInSingle(){
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//         设置难度
        comboBox.selectItem(1);
        singleBtn.click();
        Assertions.assertEquals(1,Global.DIFF);
    }

    //    测试难度设置是否正确-单人模式
    @Test
    public void testDiffThreeInSingle(){
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//         设置难度
        comboBox.selectItem(2);
        singleBtn.click();
        Assertions.assertEquals(2,Global.DIFF);
    }

    //    测试难度设置是否正确-双人模式
    @Test
    public void testDiffOneInDouble(){
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//         设置难度
        comboBox.selectItem(0);
        doubleBtn.click();
        Assertions.assertEquals(0,Global.DIFF);
    }

    //    测试难度设置是否正确-双人模式
    @Test
    public void testDiffTwoInDouble(){
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//         设置难度
        comboBox.selectItem(1);
        doubleBtn.click();
        Assertions.assertEquals(1,Global.DIFF);
    }

    //    测试难度设置是否正确-单人模式
    @Test
    public void testDiffThreeInDouble(){
//        游戏开始前的状态
        Assertions.assertFalse(Global.BEGIN);
        singleBtn.requireVisible();
        doubleBtn.requireVisible();
        comboBox.requireVisible();

//         设置难度
        comboBox.selectItem(2);
        doubleBtn.click();
        Assertions.assertEquals(2,Global.DIFF);
    }


    @After
    public void tearDown() {
        frameTest.cleanUp();
        Global.BEGIN = false;
        Global.DOUBLE = false;
        Global.DIFF = 0;
    }
}