package filestorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

/**
 * Reads data from and save data into Hard Disk.
 */
public class Storage {
    /**
     * No constructor of class needed.
     */
    private Storage() {
        assert false : "Execution should never reach this point!";
    }

    /**
     * Reads File from Hard Disk.
     *
     * @return List of Task Lists from Hard Disk or the empty List if nothing is present
     * @throws Exception Wrong file location, no data in the file, or content in wrong format
     */
    public static List<List<Task>> inputFromFile() throws TaylorException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("taylor.txt"));
            return (List<List<Task>>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new TaylorException("\n" + "File not found\n" + System.getProperty("user.dir"));
        } catch (IOException | ClassNotFoundException e) {
            throw new TaylorException("No past data");
        } catch (ClassCastException e) {
            throw new TaylorException("Content are corrupted!");
        }
    }

    /**
     * Saves the Tasks in Hard Disk.
     *
     * @param taskList get the List of Tasks Lists and save in Hard Disk
     * @throws Exception file to save the List of Tasks Lists is not available
     */
    public static String outputToFile(List<List <Task>> taskList) throws Exception {
        StringBuilder response = new StringBuilder();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("taylor.txt"));
            oos.writeObject(taskList);
            response.append("For in the end, it's not goodbye.\n").append("But just a 'see you again'");
        } catch (FileNotFoundException e) {
            response.append("File not found\n").append("Please create file in ").append(System.getProperty("user.dir"));
        }
        return response.toString();
    }
}
