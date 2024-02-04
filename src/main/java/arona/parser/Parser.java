package arona.parser;

import arona.command.CommandType;
import arona.exception.AronaInvalidCommandException;

/**
 * The Parser class can identify the command used in the input.
 *
 * @author Maximilliano Utomo
 */
public class Parser {
    /**
     * Detects the type of command in the input.
     * @param input - the line of input given by the user
     * @return the CommandType
     * @throws AronaInvalidCommandException
     */
    public CommandType parseInput(String input) throws AronaInvalidCommandException {
        String[] inputs = input.split(" ", 2);

        String command = inputs[0].toLowerCase();

        if (command.equals("bye")) {
            return CommandType.BYE;
        } else if (command.equals("todo")) {
            return CommandType.TODO;
        } else if (command.equals("deadline")) {
            return CommandType.DEADLINE;
        } else if (command.equals("event")) {
            return CommandType.EVENT;
        } else if (command.equals("delete")) {
            return CommandType.DELETE;
        } else if (command.equals("list")) {
            return CommandType.LIST;
        } else if (command.equals("mark")) {
            return CommandType.MARK;
        } else if (command.equals("unmark")) {
            return CommandType.UNMARK;
        } else {
            throw new AronaInvalidCommandException("");
        }
    }
}
