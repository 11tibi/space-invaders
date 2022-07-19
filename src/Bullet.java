import java.awt.*;

public class Bullet {
    private final int WIDTH = 5;
    private final int HEIGHT = 10;
    private final int SPEED = 5;
    private int bulletX;
    private int bulletY;
    private boolean fired = false;

    public void setBulletX(int bulletX) {
        this.bulletX = bulletX;
    }

    public void setBulletY(int bulletY) {
        this.bulletY = bulletY;
    }

    public int getBulletX() {
        return bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public void move(){
        if (fired) {
            bulletY -= SPEED;
        }
        if (bulletY <= 0){
            fired = false;
        }
    }

    public void move(int speed){
        if (fired) {
            bulletY -= speed;
        }

        if (bulletY >= Board.SCREEN_HEIGHT && speed < 0 || bulletY <= 0 && speed > 0){
            fired = false;
        }
    }

    public void draw(Graphics g){
        if (fired){
            g.setColor(Color.RED);
            g.fillRect(bulletX, bulletY, WIDTH, HEIGHT);
        }
    }
}
