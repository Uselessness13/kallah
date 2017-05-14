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
    Node root;
    HashMap<Integer, Node> rates;

    AI(Player player){
        this.player = player;
        random = new Random();
        rates = new HashMap<>();
    }
    CircleArray calculate(Board board) {

        CircleArray receivedCells = new CircleArray(board.getBoard());
        this.cells = new CircleArray(board.getBoard());

        ArrayList<Integer> availableMoves = new ArrayList<>();
        for (int i = 7; i < 13; i++) {
            if (cells.getCell(i).getNumberOfRocks() > 0)
                availableMoves.add(i);
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
        ArrayList<Integer> move = new ArrayList<>();
        move.addAll(moves.keySet());
        int max = 0;
        for (Integer m:move) {
            if (moves.get(m).getCell(13).getNumberOfRocks() > max){
                ind = moves.get(m);
                max = moves.get(m).getCell(13).getNumberOfRocks();
            }
        }

        ind.printer(ind);
        return ind;
    }

    class Node {
        Node father = null;
        ArrayList<Node> childs;
        CircleArray collocation;

        Node(Node father, CircleArray board) {
            this.father = father;
            childs = new ArrayList<>();
            this.collocation = board;
        }

        void addChild(CircleArray child) {
            this.childs.add(new Node(this, child));
        }
    }
}
