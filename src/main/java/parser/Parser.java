package parser;

import commands.*;
import exceptions.WeiException;

/**
 * Processes the commands inserted by the user.
 */
public class Parser {
    /**
     * Interprets the user command.
     *
     * @param input User command.
     * @return An object of the command type.
     * @throws WeiException If the command cannot be understood.
     */
    public Command parse(String input) throws WeiException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new AddCommand(input);
        } else if (input.startsWith("remind")) {
            return new RemindCommand(input);
        } else {
            throw new WeiException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
