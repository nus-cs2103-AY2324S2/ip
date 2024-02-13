package dude;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import dude.task.Deadline;
import dude.task.Event;
import dude.task.Task;
import dude.task.Todo;

/**
 * Storage class that controls task storing functionalities of Dude.
 */
public class Storage {
    private static final String directoryPath = "./data";
    private String filePath = "./data/dude.txt";

    /**
     * Class constructor.
     */
    public Storage() {
        boolean success = createStorageIfNotExists();
        if (!success) {
            throw new DudeNoStorageException();
        }
    }

    /**
     * Class constructor specifying the list name.
     * @param listName List name which will be used as the path for the storage file.
     */
    public Storage(String listName) {
        this.filePath = String.format("./data/%s.txt", listName);
        boolean success = createStorageIfNotExists();
        if (!success) {
            throw new DudeNoStorageException();
        }
    }

    private boolean createStorageIfNotExists() {
        new File(Storage.directoryPath).mkdirs();
        File storageFile = new File(this.filePath);
        if (!storageFile.exists()) {
            System.out.println("dude.Storage file not found. Creating storage file...");
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred creating the storage file.");
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    /**
     * Creates row in storage file.
     * @param task The task to create a row of.
     * @return Whether creation was successful.
     */
    public boolean createRow(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(task.getStorageString() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing to the storage file.");
            return false;
        }
        return true;
    }

    /**
     * Creates multiple rows in storage file (clears out whatever data was previously there).
     * @param tasks The ArrayList of tasks to create rows of.
     * @return Whether creation was successful.
     */
    public boolean createRows(ArrayList<Task> tasks) {
        boolean success = this.clearStorage();
        if (!success) {
            return false;
        }

        for (int i = 0; i < tasks.size(); i++) {
            success = this.createRow(tasks.get(i));
            if (!success) {
                return false;
            }
        }
        return true;
    }

    /**
     * Clears the storage file.
     * @return Whether clearing was successful.
     */
    public boolean clearStorage() {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred clearing the storage file.");
            return false;
        }
        return true;
    }

    /**
     * Lists the current rows in the storage file.
     * @return The ArrayList of Tasks found in the storage file.
     */
    public ArrayList<Task> listRows() {
        ArrayList<Task> output = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] rowSplit = s.nextLine().split(" \\| ", 2);
                String taskType = rowSplit[0];
                String[] rowData = rowSplit[1].split(" \\| ");
                boolean done = Objects.equals(rowData[0], "1");
                switch (taskType) {
                case "T":
                    output.add(new Todo(rowData[1], done));
                    break;
                case "D":
                    output.add(new Deadline(rowData[1], done, rowData[2]));
                    break;
                case "E":
                    output.add(new Event(rowData[1], done, rowData[2], rowData[3]));
                    break;
                default:
                    assert false : String.format("Invalid task type %s", taskType);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred reading from the storage file.");
            return new ArrayList<>();
        }
        return output;
    }
}
