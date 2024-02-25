package ben.parser;

import ben.commands.*;
import ben.exceptions.BenException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input to create corresponding Command objects for the Ben task management application.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand The full user input.
     * @return A Command object based on the parsed input.
     * @throws BenException If an error occurs during parsing or if the command is not recognized.
     */
    public static Command parse(String fullCommand) throws BenException {
        String[] tokens = fullCommand.split(" ", 2);
        String command = tokens[0];

        switch (command) {
            case "bye":
                return parseExitCommand();

            case "list":
                return parseListCommand();

            case "mark":
                return parseMarkCommand(tokens);

            case "unmark":
                return parseUnmarkCommand(tokens);

            case "todo":
                return parseTodoCommand(tokens);

            case "deadline":
                return parseDeadlineCommand(tokens);

            case "event":
                return parseEventCommand(tokens);

            case "delete":
                return parseDeleteCommand(tokens);

            case "find":
                return parseFindCommand(tokens);

            default:
                throw new BenException("   OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if the tokens array is empty and throws a BenException with the specified error message.
     *
     * @param tokens       The array of tokens to check.
     * @param errorMessage The error message to include in the exception.
     * @throws BenException If the tokens array is empty.
     */
    private static void checkEmptyField(String[] tokens, String errorMessage) throws BenException {
        if (tokens.length < 2) {
            throw new BenException(errorMessage);
        }
    }

    /**
     * Retrieves the index from the tokens array.
     *
     * @param tokens The array of tokens containing the index.
     * @return The parsed index.
     */
    private static int getIndex(String[] tokens) {
        return Integer.parseInt(tokens[1]) - 1;
    }

    /**
     * Parses the "bye" command and returns an ExitCommand object.
     *
     * @return An ExitCommand object.
     */
    public static ExitCommand parseExitCommand() {
        return new ExitCommand();
    }

    /**
     * Parses the "list" command and returns a ListCommand object.
     *
     * @return A ListCommand object.
     */
    public static ListCommand parseListCommand() {
        return new ListCommand();
    }

    /**
     * Parses the "mark" command and returns a MarkCommand object.
     *
     * @param tokens The array of tokens containing the command and index.
     * @return A MarkCommand object.
     * @throws BenException If the tokens array is empty.
     */
    public static MarkCommand parseMarkCommand(String[] tokens) throws BenException {
        checkEmptyField(tokens, "   Key in a value!");
        int index = getIndex(tokens);
        return new MarkCommand(index);
    }

    /**
     * Parses the "unmark" command and returns an UnmarkCommand object.
     *
     * @param tokens The array of tokens containing the command and index.
     * @return An UnmarkCommand object.
     * @throws BenException If the tokens array is empty.
     */
    public static UnmarkCommand parseUnmarkCommand(String[] tokens) throws BenException {
        checkEmptyField(tokens, "   Key in a value!");
        int index = getIndex(tokens);
        return new UnmarkCommand(index);
    }

    /**
     * Parses the "todo" command and returns a TodoCommand object.
     *
     * @param tokens The array of tokens containing the command and description.
     * @return A TodoCommand object.
     * @throws BenException If the tokens array is empty.
     */
    public static TodoCommand parseTodoCommand(String[] tokens) throws BenException {
        checkEmptyField(tokens, "   OOPS!!! The description of a todo cannot be empty.");

        String description = tokens[1];

        return new TodoCommand(description);
    }

    /**
     * Parses the "deadline" command and returns a DeadlineCommand object.
     *
     * @param tokens The array of tokens containing the command, description, and deadline.
     * @return A DeadlineCommand object.
     * @throws BenException If the tokens array is empty or if the deadline format is invalid.
     */
    public static DeadlineCommand parseDeadlineCommand(String[] tokens) throws BenException {
        checkEmptyField(tokens, "   OOPS!!! The description of a deadline cannot be empty.");

        // delimiting string
        String information = tokens[1];
        String[] descTokens = information.split(" /by ");
        String description = descTokens[0];
        String by = descTokens[1];

        try {
            LocalDate deadline = LocalDate.parse(by);
            return new DeadlineCommand(description, deadline);
        } catch (DateTimeParseException e) {
            throw new BenException("Invalid deadline format");
        }
    }

    /**
     * Parses the "event" command and returns an EventCommand object.
     *
     * @param tokens The array of tokens containing the command, description, and event dates.
     * @return An EventCommand object.
     * @throws BenException If the tokens array is empty or if the event dates format is invalid.
     */
    public static EventCommand parseEventCommand(String[] tokens) throws BenException {
        checkEmptyField(tokens, "   OOPS!!! The description of an event cannot be empty.");

        // delimiting string
        String information = tokens[1];
        String[] descTokens = information.split(" /from ");
        String description = descTokens[0];
        String dates = descTokens[1];
        String[] dateTokens = dates.split(" /to ");
        String startDate = dateTokens[0];
        String endDate = dateTokens[1];

        try {
            LocalDate dateFormattedStartDate = LocalDate.parse(startDate);
            LocalDate dateFormattedEndDate = LocalDate.parse(endDate);
            return new EventCommand(description, dateFormattedStartDate, dateFormattedEndDate);
        } catch (DateTimeParseException e) {
            throw new BenException("Invalid event dates format");
        }
    }

    /**
     * Parses the "delete" command and returns a DeleteCommand object.
     *
     * @param tokens The array of tokens containing the command and index.
     * @return A DeleteCommand object.
     * @throws BenException If the tokens array is empty.
     */
    public static DeleteCommand parseDeleteCommand(String[] tokens) throws BenException {
        checkEmptyField(tokens, "   Key in a value!");
        int index = getIndex(tokens);
        return new DeleteCommand(index);
    }

    /**
     * Parses the "find" command and returns a FindCommand object.
     *
     * @param tokens The array of tokens containing the command and keyword.
     * @return A FindCommand object.
     * @throws BenException If the tokens array is empty.
     */
    public static FindCommand parseFindCommand(String[] tokens) throws BenException {
        checkEmptyField(tokens, "   Key in a value!");
        String keyword = tokens[1];
        return new FindCommand(keyword);
    }
}
