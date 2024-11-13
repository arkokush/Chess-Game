import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class TestKing {
    @Test
    public void testKingMovements() throws IOException {
        King king = new King(0, 0, ChessPiece.WHITE);
        ChessPiece opponent = new Pawn(75, 75, ChessPiece.BLACK);
        ChessPiece ally = new Pawn(150, 150, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(opponent);
        pieces.add(ally);

        // Test can move
        assertTrue(king.canMove(75, 75, pieces));  // Move to empty space
        assertFalse(king.canMove(150, 150, pieces));  // Invalid move

        // Test can capture
        assertTrue(king.canMove(75, 75, pieces));  // Capture opponent

        // Test cannot jump over
        assertFalse(king.canMove(225, 225, pieces));  // Cannot jump over ally
    }
}