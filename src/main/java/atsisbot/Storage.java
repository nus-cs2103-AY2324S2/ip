package atsisbot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import atsisbot.task.Deadline;
import atsisbot.task.Event;
import atsisbot.task.Priority;
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
            try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
                lines.forEach(line -> list.addTask(readLine(line)));
            }
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
            task = processTodo(taskInfo);
            break;
        case "D":
            task = processDeadline(taskInfo, task);
            break;
        case "E":
            task = processEvent(taskInfo, task);
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
     * Represents a task.
     */
    private static Task processTodo(String[] taskInfo) {
        if (taskInfo.length != 4) {
            System.out.println("Invalid todo format in the atsisbot.task list");
            return null;
        }
        Task task;
        task = new Todo(taskInfo[2]);
        task.setPriority(Priority.valueOf(taskInfo[3].toUpperCase()));
        return task;
    }

    /**
     * Processes the task information for an Event task.
     *
     * This method takes in an array of task information and a Task object. It attempts to parse the start and
     * end date/time from the task information and create a new Event task with the parsed date/time.
     * If the date/time format is invalid, it catches the DateTimeParseException and prints an error message.
     *
     * @param taskInfo an array of Strings containing the task information
     * @param task a Task object which will be replaced with a new Event task if the date/time parsing is successful
     * @return the processed Task object, which will be an Event task if the date/time parsing is successful,
     *     or the original Task object otherwise
     * @throws DateTimeParseException if the date/time format in the task information is invalid
     */
    private static Task processEvent(String[] taskInfo, Task task) {
        try {
            LocalDateTime startDateTime = LocalDateTime.parse(taskInfo[3], Task.getDateTimeFormatter());
            LocalDateTime endDateTime = LocalDateTime.parse(taskInfo[4], Task.getDateTimeFormatter());
            task = new Event(taskInfo[2], startDateTime, endDateTime);
            task.setPriority(Priority.valueOf(taskInfo[5].toUpperCase()));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid event format in the atsisbot.task list: " + e.getMessage());
        }
        return task;
    }

    /**
     * Processes the task information for a Deadline task.
     *
     * This method takes in an array of task information and a Task object. It attempts to parse the deadline
     * from the task information and create a new Deadline task with the parsed deadline. If the deadline format
     * is invalid, it catches the DateTimeParseException and prints an error message.
     *
     * @param taskInfo an array of Strings containing the task information
     * @param task a Task object which will be replaced with a new Deadline task if the deadline parsing is successful
     * @return the processed Task object, which will be a Deadline task if the deadline parsing is successful,
     *     or the original Task object otherwise
     * @throws DateTimeParseException if the deadline format in the task information is invalid
     */
    private static Task processDeadline(String[] taskInfo, Task task) {
        try {
            LocalDateTime deadline = LocalDateTime.parse(taskInfo[3], Task.getDateTimeFormatter());
            task = new Deadline(taskInfo[2], deadline);
            task.setPriority(Priority.valueOf(taskInfo[4].toUpperCase()));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    "Invalid deadline format in the atsisbot.task list: " + e.getMessage());
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
