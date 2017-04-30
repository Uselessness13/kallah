package kallah;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    ImageView blackP = new ImageView("Images/black.png");
    ImageView whiteP = new ImageView("Images/white.png");
    String white = "Images/white.png";
    String black = "Images/black.png";
    Player player2;
    Player player1;
    private Player activePlyer;
    CircleArray cells;
    //private FlowPane[] flowPanes;
    FP fp;
    boolean gameStarted = false;
    public FlowPane BB;
    public FlowPane B1;
    public FlowPane B2;
    public FlowPane B3;
    public FlowPane B4;
    public FlowPane B5;
    public FlowPane B6;
    public FlowPane WB;
    public FlowPane W1;
    public FlowPane W2;
    public FlowPane W3;
    public FlowPane W4;
    public FlowPane W5;
    public FlowPane W6;
    public Label pl1;
    public Label pl2;
    public Label pl3;

    public void newGame() {
        gameStarted = true;
        setFlowPanes();
        player1 = new Player();
        player2 = new Player();
        activePlyer = player1;
        setCells();
        painter();
        counter();
        pl3.setText("Ваш ход"); //первое уточнение игрока
    }

    private void setCells() {
        this.cells = new CircleArray(14);
        this.cells.addCell(0, new Cell(false, player1));
        this.cells.addCell(1, new Cell(false, player1));
        this.cells.addCell(2, new Cell(false, player1));
        this.cells.addCell(3, new Cell(false, player1));
        this.cells.addCell(4, new Cell(false, player1));
        this.cells.addCell(5, new Cell(false, player1));
        this.cells.addCell(6, new Cell(true, player1));
        this.cells.addCell(7, new Cell(false, player2));
        this.cells.addCell(8, new Cell(false, player2));
        this.cells.addCell(9, new Cell(false, player2));
        this.cells.addCell(10, new Cell(false, player2));
        this.cells.addCell(11, new Cell(false, player2));
        this.cells.addCell(12, new Cell(false, player2));
        this.cells.addCell(13, new Cell(true, player2));

        this.cells.getCell(0).setAgainst(this.cells.getCell(12));
        this.cells.getCell(1).setAgainst(this.cells.getCell(11));
        this.cells.getCell(2).setAgainst(this.cells.getCell(10));
        this.cells.getCell(3).setAgainst(this.cells.getCell(9));
        this.cells.getCell(4).setAgainst(this.cells.getCell(8));
        this.cells.getCell(5).setAgainst(this.cells.getCell(7));

        this.cells.getCell(12).setAgainst(this.cells.getCell(0));
        this.cells.getCell(11).setAgainst(this.cells.getCell(1));
        this.cells.getCell(10).setAgainst(this.cells.getCell(2));
        this.cells.getCell(9).setAgainst(this.cells.getCell(3));
        this.cells.getCell(8).setAgainst(this.cells.getCell(4));
        this.cells.getCell(7).setAgainst(this.cells.getCell(5));

        for (int i = 0; i < 14; i++) {
            Cell curCell = this.cells.getCell(i);
            if (!curCell.isBig())
                for (int j = 0; j < 6; j++) {
                    Rock rock = new Rock();
                    rock.setImage(new ImageView(i < 7 ? white : black));
                    if (!curCell.isBig())
                        curCell.addRock(rock);
                }
        }
    }

    private class FP {
        FlowPane[] flowPanes;

        FP() {
            flowPanes = new FlowPane[14];
            flowPanes[1] = W2;
            flowPanes[0] = W1;
            flowPanes[2] = W3;
            flowPanes[3] = W4;
            flowPanes[4] = W5;
            flowPanes[5] = W6;
            flowPanes[6] = WB;
            flowPanes[7] = B1;
            flowPanes[8] = B2;
            flowPanes[9] = B3;
            flowPanes[10] = B4;
            flowPanes[11] = B5;
            flowPanes[12] = B6;
            flowPanes[13] = BB;
        }

        FlowPane getFP(int i) {
            return flowPanes[i];
        }
    }


    private void setFlowPanes() {
        fp = new FP();
    }

    private void counter() {
        player1.setCount(cells.getCell(6).getNumberOfRocks());
        player2.setCount(cells.getCell(13).getNumberOfRocks());
        pl1.setText(String.valueOf(player1.getCount()));
        pl2.setText(String.valueOf(player2.getCount()));
        // Вот тут для индикации надо
//        if (activePlyer == player1){
//            pl1.setBackground();
//            pl2.setBackground(null);
//        }
//        else {
//            pl2.setBackground();
//            pl1.setBackground(null);
//        }

        if (player1.getCount() >= 36 || player1.getCount() >= 36) {
            gameStarted = false;
        }
        checkForWin();
    }

    public void recognize() {  //вывод игрока
        if (activePlyer == player1){
            pl3.setText("Ваш ход");}
            else {
            pl3.setText("Ход соперника");
        }
    }

    private void checkForWin() {
        boolean lp1 = false;
        boolean lp2 = false;
        for (int i = 0; i < 6; i++) {
            if (cells.getCell(i).getNumberOfRocks() > 0) {
                lp1 = true;
            }
            if (cells.getCell(i + 1 + 6).getNumberOfRocks() > 0) {
                lp2 = true;
            }
        }
        gameStarted = lp1 && lp2;
    }

    public void clickOnCell(MouseEvent e) {
        Object view = (FlowPane) e.getSource();
        painter();
        if (gameStarted) {
            int ind = -1;
            for (int i = 0; i < 14; i++) {
                if (fp.getFP(i).equals(view))
                    ind = i;
            }
            if (cells.getCell(ind).player == activePlyer && !cells.getCell(ind).isBig()) {
                Cell endCell = null;
                Cell startCell = null;
                int startCellRocks = -1;
                if (ind >= 0) {
                    startCell = cells.getCell(ind);
                    startCellRocks = startCell.getNumberOfRocks();
                    endCell = cells.moveRocks(ind, activePlyer);
                    painter();
                }
                if (endCell != null) {
                    if (endCell.getNumberOfRocks() == 1 && endCell.getAgainst() != null) {
                        cells.moveToKallah(cells.getIndexOfCell(endCell.getAgainst()), activePlyer);
                        activePlyer = activePlyer == player1 ? player2 : player1;
                    } else if (!endCell.isBig()) {
                        activePlyer = activePlyer == player1 ? player2 : player1;
                    }
                    painter();
                }
            }
            painter();
            counter();
            recognize(); //вывод игрока

        }

    }

    public void painter() {
        for (int i = 0; i < 14; i++) {
            Cell currentCell = this.cells.getCell(i);
            fp.getFP(i).getChildren().clear();
            List<ImageView> viewList = new ArrayList<>();
            for (int j = 0; j < currentCell.getNumberOfRocks(); j++) {
                viewList.add(currentCell.getRock(j).getImage());
            }
            fp.getFP(i).getChildren().addAll(viewList);
        }
    }
}
