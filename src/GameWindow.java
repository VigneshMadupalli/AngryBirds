import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameWindow extends JFrame {
    private Bird bird;
    private List<Obstacle> obstacles;
    private List<Enemy> enemies;

    public GameWindow() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        bird = new Bird(100, 300, 20, 20);
        obstacles = new ArrayList<>();
        enemies = new ArrayList<>();

        // Sample obstacles and enemies for demonstration
        obstacles.add(new Obstacle(400, 400, 50, 50));
        enemies.add(new Enemy(500, 500, 30, 30));

        addMouseListener(new MouseAdapter() {
            private int startX, startY;

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int deltaX = startX - e.getX();
                int deltaY = startY - e.getY();
                bird.launch(deltaX, deltaY);
            }
        });

        new Thread(() -> {
            while (true) {
                bird.update();
                checkCollisions();
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void checkCollisions() {
        Rectangle birdBounds = bird.getBounds();
        obstacles.removeIf(obstacle -> birdBounds.intersects(obstacle.getBounds()));
        enemies.removeIf(enemy -> birdBounds.intersects(enemy.getBounds()));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        bird.draw(g);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    public static void main(String[] args) {
        new GameWindow();
    }
}
