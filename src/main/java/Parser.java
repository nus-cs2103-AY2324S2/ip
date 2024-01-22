import errors.Errors;
import errors.InvalidBanterUsageError;

public class Parser {
    private static String separatorBetweenCommandAndArgument = " ";
    private static String separatorBetweenDescriptionAndDueDate = "/by ";
    private static String separatorBetweenDescriptionAndStart = "/from ";
    private static String separatorBetweenStartAndEnd = "/to ";

    public static String getCommandType(String input) throws InvalidBanterUsageError {
        try {
            return input.split(separatorBetweenCommandAndArgument)[0].toUpperCase();
        } catch (StringIndexOutOfBoundsException e) {
            throw Errors.InvalidCommandError;
        }
    }

    public static String getTodoDescription(String input) throws InvalidBanterUsageError {
        try {
            return getSubstringAfterPrefix(input, CommandType.TODO + separatorBetweenCommandAndArgument);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.MissingTodoDescriptionError;
        }
    }

    public static String getDeadlineDescription(String input) throws InvalidBanterUsageError {
        if (!input.contains(separatorBetweenDescriptionAndDueDate)) {
            throw Errors.MissingDeadlineDueDateError;
        }
        try {
            return getSubstringBetweenPrefixes(input, CommandType.DEADLINE + separatorBetweenCommandAndArgument,
                    separatorBetweenDescriptionAndDueDate);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.MissingDeadlineDescriptionError;
        }
    }

    public static String getDeadlineDueDate(String input) throws InvalidBanterUsageError {
        try {
            return getSubstringAfterPrefix(input, separatorBetweenDescriptionAndDueDate);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.MissingDeadlineDueDateError;
        }
    }

    public static String getEventDescription(String input) throws InvalidBanterUsageError {
        if (!input.contains(separatorBetweenDescriptionAndStart)) {
            throw Errors.MissingEventStartError;
        }
        try {
            return getSubstringBetweenPrefixes(input, CommandType.EVENT + separatorBetweenCommandAndArgument,
                    separatorBetweenDescriptionAndStart);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.MissingEventDescriptionError;
        }
    }

    public static String getEventStart(String input) throws InvalidBanterUsageError {
        if (!input.contains(separatorBetweenStartAndEnd)) {
            throw Errors.MissingEventEndError;
        }
        try {
            return getSubstringBetweenPrefixes(input, separatorBetweenDescriptionAndStart, separatorBetweenStartAndEnd);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.MissingEventStartError;
        }
    }

    public static String getEventEnd(String input) throws InvalidBanterUsageError {
        try {
            return getSubstringAfterPrefix(input, separatorBetweenStartAndEnd);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.MissingEventEndError;
        }
    }

    public static int getMarkTaskNumber(String input) throws InvalidBanterUsageError {
        try {
            return getIntArgument(input, CommandType.MARK + separatorBetweenCommandAndArgument);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.InvalidMarkTaskNumberError;
        }
    }

    public static int getUnmarkTaskNumber(String input) throws InvalidBanterUsageError {
        try {
            return getIntArgument(input, CommandType.UNMARK + separatorBetweenCommandAndArgument);
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            throw Errors.InvalidUnmarkTaskNumberError;
        }
    }

    public static String getSubstringAfterPrefix(String input, String prefix)
            throws StringIndexOutOfBoundsException, IllegalArgumentException {

            String substring = input.substring(input.indexOf(prefix) + prefix.length());
            if (substring.isEmpty() ){
                throw new IllegalArgumentException();
            }
            return substring;
    }

    public static String getSubstringBetweenPrefixes(String input, String start, String end)
            throws StringIndexOutOfBoundsException, IllegalArgumentException {

            int startIdx = input.indexOf(start);
            int endIdx = input.indexOf(end);
            if (startIdx == -1 || endIdx == -1) {
                throw new IllegalArgumentException();
            }
            String substring = input.substring(input.indexOf(start) + start.length(), input.indexOf(end) - 1);
            if (substring.isEmpty() ){
                throw new IllegalArgumentException();
            }
            return substring;
    }

    public static int getIntArgument(String input, String command)
            throws StringIndexOutOfBoundsException, IllegalArgumentException {

        String prefix = command + separatorBetweenCommandAndArgument;
        return Integer.parseInt(getSubstringAfterPrefix(input, prefix));
    }
}
