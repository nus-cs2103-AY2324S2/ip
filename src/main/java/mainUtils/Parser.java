package mainUtils;

import commands.*;
import exceptions.*;
import tasks.*;
import fileUtils.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Parser {
    public static Command parseUserInput(String[] userInput) throws InvalidTaskException, InvalidDateException, InvalidIndexException, InvalidCommandException, StorageException {
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
        // Split taskStringData into array with sections {taskType, isDone, taskDescription, date etc}
        String[] sectionedString = taskStringData.trim().split("\\|");
        String taskType = sectionedString[0].trim().toUpperCase();
        boolean isDone = sectionedString[1].trim().equals("[X]");
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
