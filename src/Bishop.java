import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class Bishop extends ChessPiece {
    private BufferedImage bishopW, bishopB;

    public Bishop(int x, int y, int color) throws IOException {
        super(x,
                y,
                color,
                "/bishopW.png",
                "/bishopB.png");
    }

    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = false;

        for (int i = -8; i <= 8; i++)
            if ((a == x + 75 * i && b == y + 75 * i) || (a == x + 75 * i * -1 && b == y + 75 * i)) {
                canMove = true;
                break;
            }
        for(ChessPiece piece: pieces){
            if (a > x && b < y)
                for (int i = 1; i * 75 + x < a; i++)
                    if (piece.getX() == i * 75 + x && piece.getY() == y - i * 75) {
                        canMove = false;
                        break;
                    }
            if (a > x && b > y)
                for (int i = 1; i * 75 + x < a; i++)
                    if (piece.getX() == i * 75 + x && piece.getY() == y + i * 75) {
                        canMove = false;
                        break;
                    }
            if (a < x && b < y)
                for (int i = 1; i * 75 + x < a; i++)
                    if (piece.getX() == x - i * 75 && piece.getY() == y - i * 75) {
                        canMove = false;
                        break;
                    }
            if (a < x && b > y)
                for (int i = 1; i * 75 + x < a; i++)
                    if (piece.getX() == x - i * 75 && piece.getY() == y + i * 75) {
                        canMove = false;
                        break;
                    }
        }
        return canMove;
    }}


