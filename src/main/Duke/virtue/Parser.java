package virtue;

import java.time.format.DateTimeParseException;

public class Parser {

    // Gets the first word of a string.
    private static String getFirstWord(String str) {
        return str.split(" ", 2)[0];
    }

    // Removes the first word of a string.
    private static String removeFirstWord(String str) {
        return str.split(" ", 2)[1];
    }

    // Gets the type of the command, which is its first word.
    public static Command.CommandType getCommandType(String input) throws UnknownCommandTypeException {
        String firstWord = getFirstWord(input);

        for (Command.CommandType type : Command.CommandType.values()) {
            if (firstWord.equals(type.toString())) {
                return type;
            }
        }

        throw new UnknownCommandTypeException();
    }

    // Gets the index input by the user for a mark/unmark/delete command.
    public static int getIndex(String input) throws EmptyIndexException, IndexFormatException {
        String type = getFirstWord(input);
        try {
            return Integer.parseInt(removeFirstWord(input));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyIndexException(type);
        } catch (NumberFormatException e) {
            throw new IndexFormatException(type);
        }
    }

    // Gets the description for a todo/deadline/event command.
    public static String getDescription(String input) throws EmptyDescriptionException {
        String description;
        String firstWordRemoved;
        String type = getFirstWord(input);

        try {
            firstWordRemoved = removeFirstWord(input);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(type);
        }

        switch (type) {
            case "deadline":
                description = firstWordRemoved.split("/by", 2)[0];
                break;
            case "event":
                description = firstWordRemoved.split(" /from ", 2)[0];
                break;
            default: // case TODO
                description = firstWordRemoved;
        }

        return description;
    }

    // Gets the deadline for a deadline command.
    public static VirtueDateTime getBy(String input) throws VirtueDateTimeException {
        String firstWordRemoved = removeFirstWord(input);
        String dateTimeStr = firstWordRemoved.split(" /by ", 2)[1];
        try {
            return new VirtueDateTime(dateTimeStr);
        } catch (DateTimeParseException e) {
            throw new VirtueDateTimeException("by", "deadline");
        }
    }

    // Gets the start for a deadline command.
    public static VirtueDateTime getFrom(String input) throws VirtueDateTimeException {
        String firstWordRemoved = removeFirstWord(input);
        String fromAndTo = firstWordRemoved.split(" /from ", 2)[1];
        String dateTimeStr = fromAndTo.split(" /to ", 2)[0];
        try {
            return new VirtueDateTime(dateTimeStr);
        } catch (DateTimeParseException e) {
            throw new VirtueDateTimeException("from", "event");
        }
    }

    // Gets the end for a deadline command.
    public static VirtueDateTime getTo(String input) throws VirtueDateTimeException {
        String firstWordRemoved = removeFirstWord(input);
        String fromAndTo = firstWordRemoved.split(" /from ", 2)[1];
        String dateTimeStr = fromAndTo.split(" /to ", 2)[1];
        try {
            return new VirtueDateTime(dateTimeStr);
        } catch (DateTimeParseException e) {
            throw new VirtueDateTimeException("to", "event");
        }
    }
}
