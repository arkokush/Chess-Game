import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class Bishop extends ChessPiece {

    public Bishop(int x, int y, int color) throws IOException {
        super(x,
                y,
                70,
                70,
                color,
                "/bishopW.png",
                "/bishopB.png");
    }
    public Bishop(int x, int y,int w, int h, int color) throws IOException {
        super(x,
                y,
                w,
                h,
                color,
                "/bishopW.png",
                "/bishopB.png");
    }
    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = false;

        for (int i = -8; i <= 8; i++)
            if ((a == getX() + 75 * i && b == getY() + 75 * i) || (a == getX() + 75 * i * -1 && b == getY() + 75 * i)) {
                canMove = true;
                break;
            }
        if(pieceInWay(a,b,pieces)){
            canMove = false;
        }
        return canMove;
    }

public boolean pieceInWay(int a, int b, ArrayList<ChessPiece> pieces) {
    for (ChessPiece piece : pieces) {
         if (a > getX() && b < getY()) {// Top right
            for (int i = 1; i * 75 + getX() < a; i++) {
                if (piece.getX() == i * 75 + getX() && piece.getY() == getY() - i * 75) {
                    return true;
                }
            }
        } else if (a > getX() && b > getY()) {// Bottom Right
            for (int i = 1; i * 75 + getX() < a; i++) {
                if (piece.getX() == i * 75 + getX() && piece.getY() == getY() + i * 75) {
                    return true;
                }
            }
        } else if (a < getX() && b < getY()) {// Top Left
            for (int i = 1; getX() - i * 75 > a; i++) {
                if (piece.getX() == getX() - i * 75 && piece.getY() == getY() - i * 75) {
                    return true;
                }
            }
        } else if (a < getX() && b > getY()) {// Bottom Left
            for (int i = 1; getX() - i * 75 > a; i++) {
                if (piece.getX() == getX() - i * 75 && piece.getY() == getY() + i * 75) {
                    return true;
                }
            }
        }
    }
    return false;
}
}

