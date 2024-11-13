import java.io.*;
import java.util.*;

public class Pawn extends ChessPiece {


    public Pawn(int x, int y, int color) throws IOException {
        super(x,
                y,
                50,
                60,
                color,
                "/pawnW.png",
                "/pawnB.png");
    }


    @Override
    public boolean canTake(ChessPiece piece) {
        switch (color) {
            case BLACK:
                return color != piece.getColor() && piece.getY() == y + 75
                        && (piece.getX() == x + 75 || piece.getX() == x - 75);
            case WHITE:
                return color != piece.getColor() && piece.getY() == y - 75
                        && (piece.getX() == x + 75 || piece.getX() == x - 75);
        }
        return false;
    }

    public boolean canTake(int a, int b) {
        switch (color) {
            case BLACK:
                return b == y + 75 && (a == x + 75 || a == x - 75);
            case WHITE:
                return b == y - 75 && (a == x + 75 || a == x - 75);
        }
        return false;
    }

    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = false;
        ChessPiece targetPiece = getPieceAt(a, b, pieces);

         if(targetPiece==null) {
            switch (color) {
                case BLACK:
                    if (didMove) {
                        canMove = (a == x && b == y + 75);
                    } else {
                        canMove = (b == y + 150 || b == y + 75) && a == x;
                        for (ChessPiece piece : pieces) {
                            if (piece.getY() == y + 75 && piece.getX() == x) {
                                canMove = false;
                                break;
                            }
                        }
                    }
                    break;
                case WHITE:
                    if (didMove) {
                        canMove = (b == y - 75 && a == x);
                    } else {
                        canMove = (b == y - 150 || b == y - 75) && a == x;
                        for (ChessPiece piece : pieces) {
                            if (piece.getY() == y - 75 && piece.getX() == x) {
                                canMove = false;
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        else if (canTake(targetPiece)) {
            canMove = true;
        }

        return canMove;
    }



}