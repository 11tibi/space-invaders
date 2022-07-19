import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private final Bullet bullet;
    private final Spaceship spaceship;
    private final Enemy enemy;

    public Controller(Bullet bullet, Spaceship spaceship, Enemy enemy){
        this.bullet = bullet;
        this.spaceship = spaceship;
        this.enemy = enemy;
    }

    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(Board.running){
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                spaceship.direction = 1;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                spaceship.direction = 2;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                bullet.setFired(true);
                bullet.setBulletX(spaceship.getPosition() + Spaceship.getWIDTH() / 2);
                bullet.setBulletY(Board.SCREEN_HEIGHT - Spaceship.getHEIGHT());
            }
        } else {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                Board.timer.setDelay(Board.DELAY);
                spaceship.direction = 0;
                enemy.setPositionX(5);
                enemy.setPositionY(30);
                enemy.initPosition();
                Board.timer.setDelay(Board.DELAY);
                for(int i = 0; i< Enemy.getNumberOfEnemy(); i++){
                    enemy.aliens[i].setDeath(false);
                }
                Board.running = true;
                Board.timer.start();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            spaceship.direction = 0;
        }
    }
}
