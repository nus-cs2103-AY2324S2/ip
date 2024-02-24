package zhen;
//import java.io.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import zhen.task.Task;

/**
 * Class stores tasks information locally and restores the chat from local
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object from a filepath which data is stored to and retrieved from.
     *
     * @param filePath Filepath the data is stored and retrieved.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the list of task into the file specified by filePath.
     *
     * @param tasksList List containing task information.
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
        }
    }

    /**
     * Loads a list of tasks from the file specified by filePath.
     *
     * @return An Arraylist containing the information of task stored by user previously.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() {
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
