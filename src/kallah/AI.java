package kallah;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Useless on 30.04.2017.
 */
public class AI extends Player{
    CircleArray cells;
    Board board;
    HashMap<Integer, CircleArray> moves;
    Random random;
    Player player;

    AI(Board board, Player player){
        this.board = board;
        this.player = player;
        random = new Random();
    }

    public int calculate(Board board) {
        this.cells = board.getBoard();
        ArrayList<Integer> availableMoves = new ArrayList<>();
        for (int i = 7; i < 13; i++) {
            if (cells.getCell(i).getNumberOfRocks() > 0)
                availableMoves.add(i);
        }
        int ind =  0;

        for (Integer availableMove : availableMoves) {
            printer(this.cells);
            board.setBoard(this.cells);
            printer(board.getBoard());
            board.makeStep(availableMove, player);
            printer(board.getBoard());
            moves.put(availableMove, board.getBoard());
            board.setBoard(this.cells);
        }

        for (int i = 0; i < moves.size(); i++) {
            ind = moves.get(i).getCell(13).getNumberOfRocks() > ind ? i : ind;
        }

        System.out.println(ind);
        return ind;
    }

    void printer(CircleArray cells){
        for (int i = 0; i < 14; i++) {
            System.out.print(i+" "+cells.getCell(i).getNumberOfRocks() + "; ");
        }
        System.out.println();
    }
}
