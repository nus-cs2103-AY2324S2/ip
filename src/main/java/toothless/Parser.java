package toothless;

import toothless.commands.ByeCommand;
import toothless.commands.Command;
import toothless.commands.DeadlineCommand;
import toothless.commands.DeleteCommand;
import toothless.commands.EventCommand;
import toothless.commands.FindCommand;
import toothless.commands.ListCommand;
import toothless.commands.MarkCommand;
import toothless.commands.ScheduleCommand;
import toothless.commands.TodoCommand;
import toothless.commands.UnmarkCommand;
import toothless.tasks.Deadline;
import toothless.tasks.Event;
import toothless.tasks.Task;
import toothless.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is responsible for parsing user input and converting it into a corresponding Command object.
 * It also provides methods for parsing task data and date-time data from string representations.
 */
public class Parser {

    /**
     * Parses a string representing a command and returns the corresponding Command object.
     * @param input the string representing a command.
     * @return the Command object corresponding to the string.
     * @throws ToothlessException if the string cannot be parsed correctly.
     */
    public static Command parseCommand(String input) throws ToothlessException {
        String[] split = input.trim().split(" ", 2);
        String commandType = split[0].toUpperCase();
        switch (commandType) {
        case "BYE":
            return new ByeCommand();
        case "LIST":
            return new ListCommand();
        case "FIND":
            if (split.length < 2) {
                throw new ToothlessException("Find command needs a keyword! >_<\n" +
                        "Please enter in this format: find <keyword>");
            }
            return new FindCommand(split[1].trim());
        case "MARK":
        case "UNMARK":
        case "DELETE":
            return parseUpdateCommand(split);
        case "TODO":
        case "EVENT":
        case "DEADLINE":
            return parseTaskCommand(split);
        case "SCHEDULE":
            return new ScheduleCommand();
        default:
            assert false : commandType;
            throw new ToothlessException("Sorry, Toothless no understand this action :P");
        }
    }

    private static Command parseTaskCommand(String[] split) throws ToothlessException{
        if (split.length < 2) {
            throw new ToothlessException("Task has no name! >_<\n" +
                    "Please enter in this format: <task_type> <task_description> [/<date>]");
        }
        String taskType = split[0].toUpperCase();
        String taskDetails = split[1].trim();
        switch (taskType) {
        case "TODO":
            return new TodoCommand(taskDetails);
        case "EVENT":
            return new EventCommand(taskDetails);
        case "DEADLINE":
            return new DeadlineCommand(taskDetails);
        default:
            assert false : taskType;
            throw new ToothlessException("Invalid Task Command O_O");
        }
    }

    private static Command parseUpdateCommand(String[] split) throws ToothlessException {
        if (split.length < 2) {
            throw new ToothlessException("Update command has no task number! >_<\n" +
                    "Please enter in this format: <update type> <task number>");
        }
        String updateType = split[0].toUpperCase();
        String taskDetails = split[1].trim();
        switch (updateType) {
        case "MARK":
            return new MarkCommand(taskDetails);
        case "UNMARK":
            return new UnmarkCommand(taskDetails);
        case "DELETE":
            return new DeleteCommand(taskDetails);
        default:
            assert false : updateType;
            throw new ToothlessException("Invalid Update Command O_O");
        }
    }

    /**
     * Parses a string representing a task and returns the corresponding Task object.
     * @param storedTask the string representing a task.
     * @return the Task object corresponding to the string.
     * @throws ToothlessException if the string cannot be parsed correctly.
     */
    public static Task parseTask(String[] storedTask) throws ToothlessException {
        try {
            String taskType = storedTask[0];
            String description = storedTask[2];
            boolean isDone = storedTask[1].equals("1");
            switch (taskType) {
                case "T":
                    return new Todo(description, isDone);
                case "D":
                    String date = storedTask[3];
                    return new Deadline(description, date, isDone);
                case "E":
                    String startDate = storedTask[3];
                    String endDate = storedTask[4];
                    return new Event(description, startDate, endDate, isDone);
                default:
                    throw new ToothlessException("File corrupted O_O.\n" +
                            "Please delete the toothless.txt file located " +
                            "in the data folder and restart the program.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ToothlessException("File corrupted O_O.\n" +
                    "Please delete the toothless.txt file located in the data folder and restart the program.");
        }
    }

    /**
     * Parses a string representing a date-time and returns the corresponding LocalDateTime object.
     * @param dateTime the string representing a date-time.
     * @return the LocalDateTime object corresponding to the string.
     * @throws ToothlessException if the string cannot be parsed correctly.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ToothlessException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new ToothlessException("Human date not correct! D:\n" +
                    "Please enter in this format: yyyy-MM-dd HH:mm");
        }
    }
}
