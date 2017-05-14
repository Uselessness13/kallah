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

        this.getCell(0).setAgainst(this.getCell(12));
        this.getCell(1).setAgainst(this.getCell(11));
        this.getCell(2).setAgainst(this.getCell(10));
        this.getCell(3).setAgainst(this.getCell(9));
        this.getCell(4).setAgainst(this.getCell(8));
        this.getCell(5).setAgainst(this.getCell(7));

        for (int i = 0; i < 14; i++) {
            Cell curCell = this.getCell(i);
            if (!curCell.isBig())
                for (int j = 0; j < 6; j++) {
                    Rock rock = new Rock();
                    rock.setImage(new ImageView(i < 7 ? white : black));
                    if (!curCell.isBig())
                        curCell.addRock(rock);
                }
        }
    }

    CircleArray(CircleArray circleArray){
        this.cells = new Cell[14];
        Cell[] cells1 = circleArray.cells;
        for (int i = 0; i < cells1.length; i++) {
            Cell cell = cells1[i];

            addCell(i, new Cell(cell.isBig(), cell.getPlayer()));
        }
        this.getCell(0).setAgainst(this.getCell(12));
        this.getCell(1).setAgainst(this.getCell(11));
        this.getCell(2).setAgainst(this.getCell(10));
        this.getCell(3).setAgainst(this.getCell(9));
        this.getCell(4).setAgainst(this.getCell(8));
        this.getCell(5).setAgainst(this.getCell(7));

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < circleArray.cells[i].getNumberOfRocks(); j++) {
                this.cells[i].addRock(circleArray.cells[i].getRock(j));
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
        cells[ind].nextCell = cells[0];
    }

    Cell getCell(int ind) {
//        System.out.println(ind);
        return ind < 14 ? cells[ind] : cells[ind % 14];
    }

    Cell moveRocks(int ind, Player player) {
        Cell indCell = cells[ind];
        Cell cc = null;
        int i = 0;
        int nr = indCell.getNumberOfRocks();
        while (i < nr) {
            Rock cr = indCell.getAndRemoveRock();
            cc = getCell(ind + 1 + i);
            if (cc.isBig()) {
                if (cc.getPlayer() == player) {
                    cc.addRock(cr);
                }
            } else cc.addRock(cr);
            i++;
        }
        return cc;
    }

    void moveToKallah(int ind, Player player) {
        System.out.println(ind);
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
        System.out.println(cell);
        for (int i = 0; i < 14; i++) {
            if (cell.equals(cells[i]))
                return i;
        }
        return -1;
    }

    void printer(CircleArray cells){
        for (int i = 0; i < 14; i++) {
            System.out.print(i+" "+cells.getCell(i).getNumberOfRocks() + "; ");
        }
        System.out.println();
    }


    public int check() {
        int[] stones = this.getAllStones();
        int tCount =0;
        int fCount = 0;
        for (int i = 0; i < 14; i++) {
            if(i!= 6 && i!=13){
                if(i<6)
                    tCount+=stones[i];
                else fCount+=stones[i];
            }
        }
        if(stones[13]>36 || (tCount==0 && stones[6]<36))
            return -1;
        if(stones[6]>36 || (fCount==0 && stones[13]<36))
            return 1;
        if(stones[13]==stones[6] && stones[6]==36)
            return 2;
        return 0;
    }
}
