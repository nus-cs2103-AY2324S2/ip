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

public final class Parser {
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

    private static Task parseSavedToDoTask(boolean isDone, String description) {
        Task task = new ToDoTask(description);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

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

    public static ToDoTask parseTodoTask(String[] details) throws InvalidTaskException {
        String description;
        if (details.length > 0) {
            description = String.join(" ", Arrays.copyOfRange(details, 1, details.length));
        } else {
            throw new InvalidTaskException();
        }

        return new ToDoTask(description);
    }

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

    public static LocalDate parseDateTime(String dateTimeString) throws InvalidDateException {
        try {
            return LocalDate.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
