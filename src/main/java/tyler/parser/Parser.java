package tyler.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tyler.command.Command;
import tyler.command.DeadlineCommand;
import tyler.command.DeleteCommand;
import tyler.command.EventCommand;
import tyler.command.ExitCommand;
import tyler.command.FindCommand;
import tyler.command.ListCommand;
import tyler.command.MarkCommand;
import tyler.command.TodoCommand;
import tyler.command.UnmarkCommand;
import tyler.exception.EmptyNameException;
import tyler.exception.InvalidTaskException;
import tyler.exception.TylerException;
import tyler.exception.UndefinedActionException;

/**
 * This parser class is required to parse the line from System.in.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parsing the line from System.in to a Command.
     *
     * @param line            The line needed to be parsed.
     * @return                A specific command from command class.
     * @throws TylerException May have name:null, invalid action, index:null
     */
    public static Command parse(String line) throws TylerException {
        String[] input = line.split(" ");
        String command = input[0];
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark": {
            if (input.length < 2) {
                throw new InvalidTaskException();
            }
            int index = Integer.parseInt(input[1]);
            return new MarkCommand(index);
        }
        case "unmark": {
            if (input.length < 2) {
                throw new InvalidTaskException();
            }
            int index = Integer.parseInt(input[1]);
            return new UnmarkCommand(index);
        }
        case "todo": {
            if (input.length < 2) {
                throw new EmptyNameException();
            }
            String name = line.split(" ", 2)[1];
            return new TodoCommand(name);
        }
        case "deadline": {
            if (input.length < 2) {
                throw new EmptyNameException();
            }
            String[] info = line.split(" ", 2)[1].split("/by ");
            String name = info[0];
            LocalDateTime end = LocalDateTime.parse(info[1], INPUT_DATE_FORMAT);
            return new DeadlineCommand(name, end);
        }
        case "event": {
            if (input.length < 2) {
                throw new EmptyNameException();
            }
            String[] info = line.split(" ", 2)[1].split("/from ");
            String name = info[0];
            String[] startToEnd = info[1].split(" /to ");
            LocalDateTime start = LocalDateTime.parse(startToEnd[0], INPUT_DATE_FORMAT);
            LocalDateTime end = LocalDateTime.parse(startToEnd[1], INPUT_DATE_FORMAT);
            return new EventCommand(name, start, end);
        }
        case "delete": {
            if (input.length < 2) {
                throw new InvalidTaskException();
            }
            int index = Integer.parseInt(input[1]);
            return new DeleteCommand(index);
        }
        case "find": {
            if (input.length < 2) {
                throw new EmptyNameException();
            }
            String name = line.split(" ", 2)[1];
            return new FindCommand(name);
        }
        default:
            throw new UndefinedActionException();
        }
    }
}
