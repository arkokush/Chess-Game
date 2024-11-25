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

    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        return ((b == getY() || a == getX()) && !pieceInWay(a, b, pieces));
    }

    @Override
    public boolean canMove(ChessCoordinate coord, ArrayList<ChessPiece> pieces) {
        return ((coord.getY() == getY() || coord.getX() == getX()));
    }

    @Override
    public boolean pieceInWay(ChessCoordinate coord, ArrayList<ChessPiece> pieces) {
        for (ChessPiece piece : pieces) {
            if (coord.getX()> getX() && coord.getY()== getY()) {
                for (int i = 1; i * 75 + getX() < coord.getX(); i++) {
                    if (piece.getX() == i * 75 + getX() && piece.getY() == getY()) {
                        return true;
                    }
                }
            } else if (coord.getX()< getX() && coord.getY()== getY()) {
                for (int i = 1; getX() - i * 75 > coord.getX(); i++) {
                    if (piece.getX() == getX() - i * 75 && piece.getY() == getY()) {
                        return true;
                    }
                }
            } else if (coord.getY()> getY() && coord.getX()== getX()) {
                for (int i = 1; i * 75 + getY() < coord.getY(); i++) {
                    if (piece.getY() == i * 75 + getY() && piece.getX() == getX()) {
                        return true;
                    }
                }
            } else if (coord.getY()< getY() && coord.getX()== getX()) {
                for (int i = 1; getY() - i * 75 > coord.getY(); i++) {
                    if (piece.getY() == getY() - i * 75 && piece.getX() == getX()) {
                        return true;
                    }
                }

            }
        }
        return false;
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

            }
        }
        return false;
    }

}
