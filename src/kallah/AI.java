package kallah;

import java.util.*;

/**
 * Created by Useless on 30.04.2017.
 */
public class AI extends Player{
    private CircleArray cells;
    private Board board;
    private Player player;
    private Random random;

    AI(Player player){
        this.player = player;
        random = new Random();
    }
    CircleArray calculate(Board board) {

        CircleArray receivedCells = new CircleArray(board.getBoard());
        this.cells = new CircleArray(board.getBoard());

        ArrayList<Integer> availableMoves = new ArrayList<>();
        for (int i = 7; i < 13; i++) {
            if (this.cells.getCell(i).getNumberOfRocks() > 0){
                availableMoves.add(i);
                System.out.println(i + " with " + this.cells.getCell(i).getNumberOfRocks()+ " rocks");
            }
        }
        CircleArray ind = null;
        HashMap<Integer, CircleArray> moves = new HashMap<>();
        for (Integer availableMove : availableMoves) {
            board.setBoard(new CircleArray(receivedCells));
            board.makeStep(availableMove, player);
            CircleArray currentBoard = new CircleArray(board.getBoard());
            moves.put(availableMove, currentBoard);
            board.setBoard(receivedCells);
        }
        System.out.println(moves.size());
        ArrayList<Integer> move = new ArrayList<>();
        move.addAll(moves.keySet());
        System.out.println(move);
        int max = -1;
        for (Integer m:move) {
            if (moves.get(m).getCell(13).getNumberOfRocks() > max){
                ind = moves.get(m);
                max = moves.get(m).getCell(13).getNumberOfRocks();
                System.out.println(m+" move  - max "+max);
            }
        }
        System.out.println();
        receivedCells.printer();
        ind.printer();
        return ind;
    }
}
