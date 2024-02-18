package zhen;
import java.io.*;
import java.util.ArrayList;

import zhen.task.*;

/**
 * Class responsible for storing task information locally and restoring the chat from local
 */
public class Storage {
    private String filePath;

    /**
     * Construct a Storage object from a filepath which data is stored to and retrieved from
     *
     * @param filePath the filepath the data is stored and retrieved.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writing the list of task into the file specified by filePath
     *
     * @param tasksList the list containing task information
     */
    public void writeDisk(ArrayList<Task> tasksList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tasksList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Problem writing to hard disk.");
            //e.printStackTrace();
        }
    }

    /**
     * Loading a list of tasks from the file specified by filePath
     *
     * @return An Arraylist containing the information of task stored by user previously
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            ArrayList<Task> tasksList = (ArrayList<Task>) in.readObject();
            in.close();
            file.close();
            return tasksList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
