import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

//Arkady Kokush
//Date: Nov 5, 2024

public class Queen extends ChessPiece {
    private BufferedImage queenW, queenB;

    public Queen(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = false;

        for (int i = -8; i <= 8; i++)
            if ((a == x + 75 * i && b == y + 75 * i) || (a == x + 75 * i * -1 && b == y + 75 * i) || (b == y)
                    || (a == x)) {
                canMove = true;
                break;
            }
        for (ChessPiece piece : pieces) {
            if (a > x)
                for (int i = 1; i * 75 + x < a; i++)
                    if (piece.getX() == i * 75 + x && piece.getY() == y) {
                        canMove = false;
                        break;
                    }
            if (a < x)
                for (int i = 1; x - i * 75 > a; i++)
                    if (piece.getX() == x - i * 75 && piece.getY() == y) {
                        canMove = false;
                        break;
                    }
            if (b > y)
                for (int i = 1; i * 75 + y < b; i++)
                    if (piece.getY() == i * 75 + y && piece.getX() == x) {
                        canMove = false;
                        break;
                    }

            if (b < y)
                for (int i = 1; y - i * 75 > b; i++)
                    if (piece.getY() == y - i * 75 && piece.getX() == x) {
                        canMove = false;
                        break;
                    }
        }
        return canMove;
    }

    @Override
    public void draw(Graphics2D g2) {
        try {
            queenW = ImageIO.read(getClass().getResourceAsStream("/queenW.png"));
            queenB = ImageIO.read(getClass().getResourceAsStream("/queenB.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(queenW, x + 2, y + 2, 70, 70, null);
        if (color == BLACK)
            g2.drawImage(queenB, x + 2, y + 2, 70, 70, null);

    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        try {
            queenW = ImageIO.read(getClass().getResourceAsStream("/queenW.png"));
            queenB = ImageIO.read(getClass().getResourceAsStream("/queenB.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(queenW, x + 2, y + 2, 70, 70, null);
        if (color == BLACK)
            g2.drawImage(queenB, x + 2, y + 2, 70, 70, null);

    }
}
