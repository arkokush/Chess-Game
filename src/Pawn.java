import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//Arkady Kokush
//Date: Nov 4, 2024

public class Pawn extends ChessPiece
{
    private BufferedImage pawnW, pawnB;

    public Pawn(int x, int y, int color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void move(int x, int y, ArrayList<ChessPiece> pieces)
    {
        if (this.canMove(x, y))
        {
            this.x = x;
            this.y = y;
            didMove = true;
        }
        for (ChessPiece p : pieces)
            if (color != p.getColor() && p.getX() == x && p.getY() == y)
            {
                pieces.remove(p);
                this.x = x;
                this.y = y;
            }
        pickedUp = false;

    }

    @Override
    public boolean canTake(ChessPiece piece)
    {
        switch (color)
        {
        case (BLACK):
            return piece.getY() == y + 75 && (piece.getX() == x + 75 || piece.getX() == x - 75);
        case (WHITE):
            return piece.getY() == y - 75 && (piece.getX() == x + 75 || piece.getX() == x - 75);

        }
        return false;
    }

    @Override
    public boolean canMove(int a, int b)
    {
        switch (color)
        {
        case (BLACK):
            if (didMove)
                return a == x && b == y + 75;
            else
                return (b == y + 150 || b == y + 75) && a == x;

        case (WHITE):
            if (didMove)
                return b == y - 75 && a == x;
            else
                return (b == y - 150 || b == y - 75) && a == x;
        }
        return false;

    }

    @Override
    public void draw(Graphics2D g2)
    {
        try
        {
            pawnW = ImageIO.read(getClass().getResourceAsStream("/pawnW.png"));
            pawnB = ImageIO.read(getClass().getResourceAsStream("/pawnB.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(pawnW, x + 13, y + 2, 50, 70, null);
        if (color == BLACK)
            g2.drawImage(pawnB, x + 13, y + 2, 50, 70, null);

    }

    @Override
    public void draw(Graphics2D g2, int x, int y)
    {
        try
        {
            pawnW = ImageIO.read(getClass().getResourceAsStream("/pawnW.png"));
            pawnB = ImageIO.read(getClass().getResourceAsStream("/pawnB.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(pawnW, x + 13, y + 2, 50, 70, null);
        if (color == BLACK)
            g2.drawImage(pawnB, x + 13, y + 2, 50, 70, null);

    }
}
