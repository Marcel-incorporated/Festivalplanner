package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    private ArrayList<Block> blocks;

    public Map(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
