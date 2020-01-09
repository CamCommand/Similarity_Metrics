import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mediod {


    static List<Graph_table> table;

    static Graph_table mediodd , k1 , k2;
    static List<Integer> taken = new ArrayList<>();
    static List<String> cluster1= new ArrayList<>();
    static List<String> cluster2= new ArrayList<>();

    public static boolean is_mediod(List<Graph_table> graph_table){

        table = graph_table;
        taken.add(0);
        mediodd = table.get(0);
        select_random_cluster(0);
        start_Clustering(k1 , mediodd );
        int cost1 =  getCost();
        start_Clustering(k1 , k2 );
        int cost2 =  getCost();
        
        
        if(cost1 < cost2){
         return true;
        }
        else{
          return false;
        }
        
    }

    public static int getCost(){

        int sum = 0;

        for(int i =1; i < cluster1.size(); i++){
            
            String []n1 =cluster1.get(0).split(",");
            String []n2 =cluster1.get(i).split(",");
            
            int s1 = Math.abs( Integer.parseInt(n1[0]) - Integer.parseInt(n2[0]) );
            int s2 = Math.abs( Integer.parseInt(n1[1]) - Integer.parseInt(n2[1]) );
            sum = sum + (s1+s2);
            
        }
        
        for(int i =1; i < cluster2.size(); i++){
            
            String []n1 =cluster2.get(0).split(",");
            String []n2 =cluster2.get(i).split(",");
            
            int s1 = Math.abs( Integer.parseInt(n1[0]) - Integer.parseInt(n2[0]) );
            int s2 = Math.abs( Integer.parseInt(n1[1]) - Integer.parseInt(n2[1]) );
            sum = sum + (s1+s2);
            
        }


        return sum;
    }


    public static void start_Clustering(Graph_table c1, Graph_table c2){
        cluster1.clear();
        cluster2.clear();
        cluster1.add(c1.getXaxis()+","+c1.getYaxis());
        cluster2.add(c2.getXaxis()+","+c2.getYaxis());

        for(int i =0 ; i < table.size(); i++ ){
            if(c1.equals(table.get(i)) || c2.equals(table.get(i)) ){}
            else{


                int dis1 = calculate_distance(table.get(i).getXaxis()+","+table.get(i).getYaxis(), c1.getXaxis()+","+c1.getYaxis());
                int dis2 = calculate_distance(table.get(i).getXaxis()+","+table.get(i).getYaxis(), c2.getXaxis()+","+c2.getYaxis());

                if (dis1 < dis2) {
                    cluster1.add(table.get(i).getXaxis()+","+table.get(i).getYaxis());
                }
                else {
                    cluster2.add(table.get(i).getXaxis()+","+table.get(i).getYaxis());
                }
            }
        }

    }

    public static void select_random_cluster(int i){


        Random rand = new Random();
        int n = rand.nextInt(table.size());


        if(i == 0){

            if(taken.contains(n)){
                select_random_cluster(0);
            }
            else{
                k1 = table.get(n) ;
                taken.add(n);
                select_random_cluster(1);
            }
        }
        else{
            if(taken.contains(n)){
                select_random_cluster(1);
            }
            else{
                k2 = table.get(n) ;
            }

        }

    }




    public static int calculate_distance( String c1 , String c2 ){

        String [] x1 = c1.split(",");
        String [] x2 = c2.split(",");

        int distance = Math.abs(Integer.parseInt(x2[0])- Integer.parseInt(x1[0])) + Math.abs(Integer.parseInt(x2[1])- Integer.parseInt(x1[1]));
        return distance;
    }

}
