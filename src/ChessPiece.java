import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

//Arkady Kokush
//Date: Nov 4, 2024

public class ChessPiece
{
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    protected int x, y;
    protected int color;
    protected boolean pickedUp = false;
    protected boolean didMove = false;

    public ChessPiece()
    {

    }

    public ChessPiece(int x, int y, int color)
    {
        this.x = x;
        this.y = y;

        this.color = color;
    }

    public void move(int x, int y, ArrayList<ChessPiece> pieces)
    {

        if (this.canMove(x, y))
        {
            for (ChessPiece p : pieces)
                if (color != p.getColor() && p.getX() == x && p.getY() == y)
                    pieces.remove(p);

            this.x = x;
            this.y = y;
            didMove = true;
        }
        pickedUp=false;

    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public boolean isPickedUp()
    {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp)
    {
        this.pickedUp = pickedUp;
    }

    public void takePiece(ChessPiece piece)
    {
        if (piece.getX() == x && piece.getY() == y && this.canTake(piece))
            piece = null;

    }

    public boolean canTake(ChessPiece piece)
    {
        return false;
    }

    public boolean canMove(int a, int b)
    {
        return false;
    }

    public Rectangle getHitbox()
    {
        return new Rectangle(x, y, 75, 75);
    }

    public void draw(Graphics2D g2)
    {
        g2.drawRect(x, y, 75, 75);
    }

    public void draw(Graphics2D g2, int x, int y)
    {
        g2.drawRect(x, y, 75, 75);
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
