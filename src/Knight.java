import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class Knight extends ChessPiece
{

    public Knight(int x, int y, int color) throws IOException {
        super(x,
                y,
                70,
                70,
                color,
                "/knightW.png",
                "/knightB.png" );
    }
    public Knight(int x, int y,int w, int h, int color) throws IOException {
        super(x,
                y,
                w,
                h,
                color,
                "/knightW.png",
                "/knightB.png" );
    }

   
    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces)
    {
        return (a == x + 150 && (b == y - 75 || b == y + 75)) || (a == x - 150 && (b == y - 75 || b == y + 75))
                || (b == y + 150 && (a == x - 75 || a == x + 75)) || (b == y - 150 && (a == x - 75 || a == x + 75));

    }


}
