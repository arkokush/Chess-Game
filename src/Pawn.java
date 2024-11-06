import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Pawn extends ChessPiece {
    private BufferedImage pawnW, pawnB;

    public Pawn(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        loadImages();
    }

    private void loadImages() {
        try {
            pawnW = ImageIO.read(getClass().getResourceAsStream("/pawnW.png"));
            pawnB = ImageIO.read(getClass().getResourceAsStream("/pawnB.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(int x, int y, ArrayList<ChessPiece> pieces) {
        if (this.canMove(x, y, pieces)) {
            this.x = x;
            this.y = y;
            didMove = true;
        }
        ChessPiece capturedPiece = null;
        for (ChessPiece piece : pieces) {
            if (canTake(piece)&&piece.getX()==x&&piece.getY()==y) {
                capturedPiece = piece;
                break;
            }
        }
        if (capturedPiece != null) {
            pieces.remove(capturedPiece);
            this.x = capturedPiece.getX();
            this.y = capturedPiece.getY();
        }
        pickedUp = false;
    }

    @Override
    public boolean canTake(ChessPiece piece) {
        switch (color) {
            case BLACK:
                return color != piece.getColor() && piece.getY() == y + 75 && (piece.getX() == x + 75 || piece.getX() == x - 75);
            case WHITE:
                return color != piece.getColor() && piece.getY() == y - 75 && (piece.getX() == x + 75 || piece.getX() == x - 75);
        }
        return false;
    }

    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        boolean canMove = false;

        switch (color) {
            case BLACK:
                if (didMove) {
                    canMove = (a == x && b == y + 75);
                } else {
                    canMove = (b == y + 150 || b == y + 75) && a == x;
                    for (ChessPiece piece : pieces) {
                        if (piece.getY() == y + 75 &&piece.getX() == x) {
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

        return canMove;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (color == WHITE)
            g2.drawImage(pawnW, x + 13, y + 7, 50, 60, null);
        if (color == BLACK)
            g2.drawImage(pawnB, x + 13, y + 7, 50, 60, null);
    }

    @Override
    public void draw(Graphics2D g2, int x, int y) {
        if (color == WHITE)
            g2.drawImage(pawnW, x + 13, y + 7, 50, 60, null);
        if (color == BLACK)
            g2.drawImage(pawnB, x + 13, y + 7, 50, 60, null);
    }
}