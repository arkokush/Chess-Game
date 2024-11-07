import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
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
                "/rookB.png" );
    }

    @Override
    public void move(int x, int y, ArrayList<ChessPiece> pieces) {
        if (this.canMove(x, y, pieces) || this.canTake(getPieceAt(x, y, pieces))) {
            this.x = x;
            this.y = y;
            didMove = true;
        }
    }


    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = b == y || a == x;
        ChessPiece targetPiece = getPieceAt(a, b, pieces);


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

        if (color == WHITE) g2.drawImage(getWhiteImage(), x + 7, y + 2, 60, 70, null);
        if (color == BLACK) g2.drawImage(getBlackImage(), x + 7, y + 2, 60, 70, null);

    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {

        if (color == WHITE) g2.drawImage(getWhiteImage(), x + 7, y + 2, 60, 70, null);
        if (color == BLACK) g2.drawImage(getBlackImage(), x + 7, y + 2, 60, 70, null);

    }
}
