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
    private int rocks;
    private boolean big;
    Cell(boolean big, Player player){
        rocks = 0;
        this.player = player;
        setBig(big);
    }
    void addRock(){ this.rocks++; }
    void removeRock(){this.rocks--;}

    int getNumberOfRocks(){
        return rocks;
    }

    private void setBig(boolean big) {
        this.big = big;
    }

    void setAgainst(Cell against) {
        this.against = against;
        against.against = this;
    }

    Cell getAgainst() {
        return this.against;
    }


    boolean isBig() {
        return this.big;
    }


    Player getPlayer() {
        return this.player;
    }
}
