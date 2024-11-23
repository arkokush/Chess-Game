import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class King extends ChessPiece {

    public King(int x, int y, int color) throws IOException {
        super(x,
                y,
                70,
                70,
                color,
                "/kingW.png",
                "/kingB.png");
    }

    @Override
    public void move(int a, int b, ArrayList<ChessPiece> pieces) {
        ChessPiece target = getPieceAt(a, b, pieces);
        if (b == getY() && (a == getX() + 150 || a == getX() - 150) && !didMove) {
            castle(a, b, pieces);
        } else if (target == null) {
            if (this.canMove(a, b, pieces)) {

                this.setX(a);
                this.setY(b);
                didMove = true;
            }
        } else if (target.getColor() != color && canMove(a, b, pieces)) {
            pieces.remove(target); // Remove the captured piece
            this.setX(a);
            this.setY(b);
            didMove = true;
        }

        pickedUp = false;
    }

    public boolean inCheck(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean inCheck = false;
        King king = this;
        int index = pieces.indexOf(this);
        pieces.remove(this);
        for (int i = pieces.size() - 1; i >= 0; i--) {
            ChessPiece piece = pieces.get(i);
            if (piece.getClass() == Pawn.class) {
                Pawn pawn = (Pawn) piece;
                if (pawn.canTake(a, b) && pawn.getColor() != color) {
                    inCheck = true;
                    break;
                }
            } else if (piece.getClass() == King.class && piece.getColor() != color) {
                if ((Math.abs(piece.getX() - a) <= 75) && (Math.abs(piece.getY() - b) <= 75)) {
                    inCheck = true;
                    break;
                }
            } else if (piece.getColor() != color && piece.canMove(a, b, pieces)) {
                inCheck = true;
                break;
            }
        }
        if (index > -1) {
            pieces.add(index, king);
        }
        return inCheck;
    }

    public boolean inCheck(ArrayList<ChessPiece> pieces) {
        return inCheck(this.getX(), this.getY(), pieces);
    }

    public void castle(int a, int b, ArrayList<ChessPiece> pieces) {
        if (a == getX() + 150) {
            Rook rook = (Rook) getPieceAt(a + 75, b, pieces);
            if (!(this.inCheck(pieces) || this.inCheck(getX() + 75, getY(), pieces) || this.inCheck(getX() + 150, getY(), pieces))) {
                if (rook != null && rook.getColor() == color && !rook.isDidMove()) {
                    this.setX(a);
                    this.setY(b);
                    rook.setX(getX() - 75);
                    didMove = true;
                    rook.setDidMove(true);
                }
            }

        }
        if (a == getX() - 150) {
            if (!(this.inCheck(pieces) || this.inCheck(getX() - 75, getY(), pieces) || this.inCheck(getX() - 150, getY(), pieces))) {

                Rook rook = (Rook) getPieceAt(a - 150, b, pieces);
                if (rook != null && rook.getColor() == color && !rook.isDidMove()) {
                    this.setX(a);
                    this.setY(b);
                    rook.setX(getX() + 75);
                    didMove = true;
                    rook.setDidMove(true);
                }
            }
        }
    }

    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {

        boolean canMove = (Math.abs(getX() - a) <= 75) && (Math.abs(getY() - b) <= 75);
        int index = -1;
        ChessPiece piece = getPieceAt(a, b, pieces);
        if (piece != null && piece.getColor() != color) {
            index = pieces.indexOf(piece);
            pieces.remove(piece);
        }
        if (inCheck(a, b, pieces)) {
            canMove = false;
        }
        if (index > -1) {
            pieces.add(index, piece);
        }
        return canMove;
    }
}


