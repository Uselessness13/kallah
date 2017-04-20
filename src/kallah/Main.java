package kallah;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kallah.Controller;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        primaryStage.setTitle("kallah");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
        double i = 0.0d;
        primaryStage.setOpacity(0.0d);
        while (primaryStage.getOpacity() < 1){
            primaryStage.setOpacity(i);
            i+=0.01;
            Thread.sleep(5);
        }
        Controller controller = new Controller();controller.newGame();
    }
}
