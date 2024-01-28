package duke.parser;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.MissingArgumentException;
import duke.storage.Deadline;
import duke.storage.Event;
import duke.storage.Todo;

/**
 * The Parser class handles the making sense of user commands
 *
 * @author Ryan NgWH
 */
public class Parser {
    /**
     * Converts user input date (in format 'YYYY/MM/DD hh:mm') to Instant
     *
     * @param date Date to be converted (in format 'YYYY/MM/DD')
     * @param time Time to be converted (in format 'hh:mm')
     *
     * @return Instant of the specified datetime
     */
    public static Instant userDateToInstant(String date, String time)
            throws NumberFormatException, StringIndexOutOfBoundsException {
        return LocalDateTime.of(
                Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8, 10)),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(3, 5)))
                .toInstant(OffsetDateTime.now().getOffset());
    }

    /**
     * Parse a string and return a command if valid
     *
     * @param input String to be parsed
     * @return A valid command for the application
     */
    public static Command parse(String input) throws DukeException {
        // Split input
        String[] splitInput = input.split(" ");

        String description;
        String[] arguments;

        switch (splitInput[0].toLowerCase()) {
        case "bye": // Exit
            return new ExitCommand();

        case "list": // List tasks
            // Check if date filter exists
            if (splitInput.length > 1) { // List filtered tasks
                switch (splitInput[1]) {
                case "/date": // Filter by date
                    try {
                        Instant filterDate = userDateToInstant(splitInput[2], "00:00");

                        return new ListCommand(filterDate);
                    } catch (NumberFormatException | StringIndexOutOfBoundsException
                            | ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(
                                "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'");
                    }

                default: // Invalid filter
                    throw new InvalidArgumentException(
                            String.format("Unknown argument '%s' for the 'list' command", splitInput[1]));
                }
            } else { // Return full list
                return new ListCommand();
            }

        case "mark": // Mark task
            if (splitInput.length <= 1) {
                throw new MissingArgumentException("Missing argument - Index of task required");
            }

            try {
                return new MarkCommand(Integer.parseInt(splitInput[1]) - 1, true);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Index to mark is not an integer");
            }

        case "unmark": // Unmark task
            if (splitInput.length <= 1) {
                throw new MissingArgumentException("Missing argument - Index of task required");
            }

            try {
                return new MarkCommand(Integer.parseInt(splitInput[1]) - 1, false);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Index to unmark is not an integer");
            }

        case "delete": // Delete task
            if (splitInput.length <= 1) {
                throw new MissingArgumentException("Missing argument - Index of task required");
            }

            try {
                return new DeleteCommand(Integer.parseInt(splitInput[1]) - 1);
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException("Index to unmark is not an integer");
            }

        case "todo":
            if (splitInput.length <= 1) {
                throw new MissingArgumentException("Argument missing - Description of a todo cannot be empty");
            }

            description = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length));

            // Return new add todo command
            return new AddCommand(new Todo(description));

        case "deadline":
            // Get arguments
            arguments = Arrays.copyOfRange(splitInput, 1, splitInput.length);

            // Get index of '/by' argument
            int byIndex = -1;
            for (int i = 0; i < arguments.length; i++) {
                if (arguments[i].equals("/by")) {
                    byIndex = i;
                    break;
                }
            }

            if (byIndex == -1) {
                throw new MissingArgumentException("Argument '/by' missing");
            }

            // Extract task description & due date
            description = String.join(" ", Arrays.copyOfRange(arguments, 0, byIndex));

            try {
                String date = arguments[byIndex + 1];
                String time = arguments[byIndex + 2];

                // Create new add deadline command
                return new AddCommand(new Deadline(description, Parser.userDateToInstant(date, time)));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidArgumentException(
                        "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD hh:mm'");
            }

        case "event":
            // Get arguments
            arguments = Arrays.copyOfRange(splitInput, 1, splitInput.length);

            // Get index of '/from' and '/to' argument
            int fromIndex = -1;
            int toIndex = -1;
            for (int i = 0; i < arguments.length; i++) {
                if (fromIndex != -1 && toIndex != -1) {
                    break;
                }

                if (fromIndex == -1 && arguments[i].equals("/from")) {
                    fromIndex = i;
                }

                if (toIndex == -1 && arguments[i].equals("/to")) {
                    toIndex = i;
                }
            }

            if (fromIndex == -1) {
                throw new MissingArgumentException("Argument '/from' missing");
            } else if (toIndex == -1) {
                throw new MissingArgumentException("Argument '/to' missing");
            }

            // Extract task description
            description = String.join(" ", Arrays.copyOfRange(arguments, 0, fromIndex));

            try {
                // Extract start date
                String fromDate = arguments[fromIndex + 1];
                String fromTime = arguments[fromIndex + 2];

                // Extract end date
                String toDate = arguments[toIndex + 1];
                String toTime = arguments[toIndex + 2];

                // Create new add event command
                return new AddCommand(new Event(description, Parser.userDateToInstant(fromDate, fromTime),
                        Parser.userDateToInstant(toDate, toTime)));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidArgumentException(
                        "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD hh:mm'");
            }

        default:
            throw new InvalidCommandException(String.format("Unknown command '%s'", splitInput[0]));
        }
    }
}
