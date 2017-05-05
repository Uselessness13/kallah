package kallah;

/**
 * Created by Useless on 18.04.2017.
 */
public class Board {
    CircleArray board;
    Player player1;
    Player player2;
    Board(CircleArray board, Player player1, Player player2){
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    Player makeStep(int i, Player active){
        if (board.getCell(i).player == active && !board.getCell(i).isBig()) {
            Cell endCell = null;
            Cell startCell = null;
            int startCellRocks = -1;
            if (i >= 0) {
                startCell = board.getCell(i);
                startCellRocks = startCell.getNumberOfRocks();
                endCell = board.moveRocks(i, active);
            }
            if (endCell != null) {
                if (endCell.getNumberOfRocks() == 1 && endCell.getAgainst() != null) {
                    board.moveToKallah(board.getIndexOfCell(endCell.getAgainst()), active);
                    active = active == player1 ? player2 : player1;
                } else if (!endCell.isBig()) {
                    active = active == player1 ? player2 : player1;
                }
            }
        }
        return active;
    }

    public CircleArray getBoard() {
        return board;
    }

    public void setBoard(CircleArray board) {
        this.board = board;
    }
}
