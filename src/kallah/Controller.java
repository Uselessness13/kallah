package kallah;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kallah.Cell;
import kallah.Player;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

public class Controller {

    @FXML
    Label elabel;
    Stage stage;

    public void start(Stage stage) {
        newGame();
        this.stage = stage;
        stage.setResizable(false);
        Scene scene = stage.getScene();

    }

    public static void newGame(){
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(new Cell("black", false));
        cells.get(0).addRock(new Rock(cells.get(0).getColour()));
        cells.add(new Cell("black", false));
        cells.get(1).addRock(new Rock(cells.get(1).getColour()));
        cells.add(new Cell("black", false));
        cells.get(2).addRock(new Rock(cells.get(2).getColour()));
        cells.add(new Cell("black", false));
        cells.get(3).addRock(new Rock(cells.get(3).getColour()));
        cells.add(new Cell("black", false));
        cells.get(4).addRock(new Rock(cells.get(4).getColour()));
        cells.add(new Cell("black", false));
        cells.get(5).addRock(new Rock(cells.get(5).getColour()));
        cells.add(new Cell("", true));
        cells.add(new Cell("white", false));
        cells.get(7).addRock(new Rock(cells.get(7).getColour()));
        cells.add(new Cell("white", false));
        cells.get(8).addRock(new Rock(cells.get(8).getColour()));
        cells.add(new Cell("white", false));
        cells.get(9).addRock(new Rock(cells.get(9).getColour()));
        cells.add(new Cell("white", false));
        cells.get(10).addRock(new Rock(cells.get(10).getColour()));
        cells.add(new Cell("white", false));
        cells.get(11).addRock(new Rock(cells.get(11).getColour()));
        cells.add(new Cell("white", false));
        cells.get(12).addRock(new Rock(cells.get(12).getColour()));
        cells.add(new Cell("", true));
    }

    public void clickOnCell(MouseEvent e){
        ImageView view = (ImageView)e.getSource();
        System.out.println(view);
    }

    public void printall(MouseEvent event){

    }
}
