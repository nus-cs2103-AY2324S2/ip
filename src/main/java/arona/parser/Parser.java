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

        if (command.equals("bye") || command.equals("exit") || command.equals("b")) {
            return CommandType.BYE;
        } else if (command.equals("todo") || command.equals("t") || command.equals("td")) {
            return CommandType.TODO;
        } else if (command.equals("deadline") || command.equals("d") || command.equals("dl")) {
            return CommandType.DEADLINE;
        } else if (command.equals("event") || command.equals("e") || command.equals("ev")) {
            return CommandType.EVENT;
        } else if (command.equals("delete") || command.equals("del")) {
            return CommandType.DELETE;
        } else if (command.equals("list") || command.equals("l") || command.equals("ls") || command.equals("li")) {
            return CommandType.LIST;
        } else if (command.equals("mark") || command.equals("m")) {
            return CommandType.MARK;
        } else if (command.equals("unmark") || command.equals("u") || command.equals("um")) {
            return CommandType.UNMARK;
        } else if (command.equals("find") || command.equals("f")) {
            return CommandType.FIND;
        } else {
            throw new AronaInvalidCommandException("");
        }
    }
}
