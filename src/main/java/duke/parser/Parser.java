package duke.parser;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;

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
        String[] splitInput = input.split(" ");
        switch (splitInput[0].toLowerCase()) {
        case "bye": // Exit
            return new ExitCommand();

        case "list":
            // Check if date filter exists
            if (splitInput.length > 1) {
                switch (splitInput[1]) {
                case "/date":
                    try {
                        Instant filterDate = userDateToInstant(splitInput[2], "00:00");

                        return new ListCommand(filterDate);
                    } catch (NumberFormatException | StringIndexOutOfBoundsException
                            | ArrayIndexOutOfBoundsException e) {
                        throw new InvalidArgumentException(
                                "Date/time format is invalid. Please enter the date/time in the format 'YYYY/MM/DD'");
                    }

                default:
                    throw new InvalidArgumentException(
                            String.format("Unknown argument '%s' for the 'list' command", splitInput[1]));
                }
            } else { // Return full list
                return new ListCommand();
            }
        default:
            throw new InvalidCommandException(String.format("Unknown command '%s'", splitInput[0]));
        }
    }
}
