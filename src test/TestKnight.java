import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class TestKnight {
    @Test
    public void testKnightMovements() throws IOException {
        Knight knight = new Knight(0, 0, ChessPiece.WHITE);
        ChessPiece opponent = new Pawn(150, 75, ChessPiece.BLACK);
        ChessPiece ally = new Pawn(75, 75, ChessPiece.WHITE);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(opponent);
        pieces.add(ally);

        // Test can move
        assertTrue(knight.canMove(150, 75, pieces));  // Move to empty space
        assertFalse(knight.canMove(75, 75, pieces));  // Invalid move

        // Test can capture
        assertTrue(knight.canMove(150, 75, pieces));  // Capture opponent

        // Test cannot jump over
        assertTrue(knight.canMove(150, 75, pieces));  // Knights can jump over pieces
    }
}