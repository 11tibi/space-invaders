import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
    private final Random random;
    private final Spaceship spaceship;
    private final Bullet bullet;
    private final Enemy enemy;

    public GamePanel(){
        spaceship = new Spaceship();
        bullet = new Bullet();
        enemy = new Enemy(bullet);
        Controller controller = new Controller(bullet, spaceship, enemy);
        random = new Random();
        this.setPreferredSize(new Dimension(Board.SCREEN_WIDTH, Board.SCREEN_HEIGHT));
        this.setBackground(Board.BOARD_COLOR);
        this.setFocusable(true);
        addKeyListener(controller);
        startGame();
    }

    public void startGame() {
        Board.running = true;
        Board.timer = new Timer(Board.DELAY, this);
        Board.timer.start();
    }

    protected void paintComponent(Graphics g) {
        if (Board.running) {
            super.paintComponent(g);
            spaceship.draw(g);
            bullet.draw(g);
            enemy.draw(g);
        } else {
            Score.gameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Board.running){
            spaceship.move();
            enemy.move();
            if (bullet.isFired()) {
                bullet.move();
            }
            enemy.colisions();
        }
        repaint();
    }
}
