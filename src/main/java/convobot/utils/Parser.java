package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import commands.Add;
import commands.Bye;
import commands.Command;
import commands.CommandType;
import commands.Delete;
import commands.Find;
import commands.List;
import commands.Mark;
import commands.Unmark;
import exceptions.ConvoBotException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * The {@code Parser} class provides utility methods for parsing user input and task-related information.
 * It includes methods for parsing user commands and converting task-related data to and from strings.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding {@code Command} object.
     *
     * @param userInput the user input to be parsed
     * @return the corresponding {@code Command} object
     * @throws ConvoBotException if the input is invalid or cannot be parsed
     */
    public static Command parseUserInput(String userInput) throws ConvoBotException {
        ArrayList<String> inputList = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        if (inputList.size() == 0) {
            throw new ConvoBotException("Invalid input. Input must not be empty.");
        }

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(inputList.get(0).toUpperCase());
        } catch (IllegalArgumentException e) {
            // use java steams to concat all commands into a string
            String strs = Arrays.stream(CommandType.values())
                                .map(Enum::name)
                                .collect(Collectors.joining(", "));
            throw new ConvoBotException("Invalid input. Input must be one of: " + strs + ".");
        }

        Command command = null;
        int i = -1;
        int j = -1;
        int k = -1;
        String description;

        switch (commandType) {
        case BYE:
            command = new Bye();
            break;

        case LIST:
            command = new List();
            break;

        case MARK:
            try {
                i = Integer.parseInt(inputList.get(1)) - 1;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new ConvoBotException("Invalid input. Wrong number format or index.");
            }
            if (inputList.size() != 2) {
                throw new ConvoBotException("Invalid input. Wrong number of arguments.");
            }
            command = new Mark(i);
            break;

        case UNMARK:
            try {
                i = Integer.parseInt(inputList.get(1)) - 1;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new ConvoBotException("Invalid input. Wrong number format or index.");
            }

            if (inputList.size() != 2) {
                throw new ConvoBotException("Invalid input. Wrong number of arguments.");
            }
            command = new Unmark(i);
            break;

        case DELETE:
            try {
                i = Integer.parseInt(inputList.get(1)) - 1;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new ConvoBotException("Invalid input. Wrong number format or index.");
            }
            if (inputList.size() != 2) {
                throw new ConvoBotException("Invalid input. Wrong number of arguments.");
            }
            command = new Delete(i);
            break;

        case TODO:
            if (userInput.length() == 4) {
                throw new ConvoBotException("Invalid input. The description of a todo cannot be empty.");
            }
            command = new Add(new ToDo(userInput.substring(5)));
            break;

        case DEADLINE:
            j = inputList.indexOf("/by");
            if (j == 1) {
                throw new ConvoBotException("Invalid input. The description of a deadline cannot be empty.");
            }
            if (j == -1 || j == inputList.size() - 1) {
                throw new ConvoBotException("Invalid input. Start date missing.");
            }
            description = String.join(" ", inputList.subList(1, j));
            String by = String.join(" ", inputList.subList(j + 1, inputList.size()));
            command = new Add(new Deadline(description, DateTime.stringToDate(by)));
            break;

        case EVENT:
            j = inputList.indexOf("/from");
            k = inputList.indexOf("/to");
            if (j == 1 || k == 1) {
                throw new ConvoBotException("Invalid input. The description of an event cannot be empty.");
            }
            if (j == -1 || j == inputList.size() - 1 || inputList.get(j + 1).equals("/to")) {
                throw new ConvoBotException("Invalid input. Start date missing.");
            }
            if (k == -1 || k == inputList.size() - 1) {
                throw new ConvoBotException("Invalid input. End date missing.");
            }
            description = String.join(" ", inputList.subList(1, j));
            String from = String.join(" ", inputList.subList(j + 1, k));
            String to = String.join(" ", inputList.subList(k + 1, inputList.size()));
            command = new Add(new Event(description, DateTime.stringToDate(from), DateTime.stringToDate(to)));
            break;

        case FIND:
            if (inputList.size() < 2) {
                throw new ConvoBotException("Invalid input. Wrong number of arguments.");
            }
            String query = String.join(" ", inputList.subList(1, inputList.size()));
            command = new Find(query);
            break;

        default:
            // impossible to reach
            assert false;
            break;
        }
        return command;
    }

    /**
     * Parses a line of text and returns the corresponding {@code Task} object.
     *
     * @param line the line of text representing a task
     * @return the corresponding {@code Task} object
     * @throws IllegalArgumentException if the line is in an invalid format
     */
    public static Task parseTaskFromLine(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3 || parts.length > 5) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task;
        try {
            switch (parts[0]) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                task = new Deadline(description, isDone, DateTime.stringToDate(parts[3]));
                break;
            case "E":
                task = new Event(description, isDone, DateTime.stringToDate(parts[3]), DateTime.stringToDate(parts[4]));
                break;
            default:
                throw new IllegalArgumentException("Invalid line format: " + line);
            }
        } catch (ConvoBotException e) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
        return task;
    }
}
