import java.awt.*;

public class Alian {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private int positionX;
    private int positionY;
    private final Color color = Color.YELLOW;
    private boolean death = false;
    protected final Bullet bullet = new Bullet();

    public Alian(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public void draw(Graphics g) {
        if(!death) {
            g.setColor(color);
            g.fillRect(positionX, positionY, WIDTH, HEIGHT);
        }
        if (bullet.isFired()){
            bullet.draw(g);
        }
    }

    public void move() {
        if(bullet.isFired()){
            bullet.move(-5);
        }
    }
}
