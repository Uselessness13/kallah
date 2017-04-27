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
    Cell against;
    Player player;
    List<Rock> rocks;
    boolean big;
    FlowPane pane;
    Cell(boolean big, Player player){
        rocks = new ArrayList<Rock>();
        this.player = player;
        setBig(big);
    }
    void addRock(Rock rock){
        rocks.add(rock);
        //pane.getChildren().add(new ImageView(rock.image));//hz
    }
    public void removeRock(){
        rocks.remove(rocks.size()-1);
    }
    Rock getAndRemoveRock(){Rock ret = rocks.get(rocks.size()-1); rocks.remove(rocks.size()-1); return ret;}

    public int getNumberOfRocks(){
        return rocks.size();
    }

    private void setBig(boolean big) {
        this.big = big;
    }

    public void setAgainst(Cell against) {
        this.against = against;
    }

    public Cell getAgainst() {
        return this.against;
    }

    public Rock getRock() {
        return this.rocks.get(this.rocks.size() - 1);
    }

    public boolean isBig() {
        return this.big;
    }

    public Player getPlayer() {
        return this.player;
    }
}
