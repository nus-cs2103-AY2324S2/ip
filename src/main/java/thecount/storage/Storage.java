package thecount.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import thecount.exception.TheCountException;
import thecount.task.Deadline;
import thecount.task.Event;
import thecount.task.TaskList;
import thecount.task.ToDo;

/**
 * Manages the loading and writing of tasks to a file.
 */
public class Storage {
    private static final String DATAFILE_PATH = "./data/the-count.txt";
    private static final String DIR_PATH = "./data";

    /**
     * Constructs a Storage object.
     * Load tasks from the data file.
     *
     * @param tasks The task list to load tasks into.
     */
    public Storage(TaskList tasks) {
        load(tasks);
    }

    /**
     * Loads tasks from the data file into the task list.
     * Creates the data directory and file if they don't exist.
     *
     * @param tasks The task list to load tasks into.
     */
    public void load(TaskList tasks) {
        createDir();
        createFile(tasks);
    }

    private void createDir() {
        try {
            File directory = new File(DIR_PATH);
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully.");
            } else {
                System.out.println("Directory exists.");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void createFile(TaskList tasks) {
        // File creation
        try {
            File file = new File(DATAFILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created.");
            } else {
                System.out.println("File already exists.");
            }
            readFile(file, tasks);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Writes tasks from the task list to the data file.
     *
     * @param tasks The task list to load tasks into.
     */
    public void write(TaskList tasks) {
        assert tasks != null : "TaskList (tasks) must not be null";
        try {
            FileWriter fw = new FileWriter(DATAFILE_PATH);
            fw.write(tasks.getListToWrite());
            fw.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Reads tasks from the data file to the task list.
     *
     * @param file The data file to read from.
     * @param tasks The task list containing tasks to be written to.
     */
    private void readFile(File file, TaskList tasks) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            processLine(line, tasks);
        }
    }

    private void handleTaskDone(TaskList tasks, String isTaskDone, char index) {
        if (isTaskDone.equals("1")) {
            try {
                tasks.markTask(index - '0', false);
            } catch (TheCountException e) {
                System.err.println(e);
            }
        }
    }

    private void processLine(String line, TaskList tasks) {
        String[] parts = line.split("\\|");
        char index = parts[0].charAt(0);
        String taskType = parts[1].trim();
        String isTaskDone = parts[2].trim();
        String info = parts[3].trim();

        switch (taskType) {
        case "T":
            tasks.add(new ToDo(info));
            break;
        case "D":
            String deadlineTime = parts[4].trim();
            try {
                tasks.add(new Deadline(info, deadlineTime));
            } catch (DateTimeParseException e) {
                System.out.println(e);
            }
            break;
        case "E":
            String[] time = parts[4].trim().split("-");
            String fromTime = time[0];
            String toTime = time[1];
            tasks.add(new Event(info, fromTime, toTime));
            break;
        default:
            break;
        }
        handleTaskDone(tasks, isTaskDone, index);
    }
}
