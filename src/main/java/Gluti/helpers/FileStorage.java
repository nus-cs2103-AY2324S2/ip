package Gluti.helpers;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import Gluti.utils.Task;
import Gluti.utils.Todo;
import Gluti.utils.Event;
import Gluti.utils.Deadline;
/**
 * Provides a Storage interface that is shared among the program
 * Loads tasks from file when program is ran
 * and saves the tasks to file when terminated correctly
 */

public class FileStorage {
    protected static final File DATA_FOLDER = new File("./src/main/data");
    protected static final File DATA_FILE = new File("./src/main/data/Gluti.txt");
    protected ArrayList<Task> tasklist = new ArrayList<>();

    /**
     * Initialises a FileStorage instance
     * Check if there already exists a file, load tasks from file if exists
     * Creates a new file if !exists
     */
    public FileStorage() {
        checkIfExist();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = reader.readLine();
            while (line != null) {
                tasklist.add(read(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Done mounting tasks");
    }

    /**
     * Interprets the tasks loaded from file
     * @param line the line read from File
     * @return the Task interpreted from program
     */

    public Task read(String line) {
        assert line != null : "Next line read should not be empty";
        char tasktype = line.charAt(1);
        char completion = line.charAt(4);
        Task nextTask = null;
        String description;
        int seperator;

        switch (tasktype) {
            case 'T':
                description = line.substring(7);
                nextTask = new Todo(description);
                if (completion == 'X') {
                    nextTask.setDone();
                }
                break;

            case 'D':
                seperator = line.indexOf("(by:");
                description = line.substring(7, seperator);
                String by = line.substring(seperator + 4, line.length() - 1);
                nextTask = new Deadline(description, by);
                if (completion == 'X') {
                    nextTask.setDone();
                }
                break;

            case 'E':
                seperator = line.indexOf("(from: ");
                description = line.substring(7, seperator);
                String[] date = line.substring(seperator + 6, line.length() - 1).split("to:");
                nextTask = new Event(description, date[0],date[1]);
                if (completion == 'X') {
                    nextTask.setDone();
                }
                break;

            default:
                nextTask = null;
        }
        return nextTask;
    }

    /**
     * Checks and creates the directory and file if there is no such directory or file
     */
    private void checkIfExist() {
        if (!DATA_FILE.exists()) {
            if (!DATA_FOLDER.exists()) {
                DATA_FOLDER.mkdirs();
            }
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                //Todo catch errors
            }
        }
    }

    /**
     * Saves the TaskList onto a file (Called during termination)
     * @param newTasks saved tasks from program
     */
    public void saveList(ArrayList<Task> newTasks) {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            StringBuilder tasks = new StringBuilder();
            for (Task newTask : newTasks) {
                tasks.append(newTask).append(System.lineSeparator());
            }
            fileWriter.write(tasks.toString());
            fileWriter.close();
        } catch (IOException e) {
            //Todo catch errors
        }
    }

    /**
     * Returns the TaskList
     * @return List of Tasks
     */
    public ArrayList<Task> readList() {
        return tasklist;
    }
}
