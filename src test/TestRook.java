import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class TestRook {
    @Test
    public void testRookMovements() throws IOException {
        Rook rook = new Rook(0, 0, ChessPiece.WHITE);
        ChessPiece opponent = new Pawn(0, 75, ChessPiece.BLACK);
        ChessPiece ally = new Pawn(0, 150, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(opponent);
        pieces.add(ally);

        // Test can move
        assertTrue(rook.canMove(0, 75, pieces));  // Move to empty space
        assertFalse(rook.canMove(75, 75, pieces));  // Invalid move

        // Test can capture
        assertTrue(rook.canMove(0, 75, pieces));  // Capture opponent

        // Test cannot jump over
        assertFalse(rook.canMove(0, 150, pieces));  // Cannot jump over ally
    }
}