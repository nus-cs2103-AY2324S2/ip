package lelu;

import commands.*;
import exceptions.InvalidInputException;
import exceptions.LeluException;
import ui.Ui;

/**
 *  Represents a Parser which handles user input.
 */
public class Parser {

    /**
     * Returns a Command object based on the type of command entered by the user
     *
     * @param message User input
     * @param ui UI used
     * @return A Command object
     * @throws LeluException if user input is invalid
     */
    public static Command parse(String message, Ui ui) throws LeluException {
        if (message.equals("bye")) {
            return new ByeCommand();
        } else if (message.equals("list")) {
            return new ListCommand();
        }
        switch (message.split(" ")[0]) {
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "delete":
            return new DeleteCommand();
        case "todo":
            return new AddToDoCommand();
        case "deadline":
            return new AddDeadlineCommand();
        case "event":
            return new AddEventCommand();
        case "find":
            return new FindCommand();
        case "update":
            return new UpdateCommand();
        default:
            throw new InvalidInputException(ui.showInstructions());
        }
    }

}
