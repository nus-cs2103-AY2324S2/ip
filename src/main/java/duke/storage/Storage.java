package duke.storage;

import static duke.constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;
import static duke.constants.Constant.RELATIVE_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.utils.Utils;

/**
 * Handles the loading and conversion of task data from a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the default file path.
     */
    public Storage() {
        this.filePath = RELATIVE_PATH;
    }

    /**
     * Constructs a new Storage object with the given file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task data from the file and converts it into a list of Task objects.
     *
     * @return A list of Task objects loaded from the file.
     */
    public List<Task> load() {
        Path path = Paths.get(filePath);
        boolean directoryExists = Files.exists(path);
        List<Task> tasks = new ArrayList<>();
        if (!directoryExists) {
            return tasks;
        }
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("There is an error in reading the files");
        }
        for (String line : lines) {
            try (Scanner scanner = new Scanner(line)) {
                if (scanner.hasNext()) {
                    String taskType = scanner.next();
                    addTaskToList(tasks, taskType, line);
                }
            }
        }
        System.out.println("The file is loaded");
        return tasks;
    }

    /**
     * Checks if the status string represents a marked task.
     *
     * @param status The status string to check.
     * @return {@code true} if the status represents a marked task, {@code false} otherwise.
     */
    public boolean isMark(String status) {
        return status.equals("1");
    }
    /**
     * Converts a string representation of a todo task into a ToDo object.
     *
     * @param string The string representation of the todo task.
     * @return The ToDo object converted from the string representation.
     */
    public ToDo convertToTodo(String string) {
        String[] parts = string.split("\\|");
        boolean status = isMark(Utils.removeExtraSpaces(parts[1]));
        String description = Utils.removeExtraSpaces(parts[2]);
        return new ToDo(description, status);
    }

    /**
     * Converts a string representation of a deadline task into a Deadline object.
     *
     * @param string The string representation of the deadline task.
     * @return The Deadline object converted from the string representation.
     * @throws DateTimeParseException If the input date and time cannot be parsed.
     */
    public Deadline convertToDeadline(String string) throws DateTimeParseException {
        String[] parts = string.split("\\|");
        boolean status = isMark(Utils.removeExtraSpaces(parts[1]));
        String description = Utils.removeExtraSpaces(parts[2]);
        String deadlineStr = Utils.removeExtraSpaces(parts[3]);
        LocalDateTime deadline = Utils.convertToLocalDateTime(deadlineStr);
        return new Deadline(description, status, deadline, DATE_TIME_FORMATTER_FOR_PRINT);
    }

    /**
     * Converts a string representation of an event task into an Event object.
     *
     * @param string The string representation of the event task.
     * @return The Event object converted from the string representation.
     * @throws DateTimeParseException If the input start and end time cannot be parsed.
     */
    public Event convertToEvent(String string) throws DateTimeParseException {
        String[] parts = string.split("\\|");
        boolean status = isMark(Utils.removeExtraSpaces(parts[1]));
        String description = Utils.removeExtraSpaces(parts[2]);
        String startTimeStr = Utils.removeExtraSpaces(parts[3]);
        String endTimeStr = Utils.removeExtraSpaces(parts[4]);
        LocalDateTime startTime = Utils.convertToLocalDateTime(startTimeStr);
        LocalDateTime endTime = Utils.convertToLocalDateTime(endTimeStr);
        return new Event(description, status, startTime, endTime, DATE_TIME_FORMATTER_FOR_PRINT);
    }

    /**
     * Adds a task to the list based on its type.
     *
     * @param tasks    The list of tasks to add to.
     * @param taskType The type of task.
     * @param line     The string representation of the task.
     */
    public void addTaskToList(List<Task> tasks, String taskType, String line) {
        switch (taskType) {
        case "T":
            tasks.add(convertToTodo(line));
            break;
        case "D":
            try {
                tasks.add(convertToDeadline(line));
            } catch (DateTimeParseException e) {
                System.err.println("OPPS! The format for the inputted deadline is not "
                        + "accepted here. Please follow this format: 'yyyy-MM-dd HHmm' "
                        + "when you are creating the task.");
            }
            break;
        case "E":
            try {
                tasks.add(convertToEvent(line));
            } catch (DateTimeParseException e) {
                System.err.println("OPPS! The format for the inputted start and end time is "
                        + "not accepted here. Please follow this format: 'yyyy-MM-dd HHmm' "
                        + "when you are creating the task.");
            }
            break;
        default:
            System.err.println("Incorrect task type in the file");
        }
    }
}
