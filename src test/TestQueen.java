import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class TestQueen {
    @Test
    public void testMove() throws IOException {
        Queen queen = new Queen(0, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        queen.move(75, 75, pieces);
        assertEquals(75, queen.getX());
        assertEquals(75, queen.getY());
    }

    @Test
    public void testCapture() throws IOException {
        Queen queen = new Queen(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(75, 75, ChessPiece.BLACK);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        queen.move(75, 75, pieces);
        assertEquals(75, queen.getX());
        assertEquals(75, queen.getY());
        assertFalse(pieces.contains(rook));
    }

    @Test
    public void testCannotJumpOverPieces() throws IOException {
        Queen queen = new Queen(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(75, 75, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        assertFalse(queen.canMove(150, 150, pieces));
    }
}