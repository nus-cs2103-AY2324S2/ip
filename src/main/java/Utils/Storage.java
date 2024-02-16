package Utils;

import Exceptions.TaskListFullException;
import Tasks.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    static String filepath = "data/";
    static String filename = "tasklist.ser";

    /**
     * Creates a storage file if it does not exist.
     * @throws IOException, SecurityException
     */
    public static void createStorage() throws IOException, SecurityException{
        File path = new File(filepath);

        //create the directory if it does not exist
        if (!path.exists()) {
            boolean created = path.mkdirs();
        }

        File file = new File(filepath + filename);
        boolean fileExists = file.exists();

        //create the file if it does not exist
        if (!fileExists) {
            boolean created = file.createNewFile();
        }
    }

    /**
     * Deletes the storage file if it exists.
     * @throws SecurityException
     */
    public static void deleteStorage() throws SecurityException{
        File file = new File(filepath + filename);

        boolean fileExists = file.exists();

        if (fileExists) {
            boolean deleted = file.delete();
        }
    }


    public static void saveTasks(TaskList taskList) throws IOException, SecurityException{

        try{
            FileOutputStream fos = new FileOutputStream(filepath + filename);
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fos);
            oos.writeObject(taskList.getList());
            oos.close();
            fos.close();
        }
        catch (FileNotFoundException e){
            throw new FileNotFoundException("The file to save the task list was not found.");
        }
        catch (IOException e){
            throw new IOException("An error occurred while saving the task list.");
        }

    }
    

    public static void main(String[] args) throws IOException, TaskListFullException, SecurityException {
        createStorage();

        TaskList ls = new TaskList();

        ls.add_task(new Event("event1", "from1", "to1"));
        ls.add_task(new Event("event2", "from2", "to2"));
        ls.add_task(new Todo("todo1"));
        ls.add_task(new Todo("todo2"));
        ls.add_task(new Deadline("deadline1", "by1"));
        ls.add_task(new Deadline("deadline2", "by2"));

        System.out.println(ls);

        System.out.println(ls.getList());
        saveTasks(ls);
        System.out.println("Saved");

    }
}
