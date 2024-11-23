import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class KingTest {
    @Test
    public void testMove() throws IOException {
        King king = new King(0, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        king.move(75, 0, pieces);
        assertEquals(75, king.getX());
        assertEquals(0, king.getY());
    }

    @Test
    public void testCastle() throws IOException {
        King king = new King(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(225, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        king.move(150, 0, pieces);
        assertEquals(150, king.getX());
        assertEquals(75, rook.getX());
    }

    @Test
    public void testCannotMoveIntoCheck() throws IOException {
        King king = new King(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(0, 75, ChessPiece.BLACK);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        assertFalse(king.canMove(75, 75, pieces));
    }
}