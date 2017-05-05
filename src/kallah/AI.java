package kallah;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Useless on 30.04.2017.
 */
public class AI extends Player{
    CircleArray cells;
    ArrayList<CircleArray> tree;
    Random random;

    AI(CircleArray startCells){
        this.cells = startCells;
        random = new Random();
    }

    public int calculate(CircleArray board) {
        this.cells = board;
        ArrayList<Integer> availibleMoves = new ArrayList<>();
        for (int i = 7; i < 14; i++) {
            if (cells.getCell(i).getNumberOfRocks() > 0)
                availibleMoves.add(i);
        }
        int ind =  availibleMoves.get(random.nextInt(availibleMoves.size()));
        System.out.println(ind);
        return ind;
    }
}
