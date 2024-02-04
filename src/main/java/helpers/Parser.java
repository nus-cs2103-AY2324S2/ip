package helpers;

import command.AddCommand;
import command.CommandType;
import command.Command;
import command.MarkCommand;
import command.UnmarkCommand;
import command.ListCommand;
import command.DeleteCommand;
import command.ExitCommand;

public class Parser {
    public static Command parse(String[] input) throws IllegalArgumentException {
        String commandString = input[0];
        CommandType command = CommandType.valueOf(commandString.toUpperCase());
        switch (command) {
            case LIST:
                return new ListCommand();
            case TODO:
                return new AddCommand(CommandType.TODO, input);
            case DEADLINE:
                return new AddCommand(CommandType.DEADLINE, input);
            case EVENT:
                return new AddCommand(CommandType.EVENT, input);
            case MARK:
                return new MarkCommand(Integer.parseInt(input[1]));
            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(input[1]));
            case DELETE:
                return new DeleteCommand(Integer.parseInt(input[1]));
            default:
                return new ExitCommand();
        }
    }
}
