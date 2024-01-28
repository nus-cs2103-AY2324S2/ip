package saopig;

import saopig.command.*;

public class Parser {
    public static Command parse(String fullCommand) throws SaopigInvaildSizeException {
        String processedInput = fullCommand.trim().toUpperCase();
        String[] splitInput = processedInput.split(" ");
        switch (splitInput[0]) {
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
