import java.awt.Color;
import java.awt.Graphics2D;

//Arkady Kokush
//Date: Nov 4, 2024

public class ChessBoard
{
    private int w, h;

    public ChessBoard(int w, int h)
    {
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics2D g2)
    {
        g2.setColor(new Color(100,100,100));
        g2.fillRect(0, 0, w, this.h);
        g2.setColor(Color.WHITE);
        for (int i = 0; i < this.w / 75; i += 2)
            for (int q = 0; q < this.h / 75; q += 2)
                g2.fillRect(i * 75, q * 75, 75, 75);
        for (int i = 1; i < this.w / 75; i += 2)
            for (int q = 1; q < this.h / 75; q += 2)
                g2.fillRect(i * 75, q * 75, 75, 75);

    }

}
