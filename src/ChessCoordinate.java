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

    public String toString() {
        return "" + file + rank;
    }
}
