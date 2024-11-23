import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class BishopTest {
    @Test
    public void testMove() throws IOException {
        Bishop bishop = new Bishop(0, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        bishop.move(75, 75, pieces);
        assertEquals(75, bishop.getX());
        assertEquals(75, bishop.getY());
    }

    @Test
    public void testCapture() throws IOException {
        Bishop bishop = new Bishop(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(75, 75, ChessPiece.BLACK);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        bishop.move(75, 75, pieces);
        assertEquals(75, bishop.getX());
        assertEquals(75, bishop.getY());
        assertFalse(pieces.contains(rook));
    }

    @Test
    public void testCannotJumpOverPieces() throws IOException {
        Bishop bishop = new Bishop(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(75, 75, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        assertFalse(bishop.canMove(150, 150, pieces));
    }
}