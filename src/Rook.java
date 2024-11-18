import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class Rook extends ChessPiece {

    public Rook(int x, int y, int color) throws IOException {
        super(x,
                y,
                60,
                70,
                color,
                "/rookW.png",
                "/rookB.png");
    }
    public Rook(int x, int y,int w, int h, int color) throws IOException {
        super(x,
                y,
                w,
                h,
                color,
                "/rookW.png",
                "/rookB.png");
    }


    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
       return ((b == y || a == x)&&!pieceInWay(a,b,pieces));
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

            }
        }
        return false;
    }

}
