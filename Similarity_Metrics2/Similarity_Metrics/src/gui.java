import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class gui extends Application {

    static Stage stage;

    public static Stage getStage(){
        return stage;
    }

    public static void setStage(Stage s){
        stage = s;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("show_gui.fxml"));
        primaryStage.setTitle("Similarty Metrics");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }
}
