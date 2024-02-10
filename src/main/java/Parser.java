import exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public final class Parser {

//    private static final List<DateTimeFormatter> DATETIMEFORMATTER = new ArrayList<>();
//
//    static {
//        // Add DateTimeFormatters for different formats
//        DATETIMEFORMATTER.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        DATETIMEFORMATTER.add(DateTimeFormatter.ofPattern("HH:mm:ss"));
//        DATETIMEFORMATTER.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
//    }
    public static void parseUserInput(String[] userInput) throws InvalidTaskException, InvalidDateException, InvalidIndexException, InvalidCommandException, StorageException {
        TaskList tasksList = TaskList.getInstance(null);

        String firstWord = userInput[0].toUpperCase();
        int index;
        switch (firstWord) {
            case "TODO":
                tasksList.addTask(parseTodoTask(userInput));
                break;
            case "DEADLINE":
                tasksList.addTask(Parser.parseDeadlineTask(userInput));
                break;
            case "EVENT":
                tasksList.addTask(Parser.parseEventTask(userInput));
                break;
            case "LIST":
                if (userInput.length > 1) {
                    throw new InvalidCommandException();
                }
                tasksList.displayTasks();
                break;
            case "MARK":
                index = Integer.parseInt(userInput[userInput.length - 1]) - 1;
                tasksList.getTask(index).markDone();
                break;
            case "UNMARK":
                index = Integer.parseInt(userInput[userInput.length - 1]) - 1;
                tasksList.getTask(index).markNotDone();
                break;
            case "DELETE":
                index = Integer.parseInt(userInput[userInput.length - 1]) - 1;
                tasksList.deleteTask(index);
                break;
            case "BYE":
                break;
            default:
                throw new InvalidCommandException();
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
            return LocalDate.parse(dateTimeString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }

    }
}
