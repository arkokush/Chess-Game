import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class TestBishop {
    @Test
    public void testBishopMovements() throws IOException {
        Bishop bishop = new Bishop(0, 0, ChessPiece.WHITE);
        ChessPiece opponent = new Pawn(75, 75, ChessPiece.BLACK);
        ChessPiece ally = new Pawn(150, 150, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(opponent);
        pieces.add(ally);

        // Test can move
        assertTrue(bishop.canMove(75, 75, pieces));  // Move to empty space
        assertFalse(bishop.canMove(150, 150, pieces));  // Invalid move

        // Test can capture
        assertTrue(bishop.canMove(75, 75, pieces));  // Capture opponent

        // Test cannot jump over
        assertFalse(bishop.canMove(225, 225, pieces));  // Cannot jump over ally
    }
}