package kallah;

import javafx.scene.control.CheckBox;
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
    public Player player2;
    public Player player1;
    private Player activePlyer = player1;
    CircleArray cells;
    Board board;
    FP fp;
    boolean gameStarted = false;
    boolean firstStep = true;
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
    public CheckBox IIDetector;
    boolean wai = false;
    AI ai;

    public void newGame() {
        gameStarted = true;
        firstStep = true;
        setFlowPanes();
        player1 = new Player();
        player2 = new Player();
        activePlyer = player1;
        //setCells();
        board = new Board(player1, player2);
        if (wai) {
            ai = new AI(player2);
        }
        cells = board.getBoard();
        painter();
        counter();
        pl3.setText("Ваш ход"); //первое уточнение игрока
    }

    public void IIDetector() {
        wai = !wai;
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

        if (player1.getCount() > 36 || player1.getCount() > 36) {
            gameStarted = false;
        }
        checkForWin();
    }

    private void recognize() {
        if (activePlyer == player1 && gameStarted) {
            pl3.setText("Ваш ход");
        } else {
            pl3.setText("Ход соперника");
        }
        checkForWin();
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
        boolean w1 = player1.getCount() > 36;
        boolean w2 = player2.getCount() > 36;
        if (w1) pl3.setText("Вы побэйдили");
        if (w2) pl3.setText("Вас побэйдили");
        gameStarted = lp1 && lp2 && (!w1 && !w2);
    }

    public void clickOnCell(MouseEvent e) {
        Object view = (FlowPane) e.getSource();
        painter();
        if (gameStarted) {
            if (wai) {
                for (int i = 0; i < 14; i++) {
                    if (fp.getFP(i).equals(view)) {
                        if (board.getBoard().cells[i].getNumberOfRocks() > 0) {
                            if (firstStep) {
                                if (i != 0) {
                                    activePlyer = board.makeStep(i, activePlyer);
                                    observer();
                                    firstStep = false;
                                    if (activePlyer != player1) {
                                        activePlyer = player1;
                                        board.setBoard(ai.calculate(board));
                                        observer();
                                    }
                                }
                            } else {
                                activePlyer = board.makeStep(i, activePlyer);
                                observer();
                                if (activePlyer != player1) {
                                    activePlyer = player1;
                                    board.setBoard(ai.calculate(board));
                                    observer();
                                }
                            }
                            break;
                        }
                    }
                }
            } else {
                for (int i = 0; i < 14; i++) {
                    if (fp.getFP(i).equals(view))
                        if (firstStep) {
                            if (i != 0) {
                                activePlyer = board.makeStep(i, activePlyer);
                                observer();
                                firstStep = false;
                            }
                        } else {
                            activePlyer = board.makeStep(i, activePlyer);
                            observer();
                        }
                }
            }
            observer();
        }
    }

    void observer() {
        cells = board.getBoard();
        painter();
        counter();
        recognize();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void painter() {
        for (int i = 0; i < 14; i++) {
            Cell currentCell = board.getBoard().getCell(i);
            fp.getFP(i).getChildren().clear();
            List<ImageView> viewList = new ArrayList<>();
            for (int j = 0; j < currentCell.getNumberOfRocks(); j++) {
                viewList.add(currentCell.getRock(j).getImage());
            }
            fp.getFP(i).getChildren().addAll(viewList);
        }
    }
}
