import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class show_graph extends Application {


    public static void show(String [] args){
        Application.launch();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("graph.fxml"));
        primaryStage.setTitle("Graph");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}