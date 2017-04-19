package kallah;

import javafx.scene.image.ImageView;

import java.util.List;

/**
 * Created by Useless on 11.04.2017.
 */
public class Cell {
    private String colour;
    private List<Rock> rocks;
    private boolean big;
    Cell(String colour, boolean big){
        this.colour = colour;
        setBig(big);
    }
    public void addRock(Rock rock){
        this.rocks.add(rock);
    }
    public void removeRock(){
        rocks.remove(rocks.size()-1);
    }

    public String getColour() {
        return colour;
    }

    private void setBig(boolean big) {
        this.big = big;
    }
}
