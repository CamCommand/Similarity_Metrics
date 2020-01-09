import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    @FXML
    public Button btn;

    @FXML
     public Label label2;

    @FXML
    LineChart<String,Number> linechart;

    XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
    PriorityQueue<Path> similar_set;
    List<Graph_table> graph_table = new ArrayList<>();
    String ff1 , ff2 ,tt1 , tt2;
    int shorest_path;



    public void setPage(PriorityQueue<Path> similar){
        similar_set = similar;
        int flag=0;
        int x = 1;
        //


        while (!similar_set.isEmpty()) {

                    Path p = similar_set.poll();
                    graph_table.add(new Graph_table(x, p.getDistance()));

            if(x==1){
                ff1 = p.getLine1();
                ff2 = p.getLine2();
                tt1 = p.getPage_title1();
                tt2 = p.getPage_title2();
                shorest_path = p.getDistance();

            }

            String f1,f2;
            String line1 = p.getLine1();
            String line2 = p.getLine2();


            if(line1.length() > 8){
                f1 = line1.substring(0,8)+"...";
            }
            else{
                f1 = line1;
            }


            if (line2.length() > 8) {
                f2 = line2.substring(0, 8) + "...";
            } else {
                f2 = line2;
            }

            series.getData().add(new XYChart.Data<String, Number>(f1 + " - " + f2, p.getDistance()) );



            x++;

        }



        //


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setPage( Controller1.getSimilar_set() );
        btn = new Button();
        linechart.getData().add(series);

        label2.setText("Shortest weight by metrics is = ["+ff1+"] - ["+ff2+"]" );

        boolean Ismediod  = mediod.is_mediod(graph_table);

        
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(1000),
        ae -> msg_mediod(Ismediod)));
        timeline.play();
        

        
        
       
        
        
    }


    public void msg_mediod(boolean Ismediod ){
    
        
        if(Ismediod){
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Mediod");
        alert.setHeaderText("Path is Mediod");

        
        String s ="[Title1 = "+tt1+" - Title2 = "+tt2+"] = [Content1 = "+ ff1+" - Content2 = "+ff2+"]  path = " + shorest_path;
        alert.setContentText(s);
        alert.show();
        
        
        }
        else{

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Mediod");
            alert.setHeaderText("Path is not Mediod");

            String s ="[Title1 = "+tt1+" - Title2 = "+tt2+"] = [Content1 = "+ ff1+" - Content2 = "+ff2+"]  path = " + shorest_path;
            alert.setContentText(s);
            alert.show();
        
            
        }
        
    }

    @FXML
    public void go_back(){

        try {

            Parent root = FXMLLoader.load(getClass().getResource("show_gui.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Similarty Metrics");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            gui.setStage(primaryStage);
            Controller1.getStage().close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
