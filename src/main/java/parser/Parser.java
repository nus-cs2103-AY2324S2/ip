package parser;

import command.Command;
import command.ByeCommand;
import command.ListCommand;
import command.UnmarkCommand;
import command.TodoCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.DeleteCommand;
import command.UnknownCommand;
import data.Deadline;
import data.Event;
import data.Todo;
import data.exception.CoDriverException;

import java.time.LocalDate;

public class Parser {
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
            return new UnmarkCommand(index);
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
            int i;
            for (i = 1; i < arguments.length; i++) {
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

//        StringBuilder dateBuilder = new StringBuilder();
            i++;
            LocalDate date = null;
            for (; i < arguments.length; i++) {
                // check if in yyyy-mm-dd format
                date = Parser.parseDate(arguments[i]);
                if (date == null) {
                    throw new CoDriverException("Error! The date provided must be in yyyy-mm-dd format!");
                }
//            dateBuilder.append(arguments[i]).append(" ");
            }
            if (date == null) {
                throw new CoDriverException("Error! You must provide a /by date for a deadline!");
            }
            return new DeadlineCommand(descriptionBuilder.toString(), date);
        }
        case "event": {if (arguments.length < 2) {
            throw new CoDriverException("Error! You cannot provide an event with no parameters!");
        }

            StringBuilder descriptionBuilder = new StringBuilder();
            int i;
            for (i = 1; i < arguments.length; i++) {
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
                throw new CoDriverException("Error! You must provide a /from date/time for an event!");
            }

//        StringBuilder fromBuilder = new StringBuilder();
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
//            fromBuilder.append(arguments[i]).append(" ");
            }
            if (fromDate == null) { // if the /from field is empty
                throw new CoDriverException("Error! The /from field is empty!");
            }
//        fromBuilder.deleteCharAt(fromBuilder.length() - 1);

            if (i >= arguments.length - 1) { // if the last word is /to or there is no /to
                throw new CoDriverException("Error! You must provide a /to date/time for an event!");
            }

//        StringBuilder toBuilder = new StringBuilder();
            LocalDate toDate = null;
            i++;
            for (; i < arguments.length; i++) {
                // check if in yyyy-mm-dd format
                toDate = Parser.parseDate(arguments[i]);
                if (toDate == null) {
                    throw new CoDriverException("Error! The date provided must be in yyyy-mm-dd format!");
                }
//            toBuilder.append(arguments[i]).append(" ");
            }
            if (toDate == null) { // if the /to field is empty
                throw new CoDriverException("Error! The /to field is empty!");
            }
//        toBuilder.deleteCharAt(toBuilder.length() - 1);
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
        default:
            return new UnknownCommand(arguments[0]);
        }
    }

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