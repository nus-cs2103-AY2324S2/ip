package Duke;
import Duke.task.*;
import Duke.command.*;
import java.io.*;
import java.util.ArrayList;
import Duke.command.*;
import Duke.task.*;

/**
 * Class responsible for storing task information locally and restoring the chat from local
 */
public class Storage{
    private String filePath;

    /**
     * Construct a Storage object from a filepath which data is stored to and retrieved from
     *
     * @param filePath the filepath the data is stored and retrieved.
     */
    Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Writing the list of task into the file specified by filePath
     *
     * @param strList the list containing task information
     */
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

    /**
     * Loading a list of tasks from the file specified by filePath
     *
     * @return An Arraylist containing the information of task stored by user previously
     */
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
