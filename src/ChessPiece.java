import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 4, 2024

public class ChessPiece {
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    private final BufferedImage whiteImage, blackImage;
    private final int w;
    private final int h;
    protected int x, y;
    protected int color;
    protected boolean pickedUp = false;
    protected boolean didMove = false;

    public ChessPiece(int x, int y, int w, int h, int color, String whiteImageLocation, String blackImageLocation) throws IOException {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        this.whiteImage = ImageIO.read(getClass().getResourceAsStream(whiteImageLocation));
        this.blackImage = ImageIO.read(getClass().getResourceAsStream(blackImageLocation));
    }

    public static ChessPiece getPieceAt(int x, int y, ArrayList<ChessPiece> pieces) {
        for (ChessPiece piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        return null;
    }

    public boolean isDidMove() {
        return didMove;
    }

    public void setDidMove(boolean didMove) {
        this.didMove = didMove;
    }

    public void move(int x, int y, ArrayList<ChessPiece> pieces) {
        ChessPiece target = getPieceAt(x, y, pieces);
        if (target == null) {
            if (this.canMove(x, y, pieces)) {

                this.x = x;
                this.y = y;
                didMove = true;
            }
            if (this.getClass() == Pawn.class && ((Pawn) this).isEnPassantEligible()) {
                if ((color == WHITE) && (x == this.x + 75 || x == this.x - 75) && y == this.y - 75) {
                    pieces.remove(getPieceAt(x, y + 75, pieces)); // Remove the captured piece
                    this.x = x;
                    this.y = y;
                    didMove = true;
                }
                if ((color == BLACK) && (x == this.x + 75 || x == this.x - 75) && y == this.y + 75) {
                    pieces.remove(getPieceAt(x, y - 75, pieces)); // Remove the captured piece
                    this.x = x;
                    this.y = y;
                    didMove = true;
                }

            }
        } else if (target.getColor() != color && canMove(x, y, pieces)) {

            pieces.remove(target); // Remove the captured piece
            this.x = x;
            this.y = y;
            didMove = true;

        }


    }

    public ChessPieceImage draw() {
        return draw(this.x, this.y);
    }

    public ChessPieceImage draw(int x, int y) {

        if (color == WHITE) {
            return new ChessPieceImage(getWhiteImage(), x + (75 - w) / 2, y + (75 - h) / 2, w, h);
        } else {

            return new ChessPieceImage(getBlackImage(), x + (75 - w) / 2, y + (75 - h) / 2, w, h);
        }
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public boolean canTake(ChessPiece piece) {

        return false;
    }

    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        return false;
    }

    public Rectangle getHitbox() {
        return new Rectangle(
                x, y, 75, 75);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getWhiteImage() {
        return whiteImage;
    }

    public BufferedImage getBlackImage() {
        return blackImage;
    }

    enum PieceColor {
        WHITE, BLACK
    }
}
