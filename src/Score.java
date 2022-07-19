import java.awt.*;


public class Score {
    public static void gameOver(Graphics g){
        long time = (long) ((Board.endTime - Board.startTime) / 1000000000.0);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Consolas", Font.PLAIN,75));
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        g.drawString("Time: " + time, (Board.SCREEN_WIDTH - metrics.stringWidth("Time: " + time)) / 2, 75);
        g.drawString("Game Over", (Board.SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, Board.SCREEN_HEIGHT/3);

        g.setFont(new Font("Consolas", Font.PLAIN,25));
        metrics = g.getFontMetrics(g.getFont());
        g.drawString("Press enter to play again",
                (Board.SCREEN_WIDTH - metrics.stringWidth("Press enter to play again")) / 2,
                Board.SCREEN_HEIGHT-Board.SCREEN_HEIGHT/4);
    }
}
