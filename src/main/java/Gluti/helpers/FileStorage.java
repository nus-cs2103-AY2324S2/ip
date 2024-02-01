package Gluti.helpers;

import Gluti.utils.*;

import java.io.*;
import java.util.ArrayList;

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
     * @throws GlutiException IO Exceptions
     */
    public FileStorage() throws GlutiException {
        checkifexist();
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
     * @throws GlutiException IO Errors
     */
    private void checkifexist() throws GlutiException {
        if (!DATA_FILE.exists()) {
            if (!DATA_FOLDER.exists()) {
                DATA_FOLDER.mkdirs();
            }
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                throw new GlutiException("Gluti detects IO Error!");
            }
        }
    }

    /**
     * Saves the TaskList onto a file (Called during termination)
     * @param newTasks saved tasks from program
     * @throws GlutiException IO Errors
     */
    public void saveList(ArrayList<Task> newTasks) throws GlutiException {
        try {
            FileWriter fileWriter = new FileWriter(DATA_FILE);
            StringBuilder tasks = new StringBuilder();
            for (Task newTask : newTasks) {
                tasks.append(newTask).append(System.lineSeparator());
            }
            fileWriter.write(tasks.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new GlutiException("Gluti detects IO Error!");
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
