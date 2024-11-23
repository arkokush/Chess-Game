import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

public class ChessCoordinateTest {
    @Test
    public void getFile() {
    }

    @Test
    public void getRank() {
    }

    @Test
    public void getX() {
        assertEquals(525, (new ChessCoordinate('a', 1)).getX());
        assertEquals(0, (new ChessCoordinate('h', 1)).getX());
    }

    @Test
    public void getY() {
        assertEquals(525, (new ChessCoordinate('a', 1)).getY());
        assertEquals(0, (new ChessCoordinate('a', 8)).getY());
    }

    @Test
    public void testToString() {
        assertEquals("a1", (new ChessCoordinate('a', 1)).toString());
    }
}
