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
    List<Rock> rocks;
    boolean big;
    FlowPane pane;
    Cell(boolean big, FlowPane pane){
        rocks = new ArrayList<Rock>();
        this.pane = pane;
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
}
