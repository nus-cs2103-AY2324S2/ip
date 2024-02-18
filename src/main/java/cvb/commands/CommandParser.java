package cvb.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import cvb.exceptions.ConvoBotException;
import cvb.tasks.Deadline;
import cvb.tasks.Event;
import cvb.tasks.ToDo;
import cvb.utils.DateTime;

/**
 * The {@code CommandParser} class provides utility methods for parsing commands from user input.
 */
public class CommandParser {

    private static int parseIndex(ArrayList<String> inputList) throws ConvoBotException {
        try {
            return Integer.parseInt(inputList.get(1)) - 1;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ConvoBotException("Invalid input. Wrong number format or index.");
        }
    }

    private static void enforceArgumentCount(int a, int b) throws ConvoBotException {
        if (a != b) {
            throw new ConvoBotException("Invalid input. Wrong number of arguments.");
        }
    }

    /**
     * Parses user input and returns the corresponding {@code Command} object.
     *
     * @param userInput the user input to be parsed
     * @return the corresponding {@code Command} object
     * @throws ConvoBotException if the input is invalid or cannot be parsed
     */
    public static Command parse(String userInput) throws ConvoBotException {
        ArrayList<String> inputList = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        if (inputList.isEmpty()) {
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
            i = parseIndex(inputList);
            enforceArgumentCount(inputList.size(), 2);
            command = new Mark(i);
            break;

        case UNMARK:
            i = parseIndex(inputList);
            enforceArgumentCount(inputList.size(), 2);
            command = new Unmark(i);
            break;

        case DELETE:
            i = parseIndex(inputList);
            enforceArgumentCount(inputList.size(), 2);
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

}
