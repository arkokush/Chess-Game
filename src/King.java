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
    public boolean canMove ( int a, int b, ArrayList<ChessPiece > pieces){
        boolean canMove = (a == x + 75 && b == y) || (a == x - 75 && b == y) || (b == y + 75 && a == x) || (b == y - 75 && a == x)
                || (b == y + 75 && (a == x + 75 || a == x - 75)) || (b == y - 75 && (a == x + 75 || a == x - 75));
        for (int i = pieces.size()-1;i>=0;i--) {
            ChessPiece piece = pieces.get(i);
            if (piece.getClass() == Pawn.class) {
                Pawn pawn = (Pawn) piece;
                if (pawn.canTake(a, b) && pawn.getColor() != color) {
                    canMove = false;
                    break;
                }
            }
            if (piece.getClass() != Pawn.class && piece.getColor() != color && piece.canMove(a, b, pieces)) {
                canMove = false;
                break;
            }


        }


        return canMove;

    }
}


