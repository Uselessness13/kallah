package kallah;

/**
 * Created by Useless on 18.04.2017.
 */
class Board {
    private static CircleArray board;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    Board(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        board = new CircleArray(14);
        board.setCells(player1, player2);
        activePlayer = player1;
    }

    void invertPlayer(){
        if (activePlayer == player1)
            activePlayer = player2;
        else activePlayer = player1;
    }

    Player makeStep(int i, Player active){
        if (board.getCell(i).player == active && !board.getCell(i).isBig()) {
            Cell endCell = null;
            Cell startCell = null;
            if (i >= 0) {
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

    CircleArray getBoard() {
        return board;
    }

    void setBoard(CircleArray board) {
        Board.board = board;
    }


    public int[] getAllStones() {
        int i = 0;
        int[] stones = new int[14];
        for (Cell cell : board.cells) {
            stones[i] = cell.getNumberOfRocks();
            i++;
        }
        return stones;
    }
}
