package damon.parser;

import damon.command.*;
import damon.exceptions.NoDescriptionException;
import damon.exceptions.WrongInputException;

/**
 * Creates a Parser object to analysis user's input
 * and sends the input to corresponding Command object or throw corresponding Exception.
 */
public class Parser {

    /**
     * Returns corresponding command to user's input.
     *
     * @param inputString User's input.
     * @return Corresponding command
     * @throws NoDescriptionException If there is no description of task in user's input.
     * @throws WrongInputException If user's input is not understandable.
     */
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
        if (inputString.startsWith("find ")) {
            return new FindCommand(inputString);
        }
        if (inputString.equals("todo") || inputString.equals("deadline")
                || inputString.equals("event")) {
            throw new NoDescriptionException();
        }
        throw new WrongInputException();
    }
}
