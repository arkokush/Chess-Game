import java.awt.*;
import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class Rook extends ChessPiece {

    public Rook(int x, int y, int color) throws IOException {
        super(x,
                y,
                color,
                "/rookW.png",
                "/rookB.png");
    }


    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = b == y || a == x;


        for (ChessPiece piece : pieces) {
            if (a > x)
                for (int i = 1; i * 75 + x <= a; i++)
                    if (piece.getX() == i * 75 + x && piece.getY() == y && piece.getColor() == color) {
                        canMove = false;
                        break;
                    }
            if (a < x)
                for (int i = 1; x - i * 75 >= a; i++)
                    if (piece.getX() == x - i * 75 && piece.getY() == y && piece.getColor() == color) {
                        canMove = false;
                        break;
                    }
            if (b > y)
                for (int i = 1; i * 75 + y <= b; i++)
                    if (piece.getY() == i * 75 + y && piece.getX() == x && piece.getColor() == color) {
                        canMove = false;
                        break;
                    }

            if (b < y)
                for (int i = 1; y - i * 75 >= b; i++)
                    if (piece.getY() == y - i * 75 && piece.getX() == x && piece.getColor() == color) {
                        canMove = false;
                        break;
                    }


        }


        return canMove;
    }


    @Override
    public void draw(Graphics2D g2) {

        if (color == WHITE) g2.drawImage(getWhiteImage(), x + 7, y + 2, 60, 70, null);
        if (color == BLACK) g2.drawImage(getBlackImage(), x + 7, y + 2, 60, 70, null);

    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {

        if (color == WHITE) g2.drawImage(getWhiteImage(), x + 7, y + 2, 60, 70, null);
        if (color == BLACK) g2.drawImage(getBlackImage(), x + 7, y + 2, 60, 70, null);

    }
}
