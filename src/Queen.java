import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class Queen extends ChessPiece {
    private BufferedImage queenW, queenB;

    public Queen(int x, int y, int color) throws IOException {
        super(x,
                y,
                color,
                "/queenW.png",
                "/queenB.png" );
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
            if (a > x && b == y)
                for (int i = 1; i * 75 + x < a; i++)
                    if (piece.getX() == i * 75 + x && piece.getY() == y) {
                        canMove = false;
                        break;
                    }
            if (a < x && b == y)
                for (int i = 1; x - i * 75 > a; i++)
                    if (piece.getX() == x - i * 75 && piece.getY() == y) {
                        canMove = false;
                        break;
                    }
            if (b > y && a == x)
                for (int i = 1; i * 75 + y < b; i++)
                    if (piece.getY() == i * 75 + y && piece.getX() == x) {
                        canMove = false;
                        break;
                    }

            if (b < y && a == x)
                for (int i = 1; y - i * 75 > b; i++)
                    if (piece.getY() == y - i * 75 && piece.getX() == x) {
                        canMove = false;
                        break;
                    }
        }
        return canMove;
    }


}
