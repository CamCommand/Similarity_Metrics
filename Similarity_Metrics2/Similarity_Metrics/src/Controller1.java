
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller1 implements Initializable {

    @FXML
    public ComboBox<String> comboBox1;
    public ComboBox<String> comboBox2;
    public Button btn;

    static Stage stage;

    loader load = new loader();

    List<Wiki_pages> pages;
    static PriorityQueue<Path> similar_set = new PriorityQueue<Path>();

    public static PriorityQueue<Path> getSimilar_set(){
        return similar_set;
    }

    public static void setSimilar_set(PriorityQueue<Path> s){
        similar_set = s;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pages= load.getPages();
        if(similar_set.isEmpty()){

            similar_set = load.getSimilar_matrices();
        }

   //     System.out.println("ssszzz = "+similar_set.size()+" "+load.getSimilar_matrices().size());
        ObservableList<String> list1 = FXCollections.observableArrayList(getTitle());
        ObservableList<String> list2 = FXCollections.observableArrayList(getTitle());
        comboBox1.setItems(list1);
        comboBox2.setItems(list2);

        btn = new Button();

    }



    public PriorityQueue<Path> calculate_similarity_matrices(){

        PriorityQueue<Path> similar = new PriorityQueue<>();
        String page1 = comboBox1.getValue();
        String page2 = comboBox2.getValue();
        String content1 ="";
        String content2 ="";


        Iterator value = similar_set.iterator();


        while (value.hasNext()) {
            Path p = (Path)value.next();
          //  System.out.println("szz = "+p.getPage_title1()+" = "+ p.getPage_title2());
              if(page1.equals(p.getPage_title1()) && page2.equals(p.getPage_title2()) ){


                similar.add(p);
            }
            else if(page1.equals(p.getPage_title2()) && page2.equals(p.getPage_title1())){
                similar.add(p);
            }

        }


        return similar;
    }




    public void calculate(){

        if(comboBox1.getValue() == null || comboBox2.getValue() == null  ){

            
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Page Title");
        alert.setContentText("Please select page title first");
        alert.show();
        
        }
        else if(comboBox1.getValue().equals(comboBox2.getValue())){
 
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Same Page Title");
        alert.setContentText("Both page title are same, must be different");
        alert.show();
        }

        else{
            try {

                PriorityQueue<Path> similar =   calculate_similarity_matrices();
                Controller1.setSimilar_set(similar);
                Parent root = FXMLLoader.load(getClass().getResource("graph.fxml"));
                Stage primaryStage = new Stage();
                stage = primaryStage;
                primaryStage.setTitle("Graph");
                primaryStage.setScene(new Scene(root,1200,650));
                primaryStage.show();
                gui.getStage().close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public static Stage getStage(){
        return stage;
    }


    public List<String> getTitle(){
        List<String> list = new ArrayList<>();
        for(int i =0 ; i < pages.size(); i++){

            list.add(pages.get(i).getTitle());
        }
      return list;
    }


}
