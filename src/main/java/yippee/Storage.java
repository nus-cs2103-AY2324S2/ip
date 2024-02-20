package yippee;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import yippee.exceptions.InvalidCommandException;
import yippee.exceptions.YippeeException;
import yippee.exceptions.YippeeFileException;
import yippee.tasks.Deadline;
import yippee.tasks.Event;
import yippee.tasks.Task;
import yippee.tasks.ToDo;

/**
 * Represents the element for retrieval and edit of storage data.
 */
public class Storage {
    private String filePath;

    /**
     * Instantiates Storage instance and assigns filePath.
     * @param filePath String representation of file path for storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads existing data from storage file into a TaskList.
     * @return TaskList instance containing all stored tasks.
     * @throws YippeeException If errors occur setting up storage file or scanner.
     */
    public TaskList load() throws YippeeException {
        TaskList list = new TaskList();
        //load previous tasks
        File directory = new File("yippee/data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(filePath);
        Scanner fileSc = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new YippeeFileException("Error creating the file : " + e.getMessage());
            }
        }
        try {
            fileSc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new YippeeFileException("Storage data file does not exist: " + e.getMessage());
        }
        loadTasks(fileSc, list);
        fileSc.close();

        return list;
    }

    /**
     * Parses the data stored to instantiate Tasks of their respective types.
     * @param scanner Scanner to read tasks stored.
     * @param list TaskList to store instantiated Tasks into.
     * @throws InvalidCommandException If stored data is in invalid format.
     */
    private static void loadTasks(Scanner scanner, TaskList list) throws InvalidCommandException {
        assert scanner != null : "Scanner is missing from loadTasks";
        while (scanner.hasNext()) {
            String currentTask = scanner.nextLine();
            String[] taskDetails = currentTask.split("\\|");
            Task newTask = null;
            switch (taskDetails[0]) {
            case "T":
                newTask = new ToDo(taskDetails[2]);
                break;
            case "D":
                newTask = new Deadline(taskDetails[2], taskDetails[3]);
                break;
            case "E":
                newTask = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                break;
            default:
                newTask = null;
            }
            if (newTask != null) {
                if (taskDetails[1].equals("true")) {
                    newTask.markDone();
                } else {
                    newTask.markNotDone();
                }
                list.addStoredTask(newTask);
            }
        }
    }

    /**
     * Resets saved data to make way for new data.
     */
    public void resetSave() {
        try {
            Files.delete(Paths.get(this.filePath));
        } catch (IOException e) {
            System.err.println("Error deleting last saved file: " + e.getMessage());
        }
    }

    /**
     * Stores data from active TaskList to storage file.
     * @param taskList TaskList of active tasks.
     */
    public void storeData(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        for (Task task : list) {
            try {
                task.writeToData(this.filePath);
            } catch (IOException e) {
                System.err.println("Error writing file to storage: " + e.getMessage());
            }
        }
    }
}
