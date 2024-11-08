import java.awt.image.*;
import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class King extends ChessPiece {
    private BufferedImage kingW, kingB;

    public King(int x, int y, int color) throws IOException {
        super(x,
                y,
                color,
                "/kingW.png",
                "/kingB.png");
    }
@Override
    public void move(int a, int b, ArrayList<ChessPiece> pieces) {
        ChessPiece target = getPieceAt(a, b, pieces);
        if(b==y&&(a==x+150||a==x-150)&&!didMove)
            castle(a,b,pieces);

       else if (target == null){
            if (this.canMove(a, b, pieces)) {

                this.x = a;
                this.y = b;
                didMove = true;
            }} else if(target.getColor() != color && canMove(a, b, pieces)){
            pieces.remove(target); // Remove the captured piece
            this.x = a;
            this.y = b;
            didMove = true;
        }

pickedUp = false;
    }
public void castle(int a, int b, ArrayList<ChessPiece> pieces) {
    if (a == x + 150) {
        Rook rook = (Rook) getPieceAt(a + 75, b, pieces);
        if (rook != null && rook.getColor() == color && !rook.isDidMove()) {
            this.x = a;
            this.y = b;
            rook.setX(x - 75);
            didMove = true;
            rook.setDidMove(true);
        }

    }
    if (a == x - 150) {
        Rook rook = (Rook) getPieceAt(a - 150, b, pieces);
        if (rook != null && rook.getColor() == color && !rook.isDidMove()) {
            this.x = a;
            this.y = b;
            rook.setX(x + 75);
            didMove = true;
            rook.setDidMove(true);
        }
    }}
    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        King king = this;
        int index = pieces.indexOf(this);
        pieces.remove(this);
        boolean canMove = (Math.abs(x - a) <= 75) && (Math.abs(y - b) <= 75);

        for (int i = pieces.size() - 1; i >= 0; i--) {
            ChessPiece piece = pieces.get(i);
            if (piece.getClass() == Pawn.class) {
                Pawn pawn = (Pawn) piece;
                if (pawn.canTake(a, b) && pawn.getColor() != color) {
                    canMove = false;
                    break;
                }
            }
            if (piece.getClass() == King.class && piece.getColor() != color) {
                if ((Math.abs(piece.getX() - a) <= 75) && (Math.abs(piece.getY() - b) <= 75)) {
                    canMove = false;
                    break;
                }
            }
            if (piece.getClass() != Pawn.class && piece.getClass() != King.class && piece.getColor() != color && piece.canMove(a, b, pieces)) {
                canMove = false;
                break;
            }
        }
        pieces.add(index,king);
        return canMove;
    }}


