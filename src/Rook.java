import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//Arkady Kokush
//Date: Nov 5, 2024

public class Rook extends ChessPiece
{
    private BufferedImage rookW, rookB;

    public Rook(int x, int y, int color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    
   
    
    @Override
    public boolean canMove(int a, int b)
    {
        return b==y||a==x;

    }

    @Override
    public void draw(Graphics2D g2)
    {
        try
        {
            rookW = ImageIO.read(getClass().getResourceAsStream("/rookW.png"));
            rookB = ImageIO.read(getClass().getResourceAsStream("/rookB.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(rookW, x + 2, y + 2, 70, 80, null);
        if (color == BLACK)
            g2.drawImage(rookB, x + 2, y + 2, 70, 80, null);

    }

    @Override
    public void draw(Graphics2D g2, int x, int y)
    {
        try
        {
            rookW = ImageIO.read(getClass().getResourceAsStream("/rookW.png"));
            rookB = ImageIO.read(getClass().getResourceAsStream("/rookB.png"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (color == WHITE)
            g2.drawImage(rookW, x + 2, y + 2, 70, 80, null);
        if (color == BLACK)
            g2.drawImage(rookB, x + 2, y + 2, 70, 80, null);

    }
}
