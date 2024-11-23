import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class RookTest {
    @Test
    public void testMove() throws IOException {
        Rook rook = new Rook(0, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        rook.move(0, 75, pieces);
        assertEquals(0, rook.getX());
        assertEquals(75, rook.getY());
    }

    @Test
    public void testCapture() throws IOException {
        Rook rook = new Rook(0, 0, ChessPiece.WHITE);
        King king = new King(0, 75, ChessPiece.BLACK);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(king);
        rook.move(0, 75, pieces);
        assertEquals(0, rook.getX());
        assertEquals(75, rook.getY());
        assertFalse(pieces.contains(king));
    }

    @Test
    public void testCannotJumpOverPieces() throws IOException {
        Rook rook = new Rook(0, 0, ChessPiece.WHITE);
        Knight knight = new Knight(0, 75, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(knight);
        assertFalse(rook.canMove(0, 150, pieces));
    }
}