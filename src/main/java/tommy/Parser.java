package tommy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import tommy.command.AddDeadlineCommand;
import tommy.command.AddEventCommand;
import tommy.command.AddTodoCommand;
import tommy.command.ByeCommand;
import tommy.command.Command;
import tommy.command.DeleteCommand;
import tommy.command.FindCommand;
import tommy.command.ListCommand;
import tommy.command.MarkCommand;
import tommy.command.UnmarkCommand;
import tommy.exception.InvalidArgumentException;
import tommy.exception.InvalidCommandException;

/**
 * Represents the class that deals with all the formatting and making sense of the String inputs.
 */
public class Parser {
    private enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE, FIND
    }

    /**
     * Returns the respective command to be executed after deconstructing the user input.
     * Possible commands are : BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE, FIND.
     *
     * @param userInput String of user input to be deconstructed and processed.
     * @return Command to be executed.
     *
     * @throws InvalidCommandException If the input command is empty or does not match any of the above commands.
     * @throws InvalidArgumentException If the input command has an empty description or invalid format.
     */
    public static Command parseCommand(String userInput) throws InvalidCommandException, InvalidArgumentException {

        Command command;

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

            case FIND:
                description = components[1];
                command = new FindCommand(description);
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

    /**
     * Returns the reformatted date if input date matches the specific date formats.
     * <p>
     * If the input date does not match any of the formats, the input date String will be
     * returned as it is.
     *
     * @param date Unformatted date String.
     * @return Reformatted date if input matches any of the specific formats.
     */
    public static String formatDate(String date) {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LocalDate parsedDateTime = null;

        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDateTime = LocalDate.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Does not match the pattern: " + e.getMessage());
            }
        }
        if (parsedDateTime == null) {
            return date;
        }
        return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
