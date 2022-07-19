import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.Random;

public class Board {
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int DELAY = 10;
    public static final int UNIT_SIZE = 10;
    public static final Color BOARD_COLOR = Color.BLACK;
    public static final Random random = new Random();
    public static Timer timer;
    public static boolean running = false;
    public static long startTime = System.nanoTime();
    public static long endTime;
}
