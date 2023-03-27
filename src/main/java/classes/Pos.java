package classes;

/**
 * Deze klasse is er voor om de posities van de npc's op te slaan.
 */
public class Pos {
    private int x;
    private int y;
    private int index;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
        this.index = 56 * x + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }
}
