package Kokbot;

import java.util.Arrays;

/**
 * Represents a Parser
 */
public class Parser {

    /**
     * Constructor for Parser
     */
    public Parser() {

    }

    /**
     * Parses the input string and returns the corresponding Command
     * @param input Input string
     * @return Corresponding Command
     * @throws DukeException If the input string is not a valid command
     */
    public Command parse(String input) throws DukeException {
        assert input != null : "Input string should not be null";
        String[] parts = input.split(" ");
        switch (parts[0]) {
            case "bye":
                return new Command(Kokbot.CommandType.BYE);
            case "list":
                return parseList(input);
            case "mark":
                return parseMarkings(input, Kokbot.CommandType.MARK);
            case "unmark":
                return parseMarkings(input, Kokbot.CommandType.UNMARK);
            case "todo":
                return parseTodo(input);
            case "deadline":
                return parseDeadline(input);
            case "event":
                return parseEvent(input);
            case "delete":
                return parseDelete(input);
            case "find":
                return parseFind(input);
            default:
                throw new DukeException("Unknown command");
        }
    }

    /**
     * Parses the input string and returns the corresponding Command for "list" command
     * @param input Input string
     * @return Corresponding Command
     * @throws DukeException If the input string is not a valid "list" command
     */
    public Command parseList(String input) throws DukeException {
        String[] parts = input.split(" ");

        if (parts.length == 1) {
            return new Command(Kokbot.CommandType.LIST);
        }

        if (parts.length == 2) {
            return new Command(Kokbot.CommandType.LIST, new String[]{parts[1]});
        }

        throw new DukeException("Unknown usage - \"list\" command should not have more than 2 arguments");
    }

    /**
     * Parses the input string and returns the corresponding Command for marking and unmarking tasks
     * @param input Input string
     * @param commandType Type of the command
     * @return Corresponding Command
     * @throws DukeException If the input string is not a valid marking command
     */
    public Command parseMarkings(String input, Kokbot.CommandType commandType) throws DukeException {
        String[] parts = input.split(" ");
        try {
            Integer.parseInt(parts[1]);
            String[] args = new String[]{parts[1]};
            return new Command(commandType, args);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Incorrect usage - please provide the task number to be marked done");
        }
    }

    /**
     * Parses the input string and returns the corresponding Command for "to do" command
     * @param input Input string
     * @return Corresponding Command
     * @throws DukeException If the input string is not a valid "to do" command
     */
    public Command parseTodo(String input) throws DukeException {
        String todoDesc = input.substring(5);
        if (todoDesc.equals("")) {
            throw new DukeException("Incorrect usage - description cannot be empty");
        }
        String[] args = new String[]{todoDesc};
        return new Command(Kokbot.CommandType.TODO, args);
    }

    /**
     * Parses the input string and returns the corresponding Command for "deadline" command
     * @param input Input string
     * @return Corresponding Command
     * @throws DukeException If the input string is not a valid "deadline" command
     */
    public Command parseDeadline(String input) throws DukeException {
        String[] parts = input.split(" ");

        int byIndex;
        for (byIndex = 0; byIndex < parts.length; byIndex++) {
            if (parts[byIndex].equals("/by")) {
                break;
            }
        }

        if (byIndex == parts.length) {
            throw new DukeException("Unknown usage - /by not found in \"deadline\" command.");
        }

        String deadlineDesc = String.join(" ", Arrays.copyOfRange(parts, 1, byIndex));
        String dueDateStr = String.join(" ", Arrays.copyOfRange(parts, byIndex + 1, parts.length));

        if (deadlineDesc.equals("")) {
            throw new DukeException("Unknown usage - description of \"deadline\" should not be empty.");
        }
        if (dueDateStr.equals("")) {
            throw new DukeException("Unknown usage - due date of \"deadline\" should not be empty.");
        }

        return new Command(Kokbot.CommandType.DEADLINE, new String[]{deadlineDesc, dueDateStr});
    }

    /**
     * Parses the input string and returns the corresponding Command for "event" command
     * @param input Input string
     * @return Corresponding Command
     * @throws DukeException If the input string is not a valid "event" command
     */
    public Command parseEvent(String input) throws DukeException {
        String[] parts = input.split(" ");
        int fromIndex = -1;
        int toIndex = -1;

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("/from")) {
                fromIndex = i;
            } else if (parts[i].equals("/to")) {
                toIndex = i;
            }
        }

        if (fromIndex == -1) {
            throw new DukeException("Unknown usage - /from not found in \"event\" command.");
        }
        if (toIndex == -1) {
            throw new DukeException("Unknown usage - /to not found in \"event\" command.");
        }

        String description = String.join(" ", Arrays.copyOfRange(parts, 1, fromIndex));
        String startDateStr = String.join(" ", Arrays.copyOfRange(parts, fromIndex + 1, toIndex));
        String endDateStr = String.join(" ", Arrays.copyOfRange(parts, toIndex + 1, parts.length));

        if (description.equals("")) {
            throw new DukeException("Unknown usage - description of \"event\" should not be empty.");
        }
        if (startDateStr.equals("")) {
            throw new DukeException("Unknown usage - start date of \"event\" should not be empty.");
        }
        if (endDateStr.equals("")) {
            throw new DukeException("Unknown usage - end date of \"event\" should not be empty.");
        }
        return new Command(Kokbot.CommandType.EVENT, new String[]{description, startDateStr, endDateStr});
    }

    /**
     * Parses the input string and returns the corresponding Command for "delete" command
     * @param input Input string
     * @return Corresponding Command
     * @throws DukeException If the input string is not a valid "delete" command
     */
    public Command parseDelete(String input) throws DukeException {
        String[] parts = input.split(" ");
        try {
            if (parts.length == 1) {
                throw new DukeException("Unknown usage - task number should be included in \"delete\" command");
            }
            for (char c : parts[1].toCharArray()) {
                if (!Character.isDigit(c)) {
                    throw new DukeException("Unknown usage - task number given is not a valid number");
                }
            }
            int i = Integer.parseInt(parts[1]) - 1;
            return new Command(Kokbot.CommandType.DELETE, new String[]{parts[1]});
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Unknown usage - task number given is not a valid number");
        }
    }

    /**
     * Parses the input string and returns the corresponding Command for "find" command
     * @param input Input string
     * @return Corresponding Command
     * @throws DukeException If the input string is not a valid "find" command
     */
    public Command parseFind(String input) throws DukeException {
        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new DukeException("Unknown usage - keyword should be included in \"find\" command");
        }
        String keyword = input.substring(5);
        return new Command(Kokbot.CommandType.FIND, new String[]{keyword});
    }
}
