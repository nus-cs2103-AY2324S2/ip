package luke;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage implements Serializable {

    private ArrayList<Task> history;

    public Storage() {
        history = new ArrayList<>();
    }

    /**
     * Attempts to save history. (My reference: https://www.baeldung.com/java-serialization)
     * History is saved when exiting the program normally, by using "bye" command.
     *
     * @param file File object to locate the save file.
     * @param taskList An ArrayList of Tasks, as obtained from our TaskList.
     */
    public void saveHistory(File file, ArrayList<Task> taskList) {
        updateHistory(taskList);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(this);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    public static File getHistoryFile() {
        String workingDirectory = System.getProperty("user.dir");
        Path historyPath = Paths.get(workingDirectory, "data", "history.txt");
        File historyFile = new File(String.valueOf(historyPath));
        return historyFile;
    }

    public static Storage loadHistory() {
        String workingDirectory = System.getProperty("user.dir");
        Path directoryPath = Paths.get(workingDirectory,  "data");
        Path historyPath = Paths.get(workingDirectory, "data", "history.txt");
        //create directory (if it does not already exist)
        try {
            Files.createDirectory(directoryPath);
        } catch (FileAlreadyExistsException e) {
            //directory already exists.
        } catch (IOException e) {
            System.out.println("Failed to create directory");
        }
        //create file (if it does not already exist)
        try {
            Files.createFile(historyPath);
        } catch (FileAlreadyExistsException e) {
            //file already exists.
        } catch (IOException e) {
            //should (by right) never happen!
            System.out.println("Failed to create file");
            return null;
        }

        //load the file if there is save data (reference: https://www.baeldung.com/java-serialization)
        //otherwise, create a new History object.
        Storage storage;
        File historyFile = new File(String.valueOf(historyPath));
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(historyFile));
            storage = (Storage) inputStream.readObject();
        } catch (IOException e) {
            storage = new Storage();
        } catch (ClassNotFoundException e) {
            //should (by right) never happen!
            System.out.println("Class not found");
            return null;
        }

        //Storage should either have been created or loaded
        assert storage != null;

        return storage;
    }

    /**
     * Updates the history with the new given tasks.
     *
     * @param tasks The new arrayList of tasks.
     */
    public void updateHistory(ArrayList<Task> tasks) {
        this.history = tasks;
    }

    public ArrayList<Task> getHistory() {
        return history;
    }
}
