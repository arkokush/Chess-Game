public class ChessCoordinate {
    private final char file;
    private final int rank;
    private final static int aChar = 'a';

    public ChessCoordinate(char file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public ChessCoordinate(int x, int y) {
        this.file = (char) (x/75 + aChar) ;
        this.rank = 8 - (y/75);
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public int getX() {
        return (file - aChar) * 75;
    }

    public int getY() {
        return (8 - rank) * 75;
    }

    public String toString() {
        return "" + file + rank;
    }
}
