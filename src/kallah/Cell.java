package kallah;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Useless on 11.04.2017.
 */
public class Cell {
    Cell nextCell;
    private Cell against;
    Player player;
    private List<Rock> rocks;
    private boolean big;
    Cell(boolean big, Player player){
        rocks = new ArrayList<>();
        this.player = player;
        setBig(big);
    }
    void addRock(Rock rock){ rocks.add(rock); }
    private void removeRock(){
        rocks.remove(rocks.size()-1);
    }
    Rock getAndRemoveRock(){Rock ret = rocks.get(rocks.size()-1); removeRock(); return ret;}

    int getNumberOfRocks(){
        return rocks.size();
    }

    private void setBig(boolean big) {
        this.big = big;
    }

    void setAgainst(Cell against) {
        this.against = against;
    }

    Cell getAgainst() {
        return this.against;
    }

    Rock getRock(int i) {
        return this.rocks.get(i);
    }

    boolean isBig() {
        return this.big;
    }

    Player getPlayer() {
        return this.player;
    }
}
