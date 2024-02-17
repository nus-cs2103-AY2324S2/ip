package duke.mainUtils;

import duke.commands.*;
import duke.exceptions.*;
import duke.tasks.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class is responsible for parsing user input strings and task data strings into appropriate objects.
 * It provides methods to parse user commands, task data from save files, and task details into task objects.
 * Additionally, it handles date and time parsing for deadline and event tasks.
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.commands.Command
 * @see duke.exceptions.InvalidCommandException
 * @see duke.exceptions.InvalidDateException
 * @see duke.exceptions.InvalidTaskException
 * @see duke.exceptions.StorageException
 * @see duke.tasks.Task
 * @see duke.tasks.ToDoTask
 * @see duke.tasks.DeadlineTask
 * @see duke.tasks.EventTask
 */
public final class Parser {

    /**
     * Parses user input into a Command object based on the command type.
     *
     * @param userInput the user input string split into an array of strings.
     * @return a Command object corresponding to the user input command.
     * @throws InvalidCommandException if the user input command is invalid.
     */
    public static Command parseUserInput(String[] userInput) throws InvalidCommandException {
        String commandType = userInput[0].toUpperCase();
        Command command;

        switch (commandType) {
            case "TODO":
                command = new CreateTodoTask();
                break;

            case "DEADLINE":
                command = new CreateDeadlineTask();
                break;

            case "EVENT":
                command = new CreateEventTask();
                break;

            case "LIST":
                command = new ListTasks();
                break;

            case "MARK":
                command = new MarkTaskDone();
                break;

            case "UNMARK":
                command = new MarkTaskNotDone();
                break;

            case "DELETE":
                command = new DeleteTask();
                break;

            case "HELP":
                command = new Help();
                break;
            case "BYE":
                command = new ExitProgram();
                break;

            default:
                throw new InvalidCommandException();
        }
        return command;
    }

    /**
     * Parses task data from a save file into a Task object.
     *
     * @param taskStringData the task data string read from the save file.
     * @return a Task object parsed from the task data string.
     * @throws StorageException if there is an error parsing the task data.
     * @throws InvalidDateException if the task data contains an invalid date.
     */
    public static Task parseSaveFile(String taskStringData) throws StorageException, InvalidDateException {
        // Split taskStringData into array with sections {taskType, isDone, taskDescription, date etc.}
        String[] sectionedString = taskStringData.trim().split("\\|");
        String taskType;
        boolean isDone;
        try {
            taskType = sectionedString[0].trim().toUpperCase();
            isDone = sectionedString[1].trim().equals("[X]");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new StorageException();
        }
        Task task;
        Pattern pattern;
        Matcher matcher;

        switch (taskType) {
            case ("T"):
                task = new ToDoTask(sectionedString[2].trim());
                if (isDone) {
                    task.markDone();
                }
                break;

            case ("D"):
                pattern = Pattern.compile("\\(by: (.*?)\\)");
                matcher = pattern.matcher(sectionedString[3].trim());
                if (matcher.find()) {
                    String deadline = matcher.group(1);
                    task = new DeadlineTask(sectionedString[2].trim(), Parser.parseDateTime(deadline));
                    if (isDone) {
                        task.markDone();
                    }
                } else {
                    throw new StorageException();
                }
                break;

            case ("E"):
                pattern = Pattern.compile("\\(from: (.*?) to: (.*?)\\)");
                matcher = pattern.matcher(sectionedString[3].trim());
                if (matcher.find()) {
                    String startBy = matcher.group(1);
                    String endBy = matcher.group(2);
                    task = new EventTask(sectionedString[2].trim(), Parser.parseDateTime(startBy), Parser.parseDateTime(endBy));
                    if (isDone) {
                        task.markDone();
                    }
                } else {
                    throw new StorageException();
                }
                break;

            default:
                throw new StorageException();
        }
        return task;
    }

    /**
     * Parses user input details into a ToDoTask object.
     *
     * @param details an array of strings containing task details.
     * @return a ToDoTask object parsed from the task details.
     * @throws InvalidTaskException if the task details are invalid.
     */
    public static ToDoTask parseTodoTask(String[] details) throws InvalidTaskException {
        String description;
        if (details.length > 0) {
            description = String.join(" ", Arrays.copyOfRange(details, 1, details.length));
        } else {
            throw new InvalidTaskException();
        }

        return new ToDoTask(description);
    }

    /**
     * Parses user input details into a DeadlineTask object.
     *
     * @param details an array of strings containing task details.
     * @return a DeadlineTask object parsed from the task details.
     * @throws InvalidTaskException if the task details are invalid.
     * @throws InvalidDateException if the deadline date is invalid.
     */
    public static DeadlineTask parseDeadlineTask(String[] details) throws InvalidTaskException, InvalidDateException {
        int byIndex = -1;
        for (int i = 0; i < details.length; i++) {
            if ("/by".equals(details[i])) {
                byIndex = i;
                break;
            }
        }

        if (byIndex == -1) {
            throw new InvalidTaskException();
        }

        String description;
        LocalDate deadline;
        if (byIndex + 1 < details.length) {
            description = String.join(" ", Arrays.copyOfRange(details, 1, byIndex));
            String deadlineString = String.join(" ", Arrays.copyOfRange(details, byIndex + 1, details.length));
            deadline = parseDateTime(deadlineString);
        } else {
            throw new InvalidTaskException();
        }

        return new DeadlineTask(description, deadline);
    }

    /**
     * Parses user input details into an EventTask object.
     *
     * @param details an array of strings containing task details.
     * @return an EventTask object parsed from the task details.
     * @throws InvalidTaskException if the task details are invalid.
     * @throws InvalidDateException if the event dates are invalid.
     */
    public static EventTask parseEventTask(String[] details) throws InvalidTaskException, InvalidDateException {
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < details.length; i++) {
            if ("/from".equals(details[i])) {
                fromIndex = i;
            }
            if ("/to".equals(details[i])) {
                toIndex = i;
            }
        }

        if (fromIndex == -1 || toIndex == -1) {
            throw new InvalidDateException();
        }

        String description;
        LocalDate startBy;
        LocalDate endBy;
        if (toIndex - fromIndex > 1 && toIndex + 1 < details.length) {
            description = String.join(" ", Arrays.copyOfRange(details, 1, fromIndex));
            startBy = parseDateTime(String.join(" ", Arrays.copyOfRange(details, fromIndex + 1, toIndex)));
            endBy = parseDateTime(String.join(" ", Arrays.copyOfRange(details, toIndex + 1, details.length)));
        } else {
            throw new InvalidTaskException();
        }

        return new EventTask(description, startBy, endBy);
    }

    /**
     * Parses a date-time string into a LocalDate object.
     *
     * @param dateTimeString the date-time string to parse.
     * @return a LocalDate object parsed from the date-time string.
     * @throws InvalidDateException if the date-time string is invalid.
     */
    public static LocalDate parseDateTime(String dateTimeString) throws InvalidDateException {
        try {
            return LocalDate.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
