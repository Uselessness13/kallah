package kallah;

/**
 * Created by Useless on 20.04.2017.
 */
class CircleArray {
    private Cell[] cells;
    private int amount;

    CircleArray(int amount){
        this.amount = amount;
        this.cells = new Cell[amount];
    }

    public void addCell(int ind, Cell cell){
        cells[ind] = cell;
        cells[ind].nextCell = cells[0];
    }

    public Cell getCell(int ind) {
        return ind < amount ? cells[ind] : cells[Math.abs(amount - ind)];
    }

    Cell moveRocks(int ind, Player player) {
        Cell indCell = cells[ind];
        Cell cc = null;
        for (int i = 0; i < indCell.rocks.size(); i++) {
            Rock cr = indCell.getAndRemoveRock();
            cc = getCell(ind + i + 1);
            if (cc.isBig()) {
                if (cc.getPlayer() == player)
                    cc.addRock(cr);
                else i++;
            }
            cc.addRock(cr);
        }
        return cc;
    }

    int getIndexOfCell(Cell cell) {
        for (int i = 0; i < this.amount; i++) {
            if (cell.equals(cells[i]))
                return i;
        }
        return -1;
    }


}
