
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Wiki_pages implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    String title;
    String content;
    String lines="";

    public Wiki_pages(String title, String content) {
        this.title = title;
        this.content = content;
        divideIntoLine();
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "" + "title = " + title + "\ncontent= " + content ;
    }

    public String getLines() {
        return this.lines;
    }

    public void setLines(String line) {
        this.lines = line;
    }



    public void divideIntoLine(){
        int step =0;
        String [] parts = content.split(" ");


        for(int i =0 ; i < 5 ; i++){

           for(int j =0 ; j < 5 ; j++){
               lines+=parts[step]+" ";
               step++;
           }
           if(i==4){
               lines+=parts[step];
           }
           else {
               lines+=parts[step]+"`";
               step++;
           }

        }
      //  System.out.println(lines.split("`").length);
        this.setLines(lines);
    }
    
    
    
}
