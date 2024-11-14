import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

public class TestQueen {
    @Test
    public void testQueenMovements() throws IOException {
        Queen queen = new Queen(300, 300, ChessPiece.WHITE);
        ChessPiece opponent = new Pawn(75, 75, ChessPiece.BLACK);
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        pieces.add(opponent);
        pieces.add(queen);

        // Test can move
        assertTrue(queen.canMove(300, 75, pieces)); // Moves Up
        assertTrue(queen.canMove(300, 450, pieces)); // Moves Down
        assertTrue(queen.canMove(75, 300, pieces)); //Moves Left
        assertTrue(queen.canMove(450, 300, pieces)); //Moves right
        assertTrue(queen.canMove(150, 150, pieces)); // Moves Top Left
        assertTrue(queen.canMove(450, 150, pieces)); // Moves Top Right
        assertTrue(queen.canMove(450, 450, pieces)); // Moves Bottom Right
        assertTrue(queen.canMove(150, 450, pieces)); // Moves Bottom Left
        assertFalse(queen.canMove(75, 150, pieces));  // Invalid move

        // Test can capture
        assertTrue(queen.canMove(75, 75, pieces));  // Capture opponent
        queen.move(75,75,pieces);
        assertEquals(-1,pieces.indexOf(opponent)); //Makes sure piece isn't there

        pieces.add( new Pawn(300, 375, ChessPiece.WHITE));
        pieces.add( new Pawn(300, 225, ChessPiece.WHITE));
        pieces.add( new Pawn(375, 300, ChessPiece.WHITE));
        pieces.add( new Pawn(225, 300, ChessPiece.WHITE));
        pieces.add( new Pawn(225, 225, ChessPiece.WHITE));
        pieces.add( new Pawn(375, 225, ChessPiece.WHITE));
        pieces.add( new Pawn(225, 375, ChessPiece.WHITE));
        pieces.add( new Pawn(375, 375, ChessPiece.WHITE));

        // Test cannot jump over
        assertFalse(queen.canMove(300, 75, pieces)); // Moves Up
        assertFalse(queen.canMove(300, 450, pieces)); // Moves Down
        assertFalse(queen.canMove(75, 300, pieces)); //Moves Left
        assertFalse(queen.canMove(450, 300, pieces)); //Moves right
        assertFalse(queen.canMove(150, 150, pieces)); // Moves Top Left
        assertFalse(queen.canMove(450, 150, pieces)); // Moves Top Right
        assertFalse(queen.canMove(450, 450, pieces)); // Moves Bottom Right
        assertFalse(queen.canMove(150, 350, pieces)); // Moves Bottom Left
        assertFalse(queen.canMove(75, 150, pieces));  // Invalid move
    }
}