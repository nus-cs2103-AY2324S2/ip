package dino;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import dino.command.Command;
import dino.command.DeleteCommand;
import dino.command.ExitCommand;
import dino.command.FilterCommand;
import dino.command.FindCommand;
import dino.command.ListCommand;
import dino.command.MarkCommand;
import dino.command.TaskCommand;
import dino.command.UnmarkCommand;
import dino.task.Deadline;
import dino.task.Event;
import dino.task.Task;
import dino.task.ToDo;

/**
 * Handles the parsing of user commands and creating tasks based on the input.
 */
public class Parser {

    private TaskList tasks;

    /**
     * Constructs a new Parser instance with the specified TaskList, Ui, and Scanner.
     *
     * @param tasks The TaskList to be operated on.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the given command and performs the corresponding action.
     *
     * @param input The user command to be parsed.
     * @return String representation of command.
     */
    public static Command parseCommand(String input) throws DinoException {
        assert input != null : "Input cannot be null";

        String[] parts = input.trim().split(" "); // Split into command and argument
        String command = parts[0];
        String argument = parts.length > 1
                ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
                : "";

        switch (command) {
        case "list":
            return new ListCommand();

        case "bye":
            return new ExitCommand();

        case "delete":
            int taskToDelete = Integer.parseInt(argument);
            return new DeleteCommand(taskToDelete);

        case "todo":
            return new TaskCommand(Dino.TaskType.TODO, argument);

        case "deadline":
            return new TaskCommand(Dino.TaskType.DEADLINE, argument);

        case "event":
            return new TaskCommand(Dino.TaskType.EVENT, argument);

        case "filter":
            return new FilterCommand(argument.trim());

        case "mark":
            int taskToMark = Integer.parseInt(argument);
            return new MarkCommand(taskToMark);

        case "unmark":
            int taskToUnmark = Integer.parseInt(argument);
            return new UnmarkCommand(taskToUnmark);

        case "find":
            return new FindCommand(argument);

        default:
            throw new DinoException("I don't understand ;;");
        }
    }

    /**
     * Creates a Task object based on the provided task type and task details.
     *
     * @param taskType    The type of the task (ToDo, Deadline, or Event).
     * @param taskDetails The details of the task.
     * @return A Task object representing the created task.
     * @throws DinoException If there is an error creating the task.
     */
    public static Task createTaskFromInput(Dino.TaskType taskType, String taskDetails) throws DinoException {
        switch (taskType) {
        case TODO:
            return new ToDo(taskDetails);

        case DEADLINE:
            String[] deadlineParts = taskDetails.split("/by");
            if (deadlineParts.length != 2) {
                throw new DinoException("Invalid input format for deadline. "
                        + "Please use: deadline <deadline name> /by dd-mm-yyyy");
            }
            String deadlineName = deadlineParts[0].trim();
            String deadlineTimeString = deadlineParts[1].trim();
            if (deadlineName.isEmpty() || deadlineTimeString.isEmpty()) {
                throw new DinoException("Deadline name and time cannot be empty.");
            }
            return new Deadline(deadlineName, parseStringToTime(deadlineTimeString));

        case EVENT:
            String[] eventParts = taskDetails.split("/from|/to");
            if (eventParts.length != 3) {
                throw new DinoException("Invalid input format for event. "
                        + "Please use: event <event name> /from dd-mm-yyyy /to dd-mm-yyyy");
            }
            String eventName = eventParts[0].trim();
            String startTimeString = eventParts[1].trim();
            String endTimeString = eventParts[2].trim();
            if (eventName.isEmpty() || startTimeString.isEmpty() || endTimeString.isEmpty()) {
                throw new DinoException("Event name, start time, and end time cannot be empty.");
            }
            LocalDateTime startTime = parseStringToTime(startTimeString);
            LocalDateTime endTime = parseStringToTime(endTimeString);
            return new Event(eventName, startTime, endTime);
        default:
            throw new DinoException("Unknown task type: " + taskType);
        }
    }


    /**
     * Parses a string representing date and time into a LocalDateTime object.
     *
     * @param time The string representation of date and time.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime parseStringToTime(String time) {
        assert time != null : "Time cannot be null";
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm", Locale.ENGLISH);

        LocalDateTime deadlineTime;
        if (time.contains(" ")) {
            deadlineTime = LocalDateTime.parse(time, dateTimeFormatter);
        } else {
            deadlineTime = LocalDateTime.of(LocalDate.parse(time, dateOnlyFormatter), LocalTime.MIDNIGHT);
        }
        return deadlineTime;
    }

    /**
     * Parses a string representing date into a formatted string with a specific pattern.
     *
     * @param time The string representation of date.
     * @return A formatted string representing the parsed date.
     */
    public static String parseStringToNum(String time) {
        assert time != null : "Time cannot be null";
        time = time.trim();
        DateTimeFormatter dateOnlyFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);

        LocalDateTime deadlineTime;
        if (time.contains(" ")) {
            deadlineTime = LocalDateTime.parse(time, dateTimeFormatter);
        } else {
            deadlineTime = LocalDateTime.of(LocalDate.parse(time, dateOnlyFormatter), LocalTime.MIDNIGHT);
        }

        DateTimeFormatter resultFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        String formattedDate = deadlineTime.format(resultFormatter);

        if (deadlineTime.toLocalTime() != LocalTime.MIDNIGHT) {
            formattedDate += " " + deadlineTime.toLocalTime().format(DateTimeFormatter.ofPattern("HHmm"));
        }

        return formattedDate;
    }
}
