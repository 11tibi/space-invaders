import java.awt.*;

public class Spaceship {

    private final static int WIDTH = 30;
    private final static int HEIGHT = 40;
    private static int position;
    public int direction = 0;

    public Spaceship(){
        position = (Board.SCREEN_WIDTH/2) - (WIDTH/2);
    }

    public static int getPosition() {
        return position;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public void draw(Graphics g) {
        if(Board.running){
            g.setColor(Color.GREEN);
            g.fillRect(position, Board.SCREEN_HEIGHT-42, WIDTH, HEIGHT);
        }
    }

    public void move(){
        if (direction == 1 && position < (Board.SCREEN_WIDTH - WIDTH)) {
            position += Board.UNIT_SIZE;
        } else if (direction == 2 && position > 0) {
            position -= Board.UNIT_SIZE;
        }
    }
}
