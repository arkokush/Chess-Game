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
    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces)
    {
        return (a == getX() + 150 && (b == getY() - 75 || b == getY() + 75)) || (a == getX() - 150 && (b == getY() - 75 || b == getY() + 75))
                || (b == getY() + 150 && (a == getX() - 75 || a == getX() + 75)) || (b == getY() - 150 && (a == getX() - 75 || a == getX() + 75));

    }


}
