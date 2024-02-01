package duke.helpers;

import duke.command.*;

public class Parser {
    /**
     * Returns Command by doing parse of a String.
     *
     * @param command
     * @return Command.
     */
    public static Command parse(String command) {
        String[] commandArr = command.split(" ",2);

        if (command.equals(CommandType.BYE.toString())) {
            return new ByeCommand();
        } else if (command.equals(CommandType.LISTCOMMANDS.toString())) {
            return new PrintCommand();
        } else if (command.equals(CommandType.LIST.toString())) {
            return new ListCommand();
        } else if (commandArr[0].equals(CommandType.CHECKDATE.toString())){
            return new CheckDateCommand(commandArr);
        } else if (commandArr[0].equals(CommandType.MARK.toString())) {
            return new MarkCommand(commandArr);
        } else if (commandArr[0].equals(CommandType.UNMARK.toString())) {
            return new UnmarkCommand(commandArr);
        } else if (commandArr[0].equals(CommandType.DELETE.toString())) {
            return new DeleteCommand(commandArr);
        } else if (commandArr[0].equals(CommandType.FIND.toString())) {
            return new FindCommand(commandArr);
        } else {
            return new AddCommand(commandArr);
        }
    }
}
