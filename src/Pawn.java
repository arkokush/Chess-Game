import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Pawn extends ChessPiece {
    private BufferedImage pawnW, pawnB;


    public Pawn(int x, int y, int color) throws IOException {
        super(x,
                y,
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

    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = false;
        ChessPiece targetPiece = getPieceAt(a, b, pieces);

        if (targetPiece != null && canTake(targetPiece)) {
            canMove = true;
        }

        else {
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

        return canMove;
    }


    @Override
    public void draw(Graphics2D g2) {
        draw(g2, this.x, this.y);
    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        if (color == WHITE)
            g2.drawImage(getWhiteImage(), x + 12, y + 9, 50, 60, null);
        if (color == BLACK)
            g2.drawImage(getBlackImage(), x + 12, y + 9, 50, 60, null);
    }
}