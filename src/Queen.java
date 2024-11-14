import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class Queen extends ChessPiece {

    public Queen(int x, int y, int color) throws IOException {
        super(x,
                y,
                70,
                70,
                color,
                "/queenW.png",
                "/queenB.png");
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
        if (pieceInWay(a, b, pieces)) {
            canMove = false;
        }

        return canMove;

    }

    public boolean pieceInWay(int a, int b, ArrayList<ChessPiece> pieces) {
        for (ChessPiece piece : pieces) {
            if (a > x && b == y) {
                for (int i = 1; i * 75 + x < a; i++) {
                    if (piece.getX() == i * 75 + x && piece.getY() == y) {
                        return true;
                    }
                }
            } else if (a < x && b == y) {
                for (int i = 1; x - i * 75 > a; i++) {
                    if (piece.getX() == x - i * 75 && piece.getY() == y) {
                        return true;
                    }
                }
            } else if (b > y && a == x) {
                for (int i = 1; i * 75 + y < b; i++) {
                    if (piece.getY() == i * 75 + y && piece.getX() == x) {
                        return true;
                    }
                }
            } else if (b < y && a == x) {
                for (int i = 1; y - i * 75 > b; i++) {
                    if (piece.getY() == y - i * 75 && piece.getX() == x) {
                        return true;
                    }
                }
            } else if (a > x && b < y) {// Top right
                for (int i = 1; i * 75 + x < a; i++) {
                    if (piece.getX() == i * 75 + x && piece.getY() == y - i * 75) {
                        return true;
                    }
                }
            } else if (a > x && b > y) {// Bottom Right
                for (int i = 1; i * 75 + x < a; i++) {
                    if (piece.getX() == i * 75 + x && piece.getY() == y + i * 75) {
                        return true;
                    }
                }
            } else if (a < x && b < y) {// Top Left
                for (int i = 1; x - i * 75 > a; i++) {
                    if (piece.getX() == x - i * 75 && piece.getY() == y - i * 75) {
                        return true;
                    }
                }
            } else if (a < x && b > y) {// Bottom Left
                for (int i = 1; x - i * 75 > a; i++) {
                    if (piece.getX() == x - i * 75 && piece.getY() == y + i * 75) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}