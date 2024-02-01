package filestorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import tasks.Task;

/**
 * To read data from and save data into Hard Disk
 */
public class Storage {
    /**
     * No constructor of class needed
     */
    private Storage() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Read File from Hard Disk
     * @param taskList get the empty Action ArrayList
     * @return Action ArrayList from Hard Disk or the empty ArrayList if not present
     * @throws Exception Wrong file location, no data in the file, or content in wrong format
     */
    public static List<Task> inputFromFile(List<Task> taskList) throws Exception {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("taylor.txt"));
            taskList = (List<Task>) ois.readObject();
            int pos = 1;
            for (Task act : taskList) {
                System.out.println(pos++ + ". " + act);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println("Please create file in " + System.getProperty("user.dir"));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No past data");
        } catch (ClassCastException e) {
            System.out.println("Content are corrupted!");
        }
        return taskList;
    }

    /**
     * Save the Actions in Hard Disk
     * @param taskList get the Action Array List and save in Hard Disk
     * @throws Exception file to save the Actions is not available
     */
    public static void outputToFile(List<Task> taskList) throws Exception {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("taylor.txt"));
            oos.writeObject(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println("Please create file in " + System.getProperty("user.dir"));
        }
    }
}
