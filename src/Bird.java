import java.awt.Graphics;
import java.awt.Rectangle;

public class Bird {
    private int x, y;
    private int width, height;
    private int velocityX, velocityY;
    private boolean isLaunched;

    public Bird(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isLaunched = false;
    }

    public void update() {
        if (isLaunched) {
            // Simple physics for demonstration
            x += velocityX;
            y += velocityY;
            velocityY += 1; // Gravity effect
        }
    }

    public void launch(int deltaX, int deltaY) {
        this.velocityX = deltaX;
        this.velocityY = deltaY;
        this.isLaunched = true;
    }

    public void draw(Graphics g) {
        g.fillOval(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
