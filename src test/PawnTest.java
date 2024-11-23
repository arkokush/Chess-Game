import static org.junit.Assert.*;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

public class PawnTest {
    @Test
    public void testMove() throws IOException {
        Pawn pawn = new Pawn(0, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pawn.move(0, -75, pieces);
        assertEquals(0, pawn.getX());
        assertEquals(-75, pawn.getY());
    }

    @Test
    public void testCapture() throws IOException {
        Pawn pawn = new Pawn(0, 0, ChessPiece.WHITE);
        Rook rook = new Rook(75, -75, ChessPiece.BLACK);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(rook);
        pawn.move(75, -75, pieces);
        assertEquals(75, pawn.getX());
        assertEquals(-75, pawn.getY());
        assertFalse(pieces.contains(rook));
    }

    @Test
    public void testCannotMoveBackwards() throws IOException {
        Pawn pawn = new Pawn(0, 0, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        assertFalse(pawn.canMove(0, 75, pieces));
    }
}