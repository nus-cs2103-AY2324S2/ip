package duke;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ListCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.Command;

import duke.exception.InvalidArgumentException;
import duke.exception.InvalidCommandException;

public class Parser {
    public enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE
    }

    public static Command parseCommand(String userInput) throws InvalidCommandException, InvalidArgumentException {
        Command command = null;

        try {
            String[] components = userInput.split(" ", 2);
            String description;
            CommandType commandType = CommandType.valueOf(components[0].toUpperCase());

            switch (commandType) {
            case LIST:
                command = new ListCommand();
                break;

            case BYE:
                command = new ByeCommand();
                break;

            case DELETE:
                description = components[1];
                command = new DeleteCommand(description);
                break;

            case MARK:
                description = components[1];
                command = new MarkCommand(description);
                break;

            case UNMARK:
                description = components[1];
                command = new UnmarkCommand(description);
                break;

            case TODO:
                description = components[1];
                command = new AddTodoCommand(description);
                break;

            case DEADLINE:
                description = components[1];
                command = new AddDeadlineCommand(description);
                break;

            case EVENT:
                description = components[1];
                command = new AddEventCommand(description);
                break;

            default:
                throw new InvalidCommandException("Invalid Command");
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Invalid Command");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Description Cannot be Empty");
        }
        return command;
    }

    public static String formatDate(String byDate) {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate parsedDateTime = null;

        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDateTime = LocalDate.parse(byDate, formatter);
                break;
            } catch (DateTimeParseException e) {
            }
        }
        if (parsedDateTime == null) {
            return byDate;
        }
        return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
