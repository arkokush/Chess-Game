import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//Arkady Kokush
//Date: Nov 5, 2024

public class King extends ChessPiece
{
    private BufferedImage kingW, kingB;

    public King(int x, int y, int color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

  
    @Override
    public boolean canMove(int a, int b)
    {
        return (a == x + 75 && b == y) || (a == x - 75 && b == y) || (b == y + 75 && a == x) || (b == y - 75 && a == x)
                || (b == y + 75 && (a == x + 75 || a == x - 75)) || (b == y - 75 && (a == x + 75 || a == x - 75));

    }

    @Override
    public void draw(Graphics2D g2)
    {
        try
        {
            kingW = ImageIO.read(getClass().getResourceAsStream("/kingW.png"));
            kingB = ImageIO.read(getClass().getResourceAsStream("/kingB.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(kingW, x + 2, y+2, 70, 70, null);
        if (color == BLACK)
            g2.drawImage(kingB, x + 2, y+2, 70, 70, null);

    }

    @Override
    public void draw(Graphics2D g2, int x, int y)
    {
        try
        {
            kingW = ImageIO.read(getClass().getResourceAsStream("/kingW.png"));
            kingB = ImageIO.read(getClass().getResourceAsStream("/kingB.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(kingW, x + 2, y+2, 70, 70, null);
        if (color == BLACK)
            g2.drawImage(kingB, x + 2, y+2, 70, 70, null);

    }
}
