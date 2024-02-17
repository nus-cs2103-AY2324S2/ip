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
        String[] sections = taskStringData.trim().split("\\|");

        // Validate input data
        if (sections.length < 3) {
            throw new StorageException();
        }

        String taskType = sections[0].trim().toUpperCase();
        boolean isDone = sections[1].trim().equals("[X]");
        String taskDescription = sections[2].trim();

        switch (taskType) {
            case "T":
                return parseSavedToDoTask(isDone, taskDescription);

            case "D":
                return parseSavedDeadlineTask(isDone, taskDescription, sections[3].trim());

            case "E":
                return parseSavedEventTask(isDone, taskDescription, sections[3].trim());

            default:
                throw new StorageException();
        }
    }

    /**
     * Parses a saved to-do task in text file with the given information.
     * <p>
     * This method creates a new to-do task based on the provided description. If the task
     * is marked as done (i.e., {@code isDone} is {@code true}), it sets the task as done.
     * </p>
     *
     * @param isDone      {@code true} if the task is marked as done, {@code false} otherwise.
     * @param description The description of the to-do task.
     * @return The parsed to-do task.
     */
    private static Task parseSavedToDoTask(boolean isDone, String description) {
        Task task = new ToDoTask(description);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    /**
     * Parses a saved deadline task with the given information.
     * <p>
     * This method extracts the deadline information from the provided {@code deadlineSection},
     * creates a new deadline task with the specified description and deadline, and marks it
     * as done if {@code isDone} is {@code true}.
     * </p>
     * <p>
     * The deadline information is expected to be in the format "(by: deadline)", where
     * "deadline" represents the date and time of the task's deadline.
     * </p>
     *
     * @param isDone           {@code true} if the task is marked as done, {@code false} otherwise.
     * @param description      The description of the deadline task.
     * @param deadlineSection  The section of the saved task containing the deadline information.
     * @return The parsed deadline task.
     * @throws InvalidDateException If the deadline date and time cannot be parsed.
     * @throws StorageException     If the deadline section does not contain valid deadline information.
     */
    private static Task parseSavedDeadlineTask(boolean isDone, String description, String deadlineSection) throws InvalidDateException, StorageException {
        Pattern pattern = Pattern.compile("\\(by: (.*?)\\)");
        Matcher matcher = pattern.matcher(deadlineSection);
        if (matcher.find()) {
            String deadline = matcher.group(1);
            Task task = new DeadlineTask(description, Parser.parseDateTime(deadline));
            if (isDone) {
                task.markDone();
            }
            return task;
        } else {
            throw new StorageException();
        }
    }

    /**
     * Parses a saved event task with the given information.
     * <p>
     * This method extracts the start and end date/time information from the provided {@code eventSection},
     * creates a new event task with the specified description, start date/time, and end date/time,
     * and marks it as done if {@code isDone} is {@code true}.
     * </p>
     * <p>
     * The event information is expected to be in the format "(from: startBy to: endBy)", where
     * "startBy" represents the start date and time of the event, and "endBy" represents the end date
     * and time of the event.
     * </p>
     *
     * @param isDone        {@code true} if the task is marked as done, {@code false} otherwise.
     * @param description   The description of the event task.
     * @param eventSection  The section of the saved task containing the event information.
     * @return The parsed event task.
     * @throws InvalidDateException If the start or end date/time cannot be parsed.
     * @throws StorageException    If the event section does not contain valid event information.
     */
    private static Task parseSavedEventTask(boolean isDone, String description, String eventSection) throws InvalidDateException, StorageException {
        Pattern pattern = Pattern.compile("\\(from: (.*?) to: (.*?)\\)");
        Matcher matcher = pattern.matcher(eventSection);
        if (matcher.find()) {
            String startBy = matcher.group(1);
            String endBy = matcher.group(2);
            Task task = new EventTask(description, Parser.parseDateTime(startBy), Parser.parseDateTime(endBy));
            if (isDone) {
                task.markDone();
            }
            return task;
        } else {
            throw new StorageException();
        }
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
