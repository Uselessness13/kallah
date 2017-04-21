package kallah;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Useless on 11.04.2017.
 */
public class Cell {
    Cell nextCell;
    List<Rock> rocks;
    boolean big;
    private Cell against;
    private Player player;

    Cell(boolean big, Player player) {
        rocks = new ArrayList<Rock>();
        this.player = player;
        setBig(big);
    }

    public void setAgainst(Cell against) {
        if (!this.isBig()) this.against = against;
    }

    void addRock(Rock rock) {
        rocks.add(rock);
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isBig() {
        return big;
    }

    private void removeRock() {
        rocks.remove(rocks.size() - 1);
    }

    Rock getAndRemoveRock() {
        Rock ret = rocks.get(rocks.size() - 1);
        removeRock();
        return ret;
    }

    Rock getRock() {
        return rocks.get(rocks.size() - 1);
    }

    int getNumberOfRocks() {
        return rocks.size();
    }

    private void setBig(boolean big) {
        this.big = big;
    }
}
