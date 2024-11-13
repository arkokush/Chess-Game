import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class TestPawn {
    @Test
    public void testPawnMovements() throws IOException {
        Pawn pawn = new Pawn(0, 0, ChessPiece.WHITE);
        ChessPiece opponent = new Pawn(75, 75, ChessPiece.BLACK);
        ChessPiece ally = new Pawn(0, 75, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(opponent);
        pieces.add(ally);

        // Test can move
        assertTrue(pawn.canMove(0, 75, pieces));  // Move to empty space
        assertFalse(pawn.canMove(0, 150, pieces));  // Invalid move

        // Test can capture
        assertTrue(pawn.canTake(opponent));  // Capture opponent

        // Test cannot jump over
        assertFalse(pawn.canMove(0, 150, pieces));  // Cannot jump over ally
    }
}