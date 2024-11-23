import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class KnightTest {
    @Test
    public void testMove() throws IOException {
        Knight knight = new Knight(0, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        knight.move(150, 75, pieces);
        assertEquals(150, knight.getX());
        assertEquals(75, knight.getY());
    }

    @Test
    public void testCapture() throws IOException {
        Knight knight = new Knight(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(150, 75, ChessPiece.BLACK);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        knight.move(150, 75, pieces);
        assertEquals(150, knight.getX());
        assertEquals(75, knight.getY());
        assertFalse(pieces.contains(rook));
    }

    @Test
    public void testCanJumpOverPieces() throws IOException {
        Knight knight = new Knight(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(75, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        assertTrue(knight.canMove(150, 75, pieces));
    }
}