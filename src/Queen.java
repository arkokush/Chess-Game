import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//Arkady Kokush
//Date: Nov 5, 2024

public class Queen extends ChessPiece
{
    private BufferedImage queenW, queenB;

    public Queen(int x, int y, int color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public boolean canMove(int a, int b)
    {
        boolean canMove = false;

        for (int i = -8; i <= 8; i++)
            if ((a == x + 75 * i && b == y + 75 * i) || (a == x + 75 * i * -1 && b == y + 75 * i) || (b == y)
                    || (a == x))
                canMove = true;
        return canMove;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        try
        {
            queenW = ImageIO.read(getClass().getResourceAsStream("/queenW.png"));
            queenB = ImageIO.read(getClass().getResourceAsStream("/queenB.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(queenW, x + 2, y, 70, 80, null);
        if (color == BLACK)
            g2.drawImage(queenB, x + 2, y, 70, 80, null);

    }

    @Override
    public void draw(Graphics2D g2, int x, int y)
    {
        try
        {
            queenW = ImageIO.read(getClass().getResourceAsStream("/queenW.png"));
            queenB = ImageIO.read(getClass().getResourceAsStream("/queenB.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(queenW, x + 2, y, 70, 80, null);
        if (color == BLACK)
            g2.drawImage(queenB, x + 2, y, 70, 80, null);

    }
}
