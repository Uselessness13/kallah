package sample;

import java.util.List;

/**
 * Created by Useless on 11.04.2017.
 */
public class Cell {
    List<Rock> rocks;
    public Cell(String colour){
        for (int i = 0; i < 6; i++)
            addRock(new Rock(colour));
    }
    public void addRock(Rock rock){
        rocks.add(rock);
    }
    public void removeRock(){
        rocks.remove(rocks.size()-1);
    }
}
