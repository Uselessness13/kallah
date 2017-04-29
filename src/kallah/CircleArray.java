package kallah;

/**
 * Created by Useless on 20.04.2017.
 */
class CircleArray {
    private Cell[] cells;
    private int amount;

    CircleArray(int amount) {
        this.amount = amount;
        this.cells = new Cell[amount];
    }

    void addCell(int ind, Cell cell) {
        cells[ind] = cell;
        cells[ind].nextCell = cells[0];
    }

    Cell getCell(int ind) {
        return ind < amount ? cells[ind] : cells[Math.abs(amount - ind)];
    }

    Cell moveRocks(int ind, Player player) {
        Cell indCell = cells[ind];
        Cell cc = null;
        int i = 0;
        int nr = indCell.getNumberOfRocks();
        while( i < nr) {
            Rock cr = indCell.getAndRemoveRock();
            cc = getCell(ind + 1 + i);
            if (cc.isBig()) {
                if (cc.getPlayer() == player) {
                    cc.addRock(cr);
                }
            }
            else cc.addRock(cr);
            i++;
        }
        return cc;
    }

    void moveToKallah(int ind, Player player) {
        int nr = cells[ind].getNumberOfRocks();
        for (int i = 0; i < nr; i++) {
            Rock cr = cells[ind].getAndRemoveRock();
            if (player == cells[6].getPlayer())
                cells[6].addRock(cr);
            else
                cells[13].addRock(cr);
        }
    }

    int getIndexOfCell(Cell cell) {
        for (int i = 0; i < this.amount; i++) {
            if (cell.equals(cells[i]))
                return i;
        }
        return -1;
    }


}
