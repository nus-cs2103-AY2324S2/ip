package parser;

import commands.*;
import excceptions.WeiException;

public class Parser {

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
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new AddCommand(input);
        }
        else {
            throw new WeiException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
