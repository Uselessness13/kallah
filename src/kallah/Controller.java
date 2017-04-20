package kallah;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import kallah.Cell;
import kallah.Player;
import java.util.ArrayList;

public class Controller {
    Stage stage;
    String black = "kallah/black.png";
    String white = "kallah/white.png";
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

    public void newGame(){
        player1 = new Player(true);
        player2 = new Player(false);
        cells = new CircleArray(14);
        cells.addCell(0, new Cell(true, BB));
        cells.addCell(1, new Cell(false, B1));
        cells.addCell(2, new Cell(false, B2));
        cells.addCell(3, new Cell(false, B3));
        cells.addCell(4, new Cell(false, B4));
        cells.addCell(5, new Cell(false, B5));
        cells.addCell(6, new Cell(false, B6));
        cells.addCell(7, new Cell(true, WB));
        cells.addCell(8, new Cell(false, W1));
        cells.addCell(9, new Cell(false, W2));
        cells.addCell(10, new Cell(false, W3));
        cells.addCell(11, new Cell(false, W4));
        cells.addCell(12, new Cell(false, W5));
        cells.addCell(13, new Cell(false, W6));
        for (int i = 0; i < 14; i++) {
            Cell curCell = cells.getCell(i);
            if (!curCell.big)
                for (int j = 0; j < 6; j++) {
                    Rock rock = new Rock(i < 7 ? "black" : "white");
                    rock.setImage(new Image((i < 7 ? black : white)));
                    curCell.addRock(rock);
                }
        }
//        counter();
    }

    public void counter(){
        player1.setCount(cells.getCell(0).getNumberOfRocks());
        player2.setCount(cells.getCell(7).getNumberOfRocks());
    }

    public void clickOnCell(MouseEvent e){
        Object view = (Pane)e.getSource();
        System.out.println(view.toString());
    }
}
