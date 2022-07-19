import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Enemy {
    private static final int SPACE = 10;
    private static final int numberOfLines = 2;
    private static final int numberOfEnemy = (Board.SCREEN_WIDTH - Board.SCREEN_WIDTH/4) / (Alian.getWIDTH()+10) * numberOfLines;
    protected Alian[] aliens;
    private char direction = 'R';
    private int positionX;
    private int positionY;
    private final Bullet bullet;
    private static final ArrayList<Integer> pos = new ArrayList<Integer>();

    public Enemy(Bullet bullet) {
        this.bullet = bullet;
        positionX = 5;
        positionY = 30;
        aliens = new Alian[numberOfEnemy];
        for(int i=0; i<numberOfLines; i++){
            for(int j=0; j<(numberOfEnemy/numberOfLines); j++){
                aliens[j+(i*(numberOfEnemy/numberOfLines))] = new Alian(positionX, positionY);
                positionX += Alian.getWIDTH() + SPACE;
            }
            positionX = 5;
            positionY += Alian.getHEIGHT() + SPACE;
        }
        for(int i=0; i<numberOfEnemy; i++){
            pos.add(i);
        }
    }

    protected void initPosition(){
        positionX = 5;
        positionY = 30;
        for(int i=0; i<numberOfLines; i++){
            for(int j=0; j<(numberOfEnemy/numberOfLines); j++){
                aliens[j+(i*(numberOfEnemy/numberOfLines))].setPositionX(positionX);
                aliens[j+(i*(numberOfEnemy/numberOfLines))].setPositionY(positionY);
                positionX += Alian.getWIDTH() + SPACE;
            }
            positionX = 5;
            positionY += Alian.getHEIGHT() + SPACE;
        }
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public static int getNumberOfEnemy() {
        return numberOfEnemy;
    }

    public void draw(Graphics g){
        if(Board.running) {
            for(int i = 0; i<numberOfEnemy; i++) {
                if (!aliens[i].isDeath()) {
                    aliens[i].draw(g);
                }
                if(aliens[i].bullet.isFired()){
                    aliens[i].draw(g);
                }
            }
        }
    }

    public void move(){
        if(Arrays.stream(aliens).noneMatch(o -> (o.bullet.isFired()))) {
            int bullets = Board.random.nextInt(numberOfEnemy/4);
            Collections.shuffle(pos);
            for (int i = 0; i < bullets; i++) {
                if (!aliens[pos.get(i)].isDeath()) {
                    aliens[pos.get(i)].bullet.setBulletX(aliens[pos.get(i)].getPositionX());
                    aliens[pos.get(i)].bullet.setBulletY(aliens[pos.get(i)].getPositionY());
                    aliens[pos.get(i)].bullet.setFired(true);
                    if (aliens[pos.get(i)].bullet.getBulletY() >= Board.SCREEN_HEIGHT){
                        aliens[pos.get(i)].bullet.setFired(false);
                    }
                }
            }
        }

        if (direction == 'D' && aliens[numberOfEnemy-1].getPositionX() + Alian.getWIDTH()  >= Board.SCREEN_WIDTH-Alian.getWIDTH()-10){
           direction = 'L';
        } else if (direction == 'D' && aliens[0].getPositionX() <= 5){
           direction = 'R';
        } else if (aliens[0].getPositionX() < 5 || aliens[numberOfEnemy-1].getPositionX() + Alian.getWIDTH() + 5 > Board.SCREEN_WIDTH){
           direction = 'D';
        }

        for(int i = 0; i<numberOfEnemy; i++){
            aliens[i].move();
            if (direction == 'R') {
                aliens[i].setPositionX(aliens[i].getPositionX()+1);
            } else if(direction == 'L') {
                aliens[i].setPositionX(aliens[i].getPositionX()-1);
            } else {
                aliens[i].setPositionY(aliens[i].getPositionY()+10);
            }
        }
    }

    public void colisions() {
        for (int i=0; i<numberOfEnemy; i++) {
            if(!aliens[i].isDeath() &&
                    (bullet.getBulletX() > aliens[i].getPositionX() && bullet.getBulletX() < aliens[i].getPositionX() + Alian.getWIDTH()) &&
                    (bullet.getBulletY() > aliens[i].getPositionY() && bullet.getBulletY() < aliens[i].getPositionY() + Alian.getHEIGHT())) {
                aliens[i].setDeath(true);
                bullet.setFired(false);
                bullet.setBulletX(0);
                bullet.setBulletY(0);
            }
            if((aliens[i].bullet.getBulletY() >= Board.SCREEN_WIDTH-Spaceship.getWIDTH() && aliens[i].bullet.isFired()) &&
                    (aliens[i].bullet.getBulletX() >= Spaceship.getPosition() && aliens[i].bullet.getBulletX() <= Spaceship.getPosition()+Spaceship.getWIDTH())){
                Board.running = false;
                Board.endTime = System.nanoTime();
                Board.timer.stop();
                for (int j=0; j<numberOfEnemy; j++){
                    aliens[j].bullet.setFired(false);
                }
            }
        }
        if (aliens[numberOfEnemy-1].getPositionY() > Board.SCREEN_HEIGHT-Spaceship.getHEIGHT()-42+Alian.getHEIGHT() ||
                Arrays.stream(aliens).noneMatch(o -> (!o.isDeath()))){
            Board.running = false;
            Board.endTime = System.nanoTime();
            Board.timer.stop();
        }
    }
}
