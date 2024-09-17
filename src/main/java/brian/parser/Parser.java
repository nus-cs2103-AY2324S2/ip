package brian.parser;

import brian.command.ByeCommand;
import brian.command.Command;
import brian.command.DeadlineCommand;
import brian.command.DeleteCommand;
import brian.command.EventCommand;
import brian.command.FindCommand;
import brian.command.HelpCommand;
import brian.command.ListCommand;
import brian.command.MarkCommand;
import brian.command.TodoCommand;
import brian.command.UnmarkCommand;
import brian.utils.BrianException;
import brian.utils.Util;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @return the command based on the user input
     * @throws BrianException if the user input does not conform the expected format
     */
    public static Command parse(String input) throws BrianException {
        String[] split = input.split(" ", 2);
        String method = split[0];
        String params = split.length == 1 ? "" : split[1];

        switch (method) {
        case "list": {
            return new ListCommand();
        }
        case "mark": {
            if (params.equals("")) {
                throw new BrianException("The id of a mark cannot be empty.");
            }
            return new MarkCommand(Integer.parseInt(split[1]));
        }
        case "unmark": {
            if (params.equals("")) {
                throw new BrianException("The id of an unmark cannot be empty.");
            }
            return new UnmarkCommand(Integer.parseInt(split[1]));
        }
        case "todo": {
            if (params.equals("")) {
                throw new BrianException("The description of a todo cannot be empty.");
            }
            return new TodoCommand(params);
        }
        case "deadline": {
            if (params.equals("")) {
                throw new BrianException("The description of a deadline cannot be empty.");
            }
            String[] split1 = params.split(" /by ", 2);
            if (split1.length == 1) {
                throw new BrianException("The deadline of a deadline cannot be empty.");
            }
            System.out.println(split1[1].strip());
            return new DeadlineCommand(split1[0], Util.parseDate(split1[1].strip()));
        }
        case "event": {
            if (params.equals("")) {
                throw new BrianException("The description of an event cannot be empty.");
            }
            String[] split1 = params.split(" /from ", 2);
            if (split1.length == 1) {
                throw new BrianException("The from of an event cannot be empty.");
            }
            String[] split2 = split1[1].split(" /to ", 2);
            if (split2.length == 1) {
                throw new BrianException("The to of an event cannot be empty.");
            }
            return new EventCommand(split1[0], Util.parseDate(split2[0]), Util.parseDate(split2[1]));

        }
        case "delete": {
            if (params.equals("")) {
                throw new BrianException("The id of a delete cannot be empty.");
            }
            return new DeleteCommand(Integer.parseInt(params) - 1);
        }

        case "find": {
            return new FindCommand(params.trim());
        }

        case "bye": {
            return new ByeCommand();
        }
        case "help":
            // Fallthrough
        default: {
            return new HelpCommand();
        }
        }
    }
}
