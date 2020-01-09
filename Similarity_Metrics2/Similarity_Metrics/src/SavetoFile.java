
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class SavetoFile {
    
    String title;
    String content;

   
    public SavetoFile(String title, String content) throws ClassNotFoundException{
      this.title = title;
      this.content = content;
      save();
    }
    
    
    // divide whole
    // save to file
    public void save() throws ClassNotFoundException{
    
        FileOutputStream f = null;
        try {
            Wiki_pages page = new Wiki_pages(title , content);
            f = new FileOutputStream(new File("file/"+title+".txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            // Write objects to file
            o.writeObject(page);
            
         
            o.close();
            f.close();
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SavetoFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SavetoFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                Logger.getLogger(SavetoFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    
    }
    
}
