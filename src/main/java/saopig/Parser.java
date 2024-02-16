package saopig;

import saopig.command.AddCommand;
import saopig.command.Command;
import saopig.command.DeleteCommand;
import saopig.command.ExitCommand;
import saopig.command.FindCommand;
import saopig.command.ListCommand;
import saopig.command.MarkCommand;
import saopig.command.UpdateCommand;

/**
 * Represents a Parser.
 * A Parser object deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the user command and returns the corresponding Command object.
     * The user command is split into two parts: the command word and the rest of the command.
     * The command word is used to determine the type of Command object to return.
     * The rest of the command is used to determine the details of the Command object.
     *
     * @param fullCommand User input command.
     * @return Command object corresponding to the user command.
     * @throws SaopigInvaildSizeException If the user command is invalid.
     */
    public static Command parse(String fullCommand) throws SaopigInvaildSizeException {
        String processedInput = fullCommand.trim().toUpperCase();
        String[] splitInput = processedInput.split(" ");
        switch (splitInput[0]) {
        case "UPDATE":
            return new UpdateCommand(fullCommand, 0);
        case "FIND":
            return new FindCommand(fullCommand, 0);
        case "LIST":
            return new ListCommand(fullCommand, 0);
        case "LISTTASKONDATE":
            return new ListCommand(fullCommand, 1);
        case "DELETE":
            return new DeleteCommand(fullCommand, 0);
        case "UNMARK":
            return new MarkCommand(fullCommand, 1);
        case "MARK":
            return new MarkCommand(fullCommand, 0);
        case "EVENT":
            return new AddCommand(fullCommand, 2);
        case "DEADLINE":
            return new AddCommand(fullCommand, 1);
        case "TODO":
            return new AddCommand(fullCommand, 0);
        case "BYE":
            return new ExitCommand();
        default:
            throw new SaopigInvaildSizeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
