import java.io.*;
import java.util.*;

public class Pawn extends ChessPiece {
private boolean enPassantEligible = false;

    public Pawn(int x, int y, int color) throws IOException {
        super(x,
                y,
                50,
                60,
                color,
                "/pawnW.png",
                "/pawnB.png");
    }

    public boolean isEnPassantEligible() {
        return enPassantEligible;
    }

    public void setEnPassantEligible(boolean enPassantEligible) {
        this.enPassantEligible = enPassantEligible;
    }

    public boolean canTake(int a, int b) {
        boolean canMove = false;
        switch (color) {
            case BLACK:
                canMove = b == getY() + 75 && (a == getX() + 75 || a == getX() - 75);
                break;
            case WHITE:
                canMove = b == getY() - 75 && (a == getX() + 75 || a == getX() - 75);
                break;
        }
        return canMove;
    }

    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = false;
        ChessPiece targetPiece = getPieceAt(a, b, pieces);

         if(targetPiece==null) {
            switch (color) {
                case BLACK:
                    if (didMove) {
                        canMove = (a == getX() && b == getY() + 75);
                    } else {
                        canMove = (b ==  getY()+ 150 || b == getY() + 75) && a == getX();
                        for (ChessPiece piece : pieces) {
                            if (piece.getY() ==  getY()+ 75 && piece.getX() == getX()) {
                                canMove = false;
                                break;
                            }
                        }
                    }
                    break;
                case WHITE:
                    if (didMove) {
                        canMove = (b ==  getY()- 75 && a == getX());
                    } else {
                        canMove = (b ==  getY()- 150 || b ==  getY()- 75) && a == getX();
                        for (ChessPiece piece : pieces) {
                            if (piece.getY() ==  getY()- 75 && piece.getX() == getX()) {
                                canMove = false;
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        else if (canTake(a,b)) {
            canMove = true;
        }

        return canMove;
    }



}