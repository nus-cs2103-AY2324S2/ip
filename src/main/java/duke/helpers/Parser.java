package duke.helpers;

import duke.command.ByeCommand;
import duke.command.CheckDateCommand;
import duke.command.Command;
import duke.command.CommandType;
import duke.command.TaskAddingCommand;
import duke.command.TaskDeletingCommand;
import duke.command.TaskFindingCommand;
import duke.command.TaskListingCommand;
import duke.command.TaskMarkingCommand;
import duke.command.TaskUnmarkingCommand;
import duke.command.TasksPrintingCommand;
import duke.command.UndoCommand;

/**
 * Parser class
 */
public class Parser {
    /**
     * Returns Command by doing parse of a String.
     *
     * @param command
     * @return Command.
     */
    public static Command parse(String command) {
        String[] commandArr = command.split(" ", 2);

        if (command.equals(CommandType.BYE.toString())) {
            return new ByeCommand();
        } else if (command.equals(CommandType.LISTCOMMANDS.toString())) {
            return new TasksPrintingCommand();
        } else if (command.equals(CommandType.LIST.toString())) {
            return new TaskListingCommand();
        } else if (command.equals(CommandType.UNDO.toString())) {
            return new UndoCommand();
        } else if (commandArr[0].equals(CommandType.CHECKDATE.toString())) {
            return new CheckDateCommand(commandArr);
        } else if (commandArr[0].equals(CommandType.MARK.toString())) {
            return new TaskMarkingCommand(commandArr);
        } else if (commandArr[0].equals(CommandType.UNMARK.toString())) {
            return new TaskUnmarkingCommand(commandArr);
        } else if (commandArr[0].equals(CommandType.DELETE.toString())) {
            return new TaskDeletingCommand(commandArr);
        } else if (commandArr[0].equals(CommandType.FIND.toString())) {
            return new TaskFindingCommand(commandArr);
        } else {
            return new TaskAddingCommand(commandArr);
        }
    }
}
