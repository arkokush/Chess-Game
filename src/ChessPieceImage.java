import java.awt.*;

public class ChessPieceImage {
    public ChessPieceImage(Image img, int x, int y, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Image getImg() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private final Image img;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
}
