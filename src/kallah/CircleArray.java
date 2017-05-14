package kallah;

import javafx.scene.image.ImageView;

import java.nio.file.CopyOption;

/**
 * Created by Useless on 20.04.2017.
 */
class CircleArray implements CopyOption {
    Cell[] cells;
    private int amount;
    String white = "Images/white.png";
    String black = "Images/black.png";

    CircleArray(int amount) {
        this.amount = amount;
        this.cells = new Cell[amount];
    }


    public void setCells(Player player1, Player player2) {
        this.addCell(0, new Cell(false, player1));
        this.addCell(1, new Cell(false, player1));
        this.addCell(2, new Cell(false, player1));
        this.addCell(3, new Cell(false, player1));
        this.addCell(4, new Cell(false, player1));
        this.addCell(5, new Cell(false, player1));
        this.addCell(6, new Cell(true, player1));
        this.addCell(7, new Cell(false, player2));
        this.addCell(8, new Cell(false, player2));
        this.addCell(9, new Cell(false, player2));
        this.addCell(10, new Cell(false, player2));
        this.addCell(11, new Cell(false, player2));
        this.addCell(12, new Cell(false, player2));
        this.addCell(13, new Cell(true, player2));

        setNext(13);
        setNext(12);
        setNext(11);
        setNext(10);
        setNext(9);
        setNext(8);
        setNext(7);
        setNext(6);
        setNext(5);
        setNext(4);
        setNext(3);
        setNext(2);
        setNext(1);
        setNext(0);

        this.getCell(1).setAgainst(this.getCell(11));
        this.getCell(0).setAgainst(this.getCell(12));
        this.getCell(2).setAgainst(this.getCell(10));
        this.getCell(3).setAgainst(this.getCell(9));
        this.getCell(4).setAgainst(this.getCell(8));
        this.getCell(5).setAgainst(this.getCell(7));

        for (int i = 0; i < 14; i++) {
            Cell curCell = this.getCell(i);
            if (!curCell.isBig())
                for (int j = 0; j < 6; j++) {
                    if (!curCell.isBig())
                        curCell.addRock();
                }
        }
    }

    CircleArray(CircleArray circleArray) {
        this.cells = new Cell[14];
        Cell[] cells1 = circleArray.cells;
        for (int i = 0; i < cells1.length; i++) {
            Cell cell = cells1[i];
            addCell(i, new Cell(cell.isBig(), cell.getPlayer()));
            setNext(i);
        }
        setNext(13);
        setNext(12);
        setNext(11);
        setNext(10);
        setNext(9);
        setNext(8);
        setNext(7);
        setNext(6);
        setNext(5);
        setNext(4);
        setNext(3);
        setNext(2);
        setNext(1);
        setNext(0);
        this.getCell(0).setAgainst(this.getCell(12));
        this.getCell(1).setAgainst(this.getCell(11));
        this.getCell(2).setAgainst(this.getCell(10));
        this.getCell(3).setAgainst(this.getCell(9));
        this.getCell(4).setAgainst(this.getCell(8));
        this.getCell(5).setAgainst(this.getCell(7));

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < circleArray.cells[i].getNumberOfRocks(); j++) {
                this.cells[i].addRock();
            }
        }
    }

    public int getAmount() {
        return amount;
    }

    public int[] getAllStones() {
        int i = 0;
        int[] stones = new int[14];
        for (Cell cell : cells) {
            stones[i] = cell.getNumberOfRocks();
            i++;
        }
        return stones;
    }

    void addCell(int ind, Cell cell) {
        cells[ind] = cell;
    }

    void setNext(int ind) {
        cells[ind].nextCell = cells[ind == 13 ? 0 : ind + 1];
    }

    Cell getCell(int ind) {
//        System.out.println(ind);
        return ind < 14 ? cells[ind] : cells[ind % 14];
    }

    Cell moveRocks(int ind, Player player) {
        Cell indCell = cells[ind];
        Cell cc = indCell;
        int i = 0;
        int nr = indCell.getNumberOfRocks();
        while (i < nr) {
            indCell.removeRock();
            cc = cc.nextCell;
            System.out.println(cc);
            if (cc.isBig()) {
                if (cc.getPlayer() == player) {
                    cc.addRock();
                } else i--;
            } else cc.addRock();
            i++;
        }
        return cc;
    }

    void moveToKallah(int ind, Player player) {
        int nr = cells[ind].getNumberOfRocks();
        for (int i = 0; i < nr; i++) {
            cells[ind].removeRock();
            if (player == cells[6].getPlayer())
                cells[6].addRock();
            else
                cells[13].addRock();
        }
    }

    int getIndexOfCell(Cell cell) {
        for (int i = 0; i < 14; i++) {
            if (cell.equals(cells[i]))
                return i;
        }
        return -1;
    }

    void printer() {
        for (int i = 0; i < 14; i++) {
            System.out.print(i + " " + cells[i].getNumberOfRocks() + "; ");
        }
        System.out.println();
    }


    public int check() {
        int[] stones = this.getAllStones();
        int tCount = 0;
        int fCount = 0;
        for (int i = 0; i < 14; i++) {
            if (i != 6 && i != 13) {
                if (i < 6)
                    tCount += stones[i];
                else fCount += stones[i];
            }
        }
        if (stones[13] > 36 || (tCount == 0 && stones[6] < 36))
            return -1;
        if (stones[6] > 36 || (fCount == 0 && stones[13] < 36))
            return 1;
        if (stones[13] == stones[6] && stones[6] == 36)
            return 2;
        return 0;
    }
}
