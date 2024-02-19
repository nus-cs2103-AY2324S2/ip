package damon.parser;

import damon.command.*;
import damon.exceptions.NoDescriptionException;
import damon.exceptions.WrongInputException;

public class Parser {
    public static Command parse(String inputString) throws NoDescriptionException
            , WrongInputException {
        if (inputString.equals("bye")) {
            return new ExitCommand(inputString);
        }
        if (inputString.equals("list")) {
            return new ShowListCommand(inputString);
        }
        if (inputString.startsWith("mark ")) {
            return new MarkCommand(inputString);
        }
        if (inputString.startsWith("unmark ")) {
            return new UnMarkCommand(inputString);
        }
        if (inputString.startsWith("delete ")) {
            return new DeleteCommand(inputString);
        }
        if (inputString.startsWith("todo ") || inputString.startsWith("deadline ")
                || inputString.startsWith("event ")) {
            return new AddCommand(inputString);
        }
        if (inputString.equals("todo") || inputString.equals("deadline")
                || inputString.equals("event")) {
            throw new NoDescriptionException();
        }
        throw new WrongInputException();
    }
}
