
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class loader {
    
    SavetoFile save ;
    static List<Wiki_pages> pages = new ArrayList<>(); 
    Similar_Metrics similar_data  = new Similar_Metrics();

    public loader(){

    }

    public loader(String filename , String url) throws IOException, ClassNotFoundException{
    
              Document doc = (Document) Jsoup.connect(url).get();
              String content = doc.body().text();
              save = new SavetoFile(filename,content);
              pages.add(new Wiki_pages(filename,content));

     }


    public void Calculate_similar_data() {

        similar_data.calculate_data(pages);

    }

        public void show_similar_data(){


        similar_data.show_data();

    }
     
     
     public void show_disjoint_set(){
     similar_data.number_of_DisjointSet();
     }

     public List<Wiki_pages> getPages(){
         return pages;
     }

    public PriorityQueue<Path> getSimilar_matrices(){
        return similar_data.getSimilar_matrices();
    }
     
}
