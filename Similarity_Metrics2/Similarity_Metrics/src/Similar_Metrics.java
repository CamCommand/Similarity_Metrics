import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;



public class Similar_Metrics {
    
    static List<String> disjoint_set = new ArrayList<>();
    // Create a PriorityQueue
   static PriorityQueue<Path> Priorityqueue = new PriorityQueue<>();

    
    public Similar_Metrics(){

    }
    
    
    public void calculate_data(List<Wiki_pages> pages){


        for(int a = 0; a <pages.size(); a++){

             for(int i =0 ; i < pages.size(); i++){

              if(pages.get(a).getTitle().equals(pages.get(i).getTitle()) ){
                  // skip
              }
              else{

                  String [] lines1 = pages.get(a).getLines().split("`");
                  String [] lines2 = pages.get(i).getLines().split("`");

                  //    System.out.println(lines1.length +" == "+lines2.length);



                  for(int j =0 ; j < lines1.length; j++){

                      for(int z =0 ; z < lines2.length; z++){

                          int path = lines1[j].compareTo(lines2[z]);

                          if(path <= 0){
                              disjoint_set.add(pages.get(0).getTitle()+","+lines1[j]+","+pages.get(i).getTitle()+","+lines2[z]+","+path);
                          }
                          else if( lines2.equals("") || lines1.equals("") ){

                          }
                          else{
                              Path p = new Path(pages.get(a).getTitle(),pages.get(i).getTitle(),lines1[j],lines2[z],path);
                              if(Priorityqueue.contains(p)){
                                  // skip if already available
                              }
                              else{
                                  Priorityqueue.add(p);
                              }

                          }

                      }
                  }
              }




             }
        }
    
    }
    
    public void show_data(){


        Iterator value = Priorityqueue.iterator();


        while (value.hasNext()) {
            System.out.println(value.next());
        }


          //  System.out.println(temp.poll().toString());

        }
    
    public void number_of_DisjointSet(){
    
        System.out.println("Number of Disjoint Sets are = "+ disjoint_set.size());
    } 


    public PriorityQueue<Path> getSimilar_matrices(){
        return Priorityqueue;
    }
}
