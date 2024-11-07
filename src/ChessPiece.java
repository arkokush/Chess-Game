import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 4, 2024

public class ChessPiece {
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    private final BufferedImage whiteImage, blackImage;
    protected int x, y;
    protected int color;
    protected boolean pickedUp = false;
    protected boolean didMove = false;

    public ChessPiece(int x, int y, int color, String whiteImageLocation, String blackImageLocation) throws IOException {
        this.x = x;
        this.y = y;
        this.color = color;
        this.whiteImage = ImageIO.read(getClass().getResourceAsStream(whiteImageLocation));
        this.blackImage = ImageIO.read(getClass().getResourceAsStream(blackImageLocation));
    }

    public void move(int x, int y, ArrayList<ChessPiece> pieces) {
        if (this.canMove(x, y, pieces) ) {
            this.x = x;
            this.y = y;
            didMove = true;
        }
        for (int j = pieces.size()-1; j >=0; j--) {
            ChessPiece target = pieces.get(j);
            if (target.getX() == x && target.getY() == y && target.getColor()
                    != color) {
                pieces.remove(j); // Remove the captured piece
                break;
            }
        }
    }

    public ChessPiece getPieceAt(int x, int y, ArrayList<ChessPiece> pieces) {
        for (ChessPiece piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        return null;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }


    public boolean canTake(ChessPiece piece) {

        return false;
    }

    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        return false;
    }

    public Rectangle getHitbox() {
        return new Rectangle(
                x, y, 75, 75);
    }

    public void draw(Graphics2D g2) {
        draw(g2,this.x,this.y);
    }

    public void draw(Graphics2D g2, int x, int y) {

        if (color == WHITE)
            g2.drawImage(getWhiteImage(), x + 2, y + 2, 70, 70, null);
        if (color == BLACK)
            g2.drawImage(getBlackImage(), x + 2, y + 2, 70, 70, null);    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public BufferedImage getWhiteImage() {
        return whiteImage;
    }

    public BufferedImage getBlackImage() {
        return blackImage;
    }
}
