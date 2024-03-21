package virtue;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/** A parser that parses user inputs to help generate commands. */
public class Parser {
    /**
     * Gets the first word of a string.
     *
     * @param str The string to get the first word from.
     * @return The first word of a string.
     */
    private static String getFirstWord(String str) {
        return str.split(" ", 2)[0];
    }

    /**
     * Removes the first word of a string.
     *
     * @param str The string to remove the first word from.
     * @return The string without the first word.
     */
    private static String removeFirstWord(String str) {
        return str.split(" ", 2)[1];
    }

    /**
     * Gets the type of the command, which is its first word.
     *
     * @param input The string input by the user.
     * @return The type of the command.
     * @throws UnknownCommandTypeException If the first word does not match a command type.
     */
    public static Command.CommandType getCommandType(String input) throws UnknownCommandTypeException {
        String firstWord = getFirstWord(input);

        for (Command.CommandType type : Command.CommandType.values()) {
            if (firstWord.equals(type.toString())) {
                return type;
            }
        }

        throw new UnknownCommandTypeException();
    }

    /**
     * Gets the indices input by the user for a mark/unmark/delete command
     * and sorts them in descending order.
     *
     * @param input The string input by the user.
     * @return The array of indices for a mark/unmark/delete command in descending order.
     * @throws EmptyIndexException If an index does not exist.
     * @throws IndexFormatException If one of the indices is not an integer.
     */
    public static Integer[] getIndices(String input) throws EmptyIndexException, IndexFormatException {
        String type = getFirstWord(input);
        try {
            String firstWordRemoved = removeFirstWord(input);
            if (firstWordRemoved.trim().isEmpty()) {
                throw new EmptyIndexException(type);
            }
            // Solution below adapted from
            // https://stackoverflow.com/questions/4674850/converting-a-sentence-string-to-a-string-array-of-words-in-java
            String[] stringIndices = firstWordRemoved.split("\\s+");
            // Solution below adapted from
            // https://www.baeldung.com/java-convert-string-array-to-int-array#:~:text=parseInt()%20does%20the%20main,%3D%200%3B%20i%20%3C%20stringArray.
            int[] indices = Arrays.stream(stringIndices).mapToInt(s -> Integer.parseInt(s)).toArray();
            // Solution below adapted from https://stackoverflow.com/questions/880581/how-can-i-convert-int-to-integer-in-java
            Integer[] integerIndices = IntStream.of(indices).boxed().toArray(Integer[]::new);
            Arrays.sort(integerIndices, Collections.reverseOrder());
            return integerIndices;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyIndexException(type);
        } catch (NumberFormatException e) {
            throw new IndexFormatException(type);
        }
    }

    /**
     * Gets the description for a todo/deadline/event command.
     *
     * @param input The string input by the user.
     * @return The description for a todo/deadline/event command.
     * @throws EmptyDescriptionException If the description does not exist.
     */
    public static String getDescription(String input) throws EmptyDescriptionException {
        String description;
        String firstWordRemoved;
        String type = getFirstWord(input);

        try {
            firstWordRemoved = removeFirstWord(input);
            if (firstWordRemoved.trim().isEmpty()) {
                throw new EmptyDescriptionException(type);
            }
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

    /**
     * Gets the deadline for a deadline command.
     *
     * @param input The string input by the user.
     * @return The deadline date/time of the deadline command.
     * @throws VirtueDateTimeException If the date/time is invalid or not in the correct format.
     */
    public static VirtueDateTime getBy(String input) throws VirtueDateTimeException {
        String firstWordRemoved = removeFirstWord(input);
        String dateTimeStr = firstWordRemoved.split(" /by ", 2)[1];
        try {
            return new VirtueDateTime(dateTimeStr);
        } catch (DateTimeParseException e) {
            throw new VirtueDateTimeException("by", "deadline");
        }
    }

    /**
     * Gets the starting date/time for an event command.
     *
     * @param input The string input by the user.
     * @return The starting date/time of the event command.
     * @throws VirtueDateTimeException If the date/time is invalid or not in the correct format.
     */
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

    /**
     * Gets the ending date/time for an event command.
     *
     * @param input The string input by the user.
     * @return The ending date/time of the event command.
     * @throws VirtueDateTimeException If the date/time is invalid or not in the correct format.
     */
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
