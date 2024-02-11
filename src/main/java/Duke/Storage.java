package Duke;
import Duke.task.*;
import Duke.command.*;
import java.io.*;
import java.util.ArrayList;
import Duke.command.*;
import Duke.task.*;
public class Storage{
    private String filePath;
    Storage(String filePath){
        this.filePath = filePath;
    }
    public void writeDisk(ArrayList<task> strList) {
        try {
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(strList);
            out.close();
            file.close();
        }catch(IOException e){
            System.out.println("Problem writing to hard disk.");
//            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public ArrayList<task> load() {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            ArrayList<task> strlist = (ArrayList<task>) in.readObject();
            in.close();
            file.close();
            return strlist;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
}
