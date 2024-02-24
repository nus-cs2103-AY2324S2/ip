package parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.CalException;
import tasks.Task;

/**
 * responsible for parsing user input strings into executable commands.
 * contains a static method 'parseCommand' which takes a user input string and returns the appropriate Command object.
 */
public class Parser {
    public static void checkNullTaskNum(int inputLen) throws CalException {
        if (inputLen < 2) {
            throw new CalException("Task number not provided!");
        }
    }

    public static void checkBlankField(String field, String fieldName) throws CalException {
        if (field.isBlank()) {
            throw new CalException("Oops! You are missing" + fieldName + ".");
        }
    }

    /**
     * Parses the user input string and returns the corresponding Command object.
     *
     * @param line The user input string to be parsed.
     * @return The Command object corresponding to the user input.
     * @throws CalException if there is an error in parsing the command or if the command is not recognized.
     */
    public static Command parseCommand(String line) throws CalException {
        if (line.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] tokens = line.split(" ");
        String command = tokens[0];
        String description = "";

        switch(command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            checkNullTaskNum(tokens.length);
            return new MarkCommand(Integer.parseInt(tokens[1]));
        case "unmark":
            checkNullTaskNum(tokens.length);
            return new UnmarkCommand(Integer.parseInt(tokens[1]));
        case "todo":
            description = line.substring(4).strip();
            checkBlankField(description, "description");
            return new AddCommand(description);
        case "deadline":
            int byIndex = line.indexOf("/by");
            LocalDateTime dueDate;

            try {
                description = line.substring(8, byIndex).strip();
                checkBlankField(description, "description");
                String by = line.substring(byIndex + 4).strip();
                checkBlankField(by, "due date");
                dueDate = LocalDateTime.parse(by, Task.INPUT_DATE_FORMAT);
            } catch (StringIndexOutOfBoundsException e) {
                throw new CalException("Deadline Task is not in the format: "
                        + "deadline (description) /by (due date)!");
            } catch (DateTimeParseException e) {
                throw new CalException("Date should be written in the format (23/2/2019 1800)");
            }

            return new AddCommand(description, dueDate);
        case "event":
            int fromIndex = line.indexOf("/from");
            int toIndex = line.indexOf("/to");
            LocalDateTime startDate;
            LocalDateTime endDate;

            try {
                description = line.substring(5, fromIndex).strip();
                checkBlankField(description, "description");
                String startDateStr = line.substring(fromIndex + 5, toIndex).strip();
                checkBlankField(startDateStr, "start date");
                startDate = LocalDateTime.parse(startDateStr, Task.INPUT_DATE_FORMAT);
                String endDateStr = line.substring(toIndex + 3).strip();
                checkBlankField(endDateStr, "end date");
                endDate = LocalDateTime.parse(endDateStr, Task.INPUT_DATE_FORMAT);
            } catch (StringIndexOutOfBoundsException e) {
                throw new CalException("Event Task is not in the format: "
                        + "event (description) /from (startDate) /to (endDate)!");
            } catch (DateTimeParseException e) {
                throw new CalException("Date should be written in the format (23/2/2019 1800)");
            }

            return new AddCommand(description, startDate, endDate);
        case "delete":
            checkNullTaskNum(tokens.length);
            return new DeleteCommand(Integer.parseInt(tokens[1]));
        case "find":
            String keyword = line.substring(4).strip();
            checkBlankField(keyword, "search keyword");
            return new FindCommand(keyword);
        default:
            throw new CalException("Command not recognized.");
        }
    }
}
