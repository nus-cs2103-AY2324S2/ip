package atsisbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import atsisbot.task.Deadline;
import atsisbot.task.Event;
import atsisbot.task.Task;
import atsisbot.task.TaskList;
import atsisbot.task.Todo;

/**
 * The Storage class handles the saving and loading of the task list to/from a
 * file.
 */
public class Storage {

    private File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param path The path of the file to be handled by the storage.
     */
    public Storage(String path) {
        this.file = new File(path);
    }

    /**
     * Saves the given TaskList to a file.
     * If the file does not exist, it will be created.
     *
     * @param list The TaskList to be saved.
     */
    public void saveList(TaskList list) {
        try {
            createFileIfNotExist();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(list.getSavedList());
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving the atsisbot.task list: " + e.getMessage());
        }
    }

    /**
     * Loads the data from the file and returns a TaskList object.
     *
     * @return The TaskList object containing the loaded data.
     */
    public TaskList loadData() {
        TaskList list = new TaskList();
        try {
            createFileIfNotExist();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                list.addTask(readLine(line));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading the atsisbot.task list: " + e.getMessage());
        }
        return list;
    }

    /**
     * Reads a line from the file and returns a Task object.
     *
     * @param line
     * @return The Task object read from the line.
     */
    private Task readLine(String line) {
        String[] taskInfo = line.split(" \\| ");
        Task task = null;
        switch (taskInfo[0]) {
        case "T":
            task = new Todo(taskInfo[2]);
            break;
        case "D":
            try {
                LocalDateTime deadline = LocalDateTime.parse(taskInfo[3], Task.getDateTimeFormatter());
                task = new Deadline(taskInfo[2], deadline);
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Invalid deadline format in the atsisbot.task list: " + e.getMessage());
            }
            break;
        case "E":
            try {
                LocalDateTime startDateTime = LocalDateTime.parse(taskInfo[3], Task.getDateTimeFormatter());
                LocalDateTime endDateTime = LocalDateTime.parse(taskInfo[4], Task.getDateTimeFormatter());
                task = new Event(taskInfo[2], startDateTime, endDateTime);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid event format in the atsisbot.task list: " + e.getMessage());
            }
            break;
        default:
            break;
        }
        if (taskInfo[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Creates the file if it does not exist.
     */
    private void createFileIfNotExist() {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating the file: " + e.getMessage());
        }
    }
}
