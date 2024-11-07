import java.awt.image.*;
import java.io.*;
import java.util.*;

//Arkady Kokush
//Date: Nov 5, 2024

public class King extends ChessPiece {
    private BufferedImage kingW, kingB;

    public King(int x, int y, int color) throws IOException {
        super(x,
                y,
                color,
                "/kingW.png",
                "/kingB.png");
    }


    @Override
    public boolean canMove(int a, int b, ArrayList<ChessPiece> pieces) {
        return (a == x + 75 && b == y) || (a == x - 75 && b == y) || (b == y + 75 && a == x) || (b == y - 75 && a == x)
                || (b == y + 75 && (a == x + 75 || a == x - 75)) || (b == y - 75 && (a == x + 75 || a == x - 75));

    }
}


