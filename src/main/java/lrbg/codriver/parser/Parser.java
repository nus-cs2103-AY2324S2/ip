package lrbg.codriver.parser;

import lrbg.codriver.command.Command;
import lrbg.codriver.command.ByeCommand;
import lrbg.codriver.command.DeadlineCommand;
import lrbg.codriver.command.DeleteCommand;
import lrbg.codriver.command.EventCommand;
import lrbg.codriver.command.ListCommand;
import lrbg.codriver.command.MarkCommand;
import lrbg.codriver.command.TodoCommand;
import lrbg.codriver.command.UnknownCommand;
import lrbg.codriver.command.UnmarkCommand;
import lrbg.codriver.command.FindCommand;
import lrbg.codriver.data.exception.CoDriverException;

import java.time.LocalDate;

public class Parser {

    /**
     * Parses the command line and returns the corresponding Command object.
     *
     * @param commandLine The command line to be parsed.
     * @return The corresponding Command object.
     * @throws CoDriverException If the command line is invalid.
     */
    public static Command parse(String commandLine) throws CoDriverException {
        String[] arguments = commandLine.split(" ");
        String commandStart = arguments[0];
        switch (commandStart) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark": {
            if (arguments.length > 2) {
                throw new CoDriverException("Error! You should only provide 1 argument for mark!");
            } else if (arguments.length < 2) {
                throw new CoDriverException("Error! You should provide an integer argument for mark!");
            }
            int index = Integer.parseInt(arguments[1]);
            return new MarkCommand(index);
        }
        case "unmark": {
            if (arguments.length > 2) {
                throw new CoDriverException("Error! You should only provide 1 argument for unmark!");
            } else if (arguments.length < 2) {
                throw new CoDriverException("Error! You should provide an integer argument for unmark!");
            }
            int index = Integer.parseInt(arguments[1]);
            return new UnmarkCommand(index);
        }
        case "todo":
            if (arguments.length < 2) {
                throw new CoDriverException("Error! You cannot provide a todo with no description!");
            }
            return new TodoCommand(commandLine.substring(5));
        case "deadline": {
            if (arguments.length < 2) {
                throw new CoDriverException("Error! You cannot provide a deadline with no parameters!");
            }

            StringBuilder descriptionBuilder = new StringBuilder();
            int i = 1;
            for (; i < arguments.length; i++) {
                if (arguments[i].equals("/by")) {
                    break;
                }
                descriptionBuilder.append(arguments[i]).append(" ");
            }
            if (descriptionBuilder.length() == 0) {
                throw new CoDriverException("Error! You cannot provide a deadline with no description!");
            }

            descriptionBuilder.deleteCharAt(descriptionBuilder.length() - 1); // remove the last space

            if (i >= arguments.length - 1) { // if the last word is /by or there is no /by
                throw new CoDriverException("Error! You must provide a /by date for a deadline!");
            }

            i++;
            LocalDate date = null;
            for (; i < arguments.length; i++) {
                // check if in yyyy-mm-dd format
                date = Parser.parseDate(arguments[i]);
                if (date == null) {
                    throw new CoDriverException("Error! The date provided must be in yyyy-mm-dd format!");
                }
            }
            if (date == null) {
                throw new CoDriverException("Error! You must provide a /by date for a deadline!");
            }

            return new DeadlineCommand(descriptionBuilder.toString(), date);
        }
        case "event": {
            if (arguments.length < 2) {
                throw new CoDriverException("Error! You cannot provide an event with no parameters!");
            }

            StringBuilder descriptionBuilder = new StringBuilder();
            int i = 1;
            for (; i < arguments.length; i++) {
                if (arguments[i].equals("/from")) {
                    break;
                }
                descriptionBuilder.append(arguments[i]).append(" ");
            }
            if (descriptionBuilder.length() == 0) {
                throw new CoDriverException("Error! You cannot provide an event with no description!");
            }

            descriptionBuilder.deleteCharAt(descriptionBuilder.length() - 1); // remove the last space

            if (i >= arguments.length - 1) { // if the last word is /from or there is no /from
                throw new CoDriverException("Error! You must provide a /from date for an event!");
            }

            LocalDate fromDate = null;
            i++;
            for (; i < arguments.length; i++) {
                if (arguments[i].equals("/to")) {
                    break;
                }
                // check if in yyyy-mm-dd format
                fromDate = Parser.parseDate(arguments[i]);
                if (fromDate == null) {
                    throw new CoDriverException("Error! The date provided must be in yyyy-mm-dd format!");
                }
            }
            if (fromDate == null) { // if the /from field is empty
                throw new CoDriverException("Error! The /from field is empty!");
            }

            if (i >= arguments.length - 1) { // if the last word is /to or there is no /to
                throw new CoDriverException("Error! You must provide a /to date for an event!");
            }

            LocalDate toDate = null;
            i++;
            for (; i < arguments.length; i++) {
                // check if in yyyy-mm-dd format
                toDate = Parser.parseDate(arguments[i]);
                if (toDate == null) {
                    throw new CoDriverException("Error! The date provided must be in yyyy-mm-dd format!");
                }
            }
            if (toDate == null) { // if the /to field is empty
                throw new CoDriverException("Error! The /to field is empty!");
            }

            return new EventCommand(descriptionBuilder.toString(), fromDate, toDate);
        }
        case "delete": {
            if (arguments.length > 2) {
                throw new CoDriverException("Error! You should only provide 1 argument for delete!");
            } else if (arguments.length < 2) {
                throw new CoDriverException("Error! You should provide an integer argument for delete!");
            }
            int index = Integer.parseInt(arguments[1]);
            return new DeleteCommand(index);
        }
        case "find": {
            if (arguments.length < 2) {
                throw new CoDriverException("Error! You cannot provide a find with no description!");
            } else if (arguments.length > 2) {
                throw new CoDriverException("Error! You cannot provide a find with more than 1 keyword!");
            }
            return new FindCommand(commandLine.substring(5));
        }
        default:
            return new UnknownCommand(arguments[0]);
        }
    }

    /**
     * Parses the given string into a LocalDate object.
     *
     * @param dateString The string to be parsed.
     * @return The LocalDate object parsed from the given string, or null if the string is not in the correct format.
     */
    public static LocalDate parseDate(String dateString) {
        LocalDate date = null;
        // check if in yyyy-mm-dd format
        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            // do nothing
        }
        return date;
    }
}