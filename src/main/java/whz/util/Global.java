package whz.util;

public class Global {
	/*单元格的大小*/
	public static final int CELL_SIZE = 15;
//	public static final int DOUBLE_WIDTH = 20;
//	public static final int DOUBLE_HEIGHT = 20;
	public static final int DOUBLE_WIDTH = 40;
	public static final int DOUBLE_HEIGHT = 40;
	public static final int UP_LEFT = 3;
	public static final int DOWN_RIGHT= -3;
	public static final int UP_RIGHT = 4;
	public static final int DOWN_LEFT = -4;
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	public static final int F_WIDTH = DOUBLE_WIDTH * CELL_SIZE;
	public static final int F_HEIGHT = DOUBLE_HEIGHT * CELL_SIZE;
	public static boolean BEGIN = false;
	public static boolean DOUBLE = false;
	public static int DIFF = 0;
}
