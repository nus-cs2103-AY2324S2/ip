package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.constants.Constant.DATE_TIME_FORMATTER;
import static duke.constants.Constant.DATE_TIME_FORMATTER_FOR_PRINT;
import static duke.constants.Constant.RELATIVE_PATH;


/**
 * Handles the loading and conversion of task data from a file.
 */
public class Storage {
    private String filePath;

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
        if (directoryExists) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    try (Scanner scanner = new Scanner(line)) {
                        if (scanner.hasNext()) {
                            String taskType = scanner.next();
                            switch (taskType) {
                            case "T":
                                tasks.add(convertToTodo(line));
                                break;
                            case "D":
                                try {
                                    tasks.add(convertToDeadline(line));
                                } catch (DateTimeParseException e) {
                                    System.err.println("OPPS! The format for the inputted deadline is not " +
                                            "accepted here. Please follow this format: 'yyyy-MM-dd HHmm' " +
                                             "when you are creating the task.");
                                }
                                break;
                            case "E":
                                try {
                                    tasks.add(convertToEvent(line));
                                } catch (DateTimeParseException e) {
                                    System.err.println("OPPS! The format for the inputted start and end time is " +
                                            "not accepted here. Please follow this format: 'yyyy-MM-dd HHmm' " +
                                            "when you are creating the task.");
                                }
                                break;
                            }
                        }
                    }
                }
                System.out.println("The file is loaded");
            } catch (IOException e) {
                System.err.println("There is error in loading file.");
            }
        }
        return tasks;
    }

    /**
     * Converts a string representation of a todo task into a ToDo object.
     *
     * @param string The string representation of the todo task.
     * @return The ToDo object converted from the string representation.
     */
    public ToDo convertToTodo(String string) {
        String[] parts = string.split("\\|");
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
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
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String deadlineStr = parts[3].trim();
        LocalDateTime deadline = LocalDateTime.parse(deadlineStr, DATE_TIME_FORMATTER);
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
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String startTimeStr = parts[3].trim();
        String endTimeStr = parts[4].trim();
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DATE_TIME_FORMATTER);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, DATE_TIME_FORMATTER);
        return new Event(description, status, startTime, endTime, DATE_TIME_FORMATTER_FOR_PRINT);
    }
}
