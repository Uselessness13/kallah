package kallah;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kallah.Cell;
import kallah.Player;
import java.util.ArrayList;

public class Controller {
    ImageView black = new ImageView("kallah/black.png");
    ImageView white = new ImageView("kallah/white.png");
    Player player1;
    Player player2;
    CircleArray cells;
    @FXML
    FlowPane BB;
    @FXML
    FlowPane B1;
    @FXML
    FlowPane B2;
    @FXML
    FlowPane B3;
    @FXML
    FlowPane B4;
    @FXML
    FlowPane B5;
    @FXML
    FlowPane B6;
    @FXML
    FlowPane WB;
    @FXML
    FlowPane W1;
    @FXML
    FlowPane W2;
    @FXML
    FlowPane W3;
    @FXML
    FlowPane W4;
    @FXML
    FlowPane W5;
    @FXML
    FlowPane W6;

    FlowPane[] flowPanes;

    public void newGame(){
        setFlowPanes();
        player1 = new Player(true);
        player2 = new Player(false);
        setCells();
        painter();
//        counter();
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

        this.cells.getCell(0).setAgainst(this.cells.getCell(7));
        this.cells.getCell(1).setAgainst(this.cells.getCell(8));
        this.cells.getCell(2).setAgainst(this.cells.getCell(9));
        this.cells.getCell(3).setAgainst(this.cells.getCell(10));
        this.cells.getCell(4).setAgainst(this.cells.getCell(11));
        this.cells.getCell(5).setAgainst(this.cells.getCell(12));

        this.cells.getCell(12).setAgainst(this.cells.getCell(5));
        this.cells.getCell(11).setAgainst(this.cells.getCell(4));
        this.cells.getCell(10).setAgainst(this.cells.getCell(3));
        this.cells.getCell(9).setAgainst(this.cells.getCell(2));
        this.cells.getCell(8).setAgainst(this.cells.getCell(1));
        this.cells.getCell(7).setAgainst(this.cells.getCell(0));

        for (int i = 0; i < 14; i++) {
            Cell curCell = this.cells.getCell(i);
            if (!curCell.big)
                for (int j = 0; j < 6; j++) {
                    Rock rock = new Rock();
                    rock.setImage(i < 7 ? white : black);
                    curCell.addRock(rock);
                }
        }
    }

    public void setFlowPanes() {
        this.flowPanes = new FlowPane[14];
        this.flowPanes[0] = W1;
        this.flowPanes[1] = W2;
        this.flowPanes[2] = W3;
        this.flowPanes[3] = W4;
        this.flowPanes[4] = W5;
        this.flowPanes[5] = W6;
        this.flowPanes[6] = WB;
        this.flowPanes[7] = B1;
        this.flowPanes[8] = B2;
        this.flowPanes[9] = B3;
        this.flowPanes[10] = B4;
        this.flowPanes[11] = B5;
        this.flowPanes[12] = B6;
        this.flowPanes[13] = BB;

    }

    public void counter(){
        player1.setCount(cells.getCell(0).getNumberOfRocks());
        player2.setCount(cells.getCell(7).getNumberOfRocks());
    }

    public void clickOnCell(MouseEvent e){
        Object view = (Pane)e.getSource();
        System.out.println(view.toString());
    }
    public void painter() {
        for (int i = 0; i < 14; i++) {
            Cell currentCell = this.cells.getCell(i);
            for (int j = 0; j < currentCell.getNumberOfRocks(); j++) {
                flowPanes[i].getChildren().add(currentCell.getRock().getImage());
            }
        }
    }

    void moveToKalah(int ind, Player player) {
        for (int i = 0; i < cells.getCell(ind).getNumberOfRocks(); i++) {
            Rock cr = cells.getCell(ind).getAndRemoveRock();
            if (player == player1)
                cells.getCell(6).addRock(cr);
            else
                cells.getCell(13).addRock(cr);
        }
    }
}
