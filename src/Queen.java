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
            if ((a == getY() + 75 * i && b == getY() + 75 * i) || (a == getY() + 75 * i * -1 && b == getY() + 75 * i) || (b == getY())
                    || (a == getY())) {
                canMove = true;
                break;
            }
        if(pieceInWay(a,b,pieces)){
            canMove = false;
        }

        return canMove;

    }

    @Override
    public boolean canMove(ChessCoordinate coord, ArrayList<ChessPiece> pieces) {
        boolean canMove = false;

        for (int i = -8; i <= 8; i++)
            if ((coord.getX() == getY() + 75 * i && coord.getY() == getY() + 75 * i)
                    || (coord.getX() == getY() + 75 * i * -1 && coord.getY() == getY() + 75 * i)
                    || (coord.getY() == getY())
                    || (coord.getX() == getY())) {
                canMove = true;
                break;
            }

        return canMove;

    }

    @Override
    public boolean pieceInWay(int a, int b, ArrayList<ChessPiece> pieces) {
        for (ChessPiece piece : pieces) {
            if (a > getX() && b == getY()) {
                for (int i = 1; i * 75 + getX() < a; i++) {
                    if (piece.getX() == i * 75 + getX() && piece.getY() == getY()) {
                        return true;
                    }
                }
            } else if (a < getX() && b == getY()) {
                for (int i = 1; getX() - i * 75 > a; i++) {
                    if (piece.getX() == getX() - i * 75 && piece.getY() == getY()) {
                        return true;
                    }
                }
            } else if (b > getY() && a == getX()) {
                for (int i = 1; i * 75 + getY() < b; i++) {
                    if (piece.getY() == i * 75 + getY() && piece.getX() == getX()) {
                        return true;
                    }
                }
            } else if (b < getY() && a == getX()) {
                for (int i = 1; getY() - i * 75 > b; i++) {
                    if (piece.getY() == getY() - i * 75 && piece.getX() == getX()) {
                        return true;
                    }
                }
            } else if (a > getX() && b < getY()) {// Top right
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

    @Override
    public boolean pieceInWay(ChessCoordinate coord, ArrayList<ChessPiece> pieces) {
        for (ChessPiece piece : pieces) {
            if (coord.getX() > getX() && coord.getY() == getY()) { // Right
                for (int i = 1; i * 75 + getX() < coord.getX(); i++) {
                    if (piece.getX() == i * 75 + getX() && piece.getY() == getY()) {
                        return true;
                    }
                }
            } else if (coord.getX() < getX() && coord.getY() == getY()) { //Left
                for (int i = 1; getX() - i * 75 > coord.getX(); i++) {
                    if (piece.getX() == getX() - i * 75 && piece.getY() == getY()) {
                        return true;
                    }
                }
            } else if (coord.getY() > getY() && coord.getX() == getX()) { //Below
                for (int i = 1; i * 75 + getY() < coord.getY(); i++) {
                    if (piece.getY() == i * 75 + getY() && piece.getX() == getX()) {
                        return true;
                    }
                }
            } else if (coord.getY() < getY() && coord.getX() == getX()) {//Above
                for (int i = 1; getY() - i * 75 > coord.getY(); i++) {
                    if (piece.getY() == getY() - i * 75 && piece.getX() == getX()) {
                        return true;
                    }
                }
            } else if (coord.getX() > getX() && coord.getY() < getY()) {// Top right
                for (int i = 1; i * 75 + getX() < coord.getX(); i++) {
                    if (piece.getX() == i * 75 + getX() && piece.getY() == getY() - i * 75) {
                        return true;
                    }
                }
            } else if (coord.getX() > getX() && coord.getY() > getY()) {// Bottom Right
                for (int i = 1; i * 75 + getX() < coord.getX(); i++) {
                    if (piece.getX() == i * 75 + getX() && piece.getY() == getY() + i * 75) {
                        return true;
                    }
                }
            } else if (coord.getX() < getX() && coord.getY() < getY()) {// Top Left
                for (int i = 1; getX() - i * 75 > coord.getX(); i++) {
                    if (piece.getX() == getX() - i * 75 && piece.getY() == getY() - i * 75) {
                        return true;
                    }
                }
            } else if (coord.getX() < getX() && coord.getY() > getY()) {// Bottom Left
                for (int i = 1; getX() - i * 75 > coord.getX(); i++) {
                    if (piece.getX() == getX() - i * 75 && piece.getY() == getY() + i * 75) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}