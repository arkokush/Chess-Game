public class ChessCoordinate {
    private final char file;
    private final int rank;

    public ChessCoordinate(char file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public int getX() {
        return (8 - (Character.getNumericValue(file) - 9) ) * 75;
    }

    public int getY() {
        return (8 - rank) * 75;
    }

    public String toString() {
        return "" + file + rank;
    }
}
