import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FileHandler {
    public ArrayList<Task> loadTasks(){
        ArrayList<Task> temp = new ArrayList<>();
        String filePath = "";
        File f = new File(filePath);
        try{
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                //add things to the temp list
                String str = s.nextLine();

            }
        }catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }
        return temp;
    }
}
