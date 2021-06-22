package antRace;

/**
 * read-only coordinates
 **/

public class FieldCoordinate {
    private int x;
    private int y;

    public FieldCoordinate( int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return String.format("%d:%d",x,y);
    }
}
